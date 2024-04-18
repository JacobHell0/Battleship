package rempelnazarwarrener.battleship;

import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Random;

@ServerEndpoint("/ws")
public class BattleShip {

    //all these private members are static to simulate having one websocket to control the logic

    private static int state;
    private static int currentPlayers;

    //contains the counters to determine if a player has won
    private static int[] winCounters = new int[2];

    //two values to store the ship data
    private static ShipPositions ship1;
    private static ShipPositions ship2;

    //array to store the session data corresponding to P1 and P2
    private static String[] players = new String[2];
    private static Session[] playerSessions = new Session[2];

    public BattleShip() {}
    @OnOpen
    public void open(Session session) throws IOException {
        if(currentPlayers == 0) {
            currentPlayers++;
            players[0] = session.getId();
            playerSessions[0] = session;
        } else if (currentPlayers == 1) {
            currentPlayers++;
            state = 1;
            players[1] = session.getId();
            playerSessions[1] = session;
            //send ready to both players
            sendMessageToClient(playerSessions[0], "Ready", "Ready");
            sendMessageToClient(playerSessions[1], "Ready", "Ready");
        } else {
            session.close();
        }
    }

    @OnMessage
    public void handleMessage(String comm, Session session) throws IOException {

        //the user sent a json formatted string then
        JSONObject jsonmsg = new JSONObject(comm);
        String message = (String) jsonmsg.get("msg");
        String type = (String) jsonmsg.get("type");

        //which state is the server in?
        switch(state) {
            case 0:         //waiting for other player and a msg got sent
                return;
            case 1:         //Players are placing ships
                if(type.equals("ShipData")) {
                    placingShips(session, message);
                }
                break;
            case 2:         //It is Player 1's turn to shoot
                if(type.equals("Shot") && players[0].equals(session.getId())) {
                    handleShot(session, message);
                }
                break;
            case 3:         //It is player 2's turn to shoot
                if(type.equals("Shot") && players[1].equals(session.getId())){
                    handleShot(session, message);
                }
                break;
            default:
                // code block
                System.out.println("error, unknown type");
                sendMessageToClient(session, "error", "sent unknown type");
        }
    }

    public void terminateWebsocket() throws IOException {
        playerSessions[0].close();
        playerSessions[1].close();
    }

    public void handleShot(Session session, String shotData) throws IOException {
        //we don't have to check whose turn it is because the code won't run if it is not the correct players turn
        //determine which player shot the bullet

        boolean whoShot;
        if(players[0].equals(session.getId())) {
            //player 1 shot the shell
            whoShot = true;
        } else {
            //player 2 shot the sell
            whoShot = false;
        }

        //determine if the shot hit a ship
        if(whoShot) {
            //P2 ships are checked, because P1 shot the shell
            if(this.ship2.checkHit(shotData)) {
                sendMessageToClient(session, "ShotResponse", shotData);
                winCounters[0]++;
            } else {
                sendMessageToClient(session, "ShotResponse", "Miss");
            }
        } else {
            //P1 ships are checked
            if(this.ship1.checkHit(shotData)) {
                sendMessageToClient(session, "ShotResponse", shotData);
                winCounters[1]++;
            } else {
                sendMessageToClient(session, "ShotResponse", "Miss");
            }
        }
        if(winCounters[0] == 16) { //player 1 has won the game because 16 ships were sunk
            sendMessageToClient(playerSessions[0], "Win", "P1HasWon");
            sendMessageToClient(playerSessions[1], "Lose", "P2HasLost");
            terminateWebsocket();
        } else if (winCounters[1] == 16) { //player 2 has won the game
            sendMessageToClient(playerSessions[1], "Win", "P2HasWon");
            sendMessageToClient(playerSessions[0], "Lose", "P1HasLost");
            terminateWebsocket();
        }
    }

    public void placingShips(Session session, String shipData) throws IOException {
        //determine if player 1 or 2 sent the data
        if(session.getId().equals(players[0])) {
            //player 1 sent shipdata
            this.ship1 = new ShipPositions(shipData);
        } else if (session.getId().equals(players[1])) {
            this.ship2 = new ShipPositions(shipData);
        }
        //check if we have both data
        if(this.ship1 != null && this.ship2 != null) {
            //send on ready message to both clients
            //randomize who goes first
            Random rand = new Random();
            if (rand.nextBoolean()) {
                //if true, then P1 goes first
                sendMessageToClient(playerSessions[0], "StartGameFirst", "ok");
                sendMessageToClient(playerSessions[1], "StartGameSecond", "ok");
                state = 2;
            } else {
                //false, P2 goes first
                sendMessageToClient(playerSessions[1], "StartGameFirst", "ok");
                sendMessageToClient(playerSessions[0], "StartGameSecond", "ok");
                state = 3;
            }

        }
    }


    private void sendMessageToClient(Session session, String type, String message) throws IOException {

        /* custom types include:
        Ready               | Indicates the server has transistioned to state 1, meaning it's waiting for ship info
        StartGameFirst      | Starts the game and the client that receives this goes first.
        StartGameSecond     | Starts the game and the client that receives this goes second.
        ShotResponse        | Contains information on whether a shot hit or missed the target.
        Win                 | Tells the client they won the game
        Lose                | Tells the client they lost the game
        */

        //send message to client with a type
        session.getBasicRemote().sendText(makeMessageJSON(type, message));

    }

    //helper function to convert the message into a json format
    private String makeMessageJSON(String type, String message) {
        return "{\"type\": \"" + type + "\", \"message\":\"" + message + "\"}";
    }
}

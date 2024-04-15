package rempelnazarwarrener.battleship;

import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint("/ws")
public class BattleShip {

    public BattleShip() {}
    @OnOpen
    public void open(Session session) {
        System.out.println("test");
    }
}

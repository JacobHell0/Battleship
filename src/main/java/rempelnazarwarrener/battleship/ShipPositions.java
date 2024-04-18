package rempelnazarwarrener.battleship;


import org.json.JSONObject;

import java.util.Arrays;
import java.util.Random;

public class ShipPositions {
    //array from [0,5] which contains the x,y positions of each ship, in the format: "x,y,x,y"
    private String[] shipPositions = new String[5];

    public String[] getShipPositions() { //TODO Remove
        return shipPositions;
    }

    public ShipPositions(String shipPos) {
        //initializes the ship positions array
        JSONObject json = new JSONObject(shipPos);
        JSONObject jsonShipPos = json.getJSONObject("ship");

        //store ship positions in arrays
        for(int i = 0; i < 5; i++) {
            this.shipPositions[i] = jsonShipPos.getString(String.valueOf(i));
        }
    }

    public boolean checkHit(String shotData) {
        //this function returns whether a shot hit a ship

        //break up shotData
        String[] splitShots = shotData.split(",");
        int x = Integer.parseInt(splitShots[0]);
        int y = Integer.parseInt(splitShots[1]);

        //iterate and check each ship
        for(int i = 0; i < 5; i++) {
            String[] splitCoords = this.shipPositions[i].split(","); //x0, y0, x1, y1

            int[] coords = new int[4];
            //convert strings to integers
            for(int j = 0; j < 4; j++) {
                coords[j] = Integer.parseInt(splitCoords[j]);
            }

            //get everything in-between these coordinates
            for(int xs = coords[0]; xs <= coords[2]; xs++) { //this will trigger if ship is horizontal
                //check (xs, y0)
                if(xs == x && coords[1] == y) {
                    //hit
                    return true;
                }
            }

            for(int ys = coords[1]; ys <= coords[3]; ys++) { //this will trigger if ship is vertical
                //check (x0, ys)
                if(coords[0] == x && ys == y) {
                    //hit
                    return true;
                }
            }
        }
        return false;

    }

}

package rempelnazarwarrener.battleship;


//this file is not a part of the glassfish server, but it will test the ShipPositions class
public class TestShipPos {

    public static void testCheckHit(int x, int y, boolean expected, ShipPositions ship) {
        boolean ret = ship.checkHit(x + "," + y);
        if(ret == expected) {
            System.out.println("Checking: (" + x + ',' + y + ") -> Passed");
        } else {
            System.out.println("Checking: (" + x + ',' + y + ") expected: " + expected + " | actual: " + ret);
        }
    }

    public static void main(String[] args) {

        String shipData = "{\"ship\": \n" +
                "    {\n" +
                "    \"0\": \"9,0,9,4\",\n" +
                "    \"1\": \"0,0,3,0\",\n" +
                "    \"2\": \"0,9,2,9\",\n" +
                "    \"3\": \"9,7,9,9\",\n" +
                "    \"4\": \"3,4,4,4\"\n" +
                "    }\n" +
                "}";


        System.out.println("------------------Checking Ship Data-------------------------");
        ShipPositions ship1 = new ShipPositions(shipData);
        ShipPositions ship2 = new ShipPositions(shipData);

        int i = 0;
        System.out.println("ship 1 ships: ");
        for(String pos : ship1.getShipPositions()) {
            System.out.println("Ship (" + i + "):  " + pos);
            i++;
        }

        i = 0;
        System.out.println("ship 2 ships: ");
        for(String pos : ship2.getShipPositions()) {
            System.out.println("Ship (" + i + "):  " + pos);
            i++;
        }

        //test check hit
        System.out.println("-----------------------Checking checkHit()--------------------");

        testCheckHit(0, 0, true, ship1);
        testCheckHit(1, 0, true, ship1);
        testCheckHit(2, 0, true, ship1);
        testCheckHit(3, 0, true, ship1);

        testCheckHit(9, 0, true, ship1);
        testCheckHit(9, 1, true, ship1);
        testCheckHit(9, 2, true, ship1);
        testCheckHit(9, 3, true, ship1);
        testCheckHit(9, 4, true, ship1);

        testCheckHit(0, 9, true, ship1);
        testCheckHit(1, 9, true, ship1);
        testCheckHit(2, 9, true, ship1);

        testCheckHit(9, 7, true, ship1);
        testCheckHit(9, 8, true, ship1);
        testCheckHit(9, 9, true, ship1);

        testCheckHit(3,4, true, ship1);
        testCheckHit(4,4, true, ship1);

        //check ALL areas around ships
        testCheckHit(0,1, false, ship1);
        testCheckHit(1,1, false, ship1);
        testCheckHit(2,1, false, ship1);
        testCheckHit(3,1, false, ship1);
        testCheckHit(4,0, false, ship1);
        testCheckHit(4,1, false, ship1);

        testCheckHit(8,0, false, ship1);
        testCheckHit(8,1, false, ship1);
        testCheckHit(8,2, false, ship1);
        testCheckHit(8,3, false, ship1);
        testCheckHit(8,4, false, ship1);
        testCheckHit(8,5, false, ship1);
        testCheckHit(9,5, false, ship1);

    }



}

let ws; //websocket
let isMyTurn;

//------------------------ Begin Game, State 0 ----------------------------------------------------
function playerJoin() {
    //disable button
    document.getElementById("playeronebutton").hidden = true;
    document.getElementById("playertwobutton").hidden = true;

    // create the web socket
    ws = new WebSocket("ws://localhost:8080/Battle-Ship-1.0-SNAPSHOT/ws");

//----------------------------- Main Game Logic ---------------------------------------------------
    ws.onmessage = function (event) {
        let message = JSON.parse(event.data);
        console.log(event.data);
        switch (message.type) {
            case "Ready": //received when server is now waiting for ship data
                togglePopup3();
                break;

            case "StartGameFirst":
                generateTable();
                isMyTurn = 1;
                break;

            case "StartGameSecond":
                isMyTurn = 0;
                generateTable();
                break;

            case "ShotResponse":
                console.log("data:")
                console.log(message)
                changeButtonColour(message.message);
                break;

            case "YourTurn":
                isMyTurn = 1;
                break;

            case "Win":
                togglePopup()
                break;
            case "Lose":
                togglePopup2()
                break;

            default: //just log the message, never called
                console.log("type: " + message.type + ", msg: " + message.message);

        }
    }
}

function changeButtonColour(shotData) {

    console.log("changing colour: " + shotData);

    if(!(shotData === "Miss")) {
        let split = shotData.split(",");
        let x = split[0];
        let y = split[1];
        let id = 'Button[' + x + ',' + y + ']';
        document.getElementById(id).style.background = "red";
        isMyTurn = 1;
    } else {
        isMyTurn = 0;
    }
}

function sendShipData(template) {

    let shipData;
    //template ranges from 0-6, corresponding to the layouts

    switch (template) {
        case 0: //4 corners
            shipData = "{\"ship\": \n" +
                "    {\n" +
                "    \"0\": \"9,0,9,4\",\n" +
                "    \"1\": \"0,0,3,0\",\n" +
                "    \"2\": \"0,9,2,9\",\n" +
                "    \"3\": \"9,7,9,9\",\n" +
                "    \"4\": \"3,4,4,4\"\n" +
                "    }\n" +
                "}";
            break;
        case 1: //four corners v2
            shipData = "{\"ship\": \n" +
                "    {\n" +
                "    \"0\": \"0,0,0,4\",\n" +
                "    \"1\": \"0,6,0,9\",\n" +
                "    \"2\": \"9,5,9,7\",\n" +
                "    \"3\": \"5,9,7,9\",\n" +
                "    \"4\": \"3,4,4,4\"\n" +
                "    }\n" +
                "}";
            break;
        case 2: //balance
            shipData = "{\"ship\": \n" +
                "    {\n" +
                "    \"0\": \"3,2,7,2\",\n" +
                "    \"1\": \"9,1,9,4\",\n" +
                "    \"2\": \"5,5,7,5\",\n" +
                "    \"3\": \"5,0,7,0\",\n" +
                "    \"4\": \"0,2,0,3\"\n" +
                "    }\n" +
                "}";
            break;
        case 3: //House
            shipData = "{\"ship\": \n" +
                "    {\n" +
                "    \"0\": \"3,3,7,3\",\n" +
                "    \"1\": \"4,4,7,4\",\n" +
                "    \"2\": \"4,5,6,5\",\n" +
                "    \"3\": \"3,4,3,6\",\n" +
                "    \"4\": \"7,6,7,7\"\n" +
                "    }\n" +
                "}";
            break;
        case 4: //line
            shipData = "{\"ship\": \n" +
                "    {\n" +
                "    \"0\": \"0,1,4,1\",\n" +
                "    \"1\": \"3,8,6,8\",\n" +
                "    \"2\": \"0,8,2,8\",\n" +
                "    \"3\": \"7,8,9,8\",\n" +
                "    \"4\": \"8,1,9,1\"\n" +
                "    }\n" +
                "}";
            break;
        case 5: //drip stone
            shipData = "{\"ship\": \n" +
                "    {\n" +
                "    \"0\": \"5,0,5,4\",\n" +
                "    \"1\": \"4,0,4,3\",\n" +
                "    \"2\": \"8,0,8,2\",\n" +
                "    \"3\": \"2,0,2,2\",\n" +
                "    \"4\": \"0,0,0,1\"\n" +
                "    }\n" +
                "}";
            break;
        case 6: //radio frequency
            shipData = "{\"ship\": \n" +
                "    {\n" +
                "    \"0\": \"3,5,3,9\",\n" +
                "    \"1\": \"7,6,7,9\",\n" +
                "    \"2\": \"5,9,5,7\",\n" +
                "    \"3\": \"9,7,9,9\",\n" +
                "    \"4\": \"1,8,1,9\"\n" +
                "    }\n" +
                "}";
            break;
    }
    let request = { "type": "ShipData", "msg": shipData };
    console.log("sending ship data: " + request);
    ws.send(JSON.stringify(request));
}

function sendShot(x, y) {
    let request = { "type": "Shot", "msg": x + "," + y };
    console.log("firing shot at: " + x + "," + y);

    // ws.send("{\"Shot\": \"" + x + "," + y + "\"}")
    ws.send(JSON.stringify(request));
}


// Check the Win Screen/Lose Screen
function togglePopup() {
    document.getElementById("popup-1").classList.toggle("active");
}

function togglePopup2() {
    document.getElementById("popup-2").classList.toggle("active");
}

function togglePopup3() {
    document.getElementById("popup-3").classList.toggle("active");
}

function battlePlanSelector(index) {
    sendShipData(index);
    togglePopup3();
}


// STOLEN
function generateTable() {

    let container = document.body;
    let divHolder = document.createElement('div');
    divHolder.className = "Container";
    let table = document.createElement('table');

    let numRows = 10;
    let numCols = 10;

    for (let i = 0; i < numRows; i++) {
        let row = document.createElement('tr');
        for (let j = 0; j < numCols; j++) {
            let cell = document.createElement('td');
            let button = document.createElement('button');
            // button.textContent = 'Button ' + ((i * numCols) + j + 1);

            button.textContent = 'X';
            button.id = 'Button[' + j + ',' + i + ']';
            button.className = 'gamebuttons';

            //add event listener to send shots to the server
            button.addEventListener('click', function() {
                if(isMyTurn) {
                    sendShot(j, i)
                    let id = 'Button[' + j + ',' + i + ']';
                    document.getElementById(id).style.background = "white";
                }
            });

            cell.appendChild(button);
            row.appendChild(cell);
        }
        table.appendChild(row);
    }
    // First place the table within the DIV then place the DIV within the body.
    divHolder.appendChild(table)
    container.appendChild(divHolder);


}




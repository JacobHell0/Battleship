# This file contains information on how to communicate with the backend

## Format of this document

=========================================================================================

## State # | State Description

### Conditions to trigger the next state
Insert description here

### Messages expected by the server
```json
{"type":"message"}
```
### Message sent to the client
```json
{"type":"message"}
```
=========================================================================================

## State 0 | Waiting for both players

### Next State
The server will move to state 1 when it has 2 people connected to it

### Messages expected by the server
None

### Messages sent to client
```json
{"Ready" : "Ready"}
```
That message is sent to both players

=========================================================================================

## State 1 | Waiting For Ship Information

### Next State
The server will wait for ship data and transition when both sets of data have been sent

### Messages expected by the server
Json data containing ship data in the format:
```json
{"ship": 
    {
    "0": "x0,y0,x1,y1",
    "1": "x0,y0,x1,y1",
    "2": "x0,y0,x1,y1",
    "3": "x0,y0,x1,y1",
    "4": "x0,y0,x1,y1"
    }
}
```

### Messages sent to client
```json
{"StartGameFirst": "ok"}
```
```json
{"StartGameSecond": "ok"}
```
Each player receives this message at the same time, when both players have sent their ship data

=========================================================================================

## State 2 | Players Shoot Each-other (P1)

### Next State
The server will go to state 3 when player 1 has sent the 'shot' packet

### Messages expected by the server
```json
{"Shot": "x,y"}
```
This message is expected from player one

### Messages sent to client
```json
{"ShotResponse": "Hit"}
```
```json
{"ShotResponse": "Miss"}
```

The win packet is sent to P1
```json
{"Win":  "P1HasWon"}
```
The lose packet is sent to P2
```json
{"Lose":  "P2HasLost"}
```
=========================================================================================


## State 3 | Players Shoot Each-other (P2)

### Next State
The server will go to state 2 when player 2 has sent the 'shot' packet

### Messages expected by the server
```json
{"Shot": "x,y"}
```
This message is expected from player two

### Messages sent to client
```json
{"ShotResponse": "Hit"}
```
```json
{"ShotResponse": "Miss"}
```

The win packet is sent to P2
```json
{"Win":  "P1HasWon"}
```
The lose packet is sent to P1
```json
{"Lose":  "P2HasLost"}
```
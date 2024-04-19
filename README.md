# FINAL PROJECT


1. ------- PROJECT INFORMATION --------

Group Members: Jacob Rempel, Ryan Warraner, Bilal Nazar

Within this project, we were able to establish a BattleShip game that is able to handle 2 clients. The game
gives a preset list of options for users to choose how to layout there ships on the game board. Afterwards, the
players take turns at destroying each others ships. The game ends once a user has destroyed all the opponents
ships. The gameboard will display whether the user has hit its opponent ship (turning the box red) or whether
it has missed (turning the box white). The user/client interacts with a beautifully displayed UI that follows a
military theme allowing the user to immerse in a warfare/battlefield experience. 

[INSERT SCREENSHOT] --- I provided a screen shot in the img file ----


2. ---------- HOW TO RUN --------------

Step 1)
Open the Github of the assignment and copy the link of the assignment code.

Step 2:
First find a suitable folder to store the assignment code. After, open your computers terminal and type 
the git command "git clone + [link]". (Place the copied link from github in the position of [link])

Step 3: 
After successfully loading the assignment folder, access the BattleShip.java by going through
w24-csci2020u-final-project-nazar-warrener-rempel/src/main/java/rempelnazarwarrener/battleship/BattleShip.java

Once in the file edit the Run Configurations of the file by setting up a Local Glassfish Server running 
GlassFish 7.0.11. Then assign the JRE to JDK 21, specifiy the Domain as domain1, setup the initial URL to be
http://localhost:8080/Battle-Ship-1.0-SNAPSHOT/. Then go to Deployment and select the artifact "Battle-Ship: war exploded". 
Finally, apply and save these changes.

Step 4:
Click Run File. The Server would have successfully opened, automatically displaying the index.html file.

Step 5)
Begin by clicking on the button "Player One", then open a new tab and copy and paste the URL to the search bar.
Then click on "Player 2". The game won't start until both players have joined.

Step 6)
Afterwards, the user will have a popup displayed outlining the different "BattlePlans" the user can select from,
for the placement of their ships. Each player must select a "BattlePlan" for the game to continue. After the
users have choosen their "BattlePlans", the application will display a grid for each user. The players then
interact with the grids, each taking turns.

Step 7)
When a user clicks on an "X" the board will respond, displaying if the user made a successful shot. If the user
was able to hit an opponents ship, the "X" will turn red and the user will be able to take another turn. 
However, if the user missed a shot the "X" will turn white and the opponent can take its turn.

Step 8) 
The game will continue until the player has sunken all of its opponents ships. Afterwards, a popup screen will
appear displaying the who has won and who lost the game.

Step 9)
There will be a Navigation Bar on the screen, allowing the user to navigate to either the "Home" Page or the
"About" page.

3. ---------- OTHER RESOURCES ----------

Resources that were used to assist with the solution were from the StackOverflow forums that assisted 
with troubleshooting the backend and frontend of the websites. The use of Lab Codes and In-Class codes were
used to help develop aspects of the backend. The use of MDN Web Docs and w3Schools was used to help with 
the creation of the Front-End. The website Freepik.com was used for battleship images within the battleplans.

4. -------- CONTRIBUTION REPORT --------

Jacob Rempel    ----    Back-End
Bilal Nazar     ----    Front-End
Ryan Warraner   ----    Front-End




<h1 align="center">Final Project </h1>
<h1 align="center">Supervisor: Amit shahar</h1>
 <h1 align="center">Student: Elie Haddad</h3>


### This is a documentaion of my progress.
## Material and tools:
* [w3schools](https://www.w3schools.blog/android-tutorial)
* [drawio](https://app.diagrams.net/)


## Timeline
* **26/2**
the first two weeks I focused on doing some research about which data base to use,wheather I should use java or kotlin for the app development and planned the inftastructure of the application using drawio.
I decided that I will use firebase authentication for user authenticaion and firebase cloud to save all the data, and I am going to use java to develop the app.
the app is going to allow communication between the chess community, join clubs, receive news about chess events, and watch tournaments live, and while the games are being broadcasted live I will use stockfish chessengine to analyze the games and if the engine detected that the one of the players is cheating it will notify the chess club with the probabilty if cheating.

* **5/3**
I started working on the application and learned more about firebase authentecaion. I finished developing the sign up and log in screens which add users to the firebase database

* **12/3**
I finished building the homepage screen which has a recycleview that displays the user's favourity events(to be added later) and I added a side navigation bar which will allow a smooth navigation between the fragments(profile, home, clubs, seatch etc.)


* **26/3**
I improved the sign up screen, the user now can add more details about himself like chess.com username, FIDE elo, username, phone number, and since this data cannot be stored the firebase authentication, I started using Firebase cloud/Firestore database and I managed to save all the user's data to the database and then show this data in the profile screen.
* **2/4**
fixed few bugs when fetching the data from the database, and started working on the chess side of the app, I mainly did research on diffrent libraties and opensources that I could use to draw a chessboard, and how to integrate the stockfish chess engine to my app.
I am going to extract the pgn code of a chessboard possedion which describes what the current pocession is and then send it to stockfish to analayze.


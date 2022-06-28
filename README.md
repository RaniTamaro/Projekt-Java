# Tetris Game
<p>Simple tetris game for my university course.
<br>This game is for two players. Players must connect to server before start the game.
<br>Server is sending information about score to second player. When one of player end game, second player also end. Winner is the player that has higher score.
</p>
<hr>
<p>Server is only in console version. Game is a window app, it's wrote in swing.
<br>When you start the server (ServerGry.java) and run the game (Tetris.java) you get a communication about connection with the other player.
<img src="https://i.imgur.com/HQnbDl3.png" alt="A JDialog window with information about connection">
<br>After connection, two players game is start.
<br>
<img src="https://i.imgur.com/rHVCiN1.png" alt="It's a board of game">
<br>After game end the application shows a score table. This table writes information into file and reads them from file.
<br><img src="https://i.imgur.com/OXlSuZG.png" alt="A score table with points of all players.">
</p>

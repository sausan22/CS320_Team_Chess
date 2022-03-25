<!DOCTYPE html>

<html>
	<style>
		.button{
			background-color: #E92900;
			border-color: #FFFFFF;
			color: #FFFFFF;
			font-size: 48px;
			height: 100px;
			width: 100%;
			float: left;
			transition: 0.5s;
		}
		.button:hover{
			color: #E92900;
			border-color: #E92900;
			background-color: #FFFFFF;
		}
		.imageAlign{
  			display: block;
 		 	margin-left: auto;
  			margin-right: auto;
 			width: 50%;
		
		}
		.textAlignCenter{
			text-align: center;
		}
		.textBody{
			color: #FFFFFF;
		}
	</style>
	<head>
		<title>Index view</title>
	</head>

	<body style = "background-color: #000000;">
		<div class = textAlignCenter>
			<h1 style = "color: #FF0000;">Chess Rulebook</h1>
		</div>
		<div class = textAlignCenter>
			<h2 style = "color: #FF0000;"> Understanding the Board</h2>
				<img src="images/rulebook_board.png" alt="Chessboard Setup" width="300" height="300">
				<p class = textBody>
					The standard chess board is an 8x8 grid with 8 rows [1-8], and 8 columns [A-H]. <br>
					The board alternates tile colors, which will effect certain pieces movement patterns.
				</p>
		</div>
		<div class = textAlignCenter>
			<h2 style = "color: #FF0000;"> Basic Rules of Chess</h2>
				<p class = textBody>
					At the start of the game, two players will be assigned a color, with 16 corresponding chess pieces at their disposal. <br>
					They will take turns moving their respective pieces throughout the board, in an attempt to capture the other player's King and win the game.
				</p>
			<h3 style = "color: #FF0000;"> Capturing Pieces </h3>
				<p class = textBody>
					When the attacking player's piece lands on a space that the defending player's piece currently occupies it will "capture the piece". <br>
					The captured piece will be removed from play, and the attacking player's piece will now occupy that space.
				</p>
			<h3 style = "color: #FF0000;"> Check & Checkmate </h3>
			<h4 style = "color: #FF0000;"> Check </h4>
				<p class = textBody>
					When a piece moves onto a space that allows it to capture the king in their next turn, the defending player is in "check". <br>
					Check does not mean the game has ended, as the defending player has options to counter this. <br>
					Option 1. The defending player can move their king to a space that would remove it from the attacking player's path. <br>
					Option 2. The defending player moves another piece into the path of the attacking piece, obstructing its path to the King. <br>
					Option 3. The defending player uses a piece to capture the attacking player's piece that put the King in check.
				</p>
			<h4 style = "color: #FF0000;"> Checkmate </h4>
				<p class = textBody>
					Similar to Check, the attacking player has put the opponent's King in a position in which it would be captured on the next turn. <br>
					However, unlike check, the defending player doesn't have any option that would remove it from check, resulting in the King getting captured and the game ending.
				</p>
		</div>
		<div class = textAlignCenter>
			<h2 style = "color: #FF0000;"> Learning the Pieces</h2>
				<p class = textBody>
					Chess itself has multiple different pieces, each with their own functionality. <br>
					In a standard chess game, each player has 8 pawns, 2 rooks, 2 knights, 2 bishops, 1 queen, and 1 king. <br>
					This can, and will, change as the game progresses, sometimes gaining pieces, but usually losing them.
			<h3 style = "color: #FF0000;"> Pawn </h3>
				<img src="images/rule_pawn.png" alt="Pawn Visual" width="200" height="200">
				<p class = textBody>
					The pawn is the essential frontlines for both players. <br>
					Under normal circumstances the pawn can only move one space forward. <br>
					However, if the pawn hasn't moved at all yet, it has the option to move two spaces forward, or one space diagonally to capture an adjacent opposing piece.
				</p>
			<h3 style = "color: #FF0000;"> Bishop </h3>
				<img src="images/rule_bishop.png" alt="Bishop Visual" width="200" height="200">
				<p class = textBody> 
					The bishop can move any number of squares diagonally, but no horizontal or vertical movement. <br>
					This means that each bishop is limited to movement on their starting tile color, and can only take out opposing pieces on
					the corresponding color.
				</p>
			<h3 style = "color: #FF0000;"> Knight </h3>
				<img src="images/rule_knight.png" alt="Knight Visual" width="200" height="200">
				<p class = textBody>
					The knight is the only piece capable of jumping over/moving through other pieces. <br>
					The knight must move in a fixed "L" shaped pattern, in which it moves two spaces one direction and one space on the opposite axis. <br>
					So, if the knight moves two spaces to the left, then it must also move one space either up or down. <br>
					Each time the knight makes a move, it must complete the full movement pattern.
				</p> 
			<h3 style = "color: #FF0000;"> Rook (Castle) </h3>
				<img src="images/rule_rook.png" alt="Rook Visual" width="200" height="200">
				<p class = textBody> 
					Sometimes referred to as the castle, the rook is not limited in the number of spaces
					it can move, but must move in its current row or column, preventing diagonal movement. <br> It cannot pass pieces of the same color, but can 
					capture an opposing piece in its path.
				</p>
			<h3 style = "color: #FF0000;"> Queen </h3>
				<img src="images/rule_queen.png" alt="Queen Visual" width="200" height="200">
				<p class = textBody> 
					The Queen is essentially a hybrid of the Rook and Bishop, in which it can move any amount of spaces diagonally, vertically, or horizontally in its current position.
				</p>
			<h3 style = "color: #FF0000;"> King </h3>	
				<img src="images/rule_king.png" alt="King Visual" width="200" height="200">
				<p class = textBody>
					Arguably the single most important piece on the board. <br>
					The King can only move one space in any direction, but cannot move into a space that will cause it to be in check/checkmate. <br>
					Unlike the other pieces, the King can never be captured, but simply used as the end goal via checkmate.
				<p>														
		</div>
		<div class = textAlignCenter>
			<h2 style = "color: #FF0000;"> Advanced Rules of Chess</h2>
			<h3 style = "color: #FF0000;"> Pawn Promotion </h3>
				<p class = textBody>
					While the pawn seems more of a "sacrificial lamb" compared to the other pieces, the pawn has access to a special property. <br>
					If a player's pawn can reach the opposite edge (Row 1 or 8 depending on player), <br> 
					then the player can choose to replace the pawn with an extra copy of any piece of their choosing (King excluded).
				</p>
			<h3 style = "color: #FF0000;"> En Passant </h3>
				<img src="images/en_passant.png" alt="En Passant Visual" width="500" height="300">
				<p class = textBody>
					While there are variations to this move of when this move can happen, the main format remains the same. <br>
					When a player's pawn is horizontally next to an opposing player's piece, they can move one space diagonally forward <br>
					in behind the opponent's piece to perform an en passant and capture their piece.
				</p>
			<h3 style = "color: #FF0000;"> Castling </h3>
				<img src="images/castling.png" alt="Castling Visuals" width="500" height="300">			
				<p class = textBody>
					Possibly the most complex basic rule to understand, castling is an advanced movement between the King and Rooks. <br>
					In order to perform a castling, the King piece and at least one rook must not have moved yet. <br>
					The King can move two spaces toward that rook's corner, and the rook will go on the opposite side of the King's piece.
				</p>						
		</div>
		<div>
			<a href="http://localhost:8081/chessgame/index">
				<input class="button" type="Submit" name="submit" value="Index"/>
			</a>	
		</div>
	</body>
</html>

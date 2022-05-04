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
	</style>
	<head>
		<title>Index view</title>
	</head>
	<!-- scripts created with much help from w3schools thanks-->
	<script>
		function setGame(game){
			var gameID = game;
			sessionStorage.setItem("gameID", gameID);
			console.log("the game is " + game + " and the gameid is " + gameID);
		}
		function popup(ev) {
			var popup = document.getElementById("loadedGamesPopup");
			if(popup.style.display == "none"){
				popup.style.display = "block";
			}
			else{
				popup.style.display = "none";
			}
		}
		function getReal(id){
			var real = document.getElementById(id);
			real.style.display = "block";
		}
	</script>
	<body style = "background-color: #000000;">
			<!--<div style = "color: #FFFFFF;">
				Hello ${name}
			</div>-->
			<div>
				<a href="http://localhost:8081/chessgame/login">
					<input class="button" type="Submit" name="submit" value="Login/Sign Up"/>
				</a>
			</div>
			<div>
				<a href="http://localhost:8081/chessgame/game">
					<input class="button" type="Submit" name="submit" value="New Game"/>
				</a>
			</div>
			<div>
				<input class="button" type="button" value="choose game" onclick="popup(event)"/>
			</div>
			<div id="loadedGamesPopup" style="display: none"/>
				<table>
					<tr>
						<input type="button" value="game 1" onclick="setGame(1)"/>
					</tr>
				</table>
				<a href="http://localhost:8081/chessgame/game">				
					<input type="Submit" name="submit" value="Load Game"/>
				</a>
			</div>
			<div>
				<a href="http://localhost:8081/chessgame/savedGames">			
					<input class="button" type="Submit" name="submit" value="Saved Games"/>
				</a>
			</div>
			<div>
				<a href="http://localhost:8081/chessgame/rulebook">
					<input class="button" type="Submit" name="submit" value="Rulebook"/>
				</a>
			</div>
	</body>
</html>

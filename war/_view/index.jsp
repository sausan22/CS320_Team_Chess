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

	<body style = "background-color: #000000;">
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
				<a href="http://localhost:8081/chessgame/game">				
					<input class="button" type="Submit" name="submit" value="Load Game"/>
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

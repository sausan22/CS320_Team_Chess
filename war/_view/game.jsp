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

		.chatItem{
			color: #FFFFFF;
			font-size: 24px;
			border-color: #FFFFFF;
			background-color: #FF80FF;
		}
	</style>
	<head>
		<title>Game Page</title>
	</head>
	<body style = "background-color: #00FF00;">
		<div style="float: left;">
			<img src="images/chessboard.png" alt="chessboard.png" width="500" length="500">
		</div>
		<div style="float: right; width: 40%">
			<ul>
				<li class="chatItem"> User 1: yo what up </li>
				<li class="chatItem"> User 2: how to forfeit </li>
				<li class="chatItem">
					<form action="${pageContext.servletContext.contextPath}/game" method="post">
						<input type="text" name="submit" value="type message here"/>
					</form>
				</li>
			</ul>
		</div>
			<div>
				<a href="http://localhost:8081/chessgame/rulebook">
					<input class="button" type="Submit" name="submit" value="Rulebook"/>
				</a>
			</div>
	</body>
</html>
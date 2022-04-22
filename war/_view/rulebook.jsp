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
	</style>
	<head>
		<title>Index view</title>
	</head>

	<body style = "background-color: #000000;">
		<div class = textAlignCenter>
			<h1 style = "color: #FF0000;">Rules</h1>
		</div>
		<div>
			<img src = "https://cdn.discordapp.com/attachments/937487901783097355/952368095576588298/chess-board-companion-page-1_orig.png" class = "imageAlign">
		</div>
		
		<div>
			<a href="http://localhost:8081/chessgame/index">
				<input class="button" type="Submit" name="submit" value="Index"/>
			</a>	
		</div>
	</body>
</html>

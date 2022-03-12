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
		<title>Login Page</title>
	</head>

	<body style = "background-color: #000000;">
		<form action="${pageContext.servletContext.contextPath}/login" method="post">
			<div>
				<input class="button" type="Submit" name="submit" value="Login/Sign Up"/>
			</div>
			<div>
				<input class="button" type="Submit" name="submit" value="New Game"/>
			</div>
			<div>
				<input class="button" type="Submit" name="submit" value="Load Game"/>
			</div>
			<div>
				<input class="button" type="Submit" name="submit" value="Saved Games"/>
			</div>
			<div>
				<input class="button" type="Submit" name="submit" value="Rulebook"/>
			</div>
		</form>
	</body>
</html>
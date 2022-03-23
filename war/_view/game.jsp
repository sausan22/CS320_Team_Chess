<!DOCTYPE html>

<html>
	<style>
		#tile1, #tile3, #tile5, #tile7, #tile8, #tile10, #tile12, #tile14, #tile17, #tile19, #tile21, #tile23, #tile24, #tile26, #tile28, #tile30, #tile33, #tile35, #tile37, #tile39, #tile40, #tile42, #tile44, #tile46, #tile49, #tile51, #tile53, #tile55, #tile56, #tile58, #tile60, #tile62{
			background-color: #FFFFFF;
			height: 60px;
			width: 60px;
			padding: 1px;
		}
		#tile0, #tile2, #tile4, #tile6, #tile9, #tile11, #tile13, #tile15, #tile16, #tile18, #tile20, #tile22, #tile25, #tile27, #tile29, #tile31, #tile32, #tile34, #tile36, #tile38, #tile41, #tile43, #tile45, #tile47, #tile48, #tile50, #tile52, #tile54, #tile57, #tile59, #tile61, #tile63{
			background-color: #000000;
			height: 60px;
			width: 60px;
			padding: 1px;
		}
		#pawn1{
		
		}
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
	<!--scripts stolen from w3schools thanks-->
	<script>
		function allowDrop(ev) {
		  if(!ev.target.hasChild){
		  	ev.preventDefault();
		  }
		}
		
		function drag(ev) {
		  ev.dataTransfer.setData("text", ev.target.id);
		}
		
		function drop(ev) {
			if(!ev.target.hasChild){
			  ev.preventDefault();
		 	  var data = ev.dataTransfer.getData("text");
			  ev.target.appendChild(document.getElementById(data));
		  }
		}
	</script>
	<head>
		<title>Game Page</title>
	</head>
	<body style = "background-color: #FFE951;">
		<div style = "float: left; margin-left: 10px;">	
			<div style = "float: left;">
				<div id="tile0" ondrop="drop(event)" ondragover="allowDrop(event)">
					<img src="images/wRook.png" alt="pawn.png" id="wrook1" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile8" ondrop="drop(event)" ondragover="allowDrop(event)">
					<img src="images/wPawn.png" alt="pawn.png" id="wpawn1" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile16" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
				<div id="tile24" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
				<div id="tile32" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
				<div id="tile40" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
				<div id="tile48" ondrop="drop(event)" ondragover="allowDrop(event)">
					<img src="images/bPawn.png" alt="pawn.png" id="bpawn1" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile56" ondrop="drop(event)" ondragover="allowDrop(event)">
					<img src="images/bRook.png" alt="pawn.png" id="brook1" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
			</div>
			<div style = "float: left;">
				<div id="tile1" ondrop="drop(event)" ondragover="allowDrop(event)">
					<img src="images/wKnight.png" alt="pawn.png" id="wknight1" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile9" ondrop="drop(event)" ondragover="allowDrop(event)">
					<img src="images/wPawn.png" alt="pawn.png" id="wpawn2" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile17" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
				<div id="tile25" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
				<div id="tile33" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
				<div id="tile41" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
				<div id="tile49" ondrop="drop(event)" ondragover="allowDrop(event)">
					<img src="images/bPawn.png" alt="pawn.png" id="bpawn2" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile57" ondrop="drop(event)" ondragover="allowDrop(event)">
					<img src="images/bKnight.png" alt="pawn.png" id="bknight1" width="50" length="50" draggable="true" ondragstart="drag(event)">				
				</div>
			</div>
			<div style = "float: left;">
				<div id="tile2" ondrop="drop(event)" ondragover="allowDrop(event)">
					<img src="images/wBishop.png" alt="pawn.png" id="wbishop1" width="50" length="50" draggable="true" ondragstart="drag(event)">				
				</div>
				<div id="tile10" ondrop="drop(event)" ondragover="allowDrop(event)">
					<img src="images/wPawn.png" alt="pawn.png" id="wpawn3" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile18" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
				<div id="tile26" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
				<div id="tile34" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
				<div id="tile42" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
				<div id="tile50" ondrop="drop(event)" ondragover="allowDrop(event)">
					<img src="images/bPawn.png" alt="pawn.png" id="bpawn3" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile58" ondrop="drop(event)" ondragover="allowDrop(event)">
					<img src="images/bBishop.png" alt="pawn.png" id="bbishop1" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
			</div>
			<div style = "float: left;">
				<div id="tile3" ondrop="drop(event)" ondragover="allowDrop(event)">
					<img src="images/wKing.png" alt="pawn.png" id="wking" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile11" ondrop="drop(event)" ondragover="allowDrop(event)">
					<img src="images/wPawn.png" alt="pawn.png" id="wpawn4" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile19" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
				<div id="tile27" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
				<div id="tile35" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
				<div id="tile43" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
				<div id="tile51" ondrop="drop(event)" ondragover="allowDrop(event)">
					<img src="images/bPawn.png" alt="pawn.png" id="bpawn4" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile59" ondrop="drop(event)" ondragover="allowDrop(event)">
					<img src="images/bKing.png" alt="pawn.png" id="bking" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
			</div>
			<div style = "float: left;">
				<div id="tile4" ondrop="drop(event)" ondragover="allowDrop(event)">
					<img src="images/wQueen.png" alt="pawn.png" id="wqueen" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile12" ondrop="drop(event)" ondragover="allowDrop(event)">
					<img src="images/wPawn.png" alt="pawn.png" id="wpawn5" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile20" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
				<div id="tile28" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
				<div id="tile36" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
				<div id="tile44" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
				<div id="tile52" ondrop="drop(event)" ondragover="allowDrop(event)">
					<img src="images/bPawn.png" alt="pawn.png" id="bpawn5" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile60" ondrop="drop(event)" ondragover="allowDrop(event)">
					<img src="images/bQueen.png" alt="pawn.png" id="bQueen" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
			</div>
			<div style = "float: left;">
				<div id="tile5" ondrop="drop(event)" ondragover="allowDrop(event)">
					<img src="images/wBishop.png" alt="pawn.png" id="wbishop2" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile13" ondrop="drop(event)" ondragover="allowDrop(event)">
					<img src="images/wPawn.png" alt="pawn.png" id="wpawn6" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile21" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
				<div id="tile29" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
				<div id="tile37" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
				<div id="tile45" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
				<div id="tile53" ondrop="drop(event)" ondragover="allowDrop(event)">
					<img src="images/bPawn.png" alt="pawn.png" id="bpawn6" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile61" ondrop="drop(event)" ondragover="allowDrop(event)">
					<img src="images/bBishop.png" alt="pawn.png" id="bbishop2" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
			</div>
			<div style = "float: left;">
				<div id="tile6" ondrop="drop(event)" ondragover="allowDrop(event)">
					<img src="images/wKnight.png" alt="pawn.png" id="wknight2" width="50" length="50" draggable="true" ondragstart="drag(event)">				
				</div>
				<div id="tile14" ondrop="drop(event)" ondragover="allowDrop(event)">
					<img src="images/wPawn.png" alt="pawn.png" id="wpawn7" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile22" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
				<div id="tile30" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
				<div id="tile38" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
				<div id="tile46" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
				<div id="tile54" ondrop="drop(event)" ondragover="allowDrop(event)">
					<img src="images/bPawn.png" alt="pawn.png" id="bpawn7" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile62" ondrop="drop(event)" ondragover="allowDrop(event)">
					<img src="images/bKnight.png" alt="pawn.png" id="bknight2" width="50" length="50" draggable="true" ondragstart="drag(event)">				
				</div>
			</div>
			<div style = "float: left;">
				<div id="tile7" ondrop="drop(event)" ondragover="allowDrop(event)">
					<img src="images/wRook.png" alt="pawn.png" id="wrook2" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile15" ondrop="drop(event)" ondragover="allowDrop(event)">
					<img src="images/wPawn.png" alt="pawn.png" id="wpawn8" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile23" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
				<div id="tile31" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
				<div id="tile39" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
				<div id="tile47" ondrop="drop(event)" ondragover="allowDrop(event)"></div>
				<div id="tile55" ondrop="drop(event)" ondragover="allowDrop(event)">
					<img src="images/bPawn.png" alt="pawn.png" id="bpawn8" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile63" ondrop="drop(event)" ondragover="allowDrop(event)">
					<img src="images/bRook.png" alt="pawn.png" id="brook2" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
			</div>
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
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
		.boardLabel{
			height: 60px;
			width: 60px;
			padding: 1px;
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
		function popup(ev) {
			var popup = document.getElementById("forfeitPopup");
			popup.classList.toggle("show");
		}
	</script>
	<head>
		<title>Game Page</title>
	</head>
	<body style = "background-color: #FFFAC2;">
		<div style = "float: left; margin-left: 10px;">	
			<div style = "float: left">
				<div class="boardLabel">
				</div>
				<div class="boardLabel">
					8
				</div>
				<div class="boardLabel">
					7
				</div>
				<div class="boardLabel">
					6
				</div>
				<div class="boardLabel">
					5
				</div>
				<div class="boardLabel">
					4
				</div>
				<div class="boardLabel">
					3
				</div>
				<div class="boardLabel">
					2
				</div>
				<div class="boardLabel">
					1
				</div>
			</div>
			<div style = "float: left;">
				<div class="boardLabel">
					A
				</div>
				<div id="tile0" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile0" id="tile0"> ${tile0} </p>-->
					<img src="${tile0}" alt="pawn.png" id="p0" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile8" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile8" id="tile8"> ${tile8} </p>-->
					<img src="${tile8}" alt="pawn.png" id="p8" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile16" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile16" id="tile16"> ${tile16} </p>-->
				</div>
				<div id="tile24" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile24" id="tile24"> ${tile24} </p>-->
				</div>
				<div id="tile32" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile32" id="tile32"> ${tile32} </p>-->
				</div>
				<div id="tile40" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile40" id="tile40"> ${tile40} </p>-->
				</div>
				<div id="tile48" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile48" id="tile48"> ${tile48} </p>-->
					<img src="${tile48}" alt="pawn.png" id="p48" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile56" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile56" id="tile56"> ${tile56} </p>-->
					<img src="${tile56}" alt="pawn.png" id="p56" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
			</div>
			<div style = "float: left;">
				<div class="boardLabel">
					B
				</div>
				<div id="tile1" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile1" id="tile1"> ${tile1} </p>-->
					<img src="${tile1}" alt="pawn.png" id="p1" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile9" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile9" id="tile9"> ${tile9} </p>-->
					<img src="${tile9}" alt="pawn.png" id="p9" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile17" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile17" id="tile17"> ${tile17} </p>-->
				</div>
				<div id="tile25" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile25" id="tile25"> ${tile25} </p>-->
				</div>
				<div id="tile33" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile33" id="tile33"> ${tile33} </p>-->
				</div>
				<div id="tile41" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile41" id="tile41"> ${tile41} </p>-->
				</div>
				<div id="tile49" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile49" id="tile49"> ${tile49} </p>-->
					<img src="${tile49}" alt="pawn.png" id="p49" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile57" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile57" id="tile57"> ${tile57} </p>-->
					<img src="${tile57}" alt="pawn.png" id="p57" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
			</div>
			<div style = "float: left;">
				<div class="boardLabel">
					C
				</div>
				<div id="tile2" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile2" id="tile2"> ${tile2} </p>-->
					<img src="${tile2}" alt="pawn.png" id="p2" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile10" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile10" id="tile10"> ${tile10} </p>-->
					<img src="${tile10}" alt="pawn.png" id="p10" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile18" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile18" id="tile18"> ${tile18} </p>-->
				</div>
				<div id="tile26" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile26" id="tile26"> ${tile26} </p>-->
				</div>
				<div id="tile34" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile34" id="tile34"> ${tile34} </p>-->
				</div>
				<div id="tile42" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile42" id="tile42"> ${tile42} </p>-->
				</div>
				<div id="tile50" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile50" id="tile50"> ${tile50} </p>-->
					<img src="${tile50}" alt="pawn.png" id="p50" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile58" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile58" id="tile58"> ${tile58} </p>-->
					<img src="${tile58}" alt="pawn.png" id="p58" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
			</div>
			<div style = "float: left;">
				<div class="boardLabel">
					D
				</div>
				<div id="tile3" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile3" id="tile3"> ${tile3} </p>-->
					<img src="${tile3}" alt="pawn.png" id="p3" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile11" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile11" id="tile11"> ${tile11} </p>-->
					<img src="${tile11}" alt="pawn.png" id="p11" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile19" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile19" id="tile19"> ${tile19} </p>-->
				</div>
				<div id="tile27" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile27" id="tile27"> ${tile27} </p>-->
				</div>
				<div id="tile35" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile35" id="tile35"> ${tile35} </p>-->
				</div>
				<div id="tile43" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile43" id="tile43"> ${tile43} </p>-->
				</div>
				<div id="tile51" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile51" id="tile51"> ${tile51} </p>-->
					<img src="${tile51}" alt="pawn.png" id="p51" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile59" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile59" id="tile59"> ${tile59} </p>-->
					<img src="${tile59}" alt="pawn.png" id="p59" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
			</div>
			<div style = "float: left;">
				<div class="boardLabel">
					E
				</div>
				<div id="tile4" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile4" id="tile4"> ${tile4} </p>-->
					<img src="${tile4}" alt="pawn.png" id="p4" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile12" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile12" id="tile12"> ${tile12} </p>-->
					<img src="${tile12}" alt="pawn.png" id="p12" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile20" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile20" id="tile20"> ${tile20} </p>-->
				</div>
				<div id="tile28" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile28" id="tile28"> ${tile28} </p>-->
				</div>
				<div id="tile36" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile36" id="tile36"> ${tile36} </p>-->
				</div>
				<div id="tile44" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile44" id="tile44"> ${tile44} </p>-->
				</div>
				<div id="tile52" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile52" id="tile52"> ${tile52} </p>-->
					<img src="${tile52}" alt="pawn.png" id="p52" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile60" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile60" id="tile60"> ${tile60} </p>-->
					<img src="${tile60}" alt="pawn.png" id="p60" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
			</div>
			<div style = "float: left;">
				<div class="boardLabel">
					F
				</div>
				<div id="tile5" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile5" id="tile5"> ${tile5} </p>-->
					<img src="${tile5}" alt="pawn.png" id="p5" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile13" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile13" id="tile13"> ${tile13} </p>-->
					<img src="${tile13}" alt="pawn.png" id="p13" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile21" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile21" id="tile21"> ${tile21} </p>-->
				</div>
				<div id="tile29" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile29" id="tile29"> ${tile29} </p>-->
				</div>
				<div id="tile37" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile37" id="tile37"> ${tile37} </p>-->
				</div>
				<div id="tile45" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile45" id="tile45"> ${tile45} </p>-->
				</div>
				<div id="tile53" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile53" id="tile53"> ${tile53} </p>-->
					<img src="${tile53}" alt="pawn.png" id="p53" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile61" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile61" id="tile61"> ${tile61} </p>-->
					<img src="${tile61}" alt="pawn.png" id="p61" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
			</div>
			<div style = "float: left;">
				<div class="boardLabel">
					G
				</div>
				<div id="tile6" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile6" id="tile6"> ${tile6} </p>-->
					<img src="${tile6}" alt="pawn.png" id="p6" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile14" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile14" id="tile14"> ${tile14} </p>-->
					<img src="${tile14}" alt="pawn.png" id="p14" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile22" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile22" id="tile22"> ${tile22} </p>-->
				</div>
				<div id="tile30" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile30" id="tile30"> ${tile30} </p>-->
				</div>
				<div id="tile38" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile38" id="tile38"> ${tile38} </p>-->
				</div>
				<div id="tile46" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile46" id="tile46"> ${tile46} </p>-->
				</div>
				<div id="tile54" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile54" id="tile54"> ${tile54} </p>-->
					<img src="${tile54}" alt="pawn.png" id="p54" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile62" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile62" id="tile62"> ${tile62} </p>-->
					<img src="${tile62}" alt="pawn.png" id="p62" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
			</div>
			<div style = "float: left;">
				<div class="boardLabel">
					H
				</div>
				<div id="tile7" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile7" id="tile7"> ${tile7} </p>-->
					<img src="${tile7}" alt="pawn.png" id="p7" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile15" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile15" id="tile15"> ${tile15} </p>-->
					<img src="${tile15}" alt="pawn.png" id="p15" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile23" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile23" id="tile23"> ${tile23} </p>-->
				</div>
				<div id="tile31" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile31" id="tile31"> ${tile31} </p>-->
				</div>
				<div id="tile39" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile39" id="tile39"> ${tile39} </p>-->
				</div>
				<div id="tile47" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile47" id="tile47"> ${tile47} </p>-->
				</div>
				<div id="tile55" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile55" id="tile55"> ${tile55} </p>-->
					<img src="${tile55}" alt="pawn.png" id="p55" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
				<div id="tile63" ondrop="drop(event)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile63" id="tile63"> ${tile63} </p>-->
					<img src="${tile63}" alt="pawn.png" id="p63" width="50" length="50" draggable="true" ondragstart="drag(event)">
				</div>
			</div>
		</div>

		
		
		<!-- DON'T UNCOMMENT THIS IT DOESN'T WORK YET
		<div>
			<form action="${pageContext.servletContext.contextPath}/game" method="post">
				Selected Piece: 
				<input type="text" name="initpos" value="${initpos}">
				Move to: 
				<input type="text" name="finpos" value="${finpos}">
				<input type="Submit" name="submit" value="Submit Move"/>
			</form>
		</div>-->
		
		<div>
			<form action="${pageContext.servletContext.contextPath}/game" method="post">
				<input type="Submit" name="submit" value="Fix Pieces"/>
			</form>
		</div>
		<div style="float: right; width: 40%">
			<ul>
				<li class="chatItem"> User 1: yo what up </li>
				<li class="chatItem"> User 2: how to forfeit </li>
				<li class="chatItem">
					<form action="${pageContext.servletContext.contextPath}/game" method="post">
						<input type="text" name="message" value="${message}"/>
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
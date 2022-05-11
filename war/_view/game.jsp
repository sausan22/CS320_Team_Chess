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
		#gameid{
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
	<script>
		function popup(ev) {
			var popup = document.getElementById("forfeitPopup");
			popup.classList.toggle("show");
		}
	</script>
	<head>
		<title>Game Page</title>
	</head>
	<body style = "background-color: #FFFAC2;">
		<div>
			<form id="autoref" action="setAutoRefresh()"/>
				<input id="semiauto" type="button" value="Stop Auto-Refresh" onclick="setAutoRefresh()"/>
			</form>
			<span id="funnytimer">10</span>
			<form id="refresh" action="${pageContext.servletContext.contextPath}/game" method="post">
				<input type="hidden" id="gameid" name="gameid"/>
					<script>
						var tempgame;
						tempgame = sessionStorage.getItem("gameID");
						console.log("the gameid is "+ tempgame);
						gameid.setAttribute("value", tempgame);
						console.log("the set gameid value is " + gameid.getAttribute("value"));
					</script>
			</form>
			<script>
				var time = 10;
				var autoref = document.getElementById("semiauto").getAttribute("value");
				var intervalId = setInterval(function () {
						time -= 1;
						document.getElementById("funnytimer").innerHTML = time;
						console.log("time is "+time);
						if(time==0){
							document.getElementById("refresh").submit();
						}
					}, 1000);
				if(autoref != "Stop Auto-Refresh"){
					clearInterval(intervalId);
				}
			</script>
		</div>
		<div>
			<form id="msub" action="${pageContext.servletContext.contextPath}/game" method="post">
				<input id="ipos" type="hidden" name="ipos" value=""/>
				<input id="fpos" type="hidden" name="fpos" value=""/>
				<input name="submitBtn" type="hidden" value="Submit Move"/>
				<input type="hidden" id="gameid1" name="gameid1"/>
				<script>
					var tempgame;
					tempgame = sessionStorage.getItem("gameID");
					console.log("the gameid is "+ tempgame);
					gameid1.setAttribute("value", tempgame);
					console.log("the set gameid value is " + gameid1.getAttribute("value"));
				</script>
			</form>
		</div>
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
			<!-- [onerror="this.style.display='none'"] code was taken from Kevin Jantzer on stackoverflow -->
			<div style = "float: left;">
				<div class="boardLabel">
					A
				</div>
				<div id="tile0" ondrop="drop(event, 0)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile0" id="tile0"> ${tile0} </p>-->
					<img src="${tile0}" onerror="this.style.display='none'" id="p0" width="50" length="50" draggable="true" ondragstart="drag(event, 0)">
				</div>
				<div id="tile8" ondrop="drop(event, 8)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile8" id="tile8"> ${tile8} </p>-->
					<img src="${tile8}" onerror="this.style.display='none'" id="p8" width="50" length="50" draggable="true" ondragstart="drag(event, 8)">
				</div>
				<div id="tile16" ondrop="drop(event, 16)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile16" id="tile16"> ${tile16} </p>-->
					<img src="${tile16}" onerror="this.style.display='none'" id="p16" width="50" length="50" draggable="true" ondragstart="drag(event, 16)">
				</div>
				<div id="tile24" ondrop="drop(event, 24)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile24" id="tile24"> ${tile24} </p>-->
					<img src="${tile24}" onerror="this.style.display='none'" id="p24" width="50" length="50" draggable="true" ondragstart="drag(event,24)">
				</div>
				<div id="tile32" ondrop="drop(event, 32)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile32" id="tile32"> ${tile32} </p>-->
					<img src="${tile32}" onerror="this.style.display='none'" id="p32" width="50" length="50" draggable="true" ondragstart="drag(event,32)">
				</div>
				<div id="tile40" ondrop="drop(event, 40)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile40" id="tile40"> ${tile40} </p>-->
					<img src="${tile40}" onerror="this.style.display='none'" id="p40" width="50" length="50" draggable="true" ondragstart="drag(event, 40)">
				</div>
				<div id="tile48" ondrop="drop(event, 48)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile48" id="tile48"> ${tile48} </p>-->
					<img src="${tile48}" onerror="this.style.display='none'" id="p48" width="50" length="50" draggable="true" ondragstart="drag(event, 48)">
				</div>
				<div id="tile56" ondrop="drop(event, 56)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile56" id="tile56"> ${tile56} </p>-->
					<img src="${tile56}" onerror="this.style.display='none'" id="p56" width="50" length="50" draggable="true" ondragstart="drag(event, 56)">
				</div>
			</div>
			<div style = "float: left;">
				<div class="boardLabel">
					B
				</div>
				<div id="tile1" ondrop="drop(event, 1)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile1" id="tile1"> ${tile1} </p>-->
					<img src="${tile1}" onerror="this.style.display='none'" id="p1" width="50" length="50" draggable="true" ondragstart="drag(event, 1)">
				</div>
				<div id="tile9" ondrop="drop(event, 9)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile9" id="tile9"> ${tile9} </p>-->
					<img src="${tile9}" onerror="this.style.display='none'" id="p9" width="50" length="50" draggable="true" ondragstart="drag(event, 9)">
				</div>
				<div id="tile17" ondrop="drop(event, 17)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile17" id="tile17"> ${tile17} </p>-->
					<img src="${tile17}" onerror="this.style.display='none'" id="p17" width="50" length="50" draggable="true" ondragstart="drag(event, 17)">
				</div>
				<div id="tile25" ondrop="drop(event, 25)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile25" id="tile25"> ${tile25} </p>-->
					<img src="${tile25}" onerror="this.style.display='none'" id="p25" width="50" length="50" draggable="true" ondragstart="drag(event, 25)">
				</div>
				<div id="tile33" ondrop="drop(event, 33)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile33" id="tile33"> ${tile33} </p>-->
					<img src="${tile33}" onerror="this.style.display='none'" id="p33" width="50" length="50" draggable="true" ondragstart="drag(event, 33)">
				</div>
				<div id="tile41" ondrop="drop(event, 41)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile41" id="tile41"> ${tile41} </p>-->
					<img src="${tile41}" onerror="this.style.display='none'" id="p41" width="50" length="50" draggable="true" ondragstart="drag(event, 41)">
				</div>
				<div id="tile49" ondrop="drop(event, 49)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile49" id="tile49"> ${tile49} </p>-->
					<img src="${tile49}" onerror="this.style.display='none'" id="p49" width="50" length="50" draggable="true" ondragstart="drag(event, 49)">
				</div>
				<div id="tile57" ondrop="drop(event, 57)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile57" id="tile57"> ${tile57} </p>-->
					<img src="${tile57}" onerror="this.style.display='none'" id="p57" width="50" length="50" draggable="true" ondragstart="drag(event, 57)">
				</div>
			</div>
			<div style = "float: left;">
				<div class="boardLabel">
					C
				</div>
				<div id="tile2" ondrop="drop(event, 2)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile2" id="tile2"> ${tile2} </p>-->
					<img src="${tile2}" onerror="this.style.display='none'" id="p2" width="50" length="50" draggable="true" ondragstart="drag(event, 2)">
				</div>
				<div id="tile10" ondrop="drop(event, 10)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile10" id="tile10"> ${tile10} </p>-->
					<img src="${tile10}" onerror="this.style.display='none'" id="p10" width="50" length="50" draggable="true" ondragstart="drag(event, 10)">
				</div>
				<div id="tile18" ondrop="drop(event, 18)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile18" id="tile18"> ${tile18} </p>-->
					<img src="${tile18}" onerror="this.style.display='none'" id="p18" width="50" length="50" draggable="true" ondragstart="drag(event, 18)">
				</div>
				<div id="tile26" ondrop="drop(event, 26)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile26" id="tile26"> ${tile26} </p>-->
					<img src="${tile26}" onerror="this.style.display='none'" id="p26" width="50" length="50" draggable="true" ondragstart="drag(event, 26)">
				</div>
				<div id="tile34" ondrop="drop(event, 34)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile34" id="tile34"> ${tile34} </p>-->
					<img src="${tile34}" onerror="this.style.display='none'" id="p34" width="50" length="50" draggable="true" ondragstart="drag(event, 34)">
				</div>
				<div id="tile42" ondrop="drop(event, 42)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile42" id="tile42"> ${tile42} </p>-->
					<img src="${tile42}" onerror="this.style.display='none'" id="p42" width="50" length="50" draggable="true" ondragstart="drag(event, 42)">
				</div>
				<div id="tile50" ondrop="drop(event, 50)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile50" id="tile50"> ${tile50} </p>-->
					<img src="${tile50}" onerror="this.style.display='none'" id="p50" width="50" length="50" draggable="true" ondragstart="drag(event, 50)">
				</div>
				<div id="tile58" ondrop="drop(event, 58)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile58" id="tile58"> ${tile58} </p>-->
					<img src="${tile58}" onerror="this.style.display='none'" id="p58" width="50" length="50" draggable="true" ondragstart="drag(event, 58)">
				</div>
			</div>
			<div style = "float: left;">
				<div class="boardLabel">
					D
				</div>
				<div id="tile3" ondrop="drop(event, 3)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile3" id="tile3"> ${tile3} </p>-->
					<img src="${tile3}" onerror="this.style.display='none'" id="p3" width="50" length="50" draggable="true" ondragstart="drag(event, 3)">
				</div>
				<div id="tile11" ondrop="drop(event, 11)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile11" id="tile11"> ${tile11} </p>-->
					<img src="${tile11}" onerror="this.style.display='none'" id="p11" width="50" length="50" draggable="true" ondragstart="drag(event, 11)">
				</div>
				<div id="tile19" ondrop="drop(event, 19)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile19" id="tile19"> ${tile19} </p>-->
					<img src="${tile19}" onerror="this.style.display='none'" id="p19" width="50" length="50" draggable="true" ondragstart="drag(event, 19)">
				</div>
				<div id="tile27" ondrop="drop(event, 27)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile27" id="tile27"> ${tile27} </p>-->
					<img src="${tile27}" onerror="this.style.display='none'" id="p27" width="50" length="50" draggable="true" ondragstart="drag(event, 27)">
				</div>
				<div id="tile35" ondrop="drop(event, 35)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile35" id="tile35"> ${tile35} </p>-->
					<img src="${tile35}" onerror="this.style.display='none'" id="p35" width="50" length="50" draggable="true" ondragstart="drag(event, 35)">
				</div>
				<div id="tile43" ondrop="drop(event, 43)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile43" id="tile43"> ${tile43} </p>-->
					<img src="${tile43}" onerror="this.style.display='none'" id="p43" width="50" length="50" draggable="true" ondragstart="drag(event, 43)">
				</div>
				<div id="tile51" ondrop="drop(event, 51)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile51" id="tile51"> ${tile51} </p>-->
					<img src="${tile51}" onerror="this.style.display='none'" id="p51" width="50" length="50" draggable="true" ondragstart="drag(event, 51)">
				</div>
				<div id="tile59" ondrop="drop(event, 59)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile59" id="tile59"> ${tile59} </p>-->
					<img src="${tile59}" onerror="this.style.display='none'" id="p59" width="50" length="50" draggable="true" ondragstart="drag(event, 59)">
				</div>
			</div>
			<div style = "float: left;">
				<div class="boardLabel">
					E
				</div>
				<div id="tile4" ondrop="drop(event, 4)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile4" id="tile4"> ${tile4} </p>-->
					<img src="${tile4}" onerror="this.style.display='none'" id="p4" width="50" length="50" draggable="true" ondragstart="drag(event, 4)">
				</div>
				<div id="tile12" ondrop="drop(event, 12)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile12" id="tile12"> ${tile12} </p>-->
					<img src="${tile12}" onerror="this.style.display='none'" id="p12" width="50" length="50" draggable="true" ondragstart="drag(event, 12)">
				</div>
				<div id="tile20" ondrop="drop(event, 20)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile20" id="tile20"> ${tile20} </p>-->
					<img src="${tile20}" onerror="this.style.display='none'" id="p20" width="50" length="50" draggable="true" ondragstart="drag(event, 20)">
				</div>
				<div id="tile28" ondrop="drop(event, 28)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile28" id="tile28"> ${tile28} </p>-->
					<img src="${tile28}" onerror="this.style.display='none'" id="p28" width="50" length="50" draggable="true" ondragstart="drag(event, 28)">
				</div>
				<div id="tile36" ondrop="drop(event, 36)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile36" id="tile36"> ${tile36} </p>-->
					<img src="${tile36}" onerror="this.style.display='none'" id="p36" width="50" length="50" draggable="true" ondragstart="drag(event, 36)">
				</div>
				<div id="tile44" ondrop="drop(event, 44)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile44" id="tile44"> ${tile44} </p>-->
					<img src="${tile44}" onerror="this.style.display='none'" id="p44" width="50" length="50" draggable="true" ondragstart="drag(event, 44)">
				</div>
				<div id="tile52" ondrop="drop(event, 52)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile52" id="tile52"> ${tile52} </p>-->
					<img src="${tile52}" onerror="this.style.display='none'" id="p52" width="50" length="50" draggable="true" ondragstart="drag(event, 52)">
				</div>
				<div id="tile60" ondrop="drop(event, 60)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile60" id="tile60"> ${tile60} </p>-->
					<img src="${tile60}" onerror="this.style.display='none'" id="p60" width="50" length="50" draggable="true" ondragstart="drag(event, 60)">
				</div>
			</div>
			<div style = "float: left;">
				<div class="boardLabel">
					F
				</div>
				<div id="tile5" ondrop="drop(event, 5)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile5" id="tile5"> ${tile5} </p>-->
					<img src="${tile5}" onerror="this.style.display='none'" id="p5" width="50" length="50" draggable="true" ondragstart="drag(event, 5)">
				</div>
				<div id="tile13" ondrop="drop(event, 13)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile13" id="tile13"> ${tile13} </p>-->
					<img src="${tile13}" onerror="this.style.display='none'" id="p13" width="50" length="50" draggable="true" ondragstart="drag(event, 13)">
				</div>
				<div id="tile21" ondrop="drop(event, 21)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile21" id="tile21"> ${tile21} </p>-->
					<img src="${tile21}" onerror="this.style.display='none'" id="p21" width="50" length="50" draggable="true" ondragstart="drag(event21)">
				</div>
				<div id="tile29" ondrop="drop(event, 29)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile29" id="tile29"> ${tile29} </p>-->
					<img src="${tile29}" onerror="this.style.display='none'" id="p29" width="50" length="50" draggable="true" ondragstart="drag(event, 29)">
				</div>
				<div id="tile37" ondrop="drop(event, 37)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile37" id="tile37"> ${tile37} </p>-->
					<img src="${tile37}" onerror="this.style.display='none'" id="p37" width="50" length="50" draggable="true" ondragstart="drag(event, 37)">
				</div>
				<div id="tile45" ondrop="drop(event, 45)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile45" id="tile45"> ${tile45} </p>-->
					<img src="${tile45}" onerror="this.style.display='none'" id="p45" width="50" length="50" draggable="true" ondragstart="drag(event, 45)">
				</div>
				<div id="tile53" ondrop="drop(event, 53)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile53" id="tile53"> ${tile53} </p>-->
					<img src="${tile53}" onerror="this.style.display='none'" id="p53" width="50" length="50" draggable="true" ondragstart="drag(event, 53)">
				</div>
				<div id="tile61" ondrop="drop(event, 61)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile61" id="tile61"> ${tile61} </p>-->
					<img src="${tile61}" onerror="this.style.display='none'" id="p61" width="50" length="50" draggable="true" ondragstart="drag(event, 61)">
				</div>
			</div>
			<div style = "float: left;">
				<div class="boardLabel">
					G
				</div>
				<div id="tile6" ondrop="drop(event, 6)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile6" id="tile6"> ${tile6} </p>-->
					<img src="${tile6}" onerror="this.style.display='none'" id="p6" width="50" length="50" draggable="true" ondragstart="drag(event, 6)">
				</div>
				<div id="tile14" ondrop="drop(event, 14)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile14" id="tile14"> ${tile14} </p>-->
					<img src="${tile14}" onerror="this.style.display='none'" id="p14" width="50" length="50" draggable="true" ondragstart="drag(event, 14)">
				</div>
				<div id="tile22" ondrop="drop(event, 22)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile22" id="tile22"> ${tile22} </p>-->
					<img src="${tile22}" onerror="this.style.display='none'" id="p22" width="50" length="50" draggable="true" ondragstart="drag(event, 22)">
				</div>
				<div id="tile30" ondrop="drop(event, 30)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile30" id="tile30"> ${tile30} </p>-->
					<img src="${tile30}" onerror="this.style.display='none'" id="p30" width="50" length="50" draggable="true" ondragstart="drag(event, 30)">
				</div>
				<div id="tile38" ondrop="drop(event, 38)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile38" id="tile38"> ${tile38} </p>-->
					<img src="${tile38}" onerror="this.style.display='none'" id="p38" width="50" length="50" draggable="true" ondragstart="drag(event, 38)">
				</div>
				<div id="tile46" ondrop="drop(event, 46)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile46" id="tile46"> ${tile46} </p>-->
					<img src="${tile46}" onerror="this.style.display='none'" id="p46" width="50" length="50" draggable="true" ondragstart="drag(event, 46)">
				</div>
				<div id="tile54" ondrop="drop(event, 54)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile54" id="tile54"> ${tile54} </p>-->
					<img src="${tile54}" onerror="this.style.display='none'" id="p54" width="50" length="50" draggable="true" ondragstart="drag(event, 54)">
				</div>
				<div id="tile62" ondrop="drop(event, 62)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile62" id="tile62"> ${tile62} </p>-->
					<img src="${tile62}" onerror="this.style.display='none'" id="p62" width="50" length="50" draggable="true" ondragstart="drag(event, 62)">
				</div>
			</div>
			<div style = "float: left;">
				<div class="boardLabel">
					H
				</div>
				<div id="tile7" ondrop="drop(event, 7)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile7" id="tile7"> ${tile7} </p>-->
					<img src="${tile7}" onerror="this.style.display='none'" id="p7" width="50" length="50" draggable="true" ondragstart="drag(event, 7)">
				</div>
				<div id="tile15" ondrop="drop(event, 15)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile15" id="tile15"> ${tile15} </p>-->
					<img src="${tile15}" onerror="this.style.display='none'" id="p15" width="50" length="50" draggable="true" ondragstart="drag(event, 15)">
				</div>
				<div id="tile23" ondrop="drop(event, 23)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile23" id="tile23"> ${tile23} </p>-->
					<img src="${tile23}" onerror="this.style.display='none'" id="p23" width="50" length="50" draggable="true" ondragstart="drag(event, 23)">
				</div>
				<div id="tile31" ondrop="drop(event, 31)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile31" id="tile31"> ${tile31} </p>-->
					<img src="${tile31}" onerror="this.style.display='none'" id="p31" width="50" length="50" draggable="true" ondragstart="drag(event, 31)">
				</div>
				<div id="tile39" ondrop="drop(event, 39)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile39" id="tile39"> ${tile39} </p>-->
					<img src="${tile39}" onerror="this.style.display='none'" id="p39" width="50" length="50" draggable="true" ondragstart="drag(event, 39)">
				</div>
				<div id="tile47" ondrop="drop(event, 47)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile47" id="tile47"> ${tile47} </p>-->
					<img src="${tile47}" onerror="this.style.display='none'" id="p47" width="50" length="50" draggable="true" ondragstart="drag(event, 47)">
				</div>
				<div id="tile55" ondrop="drop(event, 55)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile55" id="tile55"> ${tile55} </p>-->
					<img src="${tile55}" onerror="this.style.display='none'" id="p55" width="50" length="50" draggable="true" ondragstart="drag(event, 55)">
				</div>
				<div id="tile63" ondrop="drop(event, 63)" ondragover="allowDrop(event)">
					<!--<p style="color: red;" name="tile63" id="tile63"> ${tile63} </p>-->
					<img src="${tile63}" onerror="this.style.display='none'" id="p63" width="50" length="50" draggable="true" ondragstart="drag(event, 63)">
				</div>
			</div>
		</div>

		
		
		<!-- don't really need this anymore, drag and drop pretty much works (maybe good for debugging tho?)
		<div>
			<form id = "moveForm" action="${pageContext.servletContext.contextPath}/game" method="post">
				Selected Piece: 
				<input type="text" name="initpos" value="${initpos}"/>
				Move to: 
				<input type="text" name="finpos" value="${finpos}"/>
				<input type="Submit" name="submitBtn" value="Submit Move"/>
			</form>
		</div>
		
		<div>
			<form action="${pageContext.servletContext.contextPath}/game" method="post">
				<input type="Submit" name="submitBtn" value="Start Game"/>
			</form>
		</div>
		-->
		
		<div><!-- fixes the pieces, since they rely on the servlet to render-->
			<form action="${pageContext.servletContext.contextPath}/game" method="post">
				<input type="Submit" name="submitBtn" value="Fix Pieces"/>
				<input type="hidden" id="gameid2" name="gameid"/>
				<script>
					var tempgame;
					tempgame = sessionStorage.getItem("gameID");
					console.log("the gameid is "+ tempgame);
					gameid2.setAttribute("value", tempgame);
					console.log("the set gameid value is " + gameid2.getAttribute("value"));
				</script>
			</form>
		</div>
		<div style="float: right; width: 40%">
			<ul>
				<li class="chatItem"> User 1: yo what up </li>
				<li class="chatItem"> User 2: how to forfeit </li>
				<li class="chatItem">
					<form action="${pageContext.servletContext.contextPath}/game" method="post">
						<input type="text" name="message" value="${message}"/>
						<input type="hidden" id="gameid3" name="gameid"/>
						<script>
							var tempgame;
							tempgame = sessionStorage.getItem("gameID");
							console.log("the gameid is "+ tempgame);
							gameid3.setAttribute("value", tempgame);
							console.log("the set gameid value is " + gameid3.getAttribute("value"));
						</script>
					</form>
				</li>
			</ul>
		</div>
		<div>
			<a href="http://localhost:8081/chessgame/rulebook">
				<input class="button" type="Submit" name="submitBtn" value="Rulebook"/>
			</a>
		</div>
	</body>
	<!--original drag/drop scripts stolen from w3schools thanks-->
	<script>
	function allowDrop(ev) {
		  if(!ev.target.hasChild){
		  	ev.preventDefault();
		  }
		}
		
		function drag(ev, tile) {
		  console.log(tile);
		  ipos.setAttribute("value", tile);
		  ev.dataTransfer.setData("text", ev.target.id);
		}
		
		function drop(ev, tile) {
			if(true){
			  console.log(tile);
			  fpos.setAttribute("value", tile);
			  ev.preventDefault();
		 	  var data = ev.dataTransfer.getData("text");
			  ev.target.appendChild(document.getElementById(data));
			  //submit.setAttribute("value", "Submit Move");
			  document.getElementById("msub").submit();
		  }
		}
		function setAutoRefresh(){
			var autoref = document.getElementById("semiauto").getAttribute("value");
			if(autoref == "Stop Auto-Refresh"){
				autoref = "Restart Auto-Refresh";
			}
			else{
				autoref = "Stop Auto-Refresh";
			}
		}
	</script>
</html>
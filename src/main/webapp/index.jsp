<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<link rel="shortcut icon" href="/smart-irrigation/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" href="/smart-irrigation/style.css" />
<link rel="stylesheet" href="/smart-irrigation/webjars/font-awesome/css/all.min.css" />
<script src="/smart-irrigation/webjars/sockjs-client/sockjs.min.js"
  type="text/javascript"></script>
<script src="/smart-irrigation/webjars/stomp-websocket/stomp.min.js"
  type="text/javascript"></script>
<script src="/smart-irrigation/templates.js" type="text/javascript"></script>
<script src="/smart-irrigation/webjars/jquery/3.1.0/jquery.min.js"
  type="text/javascript"></script>
<script src="/smart-irrigation/app.js" type="text/javascript"></script>
<script type="text/javascript">
	
	
	function renderZones(zones) {
		$('#zonediv').html(zones.map(Zone).join(''));
	}
	
	function renderZoneStatus(target, zones) {
		$(target).html(ZoneStatus(zones));
	}
</script>
</head>
<body>
  <div class="maincontainer">
    <!-- Tab links -->
    <div class="tab">
      <button id="Status" class="tablinks active" onclick="changeView(event, 'Status')">Status</button>
      <button id="Programs" class="tablinks" onclick="changeView(event, 'Programs')">Programs</button>
      <button id="Zones" class="tablinks" onclick="changeView(event, 'Zones')">Zones</button>
    </div>

    <!-- Tab content 
    <div id="Status" class="tabcontent" style="display: block;">
      <div id="dynamicstatus"></div>
    </div>

    <div id="Programs" class="tabcontent">
      <p>Paris is the capital of France.</p>
    </div>

    <div id="Zones" class="tabcontent">
      <div id="zonestatus"></div>
      <div id="zonediv"></div>
    </div>
 -->
	<div class="Status Zones content">
		<fieldset id="zonefieldset">
		  <legend>Zone status</legend>
		  <div>
		    Active Zone: <span id="activepinid">N/A</span><br/>
		    Name: <span id="activename">N/A</span><br/>
		    Time Left: <span id="timeleft">--:--:--</span>
		  </div>
		  <div id="zonestatuslist"></div>
		</fieldset>
	</div>
	
	<div class="Zones content hidden">
		<div id="zonediv"></div>
		<button class="addButton">
			<i class="fas fa-plus fa-2x"></i>
		</button>
	</div>
	
	<div class="Status content">
		<fieldset id="notificationsFieldset">
		  <legend>Notifications</legend>
		  <div style="width: 200px; height: 200px;">Log placeholder</div>
		</fieldset>
	</div>

  </div>
  <script type="text/javascript">
//   	var programRunning = false;
//   	var zones = [
// 		{pinId: 1, active: false},
// 		{pinId: 2, active: false},
// 		{pinId: 3, active: false},
// 		{pinId: 4, active: true},
// 		{pinId: 5, active: false},
// 		{pinId: 6, active: false},
// 		{pinId: 7, active: false},
// 		{pinId: 8, active: false},
// 		{pinId: 9, active: false},
// 	];
	//renderZones(zones);
// 	renderZoneStatus("#zonestatuslist", zones);

  </script>
</body>
</html>

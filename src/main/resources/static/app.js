var stompClient = null;

var zoneFinish = null;

function connect() {
	var socket = new SockJS('/smart-irrigation/activity');
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function (frame) {
		//setConnected(true);
		console.log('Connected: ' + frame);
		stompClient.subscribe('/active/zone', function (zone) {
			updateZoneStatus(JSON.parse(zone.body));
		});
	});
}

//function disconnect() {
//if (stompClient !== null) {
//stompClient.disconnect();
//}
//setConnected(false);
//console.log("Disconnected");
//}

//function sendName() {
//stompClient.send("/app/zone", {}, JSON.stringify({'name': $("#name").val()}));
//}

function showActiveZone(message) {
	$("#activeZone").text(message);
}


function activate(pinId) {
	$.ajax({
		url: "rest/zone/active/" + pinId,
		type: 'PUT',
		success: function(data) {
		}
	});
}

function updateAll(target, html){
	$(target).each(function(index, element){
		element.innerHTML = html;
	});
}

function updateZoneStatus(activeZone){

	let zone = activeZone.zone;
	let activePinId = null;
	let activeName = null;
	let timeleft = "--:--:--";

	if (zone != null){
		zone.id == -1 ? activePinId = "None" : activePinId = zone.id;
		activeName = zone.name;
	}
	if (activeZone.finishTime != null){
		zoneFinish = Date.parse(activeZone.finishTime);
	}
	else {
		zoneFinish = null;
	}

	updateAll('#activepinid', activePinId);
	updateAll('#activename', activeName);

	$('.activeicon').removeClass('activeicon');

	$('.zoneicon:contains(' + activePinId + ')').addClass('activeicon');

}

function timePad(number) {
	return number.toString().padStart(2, '0');
}

function countdownZoneTime() {

	let now = new Date().getTime();
	let timeleft = "--:--:--";

	if (zoneFinish != null) {
		let distance = zoneFinish - now;

		if (distance >= 0){
			let hours = Math.floor(distance  / (1000 * 60 * 60));
			let minutes = Math.floor((distance % (1000 * 60 * 60)) / (1000 * 60));
			let seconds = Math.floor((distance % (1000 * 60)) / 1000);

			timeleft = timePad(hours) + ":" + timePad(minutes) + ":" + timePad(seconds);
		}
	}

	updateAll('#timeleft', timeleft);
}

function getActive() {

	$.get("rest/zone/active",
			function(data) {
		updateZoneStatus(data);



	});
}

function initZones() {

	$.get("rest/zone",
			function(data) {
		renderZones(data);
		renderZoneStatus("#zonestatuslist", data);
		getActive();
	});
}


function openTab(evt, tabName) {
	// Declare all variables
	var i, tabcontent, tablinks;

	// Get all elements with class="tabcontent" and hide them
	tabcontent = document.getElementsByClassName("tabcontent");
	for (i = 0; i < tabcontent.length; i++) {
		tabcontent[i].style.display = "none";
	}

	// Get all elements with class="tablinks" and remove the class "active"
	tablinks = document.getElementsByClassName("tablinks");
	for (i = 0; i < tablinks.length; i++) {
		tablinks[i].className = tablinks[i].className.replace(" active", "");
	}

	// Show the current tab, and add an "active" class to the button that opened the tab
	document.getElementById(tabName).style.display = "block";
	evt.currentTarget.className += " active";
}

function changeView(event, view) {

	// hide all content
	$('.content').each(function(index, element){
		element.classList.add('hidden')
	});
	
	// Get all elements with class="tablinks" and remove the class "active"
	tablinks = document.getElementsByClassName("tablinks");
	for (i = 0; i < tablinks.length; i++) {
		tablinks[i].className = tablinks[i].className.replace(" active", "");
	}

	// show what we want
	$('.' + view).each(function(index, element){
		element.classList.toggle('hidden')
	});

	// Show the current tab, and add an "active" class to the button that opened the tab
	document.getElementById(view).style.display = "block";
	event.currentTarget.className += " active";
}

function handleSlider(checkbox, pinId) {
	$.post("rest/zone/enable/" + pinId + "/?enabled=" + checkbox.checked,
			function(data) {
		updateZoneStatus(data);



	});
}

initZones();

setInterval(countdownZoneTime, 1000);

connect();
var stompClient = null;


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
//    if (stompClient !== null) {
//        stompClient.disconnect();
//    }
//    setConnected(false);
//    console.log("Disconnected");
//}

//function sendName() {
//    stompClient.send("/app/zone", {}, JSON.stringify({'name': $("#name").val()}));
//}

function showActiveZone(message) {
    $("#activeZone").text(message);
}

function myFunction() {
	$.get("http://localhost:8080/smart-irrigation/rest/demo",
			function(data) {
				$('#demo').html(data);
			});
}
function activate(pinId) {
	$.ajax({
		url: "http://localhost:8080/smart-irrigation/rest/zone/active/" + pinId,
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

function updateZoneStatus(zone){
	let activePinId = null;
	let activeName = null;
	let timeleft = "--:--:--";
	
	if (zone != null){
		zone.pinId == -1 ? activePinId = "None" : activePinId = zone.pinId;
		activeName = zone.name;
		//timeleft = what?
	}

	updateAll('#activepinid', activePinId);
	updateAll('#activename', activeName);
	updateAll('#timeleft', timeleft);
	
	$('.activeicon').removeClass('activeicon');
	
	$('.zoneicon:contains(' + activePinId + ')').addClass('activeicon');
	
}

function getActive() {
	
	$.get("http://localhost:8080/smart-irrigation/rest/zone/active",
			function(data) {
				updateZoneStatus(data);
				
				
				
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
	
	// show what we want
	$('.' + view).each(function(index, element){
		element.classList.toggle('hidden')
	});
}

connect();
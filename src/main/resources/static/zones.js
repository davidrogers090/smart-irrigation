var stompClient = null;


function connect() {
    var socket = new SockJS('/smart-irrigation/activity');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        //setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/active/zone', function (zone) {
        	showActiveZone(JSON.parse(zone.body).name);
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

//$(function () {
//    $("form").on('submit', function (e) {
//        e.preventDefault();
//    });
//    $( "#connect" ).click(function() { connect(); });
//    $( "#disconnect" ).click(function() { disconnect(); });
//    $( "#send" ).click(function() { sendName(); });
//});

function myFunction() {
	//Use "$.get();" in order to perform a GET-Request (you have to take a look in the rest-API-documentation, if you're unsure what you need)
	//The Browser downloads the webpage from the given url, and returns the data.
	$.get("http://localhost:8080/smart-irrigation/rest/demo",
			function(data) {
				//As soon as the browser finished downloading, this function is called.
				$('#demo').html(data);
			});
}
function activate1() {
	//Use "$.get();" in order to perform a GET-Request (you have to take a look in the rest-API-documentation, if you're unsure what you need)
	//The Browser downloads the webpage from the given url, and returns the data.
	$.get("http://localhost:8080/smart-irrigation/rest/activate/1",
			function(data) {

			});
}
function activate2() {
	//Use "$.get();" in order to perform a GET-Request (you have to take a look in the rest-API-documentation, if you're unsure what you need)
	//The Browser downloads the webpage from the given url, and returns the data.
	$.get("http://localhost:8080/smart-irrigation/rest/activate/2",
			function(data) {

			});
}
function getActive() {
	//Use "$.get();" in order to perform a GET-Request (you have to take a look in the rest-API-documentation, if you're unsure what you need)
	//The Browser downloads the webpage from the given url, and returns the data.
	$.get("http://localhost:8080/smart-irrigation/rest/getActiveZone",
			function(data) {
				//As soon as the browser finished downloading, this function is called.
				$('#demo').html(data);
			});
}

connect();
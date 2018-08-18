<html>
<body>
<script src="webjars/jquery/1.9.1/jquery.min.js"></script>
<h2>Hello World!</h2>
<button onclick="myFunction()">Click me</button>
<p id="demo"></p>
<script>
        //Usually, you put script-tags into the head
        function myFunction() {
            //Use "$.get();" in order to perform a GET-Request (you have to take a look in the rest-API-documentation, if you're unsure what you need)
            //The Browser downloads the webpage from the given url, and returns the data.
            $.get( "http://localhost:8080/smart-irrigation/rest/demo", function( data ) {
                 //As soon as the browser finished downloading, this function is called.
                 $('#demo').html(data);
            });
        }
    </script>
</body>
</html>

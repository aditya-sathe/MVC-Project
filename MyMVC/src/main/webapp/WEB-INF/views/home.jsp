<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <script type = "text/javascript" 
         src = "https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        
         <script type = "text/javascript" language = "javascript">
         $(document).ready(function() {
            $("#submit").click(function(event){
               var name = $("#code").val();
               $("#stage").load('/MyMVC/stockquote', {"code":name} , function( response, status, xhr ) {
	       	   		if ( status == "error" ) {
	    		    	var msg = "Sorry but there was an error: ";
	    		    	$( "#stage" ).html( msg + xhr.status + " " + xhr.statusText );
	    		}});
            });
            
            $("#submit1").click(function(event){
            	$.ajax('http://jsonplaceholder.typicode.com/posts/1', {
            		  method: 'GET'
            		}).then(function(data) {
            			document.getElementById("json1").innerHTML = JSON.stringify(data, undefined, 2);
            		});
             });
            
            $("#submit2").click(function(event){
            	$.ajax('/MyMVC/restpost', {
            		  method: 'POST',
            		}).then(function(data) {
            			document.getElementById("json2").innerHTML = JSON.stringify(data, undefined, 2);
            		});
             });
            $("#submit3").click(function(event){
            	$.ajax('/MyMVC/restput', {
            		  method: 'PUT',
            		}).then(function(data) {
            			document.getElementById("json3").innerHTML = "Put Called Successfully";
            		});
            });
            $("#submit4").click(function(event){
            	$.ajax('http://jsonplaceholder.typicode.com/posts/1', {
            		  method: 'DELETE'
            	}).then(function(data) {
            			document.getElementById("json4").innerHTML = "Delete Called Successfully";
            	});
            });
         });
      </script>
      
    </head>
    <body>
        <h2>ISD Code for country SOAP</h2>
        <p/>       
        <p>Enter a country:</p>
        <input id = "code" type = "text"  size = "40" /><br/>
	    ISD Code: <div id = "stage" style = "background-color:yellow;"></div>
	    <input id = "submit" type = "button" value = "Show Result" />
       	
       	<p/>
       	<h2>REST Service</h2>
       	<p>GET Request (posts/1)</p>
       	<pre id="json1"><code></code></pre>
       	<input id = "submit1" type = "button" value = "Show Result" />
       	<p/>
       	<p>POST Request ()</p>
		<pre id="json2"><code></code></pre>
       	<input id = "submit2" type = "button" value = "Show Result" />
       	<p/>
       	<p>Put Request</p>
        <pre id="json3"><code></code></pre>
       	<input id = "submit3" type = "button" value = "Show Result" />
       	<p/>
       	<p>Delete Request</p>
        <pre id="json4"><code></code></pre>
       	<input id = "submit4" type = "button" value = "Show Result" />
       	       	
    </body>
</html>

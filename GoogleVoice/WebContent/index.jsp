<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Voice Search</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    
    <!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
	<link rel="stylesheet" type="text/css" href="circle.css">
	<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
	<%
   		String word = request.getParameter( "stopword" );
   		session.setAttribute( "stopword", word );
	%>
  </head>
  <style>
  #start_button {
    border: 0;
    background-color:transparent;
    padding: 0;
  }
  </style>

  <body>

    <div class="container">
       <div class="row">
    	<div class="col-sm-11">
    	</div>
    	<div class="col-sm-1">
			<a class='btn btn-default' href='setting.jsp'>
  					<span class="glyphicon glyphicon-cog" aria-hidden="true"></span> Set Stopword
			</a>
			<input type="hidden" id="stopword" value= '<%= session.getAttribute("stopword")%>' />
    	</div>
    	</div>
    	</br></br></br></br></br></br></br>
    	<div class ="row">
    	<div class="col-sm-3">
    	</div>
    	<div class="col-sm-5">   		
    	   <img src="logo.png">
    	</div>
    	<div class="col-sm-4">
    	</div>
    	</div>
  		<div class="row">
  			<form method="post" action="circleSuggestion">
    		<div class="col-sm-2">
    		</div>
    		<div class="col-sm-8">
    		<div id="info">
  			<p id="info_start">Click on the microphone icon and begin speaking.</p>
  			<p id="info_speak_now">Speak now.</p>
  			<p id="info_no_speech">No speech was detected. You may need to adjust your
    			<a href="//support.google.com/chrome/bin/answer.py?hl=en&amp;answer=1407892">
     			 microphone settings</a>.</p>
  			<p id="info_no_microphone" style="display:none">
    			No microphone was found. Ensure that a microphone is installed and that
    			<a href="//support.google.com/chrome/bin/answer.py?hl=en&amp;answer=1407892">
    			microphone settings</a> are configured correctly.</p>
  			<p id="info_allow">Click the "Allow" button above to enable your microphone.</p>
  			<p id="info_denied">Permission to use microphone was denied.</p>
  			<p id="info_blocked">Permission to use microphone is blocked. To change,
    			go to chrome://settings/contentExceptions#media-stream</p>
  			<p id="info_upgrade">Web Speech API is not supported by this browser.
     			Upgrade to <a href="//www.google.com/chrome">Chrome</a>
     			version 25 or later.</p>
			</div>
  			<select class="form-control" id="final_span" name = "final_span"></select>
  			<br>
			<button id="search_button" type="submit" class="btn btn-info btn-lg">
      			<span class="glyphicon glyphicon-search"></span> Suggestion
    		</button>

    		</div>
    		<div class="col-sm-2">
    			<p></p>
    		  	<button id="start_button" onclick="startButton(event)" type="button">
    			<img id="start_img" src="mic.gif" alt="Start"></button> 
    		</div>
    		</form>
    		<button id="button" class="btn btn-info btn-lg" onclick="SearchGoogle()">
      			<span class="glyphicon glyphicon-search"></span> Search
    		</button>
    	</div>

    </div>
	<script>
showInfo('info_start');
var create_email = false;
var final_transcript = '';
var recognizing = false;
var ignore_onend;
var start_timestamp;
var stopword = document.getElementById('stopword').value;
if(stopword == "null"){
	stopword = 'go';
}
if (!('webkitSpeechRecognition' in window)) {
  upgrade();
} else {
  start_button.style.display = 'inline-block';
  var recognition = new webkitSpeechRecognition();
  recognition.continuous = true;
  recognition.maxAlternatives = 10;
  recognition.interimResults = true;
  recognition.onstart = function() {
    recognizing = true;
    showInfo('info_speak_now');
    start_img.src = 'mic-animate.gif';
  };
  recognition.onerror = function(event) {
    if (event.error == 'no-speech') {
      start_img.src = 'mic.gif';
      showInfo('info_no_speech');
      ignore_onend = true;
    }
    if (event.error == 'audio-capture') {
      start_img.src = 'mic.gif';
      showInfo('info_no_microphone');
      ignore_onend = true;
    }
    if (event.error == 'not-allowed') {
      if (event.timeStamp - start_timestamp < 100) {
        showInfo('info_blocked');
      } else {
        showInfo('info_denied');
      }
      ignore_onend = true;
    }
  };
  recognition.onend = function() {
    recognizing = false;
    if (ignore_onend) {
      return;
    }
    start_img.src = 'mic.gif';
    if (!final_transcript) {
      showInfo('info_start');
      return;
    }
    showInfo('');
  };
  recognition.onresult = function(event) {
    //var interim_transcript = '';
    for (var i = event.resultIndex; i < event.results.length; ++i) {    	
      if (event.results[i].isFinal) {  
        final_transcript += event.results[i][0].transcript;
        for(var j = 0; j < event.results[i].length; ++j){
        	var text = event.results[i][j].transcript;
        	final_span.options[j] = new Option(text, text);
        }      
      } //else {
        //interim_transcript += event.results[i][0].transcript;
        //for(var j = 0; j < event.results[i].length; ++j){
        	//var text = event.results[i][j].transcript;
        	//final_span.options[j] = new Option(text, text);
        //} 
     // }
    }
    document.getElementById('final_span').value = linebreak(final_transcript);
    if(document.getElementById('final_span').selectedIndex!=-1){
    	document.getElementById('start_button').click();
    }
    //var index = final_transcript.indexOf(stopword);

  };
}
function upgrade() {
  start_button.style.visibility = 'hidden';
  showInfo('info_upgrade');
}
var two_line = /\n\n/g;
var one_line = /\n/g;
function linebreak(s) {
  return s.replace(two_line, '<p></p>').replace(one_line, '<br>');
}
function startButton(event) {
  if (recognizing) {
    recognition.stop();
    return;
  }
  final_span.options.length = 0;
  final_transcript = '';
  recognition.lang = 'en-US';
  recognition.start();
  ignore_onend = false;
  start_img.src = 'mic-slash.gif';
  showInfo('info_allow');
  start_timestamp = event.timeStamp;
}
function showInfo(s) {
  if (s) {
    for (var child = info.firstChild; child; child = child.nextSibling) {
      if (child.style) {
        child.style.display = child.id == s ? 'inline' : 'none';
      }
    }
    info.style.visibility = 'visible';
  } else {
    info.style.visibility = 'hidden';
  }
}
function SearchGoogle(){
    var Searchtxt = document.getElementById("final_span").value;
    window.location = "http://www.google.com/search?q=" + Searchtxt;
}

</script>
  </body>
</html>
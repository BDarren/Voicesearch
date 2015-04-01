<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Settings</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    
    <!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
	<link rel="stylesheet" type="text/css" href="circle.css">
	<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
    </br></br></br></br></br></br></br>
    	<div class="row">
    	<div class="col-sm-4">
    	</div>
		<div class="col-sm-4">
				<FORM METHOD=POST ACTION="index.jsp">
		Stopword: <input type="text" class="form-control" name= stopword placeholder="Type your stopword here...">
		</br>
		<button id="search_button" type="submit" class="btn btn-info">
      		<span class="glyphicon glyphicon-ok"></span> Save
    	</button>
		</FORM>
    	</div>

		<div class="col-sm-4">
    	</div>
		</div>
	</div>
</body>
</html>
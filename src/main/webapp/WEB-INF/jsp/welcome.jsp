<!DOCTYPE html>

<%@ include file="/common/taglib.jsp"%>

<html lang="en">
<head>

<link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />


<link rel="stylesheet" type="image/x-icon" href="${contextPath}/resources/css/main.css" />

</head>
<body>

	<div class="form-group">
		<label for="usr">Email:</label> <input type="text" class="form-control" id="email">
	</div>
	<div class="form-group">
		<label for="usr">Rate:</label> <input type="text" class="form-control" id="rate" value="7.05">
	</div>
	<button onclick="getTest()" class="btn btn-info">Get</button>

	<script src="http://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

<script>
	function getTest() {
		window.location.href = "${contextPath}/get?l="+$("#email").val()+"&r="+$("#rate").val();

	}
</script>

</html>

<!DOCTYPE html>

<%@ include file="/common/taglib.jsp"%>

<html lang="en">
<head>

<link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />


<link rel="stylesheet" type="image/x-icon" href="${contextPath}/resources/css/main.css" />

</head>
<body>

	<c:if test="${!empty rotaList}">

		<table class="table table-striped table-bordered" cellspacing="0" width="100%">
			<thead>
				<tr>
					<th>date</th>
					<th>place</th>
					<th>hours</th>
					<th>break</th>
					<th>payment</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${rotaList}" var="day">
					<tr>
						<td class="col-md-2">${day.date}</td>
						<td class="col-md-2">${day.place}</td>
						<td class="col-md-1">${day.hours} h</td>
						<td class="col-md-1">${day.break} min</td>
						<td class="col-md-2">£${day.payment}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<p>Total: £${total}</p>
	</c:if>


	<script src="http://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script type="text/javascript" src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>

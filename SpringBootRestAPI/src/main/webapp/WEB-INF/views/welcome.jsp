<%@ include file="fragments/header.jspf"%>
<%@ include file="fragments/navbar.jspf"%>

<br>
<br>
<div class="container">
<h4>Click to list details of each Student :</h4>
<br><br>
	<table class="table table-striped">
		<tr>
			<th>Student ID</th>
			<th>Student Name</th>
			<th></th>
		</tr>
		<c:forEach items="${allStudents}" var="student">
			<tr>
				<td>${student.id}</td>
				<td>${student.name}</td>
				<td><a href="/list-details?StudentId=${student.id}"
					class="btn btn-warning">view</a></td>
			</tr>
		</c:forEach>
	</table>
</div>

<%@ include file="fragments/footer.jspf"%>
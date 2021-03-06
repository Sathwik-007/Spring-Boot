<%@ include file="fragments/header.jspf"%>
<%@ include file="fragments/navbar.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<br><br>
<div class="container">

	<table class="table table-striped">
		<tr>
			<th>Couse Id</th>
			<th>Course Name</th>
			<th>Description</th>
			<th>Steps</th>
			<th></th>
			<th></th>
		</tr>

		<c:forEach items="${courses}" var="course">
			<tr>
				<td>${course.id}</td>
				<td>${course.name}</td>
				<td>${course.description}</td>
				<td>${course.steps}</td>
				<td><a href="/update-details?StudentId=${StudentId}&CourseId=${course.id}"
					class="btn btn-warning">update</a></td>
				<td><a href="/delete-details?StudentId=${StudentId}&CourseId=${course.id}"
					class="btn btn-danger">delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<br>
	<a type="button" class="btn btn-success" href="/add-details">Add a
		Course</a>
</div>

<%@ include file="fragments/footer.jspf"%>
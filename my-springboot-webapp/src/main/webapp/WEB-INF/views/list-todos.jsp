<%@ include file="fragments/header.jspf"%>
<%@ include file="fragments/navbar.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<div class="container">
<h1>Hi ${name}! Here are your todos :</h1>

	<table class="table table-striped">
		<tr>
			<th>Description</th>
			<th>TargetDate</th>
			<th>IsDone</th>
			<th></th>
			<th></th>
		</tr>

		<c:forEach items="${todos}" var="todo">
			<tr>
				<td>${todo.desc }</td>
				<td><fmt:formatDate value="${todo.targetDate }" pattern="dd/MM/yyyy"/></td>
				<td>${todo.done }</td>
				<td><a href="/update-todo?id=${todo.id}"
					class="btn btn-warning">Update</a></td>
				<td><a href="/delete-todo?id=${todo.id}" type="button"
					class="btn btn-danger">Delete</a></td>
			</tr>
		</c:forEach>

	</table>
	<a type="button" class="btn btn-success" href="/add-todo">Add a
		todo</a>
</div>
<%@ include file="fragments/footer.jspf"%>
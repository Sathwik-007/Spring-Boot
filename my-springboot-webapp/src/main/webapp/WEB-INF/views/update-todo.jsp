<%@ include file="fragments/header.jspf"%>
<%@ include file="fragments/navbar.jspf"%>
<form:form action="/update-todo" modelAttribute="oldTodo">
	<form:hidden path="id" />
	<fieldset class="form-group">
		<form:label path="desc">Description</form:label>
		<form:input path="desc" class="form-control" required="required" />
	</fieldset>
	<fieldset class="form-group">
		<form:label path="targetDate">TargetDate</form:label>
		<form:input path="targetDate" class="form-control" required="required" />
	</fieldset>
	<button class="btn btn-success">Update</button>
	<form:errors path="desc" cssClass="text-danger"></form:errors>
	<form:errors path="targetDate" cssClass="text-danger"></form:errors>
</form:form>

<%@ include file="fragments/footer.jspf"%>
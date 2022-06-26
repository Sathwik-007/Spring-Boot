<%@ include file="fragments/header.jspf"%>
<%@ include file="fragments/navbar.jspf"%>
<form:form method="post" action="/add-todo" modelAttribute="todo">
	<fieldset class="form-group">
		<form:label path="desc">Description : </form:label>
		<form:input path="desc" type="text" class="form-control"
			required="required" />
		<br>
	</fieldset>
	<fieldset class="form-group">
		<form:label path="targetDate">TargetDate : </form:label>
		<form:input path="targetDate" type="text" class="form-control"
			required="required" />
		<br>
	</fieldset>
	<button class="btn btn-success">Add</button>
	<form:errors path="desc" cssClass="text-warning"></form:errors>
</form:form>


<%@ include file="fragments/footer.jspf"%>
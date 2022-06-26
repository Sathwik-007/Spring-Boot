<%@ include file="fragments/header.jspf"%>
<%@ include file="fragments/navbar.jspf"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<div class="container">
	<form:form action="/update-details" modelAttribute="oldCourseDetails" method="post">
		<fieldset class="form-group">
			<form:label path="id">Course Id</form:label>
			<form:input path="id" class="form-control" required="required" />
		</fieldset>

		<fieldset class="form-group">
			<form:label path="name">Course Name</form:label>
			<form:input path="name" class="form-control" required="required" />
		</fieldset>

		<fieldset class="form-group">
			<form:label path="description">Description</form:label>
			<form:input path="description" class="form-control"
				required="required" />
		</fieldset>


		<fieldset class="form-group">
			<form:label path="steps">Steps</form:label>
			<form:input path="steps" class="form-control" required="required" />
		</fieldset>

		<button class="btn btn-success">Update</button>
		<form:errors path="id" cssClass="text-danger"></form:errors>
		<form:errors path="name" cssClass="text-danger"></form:errors>
		<form:errors path="description" cssClass="text-danger"></form:errors>
		<form:errors path="steps" cssClass="text-danger"></form:errors>
	</form:form>
</div>

<%@ include file="fragments/footer.jspf"%>
<%@include file="../fragments/import.jspf" %>
<html>
<%@include file="../fragments/head.jspf" %>
<body>
<%@include file="../fragments/header.jspf" %>
<div class="container">
    <a class="btn btn-info" href="?command=home"><span
            class="glyphicon glyphicon-home"></span> <fmt:message key="back_home" bundle="${bundle}"/></a>
    <div class="panel-body">
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger"><c:out value="${errorMessage}"/></div>
        </c:if>
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success"><c:out value="${successMessage}"/></div>
        </c:if>
        <h2 class="form-signin-heading"><fmt:message key="create_participant" bundle="${bundle}"/></h2>
        <form class="form-horizontal" action="?command=createParticipant" method="POST">
            <fieldset class="form-group">
                <label for="name"><fmt:message key="name" bundle="${bundle}"/></label>
                <input class="form-control" id="name" name="name"
                       pattern="[A-Z]?[a-z ]+)|([А-Я]?[а-я ]+" maxlength="20"
                       title="<fmt:message key="expected_letters" bundle="${bundle}"/> 20"
                       placeholder="<fmt:message key="name" bundle="${bundle}"/>" required
                       value="<c:out value='${participant.name}'/>">
            </fieldset>
            <fieldset class="form-group">
                <label for="age"><fmt:message key="age" bundle="${bundle}"/></label>
                <input class="form-control" id="age" name="age"
                       pattern="[0-9]{1,3}" maxlength="3"
                       title="<fmt:message key="expected_digits" bundle="${bundle}"/> 3"
                       placeholder="<fmt:message key="age" bundle="${bundle}"/>" required
                       value="<c:out value='${participant.age}'/>">
            </fieldset>
            <fieldset class="form-group">
                <label for="age"><fmt:message key="weight" bundle="${bundle}"/></label>
                <input class="form-control" id="weight" name="weight"
                       pattern="[0-9]{1,3}" maxlength="3"
                       title="<fmt:message key="expected_digits" bundle="${bundle}"/> 3"
                       placeholder="<fmt:message key="weight" bundle="${bundle}"/>" required
                       value="<c:out value='${participant.weight}'/>">
            </fieldset>
            <fieldset class="form-group">
                <label for="trainer"><fmt:message key="trainer" bundle="${bundle}"/></label>
                <input class="form-control" id="trainer" name="trainer"
                       pattern="[A-Z]?[a-z ]+)|([А-Я]?[а-я ]+" maxlength="20"
                       title="<fmt:message key="expected_letters" bundle="${bundle}"/> = 20"
                       placeholder="<fmt:message key="trainer" bundle="${bundle}"/>" required
                       value="<c:out value='${participant.trainer}'/>">
            </fieldset>
            <fieldset class="form-group">
                <label for="jockey"><fmt:message key="jockey" bundle="${bundle}"/></label>
                <input class="form-control" id="jockey" name="jockey"
                       pattern="[A-Z]?[a-z ]+)|([А-Я]?[а-я ]+" maxlength="20"
                       title="<fmt:message key="expected_letters" bundle="${bundle}"/> 20"
                       placeholder="<fmt:message key="jockey" bundle="${bundle}"/>" required
                       value="<c:out value='${participant.jockey}'/>">
            </fieldset>
            <fieldset class="form-group">
                <button class="btn btn-lg btn-primary btn-block"><fmt:message key="save" bundle="${bundle}"/></button>
            </fieldset>
        </form>
    </div>
</div>
<%@include file="../fragments/footer.jspf" %>
</body>
</html>
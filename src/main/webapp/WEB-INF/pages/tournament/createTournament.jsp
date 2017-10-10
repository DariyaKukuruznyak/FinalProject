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
            <div class="alert alert-danger">${errorMessage}</div>
        </c:if>
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success">${successMessage}</div>
        </c:if>
        <h2 class="form-signin-heading"><fmt:message key="create_tournament" bundle="${bundle}"/></h2>
        <form class="form-horizontal" action="?command=createTournament" method="POST">
            <fieldset class="form-group">
                <label for="name"><fmt:message key="name" bundle="${bundle}"/></label>
                <input class="form-control" id="name" name="name"
                       pattern="[A-Z]?[a-z ]+)|([А-Я]?[а-я ]+" maxlength="20"
                       title="<fmt:message key="expected_letters" bundle="${bundle}"/> 20"
                       placeholder="<fmt:message key="name" bundle="${bundle}"/>" required
                       value="${tournament.name}">
            </fieldset>
            <fieldset class="form-group">
                <label for="country"><fmt:message key="country" bundle="${bundle}"/></label>
                <input class="form-control" id="country" name="country"
                       pattern="[A-Z]?[a-z ]+)|([А-Я]?[а-я ]+" maxlength="20"
                       title="<fmt:message key="expected_letters" bundle="${bundle}"/> 20"
                       placeholder="<fmt:message key="country" bundle="${bundle}"/>" required
                       value="${tournament.country}">
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
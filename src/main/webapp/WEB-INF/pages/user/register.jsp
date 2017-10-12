<%@include file="../fragments/import.jspf" %>
<html>
<%@include file="../fragments/head.jspf" %>
<body>
<%@include file="../fragments/i18n.jspf" %>
<div class="container">
    <a class="btn btn-info" href="?command=home"><span
            class="glyphicon glyphicon-home"></span> <fmt:message key="back_home" bundle="${bundle}"/></a>
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger"><c:out value='${errorMessage}'/></div>
    </c:if>
    <form class="form-signin" action="?command=join" method="post">
        <h2 class="form-signin-heading"><fmt:message key="registration" bundle="${bundle}"/></h2>
        <div class="form-group row">
            <label for="first-name" class="col-sm-4 col-form-label"><fmt:message key="first_name" bundle="${bundle}"/></label>
            <div class="col-sm-8">
                <input name="firstName" id="first-name" class="form-control" placeholder="<fmt:message key="first_name" bundle="${bundle}"/>" required
                       autofocus
                       pattern="[A-Z]?[a-z]+)|([А-Я]?[а-я]+" maxlength="20"
                       title="<fmt:message key="expected_letters" bundle="${bundle}"/> 20">
            </div>
        </div>
        <div class="form-group row">
            <label for="last_name" class="col-sm-4 col-form-label"><fmt:message key="last_name" bundle="${bundle}"/></label>
            <div class="col-sm-8">
                <input name="lastName" id="last_name" class="form-control" placeholder="<fmt:message key="last_name" bundle="${bundle}"/>" required
                       pattern="([A-Z]?[a-z]+)(-[A-Z]?[a-z]+)*)|(([А-Я]?[а-я]+)(-[А-Я]?[а-я]+)*" maxlength="50"
                       title="<fmt:message key="expected_letters" bundle="${bundle}"/> 50">
            </div>
        </div>
        <div class="form-group row">
            <label for="login" class="col-sm-4 col-form-label"><fmt:message key="user_login" bundle="${bundle}"/></label>
            <div class="col-sm-8">
                <input name="login" id="login" class="form-control" placeholder="<fmt:message key="user_login" bundle="${bundle}"/>" required pattern=".{4,20}"
                       maxlength="20"
                       title="<fmt:message key="expected_symbols4_20" bundle="${bundle}"/>">
            </div>
        </div>
        <div class="form-group row">
            <label for="email" class="col-sm-4 col-form-label"><fmt:message key="user_email" bundle="${bundle}"/></label>
            <div class="col-sm-8">
                <input name="email" id="email" class="form-control" placeholder="<fmt:message key="user_email" bundle="${bundle}"/>" required
                       pattern=".+@.+"
                       maxlength="50"
                       title="<fmt:message key="incorrect_format" bundle="${bundle}"/>">
            </div>
        </div>
        <div class="form-group row">
            <label for="password" class="col-sm-4 col-form-label"><fmt:message key="user_password" bundle="${bundle}"/></label>
            <div class="col-sm-8">
                <input type="password" name="password" id="password" class="form-control" placeholder="<fmt:message key="user_password" bundle="${bundle}"/>"
                       required
                       pattern="(.{4,20})"
                       maxlength="20" title="<fmt:message key="expected_symbols4_20" bundle="${bundle}"/>">
            </div>
        </div>
        <div class="form-group row">
            <label for="confirm-password" class="col-sm-4 col-form-label"><fmt:message key="confirm_password" bundle="${bundle}"/></label>
            <div class="col-sm-8">
                <input type="password" name="confirmPassword" id="confirm-password" class="form-control"
                       placeholder="<fmt:message key="confirm_password" bundle="${bundle}"/>"
                       required
                       pattern="(.{4,20})" maxlength="20" title="<fmt:message key="expected_symbols4_20" bundle="${bundle}"/>">
            </div>
        </div>
        <input type="checkbox" id="checkbox" required>
        <label for="checkbox"><fmt:message key="terms_of_use" bundle="${bundle}"/></label>
        <button id="register-form-submit" class="btn btn-lg btn-primary btn-block"><fmt:message key="join" bundle="${bundle}"/></button>
    </form>
</div>
<%@include file="../fragments/footer.jspf" %>
</body>
</html>
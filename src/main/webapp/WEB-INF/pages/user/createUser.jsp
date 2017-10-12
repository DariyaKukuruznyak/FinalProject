<%@include file="../fragments/import.jspf" %>
<html>
<%@include file="../fragments/head.jspf" %>
<body>
<%@include file="../fragments/header.jspf" %>
<div class="container">
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger"><c:out value='${errorMessage}'/></div>
    </c:if>
    <c:if test="${not empty successMessage}">
        <div class="alert alert-success"><c:out value='${successMessage}'/></div>
    </c:if>
    <form class="form-signin" action="?command=createUser" method="post">
        <h2 class="form-signin-heading"><fmt:message key="create_user" bundle="${bundle}"/></h2>
        <div class="form-group row">
            <label for="userRole" class="col-sm-4 col-form-label"><fmt:message key="role" bundle="${bundle}"/></label>
            <div class="col-sm-8">
                <select class="form-control" id="userRole" name="userRole" size="1">
                    <option><c:out value='${adminRole}'/></option>
                    <option><c:out value='${riskControllerRole}'/></option>
                    <option selected><c:out value='${bookmakerRole}'/></option>
                </select>
            </div>
        </div>
        <div class="form-group row">
            <label for="first-name" class="col-sm-4 col-form-label"><fmt:message key="first_name"
                                                                                 bundle="${bundle}"/></label>
            <div class="col-sm-8">
                <input name="firstName" id="first-name" class="form-control"
                       placeholder="<fmt:message key="first_name" bundle="${bundle}"/>" required autofocus
                       pattern="[A-Z]?[a-z]+)|([А-Я]?[а-я]+" maxlength="20"
                       title="<fmt:message key="expected_letters" bundle="${bundle}"/> 20">
            </div>
        </div>
        <div class="form-group row">
            <label for="last_name" class="col-sm-4 col-form-label"><fmt:message key="last_name"
                                                                                bundle="${bundle}"/></label>
            <div class="col-sm-8">
                <input name="lastName" id="last_name" class="form-control"
                       placeholder="<fmt:message key="last_name" bundle="${bundle}"/>" required
                       pattern="([A-Z]?[a-z]+)(-[A-Z]?[a-z]+)*)|(([А-Я]?[а-я]+)(-[А-Я]?[а-я]+)*" maxlength="50"
                       title="<fmt:message key="expected_letters" bundle="${bundle}"/> 50">
            </div>
        </div>
        <div class="form-group row">
            <label for="login" class="col-sm-4 col-form-label"><fmt:message key="user_login"
                                                                            bundle="${bundle}"/></label>
            <div class="col-sm-8">
                <input name="login" id="login" class="form-control"
                       placeholder="<fmt:message key="user_login" bundle="${bundle}"/>" required pattern=".{4,20}"
                       maxlength="20"
                       title="<fmt:message key="expected_symbols4_20" bundle="${bundle}"/>">
            </div>
        </div>
        <div class="form-group row">
            <label for="email" class="col-sm-4 col-form-label"><fmt:message key="user_email"
                                                                            bundle="${bundle}"/></label>
            <div class="col-sm-8">
                <input name="email" id="email" class="form-control"
                       placeholder="<fmt:message key="user_email" bundle="${bundle}"/>" required
                       pattern=".+@.+" maxlength="50"
                       title="<fmt:message key="incorrect_format" bundle="${bundle}"/>">
            </div>
        </div>
        <div class="form-group row">
            <label for="password" class="col-sm-4 col-form-label"><fmt:message key="user_password"
                                                                               bundle="${bundle}"/></label>
            <div class="col-sm-8">
                <input name="password" id="password" class="form-control" value="1111">
            </div>
        </div>
        <button id="register-form-submit" class="btn btn-lg btn-primary btn-block"><fmt:message key="create_user"
                                                                                                bundle="${bundle}"/></button>
    </form>
</div>
<%@include file="../fragments/footer.jspf" %>
</body>
</html>
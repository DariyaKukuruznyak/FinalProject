<%@include file="../fragments/import.jspf" %>
<html>
<%@include file="../fragments/head.jspf" %>
<body>
<%@include file="../fragments/i18n.jspf" %>
<div class="container">
    <a class="btn btn-info" href="?command=home"><span
            class="glyphicon glyphicon-home"></span> <fmt:message key="back_home" bundle="${bundle}"/></a>
    <div class="form-signin">
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger">${errorMessage}</div>
        </c:if>
        <form class="form-horizontal" action="?command=signIn" method="post">
            <h2 class="form-signin-heading"><fmt:message key="sign_in" bundle="${bundle}"/></h2>
            <div class="form-group row">
                <label for="login" class="col-sm-2 col-form-label"><fmt:message key="user_login"
                                                                                bundle="${bundle}"/></label>
                <div class="col-sm-10">
                    <span class="fontawesome-user">
                    <input name="login" id="login" class="form-control"
                           placeholder="<fmt:message key="user_login" bundle="${bundle}"/>" required autofocus
                           pattern="(.{4,20})"
                           maxlength="20" title="<fmt:message key="expected_symbols4_20" bundle="${bundle}"/>"></span>
                </div>
            </div>
            <div class="form-group row">
                <label for="password" class="col-sm-2 col-form-label"><fmt:message key="user_password"
                                                                                   bundle="${bundle}"/></label>
                <div class="col-sm-10">
                    <span class="fontawesome-lock"></span>
                    <input type="password" name="password" id="password" class="form-control"
                           placeholder="<fmt:message key="user_password" bundle="${bundle}"/>"
                           required
                           pattern="(.{4,20})" maxlength="20"
                           title="<fmt:message key="expected_symbols4_20" bundle="${bundle}"/>">
                </div>
            </div>
            <button class="btn btn-lg btn-primary btn-block"><fmt:message key="sign_in" bundle="${bundle}"/></button>
            <c:if test="${not empty errorMessage}"><a href="?command=register"><fmt:message key="join_now"
                                                                                            bundle="${bundle}"/></a></c:if>

        </form>
    </div>
</div>
<%@include file="../fragments/footer.jspf" %>
</body>
</html>
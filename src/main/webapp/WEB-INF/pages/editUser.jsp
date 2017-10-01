<%@include file="fragments/import.jspf" %>
<html>
<%@include file="fragments/head.jspf" %>
<body>
<%@include file="fragments/header.jspf" %>
<div class="container"><a class="btn btn-info" href="?command=home"><span
        class="glyphicon glyphicon-home"></span> Back home</a>
    <h2 class="form-signin-heading">Profile</h2>
    <div class="panel-body">
        <c:if test="${errorMessage!=''}">
            <div class="alert alert-danger">${errorMessage}</div>
        </c:if>
        <c:if test="${successMessage!=''}">
            <div class="alert alert-success">${successMessage}</div>
        </c:if>
        <form class="form-horizontal" action="?command=editUser&id=${user.id}" method="POST">
            <c:if test="${user.userRole==adminRole}">
                <fieldset class="form-group">
                    <label for="userRole">Role</label>
                    <select class="form-control" id="userRole" name="userRole" size="1" value="${user.userRole}">
                        <option>${adminRole}</option>
                        <option>${riskControllerRole}</option>
                        <option>${bookmakerRole}</option>
                        <option>${clientRole}</option>
                    </select>
                </fieldset>
            </c:if>
            <fieldset class="form-group">
                <label for="firstName">First name</label>
                <input class="form-control" id="firstName" name="firstName" value="${user.firstName}"
                       required>
            </fieldset>
            <fieldset class="form-group">
                <label for="lastName">Last name</label>
                <input id="lastName" class="form-control" name="lastName"
                       value="${user.lastName}" required>
            </fieldset>
            <fieldset class="form-group">
                <label for="login">Login</label>
                <input class="form-control" id="login" name="login"
                       value="${user.login}" disabled>
            </fieldset>
            <fieldset class="form-group">
                <label for="email">Email</label>
                <input class="form-control" id="email" name="email"
                       value="${user.email}" required>
            </fieldset>
            <%--<fieldset class="form-group">--%>
            <%--<label for="dateOfRegistration">Date of registration</label>--%>
            <%--<input type="date" class="form-control" id="dateOfRegistration" name="dateOfRegistration"--%>
            <%--value="${user.dateOfRegistration}" disabled>--%>
            <%--</fieldset>--%>
            <fieldset class="form-group">
                <button class="btn btn-lg btn-primary btn-block" type="submit">Save</button>
            </fieldset>
        </form>
    </div>
</div>
<%@include file="fragments/footer.jspf" %>
</body>
</html>
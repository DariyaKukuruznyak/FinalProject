<%@include file="fragments/import.jspf" %>
<html>
<%@include file="fragments/head.jspf" %>
<body>
<%@include file="fragments/header.jspf" %>
<div class="container">
    <a class="btn btn-info" href="?command=home"><span
            class="glyphicon glyphicon-home"></span> Back home</a>
    <h2 class="form-signin-heading">Profile</h2>
    <div class="panel-body">
        <c:if test="${errorMessage!=''}">
            <div class="alert alert-danger">${errorMessage}</div>
        </c:if>
        <c:if test="${successMessage!=''}">
            <div class="alert alert-success">${successMessage}</div>
        </c:if>
        <form class="form-horizontal" action="?command=editUser&id=${editedUser.id}" method="POST">
             <fieldset class="form-group">
                <label for="firstName">First name</label>
                <input class="form-control" id="firstName" name="firstName" value="${editedUser.firstName}"
                       pattern="[A-Z]?[a-z]+)|([А-Я]?[а-я]+" maxlength="20"
                       title="Expected only letters. Max length = 20" placeholder="First Name"
                       required>
            </fieldset>
            <fieldset class="form-group">
                <label for="lastName">Last name</label>
                <input id="lastName" class="form-control" name="lastName"
                       value="${editedUser.lastName}" placeholder="Last Name" required
                       pattern="([A-Z]?[a-z]+)(-[A-Z]?[a-z]+)*)|(([А-Я]?[а-я]+)(-[А-Я]?[а-я]+)*" maxlength="50"
                       title="Expected only letters. Max length = 50">
            </fieldset>
            <fieldset class="form-group">
                <label for="login">Login</label>
                <input class="form-control" id="login" name="login"
                       value="${editedUser.login}" disabled>
            </fieldset>
            <fieldset class="form-group">
                <label for="email">Email</label>
                <input class="form-control" id="email" name="email"
                       value="${editedUser.email}" placeholder="Email" required
                       pattern=".+@.+"
                       maxlength="50"
                       title="Incorrect format">
            </fieldset>
            <fieldset class="form-group">
                <label for="dateOfRegistration">Date of registration</label>
                <input class="form-control" id="dateOfRegistration" name="dateOfRegistration"
                       value="${editedUser.dateOfRegistration}" disabled>
            </fieldset>
            <c:if test="${editedUser.userRole==clientRole}">
            <fieldset class="form-group">
                <a class="btn btn-info" href="?command=showWallet"> Show wallet</a>
            </fieldset>
            </c:if>
            <fieldset class="form-group">
                <button class="btn btn-lg btn-primary btn-block" type="submit">Save</button>
            </fieldset>
        </form>
    </div>
</div>
<%@include file="fragments/footer.jspf" %>
</body>
</html>
<%@include file="../fragments/import.jspf" %>
<html>
<%@include file="../fragments/head.jspf" %>
<body>
<%@include file="../fragments/header.jspf" %>
<div class="container">
    <c:if test="${errorMessage!=''}">
        <div class="alert alert-danger">${errorMessage}</div>
    </c:if>
    <c:if test="${successMessage!=''}">
        <div class="alert alert-success">${successMessage}</div>
    </c:if>
    <form class="form-signin" action="?command=createUser" method="post">
        <h2 class="form-signin-heading">Add new user</h2>
        <div class="form-group">
            <label for="userRole">Role</label>
            <div class="col-sm-8">
                <select class="form-control" id="userRole" name="userRole" size="1">
                    <option>${adminRole}</option>
                    <option>${riskControllerRole}</option>
                    <option selected>${bookmakerRole}</option>
                </select>
            </div>
        </div>
        <div class="form-group row">
            <label for="first-name" class="col-sm-4 col-form-label">First Name</label>
            <div class="col-sm-8">
                <input name="firstName" id="first-name" class="form-control" placeholder="First Name" required
                       autofocus
                       pattern="[A-Z]?[a-z]+)|([А-Я]?[а-я]+" maxlength="20"
                       title="Expected only letters. Max length = 20">
            </div>
        </div>
        <div class="form-group row">
            <label for="last_name" class="col-sm-4 col-form-label">Last Name</label>
            <div class="col-sm-8">
                <input name="lastName" id="last_name" class="form-control" placeholder="Last Name" required
                       pattern="([A-Z]?[a-z]+)(-[A-Z]?[a-z]+)*)|(([А-Я]?[а-я]+)(-[А-Я]?[а-я]+)*" maxlength="50"
                       title="Expected only letters. Max length = 50">
            </div>
        </div>
        <div class="form-group row">
            <label for="login" class="col-sm-4 col-form-label">Login</label>
            <div class="col-sm-8">
                <input name="login" id="login" class="form-control" placeholder="Login" required pattern=".{4,20}"
                       maxlength="20"
                       title="Expected from 4 to 20 symbols">
            </div>
        </div>
        <div class="form-group row">
            <label for="email" class="col-sm-4 col-form-label">Email</label>
            <div class="col-sm-8">
                <input name="email" id="email" class="form-control" placeholder="Email" required
                       pattern=".+@.+"
                       maxlength="50"
                       title="Incorrect format. Expected email in format 'email@domain'. Max length = 50">
            </div>
        </div>
        <div class="form-group row">
            <label for="password" class="col-sm-4 col-form-label">Password</label>
            <div class="col-sm-8">
                <input name="password" id="password" class="form-control" value="1111">
            </div>
        </div>
        <button id="register-form-submit" class="btn btn-lg btn-primary btn-block">Create user</button>
    </form>
</div>
<%@include file="../fragments/footer.jspf" %>
</body>
</html>
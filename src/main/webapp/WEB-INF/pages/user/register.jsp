<%@include file="../fragments/import.jspf" %>
<html>
<%@include file="../fragments/head.jspf" %>
<body>
<%@include file="../fragments/headerForForms.jspf" %>
<div class="container">
    <a class="btn btn-info" href="?command=home"><span
            class="glyphicon glyphicon-home"></span> Back home</a>
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger">${errorMessage}</div>
    </c:if>
    <form class="form-signin" action="?command=join" method="post">
        <h2 class="form-signin-heading">Register</h2>
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
                <input type="password" name="password" id="password" class="form-control" placeholder="Password"
                       required
                       pattern="(.{4,20})"
                       maxlength="20" title="Expected from 4 to 20 symbols">
            </div>
        </div>
        <div class="form-group row">
            <label for="confirm-password" class="col-sm-4 col-form-label">Confirm password</label>
            <div class="col-sm-8">
                <input type="password" name="confirmPassword" id="confirm-password" class="form-control"
                       placeholder="Confirm password"
                       required
                       pattern="(.{4,20})" maxlength="20" title="Expected from 4 to 20 symbols">
            </div>
        </div>
        <input type="checkbox" id="checkbox" required> <label for="checkbox"> I am at least 18 years of age and I
        have
        read, accept and agree to the
        Terms and
        Conditions, Rules, Privacy Policy, Cookie Policy and policies relating to age verification and KYC
        (Know
        Your Customer).
    </label>
        <button type="submit" id="register-form-submit" class="btn btn-lg btn-primary btn-block">Join Now</button>
    </form>
</div>
<%@include file="../fragments/footer.jspf" %>
</body>
</html>
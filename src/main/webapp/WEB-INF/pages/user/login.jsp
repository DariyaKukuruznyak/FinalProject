<%@include file="../fragments/import.jspf" %>
<html>
<%@include file="../fragments/head.jspf" %>
<body>
<%@include file="../fragments/headerForForms.jspf" %>
<div class="container">
    <a class="btn btn-info" href="?command=home"><span
            class="glyphicon glyphicon-home"></span> Back home</a>
    <div class="form-signin">
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger">${errorMessage}</div>
        </c:if>
        <form class="form-horizontal" action="?command=signIn" method="post">
            <h2 class="form-signin-heading">Sign in</h2>
            <div class="form-group row">
                <label for="login" class="col-sm-2 col-form-label">Login</label>
                <div class="col-sm-10">
                    <span class="fontawesome-user">
                    <input name="login" id="login" class="form-control" placeholder="Login" required autofocus
                           pattern="(.{4,20})"
                           maxlength="20" title="Expected from 4 to 20 symbols"></span>
                </div>
            </div>
            <div class="form-group row">
                <label for="password" class="col-sm-2 col-form-label">Password</label>
                <div class="col-sm-10">
                    <span class="fontawesome-lock"></span>
                    <input type="password" name="password" id="password" class="form-control" placeholder="Password"
                           required
                           pattern="(.{4,20})" maxlength="20" title="Expected from 4 to 20 symbols">
                </div>
            </div>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
            <c:if test="${not empty errorMessage}"><a href="?command=register">Join us now now</a></c:if>

        </form>
    </div>
</div>
<%@include file="../fragments/footer.jspf" %>
</body>
</html>
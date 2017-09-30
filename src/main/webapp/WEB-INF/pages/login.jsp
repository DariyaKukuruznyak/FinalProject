<div class="jumbotron text-center">
    <form class="form-signin" action="/pages/user.jsp">
        <h2 class="form-signin-heading">Sign in</h2>
        <div class="form-group row">
            <label for="username" class="col-sm-2 col-form-label">Username</label>
            <div class="col-sm-10">
                <input id="username" class="form-control" placeholder="Username" required autofocus pattern="(.{5,20})"
                       maxlength="20" title="Expected more than 4 symbols">
            </div>
        </div>
        <div class="form-group row">
            <label for="password" class="col-sm-2 col-form-label">Password</label>
            <div class="col-sm-10">
                <input type="password" id="password" class="form-control" placeholder="Password" required
                       pattern="(.{5,20})" maxlength="20" title="Expected more than 4 symbols">
            </div>
        </div>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    </form>
</div>

<div class="jumbotron text-center">
    <form class="form-signin" action="#">
        <h2 class="form-signin-heading">Register</h2>
        <div class="form-group row">
            <label for="first-participant" class="col-sm-4 col-form-label">First Name</label>
            <div class="col-sm-8">
                <input id="first-participant" class="form-control" placeholder="First Name" required autofocus
                       pattern="([A-Z]?[a-z]+)|([А-Я]?[а-я]+)" maxlength="20">
            </div>
        </div>
        <div class="form-group row">
            <label for="last_name" class="col-sm-4 col-form-label">Last Name</label>
            <div class="col-sm-8">
                <input id="last_name" class="form-control" placeholder="Last Name" required
                       pattern="(([A-Z]?[a-z]+)(-[A-Z]?[a-z]+)*)|(([А-Я]?[а-я]+)(-[А-Я]?[а-я]+)*)" maxlength="50">
            </div>
        </div>
        <div class="form-group row">
            <label for="login" class="col-sm-4 col-form-label">Login</label>
            <div class="col-sm-8">
                <input id="login" class="form-control" placeholder="Login" required pattern="(.{5,20})" maxlength="20"
                       title="Expected more than 4 symbols">
            </div>
        </div>
        <div class="form-group row">
            <label for="email" class="col-sm-4 col-form-label">Email</label>
            <div class="col-sm-8">
                <input id="email" class="form-control" placeholder="Email" required pattern=".+@[a-z]+(\.[a-z])+" maxlength="50"
                       title="Incorrect format">
            </div>
        </div>
        <div class="form-group row">
            <label for="password" class="col-sm-4 col-form-label">Password</label>
            <div class="col-sm-8">
                <input type="password" id="password" class="form-control" placeholder="Password" required
                       pattern="(.{5,20})"
                       maxlength="20" title="Expected more than 4 symbols">
            </div>
        </div>
        <div class="form-group row">
            <label for="confirm-password" class="col-sm-4 col-form-label">Confirm password</label>
            <div class="col-sm-8">
                <input type="password" id="confirm-password" class="form-control" placeholder="Confirm password"
                       required
                       pattern="(.{5,20})" maxlength="20" title="Expected more than 4 symbols">
            </div>
        </div>
        <div class="form-group row">
            <label for="security-number" class="col-sm-4 col-form-label">Security Number</label>
            <div class="col-sm-8">
                <input type="password" id="security-number" class="form-control" placeholder="Security Number" required
                       pattern="[0-9]{4}" maxlength="4" title="4 digits  are expected">
            </div>
        </div>
        <div class="form-group row">
            <label for="confirm-security-number" class="col-sm-4 col-form-label">Confirm Security Number</label>
            <div class="col-sm-8">
                <input type="password" id="confirm-security-number" class="form-control"
                       placeholder="Confirm Security Number" required pattern="[0-9]{4}" maxlength="4"
                       title="4 digits  are expected">
            </div>
        </div>
        <input type="checkbox" id="checkbox" required> <label for="checkbox"> I am at least 18 years of age and I have
        read, accept and agree to the
        Terms and
        Conditions, Rules, Privacy Policy, Cookie Policy and policies relating to age verification and KYC
        (Know
        Your Customer).
    </label>
        <button type="submit" id="register-form-submit" class="btn btn-lg btn-primary btn-block">Join Now</button>
    </form>
</div>
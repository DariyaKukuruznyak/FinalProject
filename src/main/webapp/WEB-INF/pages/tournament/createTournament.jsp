<%@include file="../fragments/import.jspf" %>
<html>
<%@include file="../fragments/head.jspf" %>
<body>
<%@include file="../fragments/header.jspf" %>
<div class="container">
    <a class="btn btn-info" href="?command=home"><span
            class="glyphicon glyphicon-home"></span> Back home</a>
    <div class="panel-body">
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger">${errorMessage}</div>
        </c:if>
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success">${successMessage}</div>
        </c:if>
        <h2 class="form-signin-heading">Create new tournament</h2>
        <form class="form-horizontal" action="?command=createTournament" method="POST">
            <fieldset class="form-group">
                <label for="name">Name</label>
                <input class="form-control" id="name" name="name"
                       pattern="[A-Z]?[a-z ]+)|([А-Я]?[а-я ]+" maxlength="20"
                       title="Expected only letters. Max length = 20" placeholder="Name" required
                       value="${tournament.name}">
            </fieldset>
            <fieldset class="form-group">
                <label for="country">Country</label>
                <input class="form-control" id="country" name="country"
                       pattern="[A-Z]?[a-z ]+)|([А-Я]?[а-я ]+" maxlength="20"
                       title="Expected only letters. Max length = 20" placeholder="Country" required
                       value="${tournament.country}">
            </fieldset>
            <fieldset class="form-group">
                <button class="btn btn-lg btn-primary btn-block" type="submit">Save</button>
            </fieldset>
        </form>
    </div>
</div>
<%@include file="../fragments/footer.jspf" %>
</body>
</html>
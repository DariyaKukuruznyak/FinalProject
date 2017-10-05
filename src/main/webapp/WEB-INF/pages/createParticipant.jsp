<%@include file="fragments/import.jspf" %>
<html>
<%@include file="fragments/head.jspf" %>
<body>
<%@include file="fragments/header.jspf" %>
<div class="container">
    <a class="btn btn-info" href="?command=home"><span
            class="glyphicon glyphicon-home"></span> Back home</a>
    <div class="panel-body">
        <c:if test="${errorMessage!=''}">
            <div class="alert alert-danger">${errorMessage}</div>
        </c:if>
        <c:if test="${successMessage!=''}">
            <div class="alert alert-success">${successMessage}</div>
        </c:if>
        <h2 class="form-signin-heading">Create new participant</h2>
        <form class="form-horizontal" action="?command=createParticipant" method="POST">
            <fieldset class="form-group">
                <label for="name">Name</label>
                <input class="form-control" id="name" name="name"
                       pattern="[A-Z]?[a-z ]+)|([А-Я]?[а-я ]+" maxlength="20"
                       title="Expected only letters. Max length = 20" placeholder="Name" required
                       value="${participant.name}">
            </fieldset>
            <fieldset class="form-group">
                <label for="age">Age</label>
                <input class="form-control" id="age" name="age"
                       pattern="[0-9]{1,3}" maxlength="3"
                       title="Expected only digits. Max length = 3" placeholder="Age" required
                       value="${participant.age}">
            </fieldset>
            <fieldset class="form-group">
                <label for="age">Weight</label>
                <input class="form-control" id="weight" name="weight"
                       pattern="[0-9]{1,3}" maxlength="3"
                       title="Expected only digits. Max length = 3" placeholder="Weight" required
                       value="${participant.weight}">
            </fieldset>
            <fieldset class="form-group">
                <label for="trainer">Trainer</label>
                <input class="form-control" id="trainer" name="trainer"
                       pattern="[A-Z]?[a-z ]+)|([А-Я]?[а-я ]+" maxlength="20"
                       title="Expected only letters. Max length = 20" placeholder="Trainer" required
                       value="${participant.trainer}">
            </fieldset>
            <fieldset class="form-group">
                <label for="jockey">Jockey</label>
                <input class="form-control" id="jockey" name="jockey"
                       pattern="[A-Z]?[a-z ]+)|([А-Я]?[а-я ]+" maxlength="20"
                       title="Expected only letters. Max length = 20" placeholder="Jockey" required
                       value="${participant.jockey}">
            </fieldset>
             <fieldset class="form-group">
                <button class="btn btn-lg btn-primary btn-block" type="submit">Save</button>
            </fieldset>
        </form>
    </div>
</div>
<%@include file="fragments/footer.jspf" %>
</body>
</html>
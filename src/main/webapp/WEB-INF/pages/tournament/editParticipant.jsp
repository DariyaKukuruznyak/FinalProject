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
        <div class="row">
            <div class="col-sm-6">
                <h2 class="form-signin-heading">Edit participant</h2>
                <form class="form-horizontal" action="?command=updateParticipant&participantId=${participant.id}"
                      method="POST">
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
                    <c:if test="${not empty participant.tournaments}">
                        <fieldset class="form-group">
                            <h2 class="form-signin-heading">Tournaments</h2>
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Country</th>
                                    <th>Exclude</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${participant.tournaments}" var="tournament">
                                    <tr>
                                        <td>${tournament.name}</td>
                                        <td>${tournament.country}</td>
                                        <td><a class="btn btn-info"
                                               href="?command=moveParticipant&participantId=${participant.id}&tournamentId=${tournament.id}&editedModel=participant&action=exclude">
                                            <span class="glyphicon glyphicon-minus"></span></a>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </fieldset>
                    </c:if>
                    <fieldset class="form-group">
                        <button class="btn btn-lg btn-primary btn-block" type="submit">Save</button>
                    </fieldset>
                </form>
            </div>
            <div class="col-sm-6">
                <div class="panel panel-primary">
                    <div class="panel-heading">Include participant to tournament</div>
                    <c:if test="${not empty activeTournaments}">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>Name</th>
                                <th>Country</th>
                                <th>Include</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${activeTournaments}" var="tournament">
                                <tr>
                                    <td>${tournament.name}</td>
                                    <td>${tournament.country}</td>
                                    <td>
                                        <a class="btn btn-info"
                                           href="?command=moveParticipant&participantId=${participant.id}&tournamentId=${tournament.id}&editedModel=participant&action=include">
                                            <span class="glyphicon glyphicon-plus"></span></a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="../fragments/footer.jspf" %>
</body>
</html>
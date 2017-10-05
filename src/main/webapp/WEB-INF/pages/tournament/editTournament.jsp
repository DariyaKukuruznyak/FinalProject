<%@include file="../fragments/import.jspf" %>
<html>
<%@include file="../fragments/head.jspf" %>
<body>
<%@include file="../fragments/header.jspf" %>
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
        <div class="row">
            <div class="col-sm-6">
                <h2 class="form-signin-heading">Edit tournament</h2>
                <form class="form-horizontal" action="?command=updateTournament&tournamentId=${tournament.id}"
                      method="POST">
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
                        <label for="score">Score</label>
                        <input class="form-control" id="score" name="score" placeholder="Score"
                               value="${tournament.score}">
                    </fieldset>
                    <c:if test="${not empty tournament.participants}">
                        <fieldset class="form-group">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>Participants</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${tournament.participants}" var="participant">
                                    <tr>
                                        <td>
                                            <div>${participant.name} <a class="btn btn-info"
                                                                        href="?command=excludeParticipant&participantId=${participant.id}&tournamentId=${tournament.id}&editedModel=tournament">
                                                <span class="glyphicon glyphicon-minus"></span></a></div>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </fieldset>
                    </c:if>
                    <fieldset class="form-group">
                        <button class="btn btn-lg btn-primary btn-block">Save</button>
                    </fieldset>
                </form>
            </div>
            <div class="col-sm-6">
                <h2 class="form-signin-heading">Include participant to tournament</h2>
                <c:if test="${not empty participants}">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>Active participants</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${participants}" var="participant">
                            <tr>
                                <td>
                                    <div>${participant.name} <a class="btn btn-info"
                                                                href="?command=includeParticipant&participantId=${participant.id}&tournamentId=${tournament.id}&editedModel=tournament">
                                        <span class="glyphicon glyphicon-plus"></span></a></div>
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
<%@include file="../fragments/footer.jspf" %>
</body>
</html>
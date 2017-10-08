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
                        <label for="winner">Winner</label>
                        <select class="form-control" id="winner" name="winner" size="1" value="${tournament.winner}">
                            <option selected></option>
                            <c:forEach items="${tournament.participants}" var="participant">
                                <option>${participant.name}</option>
                            </c:forEach>
                        </select>
                    </fieldset>
                    <c:if test="${not empty tournament.participants}">
                        <fieldset class="form-group">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Trainer</th>
                                    <th>Jockey</th>
                                    <th>Exclude</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${tournament.participants}" var="participant">
                                    <tr>
                                        <td>${participant.name}</td>
                                        <td>${participant.trainer}</td>
                                        <td>${participant.jockey}</td>
                                        <td>
                                            <a class="btn btn-info"
                                               href="?command=moveParticipant&participantId=${participant.id}&tournamentId=${tournament.id}&editedModel=tournament&action=exclude">
                                                <span class="glyphicon glyphicon-minus"></span></a>
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
            <div class="col-sm-4">
                <div class="panel panel-primary">
                    <div class="panel-heading">Include participant to tournament</div>
                    <c:if test="${not empty participants}">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>Name</th>
                                <th>Trainer</th>
                                <th>Jockey</th>
                                <th>Exclude</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${participants}" var="participant">
                                <tr>
                                <tr>
                                    <td>${participant.name}</td>
                                    <td>${participant.trainer}</td>
                                    <td>${participant.jockey}</td>
                                    <td><a class="btn btn-info"
                                           href="?command=moveParticipant&participantId=${participant.id}&tournamentId=${tournament.id}&editedModel=tournament&action=include">
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
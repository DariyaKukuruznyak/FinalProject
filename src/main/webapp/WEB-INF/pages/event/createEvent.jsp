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
        <c:choose>
            <c:when test="${empty successMessage}">
                <a href="?command=createEvent" class="btn btn-success"><h2>Create event</h2></a>
            </c:when>
            <c:otherwise>
                <a href="?command=editEvent&eventId=${event.id}" class="btn btn-success"><h2>Edit event</h2></a>
            </c:otherwise>
        </c:choose>
        <div class="row">
            <div class="col-sm-6">
                <h2 class="form-signin-heading">Select a tournament</h2>
                <c:if test="${not empty tournaments}">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Country</th>
                            <th>Date of beginning</th>
                            <th>Select</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${tournaments}" var="tournament">
                            <tr>
                                <td>${tournament.name}</td>
                                <td>${tournament.country}</td>
                                <td>${tournament.beginningDateAndTime}</td>
                                <td>
                                    <a href="?command=selectTournament&tournamentId=${tournament.id}"><span
                                            class="glyphicon glyphicon-ok"></span> Select</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
            </div>
            <div class="col-sm-6">
                <c:if test="${not empty selectedTournament}">
                    <h2 class="form-signin-heading">Selection</h2>
                    <h3 class="form-signin-heading">Tournament</h3>
                    <div class="form-group row">
                        <label for="name" class="col-sm-4 col-form-label">Name</label>
                        <div class="col-sm-8">
                            <div id="name">${selectedTournament.name}</div>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="country" class="col-sm-4 col-form-label">Country</label>
                        <div class="col-sm-8">
                            <div id="country">${selectedTournament.country}</div>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="beginningDateAndTime" class="col-sm-4 col-form-label">Date of beginning</label>
                        <div class="col-sm-8">
                            <div id="beginningDateAndTime">${selectedTournament.beginningDateAndTime}</div>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="participantsNumber" class="col-sm-4 col-form-label">Number of participants</label>
                        <div class="col-sm-8">
                            <div id="participantsNumber">${selectedTournament.numberOfParticipants}</div>
                        </div>
                    </div>
                    <h3 class="form-signin-heading">Participants</h3>
                    <table class="table">
                        <thead>
                        <tr>
                            <th>Name</th>
                            <th>Age</th>
                            <th>Weight</th>
                            <th>Trainer</th>
                            <th>Jockey</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${selectedTournament.participants}" var="participant">
                            <tr>
                                <td>${participant.name}</td>
                                <td>${participant.age}</td>
                                <td>${participant.weight}</td>
                                <td>${participant.trainer}</td>
                                <td>${participant.jockey}</td>
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
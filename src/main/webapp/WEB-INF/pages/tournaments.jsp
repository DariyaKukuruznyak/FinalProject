<%@include file="fragments/import.jspf" %>
<html>
<%@include file="fragments/head.jspf" %>
<body>
<%@include file="fragments/header.jspf" %>
<div class="container">
    <a class="btn btn-info" href="?command=home"><span
            class="glyphicon glyphicon-home"></span> Back home </a>
    <div class="panel-body">
        <div class="page-header">
            <h1>All Tournaments</h1>
            <h3>
                <a href="?command=addTournament"><span class="glyphicon glyphicon-plus"></span> Create new
                    tournament</a>
            </h3>
        </div>
        <table class="table">
            <thead>
            <tr>
                <th>Name</th>
                <th>Country</th>
                <th>Date of beginning</th>
                <th>Participants</th>
                <th>Score</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${tournaments}" var="tournament">
                <tr>
                    <td>${tournament.name}</td>
                    <td>${tournament.country}</td>
                    <td>${tournament.beginningDateAndTime}</td>

                    <td>
                        <c:forEach items="${tournament.participants}" var="participant">
                            <div>${participant.name}</div>
                        </c:forEach>
                    </td>
                    <td>
                        <a href="?command=editTournament&tournamentId=${tournament.id}"><span
                                class="glyphicon glyphicon-pencil"></span> Edit</a>
                    </td>
                    <td>
                        <a href="?command=deleteTournament&tournamentId=${tournament.id}"><span
                                class="glyphicon glyphicon-trash"></span> Delete</a>
                    </td>
                </tr>
            </c:forEach>
            <td>${tournament.score}</td>
            </tbody>
        </table>
    </div>
    <%@include file="fragments/footer.jspf" %>
</body>
</html>

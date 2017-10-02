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
            <h1>All Participants</h1>
            <h3>
                <a href="?command=addParticipant"><span class="glyphicon glyphicon-plus"></span> Create new participant</a>
            </h3>
        </div>
        <table class="table">
            <thead>
            <tr>
                <th>Name</th>
                <th>Age</th>
                <th>Weight (kg)</th>
                <th>Trainer</th>
                <th>Jockey</th>
                <th>Tournaments</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${participants}" var="participant">
                <tr>
                    <td>${participant.name}</td>
                    <td>${participant.age}</td>
                    <td>${participant.weight}</td>
                    <td>${participant.trainer}</td>
                    <td>${participant.jockey}</td>
                    <td>
                        <c:forEach items="${participant.tournaments}" var="tournament">
                            <div>${tournament.name}</div>
                        </c:forEach>
                    </td>
                    <td>
                        <a href="?command=editParticipant&participantId=${participant.id}"><span
                                class="glyphicon glyphicon-pencil"></span> Edit</a>
                    </td>
                    <td>
                        <a href="?command=deleteParticipant&participantId=${participant.id}"><span
                                class="glyphicon glyphicon-trash"></span> Delete</a>
                    </td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <%@include file="fragments/footer.jspf" %>
</body>
</html>

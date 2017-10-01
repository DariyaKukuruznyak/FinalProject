<%@include file="fragments/import.jspf" %>
<html>
<%@include file="fragments/head.jspf" %>
<body>
<%@include file="fragments/header.jspf" %>
<div class="container">
    <c:choose>
        <c:when test="${ empty countries}">
            <h2>Unfortunately there are no active events now</h2>
        </c:when>
        <c:otherwise>
            <div>
                <c:forEach items="${countries}" var="country">
                    <ul>
                        <li>${country.name} <span class="badge">${country.tournamentNumber}</span>
                            <ul><c:forEach items="${country.tournaments}" var="event">
                                <li>
                                    <a href="?command=event&id=${event.id}">
                                            ${event.name}
                                    </a>
                                </li>
                            </c:forEach>
                            </ul>
                        </li>
                    </ul>
                </c:forEach>
            </div>
            <div class="event-details">
                <div>${event.tournament.country.name} - ${event.tournament.name}</div>
                <div>${event.tournament.beginningDateAndTime} </div>
                <c:forEach items="${event.markets}" var="market">
                    ${market.name}
                    <c:forEach items="${market.outcomes}" var="outcome">
                        ${outcome.name}
                        <a class="btn-success" href="">${outcome.coefficient}</a>
                    </c:forEach>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>
</div>
<%@include file="fragments/footer.jspf" %>
</body>
</html>
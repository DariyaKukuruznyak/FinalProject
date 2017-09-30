<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="panel panel-primary">
    <div class="panel-heading">
        <a class="btn btn-success" href="${pageContext.request.contextPath}/event/add">
            <div class="glyphicon glyphicon-plus"></div>
            Add new event</a>
    </div>
    <div class="panel-body">
        <div class="page-header">
            <h1>All events</h1>
        </div>
        <table class="table">
            <thead>
            <tr>
                <th>Creation date</th>
                <th>Beginning date</th>
                <th>Country</th>
                <th>Tournament</th>
                <th>Participants</th>
                <th>Status</th>
                <th>Score</th>
                <th>Details</th>
                <th>Edit</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${events}" var="event">
                <tr>
                    <td>${event.creationDateAndTime}</td>
                    <td>${event.beginningDateAndTime}</td>
                    <td>${event.country}</td>
                    <td>${event.tournament}</td>
                    <td>
                        <c:forEach items="${event.participants}" var="participant">
                            ${participant.name}
                        </c:forEach>
                    </td>
                    <td>${event.status}</td>
                    <td>${event.score}</td>
                    <td>
                        <a class="btn btn-info"
                           href="${pageContext.request.contextPath}/event/details/${event.id}">
                            <div class="glyphicon glyphicon-info-sign"></div>
                        </a>
                    </td>
                    <td>
                        <a class="btn btn-success"
                           href="${pageContext.request.contextPath}/event/edit/${event.id}">
                            <div class="glyphicon glyphicon-pencil"></div>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

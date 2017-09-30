<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="panel panel-primary">
    <div class="panel-body">
        <div class="container">
            <div class="row">
                <h1>Events</h1>
                <select class="selectpicker show-tick">
                    <c:forEach items="${statusList}" var="status">
                        <option>${status}</option>
                    </c:forEach>
                </select>
                <select class="selectpicker show-tick">
                    <c:forEach items="${bookmakerList}" var="bookmaker">
                        <option>${bookmaker}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <table class="table">
            <thead>
            <tr>
                <th>Start time</th>
                <th>Country</th>
                <th>Tournament</th>
                <th>Status</th>
                <th>Bookmaker</th>
                <th>Score</th>
                <th>Suspended</th>
                <th>Turnover</th>
                <th>Balance</th>
                <th>Stakes</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${events}" var="event">
                <tr>
                    <td>${event.beginningDateAndTime}</td>
                    <td>${event.country}</td>
                    <td>${event.tournament}</td>
                    <td>${event.status}</td>
                    <td>${event.bookmaker} <a class="btn btn-success"
                                              href="${pageContext.request.contextPath}/event/edit/${event.id}">
                        <div class="glyphicon glyphicon-pencil"></div>
                    </a>
                    </td>
                    <td>${event.score}</td>
                    <td>${event.isSuspended}</td>
                    <td>${event.turnover}</td>
                    <td>${event.balance}</td>
                    <td><a class="btn btn-info"
                           href="${pageContext.request.contextPath}/bets"> ${event.numberOfBets}
                    </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

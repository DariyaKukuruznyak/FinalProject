<%@include file="fragments/import.jspf" %>
<html>
<%@include file="fragments/head.jspf" %>
<body>
<%@include file="fragments/header.jspf" %>
<div class="container">
    <a class="btn btn-info" href="?command=home"><span
            class="glyphicon glyphicon-home"></span> Back home</a>
    <c:if test="${errorMessage!=''}">
        <div class="alert alert-danger">${errorMessage}</div>
    </c:if>
    <c:if test="${successMessage!=''}">
        <div class="alert alert-success">${successMessage}</div>
    </c:if>
    <form class="form-signin" action="?command=createEvent" method="post">
        <h2 class="form-signin-heading">Create new event</h2>
        <%--<div class="form-group">--%>
            <%--<label for="startDate">Start date</label>--%>
            <%--<div class="col-sm-8">--%>
                <%--<input type="date" name="startDate" id="startDate" class="form-control" required>--%>
            <%--</div>--%>
        <%--</div>--%>
        <div class="form-group row">
            <label for="tournament" class="col-sm-4 col-form-label">Tournament</label>
            <div class="col-sm-8">
                <select name="tournament" id="tournament" class="form-control" size="1">
                    <c:forEach items="${activeTournaments}" var="tournament">
                        <option>${tournament.fullName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <%--<div class="form-group row">--%>
            <%--<label for="status" class="col-sm-4 col-form-label">Status</label>--%>
            <%--<div class="col-sm-8">--%>
                <%--<select class="form-control" id="status" name="status" size="1">--%>
                    <%--<c:forEach items="${eventStatuses}" var="status">--%>
                        <%--<option>${status}</option>--%>
                    <%--</c:forEach>--%>
                <%--</select>--%>
            <%--</div>--%>
        <%--</div>--%>
        <div class="form-group row">
            <label for="maxWin" class="col-sm-4 col-form-label">Max Win</label>
            <div class="col-sm-8">
                <div class="input-group">
                    <input name="maxWin" id="maxWin" class="form-control" placeholder="Max Win" required
                           pattern="[0-9]+" maxlength="10" title="Only integer" value="5000">
                    <span class="input-group-addon">$</span>
                </div>
            </div>
        </div>
        <button id="register-form-submit" class="btn btn-lg btn-primary btn-block">Create event</button>
    </form>
</div>
<%@include file="fragments/footer.jspf" %>
</body>
</html>
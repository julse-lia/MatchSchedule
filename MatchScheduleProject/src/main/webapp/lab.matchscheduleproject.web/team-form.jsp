<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>Team Management Application</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>

        <body>

            <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: #F4A460">
                    <div>
                        <a href="<%=request.getContextPath()%>" class="navbar-brand"> Team Management App </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Teams</a></li>
                    </ul>
                </nav>
            </header>
            <br>
            <div class="container col-md-5">
                <div class="card">
                    <div class="card-body">
                        <c:if test="${team != null}">
                            <form action="update" method="post">
                        </c:if>
                        <c:if test="${team == null}">
                            <form action="insert" method="post">
                        </c:if>

                        <caption>
                            <h2>
                                <c:if test="${team != null}">
                                    Edit Team
                                </c:if>
                                <c:if test="${team == null}">
                                    Add New Team
                                </c:if>
                            </h2>
                        </caption>

                        <c:if test="${team != null}">
                            <input type="hidden" name="id" value="<c:out value='${team.id}' />" />
                        </c:if>

                        <fieldset class="form-group">
                            <label>Team Name</label> <input type="text" value="<c:out value='${team.teamname}' />" class="form-control" name="teamname" required="required">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Homecountry</label> <input type="text" value="<c:out value='${team.homecountry}' />" class="form-control" name="homecountry">
                        </fieldset>

                        <fieldset class="form-group">
                            <label>Nickname</label> <input type="text" value="<c:out value='${team.nickname}' />" class="form-control" name="nickname">
                        </fieldset>
                        
                        <fieldset class="form-group">
                            <label>League</label> <input type="text" value="<c:out value='${team.league}' />" class="form-control" name="league">
                        </fieldset>
                        
                        <fieldset class="form-group">
                            <label>Manager</label> <input type="text" value="<c:out value='${team.manager}' />" class="form-control" name="manager">
                        </fieldset>
                        
                        <fieldset class="form-group">
                            <label>Cochairman</label> <input type="text" value="<c:out value='${team.cochairman}' />" class="form-control" name="cochairman">
                        </fieldset>
                        
                        <fieldset class="form-group">
                            <label>Founded</label> <input type="text" value="<c:out value='${team.founded}' />" class="form-control" name="founded">
                        </fieldset>

                        <button type="submit" class="btn btn-success">Save</button>
                        </form>
                    </div>
                </div>
            </div>
        </body>

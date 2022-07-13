<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <html>

        <head>
            <title>Team Match Schedule</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>

        <body>

            <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
                    <div>
                        <a href="<%=request.getContextPath()%>" class="navbar-brand"> Teams Management App </a>
                    </div>

                    <ul class="navbar-nav">
                        <li><a href="<%=request.getContextPath()%>/list" class="nav-link">Teams</a></li>
                    </ul>
                </nav>
            </header>
            <br>

            <div class="row">
                <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

                <div class="container">
                    <h3 class="text-center">Teams Information</h3>
                    <hr>
                    <div class="container text-left">

                        <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
     New Team</a>
                    </div>
                    <br>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>TeamName</th>
                                <th>HomeCountry</th>
                                <th>Nickname</th>
                                <th>League</th>
                                <th>Manager</th>
                                <th>Cochairman</th>
                                <th>Founded</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!--   for (Todo todo: todos) {  -->
                            <c:forEach var="team" items="${listTeam}">

                                <tr>
                                    <td>
                                        <c:out value="${team.id}" />
                                    </td>
                                    <td>
                                        <a href="<%=request.getContextPath()%>/serv?id=<c:out value='${team.id}' />"><c:out value="${team.teamname}" /></a>
                                    </td>
                                    <td>
                                        <c:out value="${team.homecountry}" />
                                    </td>
                                    <td>
                                        <c:out value="${team.nickname}" />
                                    </td>
                                    <td>
                                        <c:out value="${team.league}" />
                                    </td>
                                    <td>
                                        <c:out value="${team.manager}" />
                                    </td>
                                    <td>
                                        <c:out value="${team.cochairman}" />
                                    </td>
                                    <td>
                                        <c:out value="${team.founded}" />
                                    </td>
                                    <td><a href="<%=request.getContextPath()%>/edit?id=<c:out value='${team.id}' />">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp; <a href="<%=request.getContextPath()%>/delete?id=<c:out value='${team.id}' />">Delete</a></td>
                                </tr>
                            </c:forEach>
                            <!-- } -->
                        </tbody>

                    </table>
                </div>
            </div>
        </body>

        </html>
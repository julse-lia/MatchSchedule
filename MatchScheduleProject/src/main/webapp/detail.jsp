<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

        <html>

        <head>
            <title>Team Schedule And Match Results</title>
            <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        </head>

        <body>

            <header>
                <nav class="navbar navbar-expand-md navbar-dark" style="background-color: #F4A460">
                    <div>
                        <a href="<%=request.getContextPath()%>" class="navbar-brand"> Team Management App</a>
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
                    <h3 class="text-center">Team Match Results</h3>
                    <hr>
                    <div class="container text-left">

                    </div>
                    <br>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Team1</th>
                                <th>Team2</th>
                                <th>Goal1</th>
                                <th>Goal2</th>
                                <th>MatchDate</th>
                                <th>Venue</th>
                            </tr>
                        </thead>
                        <tbody>
                            
                            <!--   for (Todo todo: todos) {  -->
                            <c:forEach var="res" items="${teamDetail}">

                                <tr>
                                    <td>
                                        <c:out value="${res[0]}" />
                                    </td>
                                    <td>
                                        <c:out value="${res[1]}" />
                                    </td>
                                    <td>
                                        <c:out value="${res[2]}" />
                                    </td>
                                    <td>
                                        <c:out value="${res[3]}" />
                                    </td>
                                    <td>
                                        <c:out value="${res[4]}" />
                                    </td>
                                    <td>
                                        <c:out value="${res[5]}" />
                                    </td>
                                </tr>
                            </c:forEach>
                            <!-- } -->
                        </tbody>

                    </table>
                </div>
            </div>

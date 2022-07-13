/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.matchscheduleproject.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lab.matchscheduleproject.entities.Teams;
import lab.matchscheduleproject.model.TeamsFacade;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Admin
 */


@WebServlet("/")
public class TeamController extends HttpServlet {


    @EJB
    private TeamsFacade teamsFacade;
    
    String jdbcURL = "jdbc:postgresql://localhost:5432/sportschedule";
    String jdbcUsername = "postgres";
    String jdbcPassword = "1111";
    
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertTeam(request, response);
                    break;
                case "/delete":
                    deleteTeam(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateTeam(request, response);
                    break;
                case "/detail":
                    detailTeam(request, response);
                    break;
                default:
                    listTeam(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    
    private void listTeam(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List < Teams > listTeam = teamsFacade.findAll();
        request.setAttribute("listTeam", listTeam);
        RequestDispatcher dispatcher = request.getRequestDispatcher("team-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("team-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Teams existingTeam = teamsFacade.find(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("team-form.jsp");
        request.setAttribute("team", existingTeam);
        dispatcher.forward(request, response);

    }

    private void insertTeam(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
    	
        String teamname = request.getParameter("teamname");
        String homecountry = request.getParameter("homecountry");
        String nickname = request.getParameter("nickname");
        String league = request.getParameter("league");
        String manager = request.getParameter("manager");
        String cochairman = request.getParameter("cochairman");
        Integer founded = Integer.parseInt(request.getParameter("founded"));
        
        Teams newTeam = new Teams(teamname, homecountry, nickname, league, manager, cochairman, founded);
        teamsFacade.create(newTeam);
        response.sendRedirect("list");
    }

    private void updateTeam(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String teamname = request.getParameter("teamname");
        String homecountry = request.getParameter("homecountry");
        String nickname = request.getParameter("nickname");
        String league = request.getParameter("league");
        String manager = request.getParameter("manager");
        String cochairman = request.getParameter("cochairman");
        Integer founded = Integer.parseInt(request.getParameter("founded"));

        Teams team = new Teams(id, teamname, homecountry, nickname, league, manager, cochairman, founded);
        teamsFacade.edit(team);
        response.sendRedirect("list");
    }

    private void deleteTeam(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Teams team = teamsFacade.find(id);
        teamsFacade.remove(team);
        response.sendRedirect("list");

    }
    
    private void detailTeam(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
   
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String query = "SELECT temp.team1, temp2.team2, temp.goal1, temp2.goal2, temp.matchdate, temp.venue FROM " +
                        "(SELECT t.teamname AS team1, t1.goalnum AS goal1, m.matchdate AS matchdate, m.venue AS venue, m.id AS matchid, t1.teamid AS teamid " +
                        " FROM sc.matchresults AS t1 " +
                        " JOIN sc.matches AS m ON t1.matchid = m.id " +
                        " JOIN sc.teams AS t ON t.id = t1.teamid) AS temp " +
                        " JOIN (SELECT z.teamname AS team2, t2.goalnum AS goal2, m2.matchdate AS matchdate, m2.venue AS venue, m2.id AS matchid, t2.teamid AS teamid " +
                        " FROM sc.matchresults AS t2 " +
                        " JOIN sc.matches AS m2 ON t2.matchid = m2.id " +
                        " JOIN sc.teams AS z ON z.id = t2.teamid) AS temp2 " +
                        " ON temp.matchid = temp2.matchid " +
                        " WHERE temp.team1 != temp2.team2 AND temp.teamid = ?;";
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);){
            int id = Integer.parseInt(request.getParameter("id"));
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            
            ArrayList Rows = new ArrayList();

            while (resultSet.next()){
                ArrayList row = new ArrayList();
                for (int i = 1; i <= 6 ; i++){
                    row.add(resultSet.getString(i));
                }
                Rows.add(row);
            }
            
            request.getSession().setAttribute("teamDetail", Rows);
            RequestDispatcher dispatcher = request.getRequestDispatcher("detail.jsp");
            dispatcher.forward(request, response);   
            
        }
        catch (SQLException e) {

         e.printStackTrace();
        }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.matchscheduleproject.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lab.matchscheduleproject.model.TeamsFacade;
import lab.matchscheduleproject.entities.Teams;

/**
 *
 * @author Admin
 */
@WebServlet(name = "serv", urlPatterns = {"/serv"})
public class serv extends HttpServlet {
    
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


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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

            out.println("<html><body><h2>The Select query has following results : </h2>");
            out.println("<hr></br><table cellspacing='0' cellpadding='5' border='1'>");
            out.println("<tr>");
            out.println("<td><b>team1</b></td>");
            out.println("<td><b>team2</b></td>");
            out.println("<td><b>goal1</b></td>");
            out.println("<td><b>goal2</b></td>");
            out.println("<td><b>matchdate</b></td>");
            out.println("<td><b>venue</b></td>");
            out.println("</tr>");

            while(resultSet.next()) {
                out.println("<tr>");
                out.println("<td>"+resultSet.getString(1) + "</td>");
                out.println("<td>"+resultSet.getString(2) + "</td>");
                out.println("<td>"+resultSet.getString(3) + "</td>");
                out.println("<td>"+resultSet.getString(4) + "</td>");
                out.println("<td>"+resultSet.getString(5) + "</td>");
                out.println("<td>"+resultSet.getString(6) + "</td>");
                out.println("</tr>");
         }

         out.println("</table></br><hr></body></html>");
        }
        catch (SQLException e) {

         e.printStackTrace();
        }
  
 }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

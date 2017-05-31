/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entities.Team;
import facades.TeamFacade;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author Анюта
 */
public class TeamServlet extends HttpServlet {

    private TeamFacade teamFac;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String answer;
        ObjectMapper mapper = new ObjectMapper();
        if (request.getMethod() == "GET") {
            //получение списка команд??
            answer = mapper.writeValueAsString(teamFac.findAll());
        } else if (request.getMethod() == "POST") {
            //добавление команды
            String ids_prefix = request.getParameter("ids") + "_";
            Team newTeam = new Team();
            newTeam.setIcon(request.getParameter(ids_prefix + "icon"));
            newTeam.setTeamName(request.getParameter(ids_prefix + "teamName"));
            teamFac.create(newTeam);
            answer = mapper.writeValueAsString(newTeam);
        } else {
            answer = "Incorrect METHOD";
        }
        log(answer);
        response.getWriter().write(answer);

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

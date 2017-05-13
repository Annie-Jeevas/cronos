/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entities.Sportsman;
import entities.Team;
import facades.SportsmanFacade;
import facades.TeamFacade;
import java.io.IOException;
import java.sql.Date;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author Анюта
 */
public class SportsmenController extends HttpServlet {

    @EJB
    private SportsmanFacade sportsmenFac;
    @EJB
    private TeamFacade teamFac;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String answer;
        ObjectMapper mapper = new ObjectMapper();
        if (request.getMethod() == "GET") {
            try {
                answer = mapper.writeValueAsString(sportsmenFac.findAll());

            } catch (IOException ex) {
                answer = ex.getLocalizedMessage();
                response.setStatus(500);

            }
            

        } else if (request.getMethod() == "POST") {
            String ids_prefix = request.getParameter("ids")+"_";
            Sportsman newSportsman = new Sportsman();
            newSportsman.setName(request.getParameter(ids_prefix + "name"));
            newSportsman.setAccuracy(Float.valueOf(request.getParameter(ids_prefix + "accuracy")));
            newSportsman.setBirtdate(Date.valueOf(request.getParameter(ids_prefix + "birthdate")));
            newSportsman.setGender(Boolean.valueOf(request.getParameter(ids_prefix + "gender")));
            Team newTeam = (Team) mapper.readValue(request.getParameter(ids_prefix + "team"), Team.class);
            newSportsman.setTeam(teamFac.find(newTeam.getId()));
            sportsmenFac.create(newSportsman);
            answer = mapper.writeValueAsString(newSportsman);
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

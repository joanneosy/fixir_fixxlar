/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import util.HashCode;
import dao.UserDAO;
import entity.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Joanne
 */
@WebServlet(name = "ChangePasswordServlet", urlPatterns = {"/ChangePassword"})
public class ChangePasswordServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession(true);
        
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmNewPassword");
        User user = (User)session.getAttribute("loggedInUser");
        String email = user.getEmail();
        if (oldPassword.equals("") || newPassword.equals("") || confirmPassword.equals("")) {
            request.setAttribute("errMsg", "Incorrect password / New passwords do not match.");
            request.setAttribute("email", email);
            RequestDispatcher view = request.getRequestDispatcher("ChangePassword.jsp");
            view.forward(request, response);
        }
        String passwordStored = user.getPassword();
        HashCode hc = new HashCode();
        
        // Check if the old password matches the password stored in the database
        // and if the new passwords match
        if (hc.check(oldPassword, passwordStored) && newPassword.equals(confirmPassword)) {
            try {
                UserDAO uDAO = new UserDAO();
                newPassword = hc.generateSaltedHash(newPassword);
                uDAO.updateUserPassword(email, newPassword);
                request.setAttribute("successChangePasswordMsg", "Your password has been changed!");
                String redirectTo = user.getUserType() + ".jsp";
                RequestDispatcher view = request.getRequestDispatcher(redirectTo);
                view.forward(request, response);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            request.setAttribute("errMsg", "Incorrect password / New passwords do not match.");
            request.setAttribute("email", email);
            RequestDispatcher view = request.getRequestDispatcher("ChangePassword.jsp");
            view.forward(request, response);
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ChangePasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ChangePasswordServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
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

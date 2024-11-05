package servlets;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DBCommunication;
import data.User;

public class RegServlet extends HttpServlet {

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
            throws ServletException, IOException {
        String email = request.getParameter("email");
        int password = Integer.parseInt(request.getParameter("password"));

        User user = DBCommunication.selectData(email, password);

        if ("http://localhost:8080/javaTask2/signupPage.jsp".equals(request.getHeader("referer"))) {
            if (user == null) {
                String name = request.getParameter("name");
                user = new User();
                user.setName(name);
                user.setEmail(email);
                DBCommunication.insertData(name, email, password);
                request.setAttribute("user", user);
                request.getRequestDispatcher("/welcomePage.jsp").forward(request, response);
            } else {
                request.setAttribute("alertMessage", "You already have an email, please login");
                request.getRequestDispatcher("/signupPage.jsp").forward(request, response);
            }
        } else {
            if (user == null) {
                request.setAttribute("alertMessage", "You do not have an email, please signup first");
                request.getRequestDispatcher("/loginPage.jsp").forward(request, response);
            } else {
                if (user.getPassword() == password) {
                    request.setAttribute("user", user);
                    request.getRequestDispatcher("/welcomePage.jsp").forward(request, response);
                } else {
                    request.setAttribute("alertMessage", "Your password is incorrect, please try again...");
                    request.getRequestDispatcher("/loginPage.jsp").forward(request, response);
                }
            }
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

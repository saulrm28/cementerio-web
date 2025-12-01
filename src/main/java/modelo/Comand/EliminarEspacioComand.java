/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package modelo.Comand;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import modelo.DAO.DAOImpl.EspacioDAOImpl;
import modelo.DAO.EspacioDAO;

public class EliminarEspacioComand implements Comand {
    private final EspacioDAO espacioDAO = new EspacioDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            espacioDAO.eliminar(id);
            response.sendRedirect("EspacioServlet?action=listar");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
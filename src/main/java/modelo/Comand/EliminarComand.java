/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// Archivo: src/main/java/modelo/Comand/EliminarComand.java
package modelo.Comand;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import modelo.DAO.DAOImpl.DifuntoDAOImpl;
import modelo.DAO.DifuntoDAO;

public class EliminarComand implements Comand {
    private final DifuntoDAO difuntoDAO = new DifuntoDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            difuntoDAO.eliminar(id);
            // Redirigimos a la lista para que se vea el cambio
            response.sendRedirect("DifuntoServlet?action=listar");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
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
import modelo.DTO.EspacioDTO;

public class InsertarEspacioComand implements Comand {
    private final EspacioDAO espacioDAO = new EspacioDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            EspacioDTO espacio = new EspacioDTO();
            espacio.setCodigo(request.getParameter("codigo"));
            espacio.setTipo(request.getParameter("tipo"));
            espacio.setEstado(request.getParameter("estado"));
            espacioDAO.insertar(espacio);
            response.sendRedirect("EspacioServlet?action=listar");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
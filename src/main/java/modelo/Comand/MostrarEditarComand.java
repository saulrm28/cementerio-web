/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// Archivo: src/main/java/modelo/Comand/MostrarEditarComand.java
package modelo.Comand;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import modelo.DAO.DAOImpl.DifuntoDAOImpl;
import modelo.DAO.DifuntoDAO;
import modelo.DTO.DifuntoDTO;

public class MostrarEditarComand implements Comand {
    private final DifuntoDAO difuntoDAO = new DifuntoDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            DifuntoDTO difunto = difuntoDAO.buscarPorId(id);
            // Ponemos el objeto encontrado en el request para que el JSP lo pueda usar
            request.setAttribute("difunto", difunto);
            // Enviamos la petición a la página de modificación
            request.getRequestDispatcher("/modificar.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
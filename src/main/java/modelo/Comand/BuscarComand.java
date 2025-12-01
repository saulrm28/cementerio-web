/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// Archivo: modelo/Comand/BuscarComand.java
package modelo.Comand;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import modelo.DAO.DAOImpl.DifuntoDAOImpl;
import modelo.DAO.DifuntoDAO;
import modelo.DTO.DifuntoDTO;

public class BuscarComand implements Comand {
    private final DifuntoDAO difuntoDAO = new DifuntoDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            String termino = request.getParameter("terminoBusqueda");
            List<DifuntoDTO> resultados = difuntoDAO.buscarPorNombre(termino);
            
            request.setAttribute("listaDifuntos", resultados);
            request.setAttribute("terminoBusqueda", termino);
            
            request.getRequestDispatcher("/index.jsp").forward(request, response);
            
        } catch (SQLException e) {
            throw new ServletException("Error en la búsqueda en la base de datos", e);
        }
    }
}
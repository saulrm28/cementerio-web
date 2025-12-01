/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// Archivo: modelo/Comand/ListarEspaciosComand.java
package modelo.Comand;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import modelo.DAO.DAOImpl.EspacioDAOImpl;
import modelo.DAO.EspacioDAO;
import modelo.DTO.EspacioDTO;

public class ListarEspaciosComand implements Comand {
    private final EspacioDAO espacioDAO = new EspacioDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            List<EspacioDTO> lista = espacioDAO.listarTodos();
            request.setAttribute("listaEspacios", lista);
            request.getRequestDispatcher("/espacios.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error de base de datos listando espacios", e);
        }
    }
}
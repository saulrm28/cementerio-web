/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// Archivo: src/main/java/modelo/Comand/ActualizarComand.java
package modelo.Comand;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import modelo.DAO.DAOImpl.DifuntoDAOImpl;
import modelo.DAO.DifuntoDAO;
import modelo.DTO.DifuntoDTO;

public class ActualizarComand implements Comand {
    private final DifuntoDAO difuntoDAO = new DifuntoDAOImpl();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            DifuntoDTO difunto = new DifuntoDTO();
            difunto.setId(Integer.parseInt(request.getParameter("id")));
            difunto.setNombre(request.getParameter("nombre"));
            difunto.setFechaNacimiento(LocalDate.parse(request.getParameter("fechaNacimiento")));
            difunto.setFechaDefuncion(LocalDate.parse(request.getParameter("fechaDefuncion")));
            
            
            difuntoDAO.actualizar(difunto);
            response.sendRedirect("DifuntoServlet?action=listar");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
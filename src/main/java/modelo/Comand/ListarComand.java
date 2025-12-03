/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


package modelo.Comand;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import modelo.DAO.*;
import modelo.DAO.DAOImpl.*;
import modelo.DTO.DifuntoDTO;
import java.util.List;

public class ListarComand implements Comand {
    private final DifuntoDAO difuntoDAO = new DifuntoDAOImpl();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<DifuntoDTO> lista = difuntoDAO.listarTodos();
            request.setAttribute("listaDifuntos", lista);
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
            
            
                
            }  
            }}
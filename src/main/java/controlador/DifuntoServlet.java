/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
// Archivo: src/main/java/controlador/DifuntoServlet.java
package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import modelo.Comand.*;

@WebServlet("/DifuntoServlet")
public class DifuntoServlet extends HttpServlet {

    private final Map<String, Comand> commands = new HashMap<>();

    @Override
    public void init() {
       
        commands.put("listar", new ListarComand());
        commands.put("insertar", new InsertarComand());
        commands.put("eliminar", new EliminarComand());
        commands.put("editar", new MostrarEditarComand());
        commands.put("actualizar", new ActualizarComand());
        commands.put("buscar", new BuscarComand());
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        String action = request.getParameter("action");
        if (action == null || !commands.containsKey(action)) {
            action = "listar";
        }

        
        Comand command = commands.get(action);

     
        command.execute(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}

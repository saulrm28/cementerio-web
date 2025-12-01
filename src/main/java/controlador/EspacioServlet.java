/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// Archivo: controlador/EspacioServlet.java
package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import modelo.Comand.ActualizarEspacioComand;
import modelo.Comand.Comand;
import modelo.Comand.EliminarEspacioComand;
import modelo.Comand.InsertarEspacioComand;
import modelo.Comand.ListarEspaciosComand;
import modelo.Comand.MostrarEditarEspacioComand;

@WebServlet("/EspacioServlet")
public class EspacioServlet extends HttpServlet {

    private final Map<String, Comand> commands = new HashMap<>();

    @Override
    public void init() {
        commands.put("listar", new ListarEspaciosComand());
        commands.put("insertar", new InsertarEspacioComand());
        commands.put("editar", new MostrarEditarEspacioComand());
        commands.put("actualizar", new ActualizarEspacioComand());
        commands.put("eliminar", new EliminarEspacioComand());
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

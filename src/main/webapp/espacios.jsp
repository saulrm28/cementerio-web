<%-- 
    Document   : espacios
    Created on : 14 oct. 2025, 12:41:46 p. m.
    Author     : ASUS
--%>

<%-- Archivo: src/main/webapp/espacios.jsp --%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestión de Espacios</title>
    
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>
    <div class="container mt-4">
        <jsp:include page="general/header.jsp" />

        <section id="consultas" class="card shadow-sm">
             <div class="card-header d-flex justify-content-between align-items-center">
                <h3>Gestión de Espacios del Cementerio</h3>
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#registroEspacioModal">
                    <i class="bi bi-plus-circle"></i> Agregar Nuevo Espacio
                </button>
             </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-striped table-hover">
                        <thead class="table-dark">
                            <tr>
                                <th>ID</th>
                                <th>Código</th>
                                <th>Tipo</th>
                                <th>Estado</th>
                                <th class="text-center">Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="espacio" items="${listaEspacios}">
                                <tr>
                                    <td>${espacio.id}</td>
                                    <td><c:out value="${espacio.codigo}"/></td>
                                    <td><c:out value="${espacio.tipo}"/></td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${espacio.estado == 'Disponible'}"><span class="badge bg-success">Disponible</span></c:when>
                                            <c:when test="${espacio.estado == 'Ocupado'}"><span class="badge bg-danger">Ocupado</span></c:when>
                                            <c:otherwise><span class="badge bg-warning text-dark">${espacio.estado}</span></c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td class="text-center">
                                        <a href="EspacioServlet?action=editar&id=${espacio.id}" class="btn btn-warning btn-sm" title="Modificar"><i class="bi bi-pencil-square"></i></a>
                                        <a href="EspacioServlet?action=eliminar&id=${espacio.id}" class="btn btn-danger btn-sm" title="Eliminar" onclick="return confirm('¿Seguro que desea eliminar el espacio ${espacio.codigo}?');"><i class="bi bi-trash"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                            <c:if test="${empty listaEspacios}"><td colspan="5" class="text-center text-muted">No hay espacios registrados.</td></c:if>
                        </tbody>
                    </table>
                </div>
            </div>
        </section>

        <footer class="text-center footer-custom"><p>Cementerio La Paz &copy; 2025</p></footer>
    </div>

    <div class="modal fade" id="registroEspacioModal" tabindex="-1">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Formulario de Nuevo Espacio</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
          </div>
          <div class="modal-body">
            <form action="EspacioServlet?action=insertar" method="post" id="formRegistroEspacio">
                <div class="mb-3">
                    <label for="codigo" class="form-label">Código del Espacio (ej. A-01-N03):</label>
                    <input type="text" id="codigo" name="codigo" class="form-control" required>
                </div>
                <div class="mb-3">
                    <label for="tipo" class="form-label">Tipo de Espacio:</label>
                    <select class="form-select" id="tipo" name="tipo" required>
                        <option value="Nicho">Nicho</option>
                        <option value="Tumba">Tumba</option>
                        <option value="Mausoleo">Mausoleo</option>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="estado" class="form-label">Estado Inicial:</label>
                    <select class="form-select" id="estado" name="estado" required>
                        <option value="Disponible">Disponible</option>
                        <option value="Ocupado">Ocupado</option>
                        <option value="Mantenimiento">Mantenimiento</option>
                    </select>
                </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
            <button type="submit" form="formRegistroEspacio" class="btn btn-primary"><i class="bi bi-save"></i> Guardar Espacio</button>
          </div>
        </div>
      </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
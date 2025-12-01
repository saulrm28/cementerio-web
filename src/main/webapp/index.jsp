<%-- 
    Document   : index
    Created on : 14 oct. 2025, 11:34:46 a. m.
    Author     : ASUS
--%>


<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Sistema de Gestión - Panel Principal</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>
    <div class="container mt-4">
        <jsp:include page="general/header.jsp" />

        <section class="mb-4">
            <div class="row">
                <div class="col-md-4 mb-3">
                    <div class="card dashboard-card card-border-primary shadow-sm h-100">
                        <div class="card-body">
                            <h5 class="card-title">Total de Registros</h5>
                            <p class="card-text">${listaDifuntos.size()}</p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 mb-3">
                    <div class="card dashboard-card card-border-success shadow-sm h-100">
                        <div class="card-body">
                            <h5 class="card-title">Último Registrado</h5>
                            <p class="card-text fs-5"><c:out value="${listaDifuntos[0].nombre}"/></p>
                        </div>
                    </div>
                </div>
                <div class="col-md-4 mb-3">
                    <div class="card dashboard-card card-border-info shadow-sm h-100">
                        <div class="card-body">
                            <h5 class="card-title">Espacios Disponibles</h5>
                            <p class="card-text">42</p>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <div class="card shadow-sm mb-4">
            <div class="card-body">
                <form action="DifuntoServlet" method="get" class="row g-3 align-items-center">
                    <input type="hidden" name="action" value="buscar">
                    <div class="col">
                        <label class="visually-hidden" for="terminoBusqueda">Buscar por nombre</label>
                        <input type="text" class="form-control" name="terminoBusqueda" 
                               placeholder="Buscar por nombre..." value="<c:out value='${terminoBusqueda}'/>">
                    </div>
                    <div class="col-auto">
                        <button type="submit" class="btn btn-secondary">
                            <i class="bi bi-search"></i> Buscar
                        </button>
                    </div>
                </form>
            </div>
        </div>

        <section id="consultas" class="card shadow-sm">
             <div class="card-header d-flex justify-content-between align-items-center">
                <h3>Registros Actuales</h3>
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#registroModal">
                    <i class="bi bi-plus-circle"></i> Registrar Nuevo Difunto
                </button>
             </div>
            <div class="card-body">
                <div class="table-responsive">
                    <table class="table table-striped table-hover">
                        <thead class="table-dark">
                            <tr>
                                <th>ID</th>
                                <th>Nombre</th>
                                <th>Fecha Nacimiento</th>
                                <th>Fecha Defunción</th>
                                <th>Ubicación</th>
                                <th class="text-center">Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="difunto" items="${listaDifuntos}">
                                <tr>
                                    <td>${difunto.id}</td>
                                    <td><c:out value="${difunto.nombre}"/></td>
                                    <td>${difunto.fechaNacimiento}</td>
                                    <td>${difunto.fechaDefuncion}</td>
                                    <td><c:out value="${difunto.ubicacion}"/></td>
                                    <td class="text-center">
                                        <a href="DifuntoServlet?action=editar&id=${difunto.id}" class="btn btn-warning btn-sm" title="Modificar">
                                            <i class="bi bi-pencil-square"></i>
                                        </a>
                                        <a href="DifuntoServlet?action=eliminar&id=${difunto.id}" class="btn btn-danger btn-sm" title="Eliminar" onclick="return confirm('¿Está seguro de que desea eliminar este registro?');">
                                            <i class="bi bi-trash"></i>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                             <c:if test="${empty listaDifuntos}">
                                <tr>
                                    <td colspan="6" class="text-center text-muted">No hay registros para mostrar.</td>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
        </section>

        <footer class="text-center footer-custom">
            <p>Cementerio La Paz &copy; 2025</p>
        </footer>
    </div>

    <div class="modal fade" id="registroModal" tabindex="-1" aria-labelledby="registroModalLabel" aria-hidden="true">
      <div class="modal-dialog modal-lg">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="registroModalLabel">Formulario de Nuevo Registro de Difunto</h5>
            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
            <form action="DifuntoServlet?action=insertar" method="post" id="formRegistro">
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="nombre" class="form-label">Nombre completo:</label>
                        <input type="text" id="nombre" name="nombre" class="form-control" required>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="ubicacion" class="form-label">Ubicación (lote, manzana, nicho):</label>
                        <input type="text" id="ubicacion" name="ubicacion" class="form-control" required>
                    </div>
                </div>
                <div class="row">
                     <div class="col-md-6 mb-3">
                        <label for="fechaNacimiento" class="form-label">Fecha de nacimiento:</label>
                        <input type="date" id="fechaNacimiento" name="fechaNacimiento" class="form-control" required>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="fechaDefuncion" class="form-label">Fecha de defunción:</label>
                        <input type="date" id="fechaDefuncion" name="fechaDefuncion" class="form-control" required>
                    </div>
                </div>
            </form>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
            <button type="submit" form="formRegistro" class="btn btn-primary"><i class="bi bi-save"></i> Guardar Registro</button>
          </div>
        </div>
      </div>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
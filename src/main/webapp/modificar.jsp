<%-- 
    Document   : modificar
    Created on : 14 oct. 2025, 11:42:42 a. m.
    Author     : ASUS
--%>


<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modificar Registro - Cementerio La Paz</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>
    <div class="container mt-4">
        
        <header class="header-custom p-4 rounded text-center mb-4">
            <h1>Cementerio La Paz</h1>
            <p class="lead">Sistema de Gestión de Difuntos</p>
        </header>

        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="DifuntoServlet?action=listar">Inicio</a></li>
                <li class="breadcrumb-item active" aria-current="page">Modificar Registro</li>
            </ol>
        </nav>

        <section id="edicion" class="card shadow-sm">
            <div class="card-header">
                <h3>Editando el Registro de: <c:out value="${difunto.nombre}"/></h3>
            </div>
            <div class="card-body">
                <form action="DifuntoServlet?action=actualizar" method="post">
                    <input type="hidden" name="id" value="${difunto.id}">
                    
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <label for="nombre" class="form-label">Nombre completo:</label>
                            <input type="text" id="nombre" name="nombre" class="form-control" value="<c:out value='${difunto.nombre}'/>" required>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="ubicacion" class="form-label">Ubicación (lote, manzana, nicho):</label>
                            <input type="text" id="ubicacion" name="ubicacion" class="form-control" value="<c:out value='${difunto.ubicacion}'/>" required>
                        </div>
                    </div>
                    <div class="row">
                         <div class="col-md-6 mb-3">
                            <label for="fechaNacimiento" class="form-label">Fecha de nacimiento:</label>
                            <input type="date" id="fechaNacimiento" name="fechaNacimiento" class="form-control" value="${difunto.fechaNacimiento}" required>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="fechaDefuncion" class="form-label">Fecha de defunción:</label>
                            <input type="date" id="fechaDefuncion" name="fechaDefuncion" class="form-control" value="${difunto.fechaDefuncion}" required>
                        </div>
                    </div>
                    <hr>
                    <button type="submit" class="btn btn-success"><i class="bi bi-check-circle"></i> Guardar Cambios</button>
                    <a href="DifuntoServlet?action=listar" class="btn btn-secondary"><i class="bi bi-x-circle"></i> Cancelar</a>
                </form>
            </div>
        </section>
        
        <footer class="text-center footer-custom">
            <p>Cementerio La Paz &copy; 2025</p>
        </footer>
    </div>
</body>
</html>
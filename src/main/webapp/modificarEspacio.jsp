<%-- 
    Document   : modificarEspacio
    Created on : 14 oct. 2025, 1:04:05 p. m.
    Author     : ASUS
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Modificar Espacio</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>
    <div class="container mt-4">
        <jsp:include page="general/header.jsp" />

        <section id="edicion" class="card shadow-sm">
            <div class="card-header">
                <h3>Editando el Espacio: <c:out value="${espacio.codigo}"/></h3>
            </div>
            <div class="card-body">
                <form action="EspacioServlet?action=actualizar" method="post">
                    <input type="hidden" name="id" value="${espacio.id}">
                    
                    <div class="mb-3">
                        <label for="codigo" class="form-label">Código del Espacio:</label>
                        <input type="text" id="codigo" name="codigo" class="form-control" value="<c:out value='${espacio.codigo}'/>" required>
                    </div>

                    <div class="mb-3">
                        <label for="tipo" class="form-label">Tipo de Espacio:</label>
                        <select class="form-select" id="tipo" name="tipo" required>
                            <option value="Nicho" ${espacio.tipo == 'Nicho' ? 'selected' : ''}>Nicho</option>
                            <option value="Tumba" ${espacio.tipo == 'Tumba' ? 'selected' : ''}>Tumba</option>
                            <option value="Mausoleo" ${espacio.tipo == 'Mausoleo' ? 'selected' : ''}>Mausoleo</option>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="estado" class="form-label">Estado:</label>
                        <select class="form-select" id="estado" name="estado" required>
                            <option value="Disponible" ${espacio.estado == 'Disponible' ? 'selected' : ''}>Disponible</option>
                            <option value="Ocupado" ${espacio.estado == 'Ocupado' ? 'selected' : ''}>Ocupado</option>
                            <option value="Mantenimiento" ${espacio.estado == 'Mantenimiento' ? 'selected' : ''}>Mantenimiento</option>
                        </select>
                    </div>

                    <hr>
                    <button type="submit" class="btn btn-success"><i class="bi bi-check-circle"></i> Guardar Cambios</button>
                    <a href="EspacioServlet?action=listar" class="btn btn-secondary"><i class="bi bi-x-circle"></i> Cancelar</a>
                </form>
            </div>
        </section>
        
        <footer class="text-center footer-custom"><p>Cementerio La Paz &copy; 2025</p></footer>
    </div>
</body>
</html>
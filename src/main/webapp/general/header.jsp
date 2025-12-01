<%-- 
    Document   : header
    Created on : 14 oct. 2025, 11:33:27?a. m.
    Author     : ASUS
--%>

<%-- Archivo: src/main/webapp/general/header.jsp --%>
<header class="header-custom p-4 rounded text-center mb-4">
    <h1>Cementerio La Paz</h1>
    <p class="lead">Sistema de Gestión</p>
</header>

<nav class="navbar navbar-expand-lg navbar-light bg-light rounded mb-4 shadow-sm">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Módulos:</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="DifuntoServlet?action=listar">
                        <i class="bi bi-person-lines-fill"></i> Gestión de Difuntos
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="EspacioServlet?action=listar">
                        <i class="bi bi-grid-3x3-gap-fill"></i> Gestión de Espacios
                    </a>
                </li>
            </ul>
        </div>
    </div>
</nav>
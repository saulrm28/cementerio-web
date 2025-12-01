<%-- 
    Document   : acceso
    Created on : 14 oct. 2025, 11:28:59 a. m.
    Author     : ASUS
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Acceso al Sistema</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <!-- Bootstrap -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.1/font/bootstrap-icons.css">
    <link rel="stylesheet" href="css/estilos.css">
</head>

<body class="bg-light">
    <div class="container">
        <div class="card shadow-sm login-container mx-auto mt-5" style="max-width: 400px;">
            <div class="card-body p-5">
                <div class="text-center mb-4">
                    <i class="bi bi-person-circle" style="font-size: 4rem;"></i>
                    <h1 class="h3 mb-3 fw-normal">Gestión de Cementerio</h1>
                    <p class="text-muted">Por favor, inicie sesión para continuar</p>
                </div>

                <!-- Formulario -->
                <form id="loginForm" action="index.xhtml" method="get" novalidate>

                    <input type="hidden" name="action" value="listar">

                    <!-- Usuario -->
                    <div class="form-floating mb-3">
                        <input type="text" class="form-control" id="usuario" name="usuario" placeholder="usuario" value="admin" required>
                        <label for="usuario">Usuario</label>
                        <div class="invalid-feedback">Por favor, ingrese su usuario.</div>
                    </div>

                    <!-- Contraseña -->
                    <div class="form-floating mb-3">
                        <input type="password" class="form-control" id="password" name="password" placeholder="Contraseña" value="1234" required minlength="4">
                        <label for="password">Contraseña</label>
                        <div class="invalid-feedback">Ingrese una contraseña válida (mínimo 4 caracteres).</div>
                    </div>

                    <!-- Mensaje de error general -->
                    <div id="errorMensaje" class="alert alert-danger d-none text-center p-2" role="alert">
                        Usuario o contraseña incorrectos.
                    </div>

                    <!-- Botón -->
                    <button class="w-100 btn btn-lg btn-primary" type="submit">
                        <i class="bi bi-box-arrow-in-right"></i> Ingresar
                    </button>

                    <p class="mt-5 mb-3 text-muted text-center">&copy; 2025</p>
                </form>
            </div>
        </div>
    </div>

   
    <script>
        (function () {
            const form = document.getElementById('loginForm');
            const usuarioInput = document.getElementById('usuario');
            const passwordInput = document.getElementById('password');
            const errorMensaje = document.getElementById('errorMensaje');

            form.addEventListener('submit', function (event) {
                event.preventDefault(); 

                const usuario = usuarioInput.value.trim();
                const password = passwordInput.value.trim();

           
                form.classList.add('was-validated');

                // Validación general de campos vacíos
                if (!usuario || !password) {
                    return;
                }

                // Validación de credenciales fijas
                if (usuario === "admin" && password === "1234") {
                    errorMensaje.classList.add("d-none"); // Oculta mensaje de error
                    form.submit(); // Envía el formulario
                } else {
                    errorMensaje.classList.remove("d-none"); // Muestra error
                    passwordInput.classList.add("is-invalid");
                }
            });
        })();
    </script>
</body>
</html>



document.addEventListener('DOMContentLoaded', function() {
    document.getElementById("loginForm").addEventListener("submit", function(event) {
        event.preventDefault(); // Evitar el envío del formulario predetermine

        var email = document.getElementById("email").value;
        var password = document.getElementById("password").value;

        // Crear objeto de datos con las credenciales
        var credentials = {
            email: email,
            password: password
        };

        // Realizar solicitud HTTP POST al endpoint de autenticación
        fetch('/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(credentials)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error en la solicitud de inicio de sesión');
            }
            return response.json();
        })
        .then(data => {
            // Redirigir a la página de perfil del usuario
            window.location.href = '/user/home';
        })
        .catch(error => {
            // Manejar errores
            throw new Error('Parametros incorrectos');
        });
    });
});

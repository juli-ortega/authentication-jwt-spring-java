document.addEventListener('DOMContentLoaded', function() {
    document.getElementById("loginForm").addEventListener("submit", function(event) {
        event.preventDefault(); // Evitar el envío del formulario predetermine

        var username = document.getElementById("username").value;
        var password = document.getElementById("password").value;

        // Crear objeto de datos con las credenciales
        var credentials = {
            username: username,
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
            // Convertir la respuesta a JSON y retornarla
            return response.json();
        })
        .then(data => {
            // Manejar la respuesta del servidor y extraer el token JWT
            const jwtToken = data.token;

            // Almacena el token JWT en localStorage
            localStorage.setItem('jwtToken', jwtToken);

            // Redirigir a la página de perfil del usuario
            window.location.href = '/user/profile';
        })
        .catch(error => {
            console.error('Error:', error);
            // Manejar errores
            throw new Error('Error brother');
        });
    });
});

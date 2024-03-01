
    document.getElementById('registrationForm').addEventListener('submit', function(event) {
        event.preventDefault(); // Evita que se envíe el formulario de manera predeterminada

        // Obtener datos del formulario
        const formData = new FormData(this);

        // Convertir los datos del formulario en un objeto JSON
        const userData = {};
        formData.forEach(function(value, key) {
            userData[key] = value;
        });

        // Enviar datos al servidor
        fetch('/auth/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userData)
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Error en la solicitud de registro');
            }
            return response.json();
        })
        .then(data => {
            window.location.href = '/auth/login'; // Redireccionar a la página de inicio de sesión después del registro exitoso
        })
        .catch(error => {
            console.error('Error en el registro:', error);
        });
    });
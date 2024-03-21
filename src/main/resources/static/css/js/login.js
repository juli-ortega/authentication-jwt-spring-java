document.addEventListener('DOMContentLoaded', function() {

    // Agregar un evento que se activará cuando se muestre el modal
    var myModal = document.getElementById('myModal');
    var myInput = document.getElementById('myInput');

    myModal.addEventListener('shown.bs.modal', function () {
        myInput.focus();
    });

    // Agregar evento de envío al formulario
    document.addEventListener("submit", function(event) {
        // Verificar si el formulario enviado está dentro de un popup
        if (event.target.classList.contains("popup-form")) {
            // Evitar que el formulario se envíe de manera predeterminada
            event.preventDefault();

            // Obtener referencia al formulario
            var form = event.target;

            var email = form.querySelector("#email").value;
            var password = form.querySelector("#password").value;

            // Crear un objeto de datos con las credenciales del usuario
            var credentials = {
                email: email,
                password: password
            };

            // Realizar una solicitud HTTP POST al endpoint de autenticación
            fetch('/auth/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(credentials)
            })
            .then(response => {
                // Verificar si la respuesta es exitosa
                if (!response.ok) {
                    throw new Error('Error en la solicitud de inicio de sesión');
                }

            })
            .catch(error => {
                // Capturar y manejar errores
                console.error('Error en la solicitud de inicio de sesión:', error.message);
            });
        }
    });
});

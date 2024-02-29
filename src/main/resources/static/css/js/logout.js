// logout.js
document.getElementById('logoutButton').addEventListener('click', function() {
    fetch('/auth/logout', {
        method: 'POST',
        credentials: 'same-origin',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => {
        if (response.ok) {
            console.log('Sesión cerrada exitosamente');
            // Redireccionar a la página de inicio de sesión u otra página
            window.location.href = '/auth/login';
        } else {
            console.error('Error al cerrar sesión:', response.status);
        }
    })
    .catch(error => {
        console.error('Error al cerrar sesión:', error);
    });
});
document.addEventListener('DOMContentLoaded', function() {
    // Obtener referencias a los elementos relevantes
    const openLoginPopup = document.getElementById('openLoginPopup');
    const modalContainer = document.getElementById('modal_container');

    var myModal = document.getElementById('myModal')
    var myInput = document.getElementById('myInput')

myModal.addEventListener('shown.bs.modal', function () {
    myInput.focus()
})
    // Agregar un event listener al enlace de inicio de sesión para abrir el pop-up
/*    myInput.addEventListener('click', function(event) {
        event.preventDefault(); // Evitar que el enlace navegue a otra página
        myModal.toggle(); // Mostrar el pop-up
        loadPopupContent(); // Cargar el contenido del pop-up
    });*/

    // Agregar un event listener al botón de cerrar del pop-up para cerrarlo
    /*const close = document.getElementById('close');
    close.addEventListener('click', function() {
        modalContainer.classList.remove('show');
    });*/
});

function loadPopupContent() {
    // Aquí debes colocar el código para cargar el contenido del pop-up
    // Por ejemplo, puedes hacer una solicitud AJAX para obtener el contenido del servidor
    // y luego colocar ese contenido en el elemento HTML adecuado dentro del pop-up
    var popupContent = document.getElementById("popup-content");
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                popupContent.innerHTML = xhr.responseText;
            } else {
                console.error("Error al cargar el contenido del pop-up");
            }
        }
    };
    xhr.open("GET", "/auth/login", true); // Especifica la ruta correcta para obtener el contenido
    xhr.send()
}
loadPopupContent();

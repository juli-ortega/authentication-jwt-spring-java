console.log("homero-1")
document.addEventListener('DOMContentLoaded', (event) => {
    console.log("omaiga");
});
console.log("homero");
const info = document.querySelector(".informacion");
const modal_container = document.getElementById('modal_container');
const close = document.getElementById('close');
const open = document.getElementById('open');
/*function modalContainerToggle() {
    console.log('omaiga');
    modal_container.classList.add('show');
    loadPopupContent();
};
*/
console.log("homero2");

open.addEventListener('click', () => {
    modal_container.classList.add('show');
    loadPopupContent();
});
console.log("homero3");
close.addEventListener('click', () => {
    modal_container.classList.remove('show');
});

function loadPopupContent() {
    console.log('works?');
    var popupContent = document.getElementById("popup-content");
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                popupContent.innerHTML = xhr.responseText;
                popupContent.style.display = ".popup-content"; // Mostrar el contenido del menú desplegable
            } else {
                console.error("Error al cargar la página externa");
            }
        }
    };
    xhr.open("GET", "/user/login", true);
    xhr.send();
};
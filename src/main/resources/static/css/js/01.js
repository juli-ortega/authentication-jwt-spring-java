const nav = document.querySelector(".nav")

window.addEventListener("scroll", function () {
    nav.classList.toggle("active", window.scrollY > 0)
})

const info = document.querySelector(".informacion")

function loadPopupContent(pageUrl) {
    var popupContent = document.getElementById("popup-content");
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                popupContent.innerHTML = xhr.responseText;
                popupContent.style.display = "block"; // Mostrar el contenido del menú desplegable
            } else {
                console.error("Error al cargar la página externa");
            }
        }
    };
    xhr.open("GET", pageUrl, true);
    xhr.send();
}
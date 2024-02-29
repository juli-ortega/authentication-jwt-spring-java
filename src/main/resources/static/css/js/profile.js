const token = localStorage.getItem('token');

fetch('/user/profile', {
  method: 'GET',
  headers: {
    'Authorization': `Bearer ${token}`
  }
})
.then(response => {
})
.catch(error => {
  // Manejar errores aquí
});
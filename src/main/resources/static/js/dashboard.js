/**
 * 
 */

var usuariosBtn = document.getElementById('usuarios-tab');
var clientesBtn = document.getElementById('clientes-tab');

var usuariosContainer = document.getElementById('usuarios');
var clientesContainer = document.getElementById('clientes');


if (usuariosBtn) {
	usuariosBtn.addEventListener('click', function() {
		usuariosContainer.classList.add("show");
		usuariosContainer.classList.add("active");
		clientesContainer.classList.remove("show");
		clientesContainer.classList.remove("active");
	});
}

if (clientesBtn) {
	clientesBtn.addEventListener('click', function() {
		clientesContainer.classList.add("show");
		clientesContainer.classList.add("active");
		usuariosContainer.classList.remove("show");
		usuariosContainer.classList.remove("active");
	});
}
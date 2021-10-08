var toastDelete = document.getElementById('deleteToast');

var password = '';

function eliminar(cedula){
	console.log('eliminar ', cedula);
	
	fetch('http://localhost:8080/usuarios/eliminar/' + cedula, {
			method: 'DELETE'
		})
		.then(res => {
			console.log(res);
			if(res.status == 200)
				location.reload();
		});
	
	var toast = new bootstrap.Toast(toastDelete)
    toast.show()
}


function editar(id, cedula_usuario, nombre_usuario, email_usuario, usuario, psw){
	document.getElementById('id').value = id;
	document.getElementById('cedula_usuario2').value = cedula_usuario;
	document.getElementById('email_usuario2').value = email_usuario;
	document.getElementById('nombre_usuario2').value = nombre_usuario;
	document.getElementById('usuario2').value = usuario;
	password = psw;
}

$(document).ready(function(){
	console.log('ready');		
		
		const formularioEditar = document.getElementById('modifyUserForm');
		formularioEditar.addEventListener('submit', function(event){
			console.log('submit editar');
			
			event.preventDefault();
			
			var id = document.getElementById('id').value;
			id = parseFloat(id, 10);
			var cedula = document.getElementById('cedula_usuario2').value;
			cedula = parseFloat(cedula, 10);
			var email_usuario = document.getElementById('email_usuario2').value;
			var nombre_usuario = document.getElementById('nombre_usuario2').value;
			//var password = document.getElementById('password2').value;
			var usuario = document.getElementById('usuario2').value;
			
			var datos = {
					"id": id,
					"cedula_usuario": cedula,
					"email_usuario": email_usuario,
					"nombre_usuario": nombre_usuario,
					"password": password,
					"usuario": usuario
			}
			
			console.log(datos);
			fetch('http://localhost:8080/usuarios/actualizar', {
				method: 'PUT',
				body: JSON.stringify(datos),
				headers: {
				      'Content-Type': 'application/json'
				}
			})
			.then(res => {
				console.log(res);
				if(res.status == 200)
					location.reload();
			});
		});
		
	});
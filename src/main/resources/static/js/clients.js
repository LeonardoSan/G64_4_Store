function eliminar(id) {
	fetch('http://localhost:8080/clientes/eliminar/' + id, {
		method: 'DELETE'
	})
	.then(res => {
		if(res.status == 200)
			location.reload();
	})
}

function editar(id, cedula, nombre, email, direccion, telefono) {
	document.getElementById('id').value = id;
	document.getElementById('cedula_cliente').value = cedula;
	document.getElementById('nombre_cliente').value = nombre;
	document.getElementById('email_cliente').value = email;
	document.getElementById('direccion_cliente').value = direccion;
	document.getElementById('telefono_cliente').value = telefono;	
}

$(document).ready(function(){
	console.log('ready');
	
	const formularioEditar = document.getElementById('modifyClientForm');
	
	formularioEditar.addEventListener('submit', function(event){
		console.log('submit editar');
		event.preventDefault();
		
		var id = document.getElementById('id').value;
		id = parseFloat(id, 10);
		
		var cedula = document.getElementById('cedula_cliente').value;
		cedula = parseFloat(cedula, 10);
		
		var email = document.getElementById('email_cliente').value;
		var nombre = document.getElementById('nombre_cliente').value;
		var direccion = document.getElementById('direccion_cliente').value;
		var telefono = document.getElementById('telefono_cliente').value;
		
		var datos = {
			"id": id,
			"cedula": cedula,
			"nombre": nombre,
			"direccion": direccion,
			"telefono": telefono,
			"email": email
		}
		
		console.log(datos);
		
		fetch('http://localhost:8080/clientes/actualizar', {
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
	})
	
}); 

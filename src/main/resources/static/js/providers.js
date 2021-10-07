var toastDelete = document.getElementById('deleteToast');
var toastTrigger = document.getElementById('deleteToastBtn');

function eliminar(id){
	console.log('eliminar ', id);
	
	fetch('http://localhost:8080/proveedores/eliminar/' + id, {
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


function editar(id, nit, nombre, direccion, ciudad, telefono){
	document.getElementById('id2').value = id;
	document.getElementById('nombre_proveedor2').value = nombre;
	document.getElementById('nit_proveedor2').value = nit;
	document.getElementById('telefono_proveedor2').value = telefono;
	document.getElementById('ciudad_proveedor2').value = ciudad;
	document.getElementById('direccion_proveedor2').value = direccion;
}

$(document).ready(function(){
	console.log('ready');		
		
		const formularioEditar = document.getElementById('modifyProviderForm');
		formularioEditar.addEventListener('submit', function(event){
			console.log('submit editar');
			
			event.preventDefault();
			
			var id = document.getElementById('id2').value;
			id = parseFloat(id, 10);
			var nit = document.getElementById('nit_proveedor2').value;
			nit = parseFloat(nit, 10);
			var nombre = document.getElementById('nombre_proveedor2').value;
			var telefono = document.getElementById('telefono_proveedor2').value;
			var ciudad = document.getElementById('ciudad_proveedor2').value;
			var direccion = document.getElementById('direccion_proveedor2').value;
			
			var datos = {
					"id": id,
					"nit": nit,
					"nombre": nombre,
					"direccion": direccion,
					"telefono": telefono,
					"ciudad": ciudad
			}
			
			console.log(datos);
			fetch('http://localhost:8080/proveedores/actualizar', {
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
		
		const formularioAgregar = document.getElementById('addProviderForm');
		formularioAgregar.addEventListener('submit', function(event){
			console.log('submit add');
			
			event.preventDefault();
			
			var nit = document.getElementById('nit_proveedor').value;
			nit = parseFloat(nit, 10);
			var nombre = document.getElementById('nombre_proveedor').value;
			var telefono = document.getElementById('telefono_proveedor').value;
			var ciudad = document.getElementById('ciudad_proveedor').value;
			var direccion = document.getElementById('direccion_proveedor').value;
			
			var datos = {
					"nit": nit,
					"nombre": nombre,
					"direccion": direccion,
					"telefono": telefono,
					"ciudad": ciudad
			}
			
			console.log(datos);
			fetch('http://localhost:8080/proveedores/guardar', {
				method: 'POST',
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
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
}

$(document).ready(function(){
	console.log('ready');
})
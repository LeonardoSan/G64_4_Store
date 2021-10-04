var toastDelete = document.getElementById('deleteToast')
var toastTrigger = document.getElementById('deleteToastBtn')
var modalDelete = new bootstrap.Modal(document.getElementById('deleteModal'))

function showModal(modal) {
	modal.show()
}

function eliminar(cedula){
	console.log('eliminar ', cedula);
	
	// showModal(modalDelete)
	
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



$(document).ready(function(){
	console.log('ready');
})
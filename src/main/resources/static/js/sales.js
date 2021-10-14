/**
 * 
 */
buttonSearchClient = document.getElementById('btnCedulaSearch');

StaticProductAmount1 = document.getElementById('staticProductAmount1');
StaticProductAmount2 = document.getElementById('staticProductAmount2');
StaticProductAmount3 = document.getElementById('staticProductAmount3');

var gCedulaCliente;

async function searchClient() {
	cedula = document.getElementById('cedulaSearch').value;
	
	// Si no se ingresa una cedula, returna una alerta al usuario.
	if (cedula === '') {
		return alert('Debes ingresar la cédula de un cliente.');
	}
	
	// Pequeño modulo del botón para mostrar que está cargando
	var wrapper = document.createElement('span');
	wrapper.innerHTML = '<span class="spinner-grow spinner-grow-sm" role="status" aria-hidden="true"></span>'
	buttonSearchClient.append(wrapper);
	
	
	// Hace el llamado a la API
	const request = await fetch('http://localhost:8080/clientes/consultarPorCedula/' + cedula, {
        method: 'GET',
        headers:         {'Accept': 'application/json',
        'Content-Type': 'application/json',}
    });
    const cliente = await request.json();
	
    document.getElementById('staticClient').value = cliente['nombre'];
	buttonSearchClient.removeChild(wrapper);
	gCedulaCliente = cliente['cedula'];
}


async function searchProduct() {
	producto = document.getElementById('productSearch1').value;
	
	const request = await fetch('http://localhost:8080/productos/consultar/' + producto, {
        method: 'GET',
        headers:         {'Accept': 'application/json',
        'Content-Type': 'application/json',}
    });
    const producto = await request.json();

	document.getElementById('staticProductName1').value = producto['nombre'];
}

StaticProductAmount1.addEventListener('focusout', (event) => {
	// TO-DO Calculos de IVA y valor total 
})

StaticProductAmount2.addEventListener('focusout', (event) => {
	// TO-DO Calculos de IVA y valor total 
})

StaticProductAmount3.addEventListener('focusout', (event) => {
	// TO-DO Calculos de IVA y valor total 
})

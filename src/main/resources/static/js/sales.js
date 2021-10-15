/**
 * 
 */
buttonSearchClient = document.getElementById('btnCedulaSearch');

StaticProductAmount1 = document.getElementById('staticProductAmount1');
StaticProductAmount2 = document.getElementById('staticProductAmount2');
StaticProductAmount3 = document.getElementById('staticProductAmount3');

var gCedulaCliente;

function getElement(elementId) {
	return document.getElementById(elementId);
}

async function searchClient() {
	cedula = getElement('cedulaSearch').value;
	
	// Si no se ingresa una cedula, retorna una alerta al usuario.
	if (cedula === '') return alert('Debes ingresar la cédula de un cliente.');
	
	// Pequeño modulo del botón para mostrar que está cargando
	var wrapper = document.createElement('span');
	wrapper.innerHTML = '<span class="spinner-grow spinner-grow-sm" role="status" aria-hidden="true"></span>'
	buttonSearchClient.append(wrapper);
	
	
	// Hace el llamado a la API
	const datosCliente = await fetch('http://localhost:8080/clientes/consultarPorCedula/' + cedula, {
        method: 'GET',
        headers: {'Accept': 'application/json',
        'Content-Type': 'application/json'}
    })
    const cliente = await datosCliente.json().catch(e => { // Si hay algún error
		// console.log(e);
		
		// Detiene la animacion de cargando
		buttonSearchClient.removeChild(wrapper);
		return alert('Lo sentimos, el cliente no existe. Verifica e inténtalo de nuevo.')
	});
	
	// Si se tienen los datos del cliente, se utilizan
	if (cliente) {
		document.getElementById('staticClient').value = cliente['nombre'];
		buttonSearchClient.removeChild(wrapper);
		gCedulaCliente = cliente['cedula'];
	}
}


async function searchProduct(index) {
	switch(index) {
		case 1:
			codigoProducto = getElement('productSearch1'.value);
			break;
		default:
			codigoProducto = null;
	}
	
	if (codigoProducto === '') {
		
	}
	
	const datosProducto = await fetch('http://localhost:8080/productos/consultar/' + codigoProducto, {
        method: 'GET',
        headers: {'Accept': 'application/json',
        'Content-Type': 'application/json',}
    });

    const producto = await datosProducto.json();
	console.log(producto);

	document.getElementById('staticProductName1').value = producto['nombre'];
}

StaticProductAmount1.addEventListener('focusout', (event) => {
	// Se obtienen los datos del producto
	var amount = parseInt(StaticProductAmount1.value, 10);
	var costPerProduct = 1000;
	
	var totalCost = amount * costPerProduct;
	
	document.getElementById('staticProductTotal1').value = '$ ' + (totalCost).toString();
})

StaticProductAmount2.addEventListener('focusout', (event) => {
	
})

StaticProductAmount3.addEventListener('focusout', (event) => {
	// TO-DO Calculos de IVA y valor total 
})

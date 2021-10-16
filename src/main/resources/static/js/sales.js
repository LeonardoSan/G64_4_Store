/**
 * 
 */
buttonSearchClient = document.getElementById('btnCedulaSearch');

StaticProductAmount1 = document.getElementById('staticProductAmount1');
StaticProductAmount2 = document.getElementById('staticProductAmount2');
StaticProductAmount3 = document.getElementById('staticProductAmount3');

var gCliente;
var gUsuario;

var gTotalIva = 0.0; // <! Acumulador del total IVA
var gValorVenta = 0.0; // 
var gTotalVenta = 0.0; // <! Acumulador del total de la venta

var gDatosProductos = [];
					// Total    -     IVA    -   Subtotal
var gDatosVenta = [[0, 0, 0, 0], [0, 0, 0, 0], [0, 0, 0, 0]];

var gConsecutivo;

var buttonConfirm = false;



async function searchClient() {
	cedula = getElement('cedulaSearch').value;
	
	// Si no se ingresa una cedula, retorna una alerta al usuario.
	if (cedula === '') {
		gCedulaCliente = 0;
		getElement('staticClient').value = '';
		checkAvailability();
		return alert('Debes ingresar la cédula de un cliente.');
	}
	
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
		// Detiene la animacion de cargando
		buttonSearchClient.removeChild(wrapper);
		return alert('Lo sentimos, el cliente no existe. Verifica e inténtalo de nuevo.')
	});
	
	// Si se tienen los datos del cliente, se utilizan
	if (cliente) {
		getElement('staticClient').value = cliente['nombre'];
		buttonSearchClient.removeChild(wrapper);
		gCliente = cliente;
		checkAvailability();
	}
}


async function searchProduct(index) {
	switch(index) {
		case 1:
			codigoProducto = getElement('productSearch1').value;
			break;
		case 2:
			codigoProducto = getElement('productSearch2').value;
			break;
		case 3:
			codigoProducto = getElement('productSearch3').value;
			break;
	}
		
	if (codigoProducto === '') return alert('Debes ingresar el código de un producto.');
	
	const datosProducto = await fetch('http://localhost:8080/productos/consultar/' + codigoProducto, {
        method: 'GET',
        headers: {'Accept': 'application/json',
        'Content-Type': 'application/json',}
    });

    const lDatosProducto = await datosProducto.json();
	
	gDatosProductos[index] = lDatosProducto; 
	switch(index) {
		case 1:
			getElement('staticProductName1').value = lDatosProducto['nombre'];
			getElement('staticProductAmount1').value = 1;
			
			break;
		case 2:
			getElement('staticProductName2').value = lDatosProducto['nombre'];
			getElement('staticProductAmount2').value = 1;
			break;
		case 3:
			getElement('staticProductName3').value = lDatosProducto['nombre'];
			getElement('staticProductAmount3').value = 1;
			break;
	}
	
	calculateProductTotal(index, gDatosProductos[index]['precio_venta']);
	checkAvailability();
}

async function saveOrder() {
	// Obtengo la cedula del usuario del HTML oculto
	lCedulaUsuario = parseInt(getElement('cedulaUsuario').textContent);
	
	// Obtengo los datos del usuario haciendo un llamado a la API
	lDatosUsuario = await fetch('http://localhost:8080/usuarios/consultar/' + lCedulaUsuario, {
        method: 'GET',
        headers: {'Accept': 'application/json',
        'Content-Type': 'application/json',}
    });

	// Guardo los datos del usuario en una variable global
	gUsuario = await lDatosUsuario.json();
	
	// Construcción del JSON con los datos de la VENTA
	var datosVentaEnviados = {
		"iva_venta": gTotalIva,
		"valor_venta": gValorVenta,
		"total_venta": gTotalVenta,
		"cliente": gCliente,
		"usuario": gUsuario
	}
	
	
	// Hago el llamado a la API enviando el objeto datosVenta y returna el objeto venta (con el consecutivo)
	const datosVentaRecibidos = await fetch('http://localhost:8080/ventas/guardar/', {
		method: 'POST',
		body: JSON.stringify(datosVentaEnviados),
		headers: {
			'Accept': 'application/json',
			'Content-Type': 'application/json'
		}
	});
	const venta = await datosVentaRecibidos.json();

	// Hago la iteración de cada producto para crear cada detalle_venta
	gDatosProductos.forEach((producto, index) => {
		if(producto != null) {
			amount = gDatosVenta[0][index] / producto['precio_venta'];
			
			console.log(gDatosVenta);
			var datoDetalleVentas = {
				"cantidad_producto": amount,
				"valor_total": gDatosVenta[0][index],
				"valor_iva": gDatosVenta[1][index],
				"valor_venta": gDatosVenta[2][index],				
				"ventas": venta,
				"productos": producto				
			}
			
			fetch('http://localhost:8080/detalle_ventas/guardar/', {
				method: 'POST',
				body: JSON.stringify(datoDetalleVentas),
				headers: {
					'Accept': 'application/json',
					'Content-Type': 'application/json'
				}
			}); 
		}
	});
	
	// Se refresca la pagina cuando termina
	location.href = '/dash/ventas?success&order=' + venta['codigo'];
}

function updateSubtotalVariable(index, subtotal) {
	gDatosVenta[2][index] = subtotal;
	console.log(gDatosVenta);
	gValorVenta = gDatosVenta[2][1] + gDatosVenta[2][2] + gDatosVenta[2][3];
	getElement('staticSubtotal').value = '$ ' + gValorVenta;
}

function updateIvaVariable(index, iva) {
	gDatosVenta[1][index] = iva;
	gTotalIva = gDatosVenta[1][1] + gDatosVenta[1][2] + gDatosVenta[1][3];
	getElement('staticIVA').value = '$ ' + gTotalIva;
}

function updateTotalVariable(index, total) {
	gDatosVenta[0][index] = total;
	gTotalVenta = gDatosVenta[0][1] + gDatosVenta[0][2] + gDatosVenta[0][3];
	getElement('staticTotal').value = '$ ' + gTotalVenta;
}

function calculateProductTotal(index, costPerProduct) {
	switch(index) {
		case 1:
			if (getElement('staticProductName1').value === '') break;
			var amount = parseInt(StaticProductAmount1.value, 10);
			var totalCost = amount * costPerProduct;
			
			var iva = gDatosProductos[index]['precio_venta'] - gDatosProductos[index]['precio_compra'];
			var totalIva = amount * iva;

			var totalSubtotal = totalCost - totalIva;
			
			updateSubtotalVariable(index, totalSubtotal);
			updateTotalVariable(index, totalCost);
			updateIvaVariable(index, totalIva);
			getElement('staticProductTotal1').value = '$ ' + (totalCost).toString();
			
			break;
		case 2:
			if (getElement('staticProductName2').value === '') break;
			var amount = parseInt(StaticProductAmount2.value, 10);
			var totalCost = amount * costPerProduct;
			
			var iva = gDatosProductos[index]['precio_venta'] - gDatosProductos[index]['precio_compra'];
			var totalIva = amount * iva;
			
			var totalSubtotal = totalCost - totalIva;
			
			updateSubtotalVariable(index, totalSubtotal);
			updateTotalVariable(index, totalCost);
			updateIvaVariable(index, totalIva);
			getElement('staticProductTotal2').value = '$ ' + (totalCost).toString();
			
			
			break;
		case 3:
			if (getElement('staticProductName2').value === '') break;
			var amount = parseInt(StaticProductAmount3.value, 10);
			var totalCost = amount * costPerProduct;
			
			var iva = gDatosProductos[index]['precio_venta'] - gDatosProductos[index]['precio_compra'];
			var totalIva = amount * iva;
			
			var totalSubtotal = totalCost - totalIva;
			
			updateSubtotalVariable(index, totalSubtotal);
			updateTotalVariable(index, totalCost);
			updateIvaVariable(index, totalIva);
			getElement('staticProductTotal3').value = '$ ' + (totalCost).toString();
			
			break;
	}
}

/************************************************
 *
 *              Funciones CALLBACKS
 *
 ************************************************/

StaticProductAmount1.addEventListener('focusout', (event) => {
	if (getElement('staticProductName1').value === '') return;
	calculateProductTotal(1, gDatosProductos[1]['precio_venta']);
})

StaticProductAmount2.addEventListener('focusout', (event) => {
	if (getElement('staticProductName2').value === '') return;
	calculateProductTotal(2, gDatosProductos[2]['precio_venta']);
})

StaticProductAmount3.addEventListener('focusout', (event) => {
	if (getElement('staticProductName3').value === '') return;
	calculateProductTotal(3, gDatosProductos[3]['precio_venta']);
})

getElement('confirmButton').addEventListener('click', (event) => {
	
})

/************************************************
 *
 *                Funciones STOCK
 *
 ************************************************/

function toggleButton(btnId, status=0) {
	if (status)
		getElement(btnId).classList.add('disabled');
	else
		getElement(btnId).classList.remove('disabled');	
}

function getElement(elementId) {
	return document.getElementById(elementId);
}

function checkAvailability() {
	if (gCliente['cedula'] && gTotalVenta) {
		toggleButton('confirmButton');
		buttonConfirm = true;
	}
	else {
		if (buttonConfirm) {
			toggleButton('confirmButton', 1);
			buttonConfirm = false;
		}
			
	}
}

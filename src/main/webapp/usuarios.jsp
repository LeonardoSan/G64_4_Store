<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script>
	var usuarios = $.ajax({
		type: "GET",
		url: "http://localhost:8080/usuarios/listar",
		success: function(data){
			$.each(data, function(i, item){
				lista = document.getElementById("myTable");
				var tr = document.createElement("tr");
				var cedula = document.createElement("td");
				cedula.innerHTML = item.cedula_usuario;
				var email = document.createElement("td");
				email.innerHTML = item.email_usuario;
				var nombre = document.createElement("td");
				nombre.innerHTML = item.nombre_usuario;
				var usuario = document.createElement("td");
				usuario.innerHTML = item.usuario;
				var password = document.createElement("td");
				password.innerHTML = item.password;
				var editar = document.createElement("td");
				editar.innerHTML = "<a href='#'>Editar</a>";
				var eliminar = document.createElement("td");
				eliminar.innerHTML = "<a href='#' onclick='eliminar("+item.cedula_usuario+")'>Eliminar</a>";
				
				lista.appendChild(tr);
				tr.appendChild(cedula);
				tr.appendChild(nombre);
				tr.appendChild(email);
				tr.appendChild(usuario);
				tr.appendChild(password);
				tr.appendChild(editar);
				tr.appendChild(eliminar);
			});
		}
	});
	
	function eliminar(cedula){
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
		const formulario = document.getElementById('formulario');
		formulario.addEventListener('submit', function(event){
			console.log('submit');
			
			event.preventDefault();
			
			var cedula = document.getElementById('cedula_usuario').value;
			cedula = parseInt(cedula, 10);
			var email_usuario = document.getElementById('email_usuario').value;
			var nombre_usuario = document.getElementById('nombre_usuario').value;
			var password = document.getElementById('password').value;
			var usuario = document.getElementById('usuario').value;
			
			var datos = {
					"cedula_usuario": cedula,
					  "email_usuario": email_usuario,
					  "nombre_usuario": nombre_usuario,
					  "password": password,
					  "usuario": usuario
			}
			fetch('http://localhost:8080/usuarios/guardar', {
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
	})
</script>
</head>
<body>
	<h1>Usuarios</h1>
	<table border="1">
		<thead>
			<tr>Cédula</tr>
			<tr>Nombre</tr>
			<tr>Correo</tr>
			<tr>Usuario</tr>
			<tr>Contraseña</tr>
			<tr>Editar</tr>
			<tr>Eliminar</tr>
		</thead>
		<tbody id="myTable">
			
		</tbody>
	</table>
	
	<h2>Agregar usuarios</h2>
	<form id="formulario">
		<label for="cedula_usuario">Cédula</label>
		<input type="text" id="cedula_usuario" name="cedula_usuario">
		<br>
		<label for="email_usuario">E-mail</label>
		<input type="text" id="email_usuario" name="email_usuario">
		<br>
		<label for="nombre_usuario">Nombre</label>
		<input type="text" id="nombre_usuario" name="nombre_usuario">
		<br>
		<label for="usuario">Usuario</label>
		<input type="text" id="usuario" name="usuario">
		<br>
		<label for="password">Password</label>
		<input type="text" id="password" name="password">
		<br>
		<button type="submit">Agregar</button>
	</form>
</body>
</html>
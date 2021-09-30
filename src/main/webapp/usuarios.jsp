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
				editar.innerHTML = "<a href='actualizar/"+item.cedula_usuario+"'>Editar</a>";
				var eliminar = document.createElement("td");
				eliminar.innerHTML = "<a href='eliminar/"+item.cedula_usuario+"'>Eliminar</a>";
				
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
</script>
</head>
<body>
	<h1>Usuarios</h1>
	<table>
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
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es_ES">
<head>
	<meta charset="utf-8">
	<title>Iniciar sesión | Tienda genérica</title>
	
	<link rel="stylesheet" href="./css/main-login.css">
	<link rel="shortcut icon" href="./src/main/resources/static/favicon.ico">
	
	<script src="https://kit.fontawesome.com/5a291bbc37.js" crossorigin="anonymous"></script>
	
	<meta property="og:site_name" content="Tienda Genérica">
    <meta property="og:type" content="shop">
    <meta property="og:title" content="Iniciar sesión | Tienda Genérica">
    <meta property="og:url" content="">
    <meta property="og:image" content="">
    <meta property="og:description" content="Proyecto web del Ciclo 3 de MisionTIC 2022 - Grupo 4.">
</head>
<body>
	
	<div class="container">
        <div class="forms-container">
            <div class="signin">
                <form action="" method="POST" class="sign-in-form">
                    <div class="logo noselect">
                    <a href="./">
                        <img class="logo-img" src="https://cdn.glitch.com/035904e0-5072-486e-aa88-45d03ef24105%2FstoreLogo.png?v=1632054361351">
                    </a>
                    </div>
                    <h2 class="title">Iniciar sesión</h2>
                    <div class="input-field">
                        <i class="fas fa-user"></i>
                        <input type="text" placeholder="Nombre de usuario">
                    </div>
                    <div class="input-field">
                        <i class="fas fa-lock"></i>
                        <input type="password" placeholder="Contraseña">
                    </div>
                    
                    <input type="submit" value="Iniciar sesión" class="btn solid">

                    <a href="/ayuda" target="_blank" class="problem-text"><p>Tengo problemas para iniciar sesión</p></a>
                </form>
            </div>
        </div>

        <div class="panels-container">
            <div class="panel left-panel">
                <div class="content">
                    <h3>Nuevo aquí ?</h3>
                    <p>Descubre la facilidad de administrar tu tienda virtual y disfruta las oportunidades que te esperan en tu tiempo libre</p>
                    <!--<button class="btn transparent" id="sign-up-btn">Unirse</button>-->

                    <img src="https://cdn.glitch.com/035904e0-5072-486e-aa88-45d03ef24105%2Fundraw_web_shopping_re_owap%20(1).svg?v=1632054258803" class="image" alt="">
                </div>
            </div>
        </div>
    </div>
    
    <script src="/js/login.js"></script>

</body>
</html>
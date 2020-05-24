<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="modele.*" %>

<html>
<head>
<title>Conditions d'utilisations</title>
<link rel="shortcut icon" type="img/png"
	href="http://localhost:8080/Football/img/flavicon.png">
<link rel="stylesheet" href="style/bootstrap/css/bootstrap.min.css">
<script src="style/bootstrap/js/bootstrap.min.js"></script>
<link href="style/css/conditions.css" rel="stylesheet">
</head>
<body>
	<nav class="navbar navbar-expand-md navbar-light bg-light sticky-top">
		<div class="container-fluid">
			<a class="navbar-brand"
				href="http://localhost:8080/Football/index.jsp"> <img
				src="img/foot_logo.jpg"></a>
		</div>
	</nav>
	<div id="wrap">
		<div id="main" class="container">
			<c:if test="${sessionScope.connected!='true'}">
				<div class="col-md-12">
					<form class="form" role="form" method="get"
						action="http://localhost:8080/Football/UtilisateurServlet"
						id="login-nav">
						<div class="form-group">
							<label for="email">Email :</label> <input type="text"
								class="form-control" id="email" name="email" placeholder="Email"
								required>
						</div>
						<div class="form-group">
							<label for="password">Mot de passe :</label> <input
								type="password" class="form-control" id="password" name="mdp"
								placeholder="Mot de passe" required>
						</div>
						<div class="form-group">
							<button type="submit" class="btn btn-primary btn-block">Se
								connecter</button>
						</div>
						<input type="hidden" name="hidden" value="authentification">
					</form>
				</div>
				<div class="bottom text-center">
					Nouveau ici ? <a
						href="http://localhost:8080/Football/Inscription.html"><b>Rejoignez-nous</b></a>
				</div>
			</c:if>
			<c:if test="${sessionScope.connected=='true'}">
				<div class="col-md-12">
					<p>Vous êtes déjà connecté.</p>
				</div>
				<div class="col-md-12">
					<a href="http://localhost:8080/Football/index.jsp"
						class="btn btn-primary">Retour à l'accueil</a>
				</div>
			</c:if>
		</div>
	</div>
	<!--Footer-->
	<footer>
		<div class="container-fluid">
			<div class="container">
				<div class="row text-center">
					<div class="col-md-6">
						<img src="img/foot_logo.jpg">
						<hr class="light">
						<p>Paris, France</p>
					</div>
					<div class="col-md-6">
						<hr class="light">
						<h5>Contactez-nous</h5>
						<hr class="light">
						<p>E-mail: football_score@gmail.com</p>
						<p>Tel: +33 658 087 841</p>
					</div>
					<div class="col-12">
						<hr class="light-100">
						<h5>&copy; footballscore.com</h5>
					</div>
				</div>
			</div>
		</div>
	</footer>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="modele.*" %>

<!DOCTYPE html>
<html>
	<head>
		<title>Live score football</title>
		<meta charset="utf-8">
		<link rel="shortcut icon" type="img/png" href="img/flavicon.jpg">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script src="style/bootstrap/js/jquery.min.js"></script>
		<link rel="stylesheet" href="style/bootstrap/css/bootstrap.min.css">
		<script src="style/bootstrap/js/bootstrap.min.js"></script>
		<link href="style/css/index.css" rel="stylesheet">
	</head>
	<body>
		<!--Navigation-->
		<nav class="navbar navbar-expand-md navbar-light bg-light sticky-top">
			<div class="container-fluid">
				<a class="navbar-brand" href="http://localhost:8080/Football/index.jsp"><img
					src="img/foot_logo.jpg"></a>
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbar">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbar">
					<ul class="navbar-nav ml-auto">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" data-toggle="dropdown"
							data-target="login" href="#"> <c:if
									test="${sessionScope.connected=='true'}">
	                            ${sessionScope.nom} ${sessionScope.prenom} 
	                            </c:if> <c:if test="${sessionScope.connected!='true'}">
	                            Login
	                            </c:if> <span class="caret"></span>
						</a>
							<div class="dropdown-menu" aria-labelledby="login" id="login-dp">
								<div class="row">
									<c:if test="${sessionScope.connected!='true'}">
										<div class="col-md-12">
											<form class="form" role="form" method="get"
												action="http://localhost:8080/Football/UtilisateurServlet"
												id="login-nav">
												<div class="form-group">
													<label for="email">Email :</label> <input
														type="text" class="form-control" id="email" name="email"
														placeholder="Email" required>
												</div>
												<div class="form-group">
													<label for="password">Mot de passe :</label> <input
														type="password" class="form-control" id="password"
														name="mdp" placeholder="Mot de passe" required>
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
											<p>
												<a href="http://localhost:8080/Football/profil.jsp">Profil</a>
											</p>
										</div>
										<div class="col-md-12">
	                                        <form action="http://localhost:8080/Football/UtilisateurServlet" method="get">
	                                        	<input type='hidden' name='hidden' value='deconnexion'/>
	                                            <button type="submit" class="btn-link">
	                                                <span class="nav-label">Deconnexion</span>
	                                            </button>
	                                        </form>
	                                    </div>
									</c:if>
								</div>
							</div>
						</li>
						<li class="nav-item "><a class="nav-link" href="http://localhost:8080/Football/standings.jsp">Standings</a></li>
						<li class="nav-item"><a class="nav-link" href="http://localhost:8080/Football/conditions.html">Conditions générales</a></li>
					</ul>
				</div>
			</div>
		</nav>
	
		<div id="search-bg">
			<div class="container">
				<div class="search">
					<form id="idForm" class="rounded">
						<div class="row p-3 justify-content-around">
						<span class=" col-xm-12 col-md-3"> 
						<select	class="custom-select" name="competition" id="competition">
								<option selected disabled>Compétition</option>
								<option value='Premier League'>Premier League</option>
								<option value='Ligue 1'>Ligue 1</option>
								<option value='Serie A'>Serie A</option>
						</select>
						</span>
						</div>
						<button type=submit id='rechercher' class="btn btn-primary m-1"> Rechercher </button>
					</form>
				</div>
			</div>
		</div>
	
		<!--Three column section-->
		<br>
		<div class="col-12">
			<h4 class="lead text-center">Les plus grands championnats européens</h4>
		</div>
		<br>
		<br>
		<div class="container">
			<div class="row text-center">
				<div class="col-xs-12 col-sm-6 col-md-4">
					<img src="img/pl.png" id="pl">
				</div>
				<div class="col-xs-12 col-sm-6 col-md-4">
					<img src="img/fl1.png" id="fl1">
				</div>
							<div class="col-xs-12 col-sm-6 col-md-4">
					<img src="img/SerieA.png" id="fl1">
				</div>
			</div>
		</div>
		<br>
		<br>
		<div class="container">
			<div class="row text-center">
				<div class="col-xs-12 col-sm-12 col-md-4">
					<img src="img/bundesliga.png" id="bundesliga">
				</div>
				<div class="col-xs-12 col-sm-6 col-md-4">
					<img src="img/LigaNos.png" id="liganos">
				</div>
				<div class="col-xs-12 col-sm-12 col-md-4">
					<img src="img/LaLiga.png" id="laliga">
				</div>
			</div>
		</div>
		<br>
		<br>
		<!--Fixed background-->
		<div id="fixed">
			<div class="container">
				<h1>
					<b>Voir le classement des plus grands championnats <br>
					</b>
				</h1>
				<br> <a href="http://localhost:8080/Football/standings.jsp"><button type="button"
						class="btn btn-primary btn-lg">Standings</button></a>
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
		<script>
			$(document).ready(function(){
				  $("#rechercher").click(function(e){
				     e.preventDefault();
				     $.ajax({
				            url: "http://localhost:8080/Football/RencontreServlet",
				            type: "GET",
				            data: $("#idForm").serialize(),
				            success: function(response) {
				            	console.log(response+'succes');
				                $("#idForm").replaceWith(response);
				            }            
				        });
				  });
				});
		</script>
	</body>
</html>
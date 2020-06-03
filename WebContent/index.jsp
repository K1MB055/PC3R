<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="modele.*" %>

<!DOCTYPE html>
<html>
	<head>
		<title>Live score football</title>
		<meta charset="utf-8">
		<link rel="shortcut icon" type="img/png" href="img/flavicon.png">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script src="style/bootstrap/js/jquery.min.js"></script>
		<link rel="stylesheet" href="style/bootstrap/css/bootstrap.min.css">
		<script src="style/bootstrap/js/bootstrap.min.js"></script>
		<link href="style/css/index.css" rel="stylesheet">
		<link href="style/fa/css/fontawesome-all.min.css" rel="stylesheet">
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
												<a id='profil' href="http://localhost:8080/Football/profil.jsp">Profil</a>
											</p>
										</div>
										<div class="col-md-12">
	                                        <form action="http://localhost:8080/Football/UtilisateurServlet" method="get">
	                                        	<input type='hidden' name='hidden' value='deconnexion'/>
	                                            <button type="submit" class="btn-link">
	                                                Deconnexion
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
				<div class="search text-center">
					<form id="idForm" class="rounded">
						<div class="row p-3 justify-content-around inline">
						<span class=" col-xm-12 col-md-3"> 
						<select	class="custom-select" name="competition" id="competition">
								<option selected disabled>Compétition</option>
								<option value='Premier League'>Premier League</option>
								<option value='Ligue 1'>Ligue 1</option>
								<option value='Serie A'>Serie A</option>
						</select>
						<select	class="custom-select" name="tour" id="tour">
								<option selected disabled>Journée</option>
						</select>
						</span>
						</div>
						<button type=submit id='rechercher' class="btn btn-primary col-xm-12 col-md-3"> Rechercher </button>
					</form>
				</div>
			</div>
		</div>

	<div class="container">
		<!-- Modal -->
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body" align='center'>
						<p class='match'></p>
					</div>
						<div class="actionBox">
							<c:if test="${sessionScope.connected=='true'}">
								<h3 align='center'>Section commentaires </h3>             
								<ul class="commentList">							
								</ul>
								<form  role="form">
									<div class="form-row">
										<div class="col-10">
										<input class="form-control" type="text" name="contenu" id="contenu"
											 placeholder="Votre commentaire" />
										</div>
										<div class="col-auto">
										<button class="btn btn-primary" id='ajouter'>Ajouter</button>
										</div>
									</div>
								</form>
							</c:if>
							<c:if test="${sessionScope.connected!='true'}">
	                    		<h4 align='center'>Il faut être connecté pour acceder à la section commentaire </h4>
	                		</c:if>
						</div>			
				</div>
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
				
				  // remplir le select de journée avec les valeurs allons de 1 - 38.
				  var $select = $("#tour");
			      for (i=1;i<=38;i++){
			          $select.append($('<option></option>').val(i).html(i))
			      }
			      
				  $("#rechercher").click(function(e){
				     e.preventDefault();
				     $.ajax({
				            url: "http://localhost:8080/Football/RencontreServlet",
				            type: "GET",
				            data: $("#idForm").serialize(),
				            success: function(response) {
				                $("#idForm").replaceWith(response);
				            }            
				        });
				  });
				  $("#ajouter").click(function(e){
					     e.preventDefault();				  
					     $.ajax({
					            url: "http://localhost:8080/Football/CommentaireServlet",
					            type: "PUT",
					            data: {contenu : $('#contenu').val(), idRencontre : $('.match').attr('id')},
					            success: function(response) {
					                $(".commentList").html(response);
					                $('.commentList').scrollTop(10000);
					            }            
					        });
					     $('#contenu').val('');
				 });
			});  
			
			$(document).on('submit', '#formEdit', function(e){
				 e.preventDefault();
				 $.ajax({
				 	url: "http://localhost:8080/Football/CommentaireServlet",
				    type: "POST",
			        data: {id : $('#formEdit').closest('li').attr('id'),contenu : $("#inputEdit").val()},
		            success: function(response) {
		            	$('#formEdit').replaceWith(response);
			        }
			     });
			});
			
			$(document).ajaxStop(function() {
				$(".list-group-item-action").click(function(){
					if ($(".commentList")[0]){
				  	     $('.match').text($(this).text());
				  	     $('.match').attr('id',$(this).attr('id'));
					     	$.ajax({
					            url: "http://localhost:8080/Football/CommentaireServlet",
					            type: "GET",
					            data: {idRencontre : $(this).attr('id')},
					            success: function(response) {				     
					                $(".commentList").html(response);
					            }
					         });
					}
				});
				
				$(".edit").click(function(){
					var p = $(this).closest('li').find('p');
					p.replaceWith("<form id ='formEdit'><input type='text' id ='inputEdit' value='"+p.text()+"' /></form>");
				});
					
				$(".supp").click(function(){
						var li = $(this).closest('li');
					    $.ajax({
					    	url: "http://localhost:8080/Football/CommentaireServlet",
					        type: "DELETE",
				            data: {id : li.attr('id')},
			                success: function(response) {
			                	if(response){
				            		li.empty();		     
			                	}
				            }
				         });
				});
			 });
		</script>
	</body>
</html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <title>Editer le profil</title>
        <link rel="shortcut icon" type="img/png"  href="http://localhost:8080/Football/img/flavicon.png">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="style/bootstrap/js/jquery.min.js"></script>
        <link rel="stylesheet" href="style/bootstrap/css/bootstrap.min.css">
        <script src="style/bootstrap/js/bootstrap.min.js"></script>
        <link href="style/css/index.css" rel="stylesheet">
        <link href="style/fa/css/fontawesome-all.min.css" rel="stylesheet">
		<script src="jquery-validate/jquery.validate.js"></script>
        <script type="text/javascript" src="jquery-validate/localization/messages_fr.js"> </script>
    </head>
    <body>
        <c:if test="${sessionScope.connected!='true'}">
                <c:redirect url="authentification.jsp"></c:redirect>
        </c:if>
        
		<!--Navigation-->
		<nav class="navbar navbar-expand-md navbar-light bg-light sticky-top">
			<div class="container-fluid">
				<a class="navbar-brand" href="http://localhost:8080/Football/index.jsp"><img src="img/foot_logo.jpg"></a>
				<button class="navbar-toggler" type="button" data-toggle="collapse"
					data-target="#navbar">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbar">
					<ul class="navbar-nav ml-auto">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" data-toggle="dropdown"
							data-target="login" href="#"> 
								<c:if test="${sessionScope.connected=='true'}">
	                            	${sessionScope.nom} ${sessionScope.prenom} 
	                            </c:if> 
						<span class="caret"></span>
						</a>
							<div class="dropdown-menu" aria-labelledby="login" id="login-dp">
								<div class="row">
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

		<div class="main">
			<div class="container-fluid">
				<div class="mt-2 content">
						<div class="personal-info">
							<h1>Editer mon Profil</h1>
							<hr>
							<div id='reponse'>
							</div>
							<h3>Informations personnelles:</h3>
	
							<form class="form-horizontal bg-light rounded pb-1" role="form" id="idForm">
								<div class="form-group">
									<label class="col-lg-3 control-label">Nom:</label>
									<div class="col-lg-12">
										<input class="form-control" type="text" name="nom" id="nom"
											value="${sessionScope.nom}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-3 control-label">Prenom:</label>
									<div class="col-lg-12">
										<input class="form-control" type="text" name="prenom"
											id="prenom" value="${sessionScope.prenom}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-3 control-label">Email:</label>
									<div class="col-lg-12">
										<input class="form-control" type="text" name="email" id="email"
											value="${sessionScope.email}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label">Mot de passe:</label>
									<div class="col-md-12">
										<input class="form-control" type="password" name="mdp" id="mdp"
											value="" placeholder="******">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-4 control-label">Confirmer le mot
										de passe:</label>
									<div class="col-md-12">
										<input class="form-control" type="password" name="confirmerMdp"
											id="confirmerMdp" placeholder="******">
									</div>
								</div>
								<div class="form-group">
									<label class="col-md- control-label"></label>
									<div class="col-md-12">
										<input type="submit" class="btn btn-primary"
											value="Enregistrer"> <span></span> <a
											href="http://localhost:8080/Football/index.jsp"
											class="btn btn-default">Annuler</a>
									</div>
								</div>
							</form>
						</div>
					</div>	
				<hr>
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
			
				  jQuery.validator.addMethod("noSpace", function(value, element) { 
				  return value.indexOf(" ") < 0 && value != ""; 
				}, "Espace entre caractéres non autorisé");
			
				  $.validator.addMethod("checkEmail",
					        function(value, element) {
					            var result = false;
					            $.ajax({
				                    url: "http://localhost:8080/Football/UtilisateurServlet",
				                    async: false,
				                    method: "GET",
				                    data: {email: value, hidden:'checkEmail'},
				                    success: function(data) {   
				                    	result = data == 'true';
				                    }
				                });
					            return result; 
					        },
					        "Email deja utilisé veuillez essayez un autre."
					   ); 
				  
				    $('#idForm').validate({
				    	
				    	lang: 'fr',
				        rules: {
						    email: {
						        required: true,
						        email: true,
						        checkEmail: true
						    }
						    ,
						    nom: {
						        required: true,
		
						    },
						    prenom: {
						        required: true,
						    },
						    mdp: {
						    	minlength : 6
						    },
						    confirmerMdp: {
						    	minlength : 6,
						    	equalTo : '#mdp'
						    }
						},
					    submitHandler: function(form) {
		                    $.ajax({
		                        url: "http://localhost:8080/Football/UtilisateurServlet",
		                        type: "POST",
		                        data: $(form).serialize(),
		                        success: function(response) {
		                            $("#reponse").append(response);
		                        }            
		                    });
		              	  }
					    });
					});
			</script>  
	</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Inscription</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
</head>
<body>
	<form>
		<table border=0>
			<tr valign=top>
				<td align=right>nom:</td>
				<td align=left><input type=text name=nom size=32 maxlength=80>
				</td>
			</tr>
			<tr valign=top>
				<td align=right>prénom:</td>
				<td align=left><input type=text name=prenom size=32 maxlength=80></td>
			</tr>
			<tr valign=top>
				<td align=right>email:</td>
				<td align=left><input type=text name=email size=32 maxlength=80></td>
			</tr>
			<tr valign=top>
				<td align=right>mots de passe:</td>
				<td align=left><input type=password name=mdp size=32 maxlength=80></td>
			</tr>
			<tr valign=top>
				<td colspan=2 align=center><input type=submit id="submit" value="s'inscrire">
				</td>
			</tr>
		</table>
	</form>
	
	<script type="text/javascript">
		$(document).ready(function() {
			$('#submit').click(function() {
				console.log("click");
				var sendData = $('#data').val();

				$.ajax({
					url : 'http://localhost:8080/Football/UtilisateurServlet',
					type : 'PUT',
					success : function() {
					}
				});

			});
		});
	</script>
</body>
</html>
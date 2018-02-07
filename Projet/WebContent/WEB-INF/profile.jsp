<%@page import="beans.Utilisateur"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html >
<head>
    <meta charset="utf-8">
    <link rel="shortcut icon" type="image/x-icon" href="image/etudiant.png" />
      <title>Ma PFE</title>

    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/rapport.css">
    <link rel="stylesheet" href="css/profile.css">

</head>
<%
Utilisateur users = (Utilisateur)session.getAttribute("users");

 String email =(String) request.getAttribute("email");
if(email==null||email==""){
	   response.sendRedirect( request.getContextPath()+"/connexion");
   }
%>

<body>


<div class="container-full">
    <div class="well well-lg">
        <div id="div-infoUser">
            <img src="image/${users.image_user }" class="img-profil">
            <p id="nom-user"> ${users.nom_user } ${users.prenom_user }</p>

        </div>
        <div class="btn-group" id="btn-profil">
            <button class="btn btn-default btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span class="caret" id="d"></span>Profile <span class="glyphicon glyphicon-user"></span>
            </button>
            <ul class="dropdown-menu">
                <li><a href="profile.php">Modifier Profile<span class="glyphicon glyphicon-cog icons" ></span></a> </li>
                <li> <a href="deconnexion">Déconnecter<span class="glyphicon glyphicon-off icons"></span> </a></li>
            </ul>
        </div>

    </div>
    <div class="row">
        <div class="col-lg-2" id="navbar-inverse">
            <div id="container-logo">
                <h2 id="nom_site">Ma PFE</h2>
                <img src="image/logo.png" alt="logo">
                <p id="titre-logo">rapports</p>
            </div>
            <nav class="navbar navbar-inverse">
              <ul class="nav navbar-left" id="ul-nav">
                  <li class="active" > <a href="index">accueil  </a> </li>
                    <li class="active" id="a"> <a href="#">MDW <span class="caret"></span> </a> </li>
                  <div class="div-nav-page" id="aa"  hidden><br>
                      <a href="home.php?br=T.L.A TMW" class="lien-page pos-lient">-Rappots T.L.A TMW </a>
                  </div>
                  <li> <a href="#" id="i">RSI<span class="caret"></span></a> </li>
                  <div class="div-nav-page" id="ii" hidden><br>

                      <a href="home.php?br=T.L.A FIM" class="lien-page pos-lient">Rappots T.L.A FIM </a>
                  </div>
                  <li> <a href="#" id="f">DSI<span class="caret"></span></a> </li>
                  <div class="div-nav-page" id="ff" hidden><br>
                      <a href="home.php?br=P.MSI" class="lien-page pos-lient">Rappots P.MSI</a><br>
                      <a href="home.php?br=D.MSI" class="lien-page pos-lient">Rappots D.MSI </a>
                  </div>
                  
                  <div class="div-nav-page" id="oo" hidden><br>

                      <a href="home.php?br=T.G TMW" class="lien-page pos-lient">Rappots T.G TMW </a>
                  </div>
               <%int type= users.getType_user(); 
                  if (!(type == 0)){  
                  %>
             <li><a href="parametre" >Paramétres</a> </li>
                  <% } %> 
              </ul>
            </nav>

        </div>

        <div class="col-lg-7" id="container-rapport">
            <h2 id="h2">Paramétres de Profile:</h2>
            <!-- recherche-->
            <div class="container">





                <div class="col-lg-6 " id="bloc-parametre-general">

                    <div id="div-img-parametre">

                        <img src="image/${users.image_user }" alt="mybook logo" class="photo-profil">
                        <span class="span"> </span>
                    </div>
                    <br><br>

                    <!-- nom et prenom-->
                    <div class="form-group-sm">
                        <form method="post" action="" id="form" >
                            <label>Nom:
                              <input type="text" class="form-control" name="nom" id="nom" value="${users.nom_user }" required>
                            </label>
                            <label>Prenom:
                              <input type="text" class="form-control" name="prenom" id="prnom" value="${users.prenom_user }" required>
                            </label>
<br>
                            <label>Email:
                                <input type="email" class="form-control inp"  readonly="readonly" name="email" id="email" value="${users.email_user }" required>
                            </label>
<br>
                            <label>Nouveau Mot de passe:
                                <input type="password" class="form-control inp" name="n_pass" id="n_pass" value="${users.pass_user }"  required>
                            </label>
                            <br>
                            <label>Confirmation Mot de passe:
                                <input type="password" class="form-control inp" name="c_pass" id="c_pass" value="${users.pass_user }" required>
                            </label>

                            <label>Photo De Profile
                                <input type="file" class="form-control inp" name="file" >
                            </label>
                            <br>
                            <br>
                            <div class="lyawt">
                        <input type="submit" class="btn btn-default input-group-sm" name="submit">
                        <input type="reset" class="btn btn-default">
                            </div>
                    </form>
                    </div>


            </div>

        </div>
        </div>


    </div>


    <script src="js/jquery.js"></script>
    <script src="js/profile.js" ></script>
    <script src="js/ex1.js" ></script>
    <script src="bootstrap/js/bootstrap.js"></script>

    <script>
$(document).ready(function () {
   $('#form').submit(function () {

       pass= $("input[type=password]#n_pass").val();

       npass=$("input[type=password]#c_pass").val();


     if (pass!==npass){
           alert('Le Mot De Passe Actuelle ET Le Nouveau Incorrect');
           return false;
       }
   });

});
    </script>
</body>
</html>

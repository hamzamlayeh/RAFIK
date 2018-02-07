<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="beans.Utilisateur"%>
<%@page import="beans.rapport"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
   Utilisateur users = (Utilisateur)session.getAttribute("users");
    
	 String email =(String) request.getAttribute("email");
    if(email==null||email==""){
		   response.sendRedirect( request.getContextPath()+"/connexion");
	   }
    %>
<!doctype html>
<html >
<head>

    <meta charset="utf-8">
    <link rel="shortcut icon" type="image/x-icon" href="image/etudiant.png" />
      <title>Ma PFE</title>
    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="css/style.css">
<style>
    .btn-xs{
        font-size: 11px;
    }
</style>

</head>
<body>

<div class="container-full">
    <div class="well well-lg">
        <div id="div-infoUser">
            <img src="image/${users.image_user }" class="img-profil">
            <p id="nom-user">${users.nom_user } ${users.prenom_user } </p>

        </div>
        <div class="btn-group" id="btn-profil">
            <button class="btn btn-default btn-sm dropdown-toggle" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span class="caret" id="d"></span>Profile <span class="glyphicon glyphicon-user"></span>
            </button>
            <ul class="dropdown-menu">
                <li><a href="modifier">Modifier Profile<span class="glyphicon glyphicon-cog icons" ></span></a> </li>
                <li> <a href="deconnexion">Ddéconnecter<span class="glyphicon glyphicon-off icons"></span> </a></li>
            </ul>
        </div>

    </div>
    <div class="row">
        <div class="col-lg-2" id="navbar-inverse">
            <div id="container-logo">
                <h2 id="nom_site">Ma PFE</h2>
                <img src="../image/logo.png" alt="logo">
                <p id="titre-logo">rapports</p>
            </div>
            <nav class="navbar navbar-inverse">
              <ul class="nav navbar-left" id="ul-nav">
                  <li class="active" > <a href="index">Accueil  </a> </li>
                    <li class="active" id="a"> <a href="#">MDW <span class="caret"></span> </a> </li>
                  <div class="div-nav-page" id="aa"  hidden><br>
                      <a href="home.php?br=T.L.A TMW" class="lien-page pos-lient">-Rappots T.L.A TMW </a>
                  </div>
                  <li> <a href="#" id="i">RSI<span class="caret"></span></a> </li>
                  <div class="div-nav-page" id="ii" hidden><br>

                      <a href="home.php?br=T.L.A FIM" class="lien-page pos-lient">Rappots T.L.A FIM </a>
                  </div>
                  <li> <a href="#" id="f">DSI<span class="caret"></span></a> </li>
                  <div class="div-nav-page" id="ff" hidden> <br>
                      <a href="home.php?br=P.MSI" class="lien-page pos-lient">Rappots P.MSI</a><br>
                      <a href="home.php?br=D.MSI" class="lien-page pos-lient">Rappots D.MSI </a>
                  </div>
                  
                  <div class="div-nav-page" id="oo" hidden> <br>

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
            <h2 id="h2">Accueil </h2>
                <a href="home.php">Afficher Touts Les Rapports</a>
            <!-- recherche-->
            <div class="container">
                <div class="div-recherch">
                    <div class="col-md-3">
                        <!-- recherche -->
                        <div id="custom-search-input">
                            <form method="post" action="recherch.php" name="form">
                            <div class="input-group col-md-10">

                                <input type="text" class="form-control input-ms" placeholder="Recherch ..." name="rech" />
                                <span class="input-group-btn">
                        <button class="btn btn-info btn-ms" type="submit" >
                            <i class="glyphicon glyphicon-search"></i>
                        </button>
                    </span>


                            </div>
                            </form>
                        </div>


                    </div>
                </div>

            </div>
            <div class="row">
<% 
List<rapport> list=(ArrayList<rapport>)request.getAttribute("list");

for(int i = 0; i < list.size();i++){
%>
            
                <!-- conteneure de rapport -->
 <div class="col-sm-6 col-md-4">
        <div class="thumbnail">
            <img src="../image/logo-pdf.png" alt="logo pdf" class="image-articl">
                <div class="caption">
                    <h4><span class="glyphicon glyphicon-education icon-user" ></span><% out.println(list.get(i).getNom_etudi()+" "+list.get(i).getPrenom_etudiant()); %></h4>
                        <p class="pr"><% out.println(list.get(i).getTitre_rapport()); %></p>
                            <p>
                                <a href="telacharger.php?id_r=8" class="btn btn-primary btn-xs" role="button">Telecharger <span class="glyphicon glyphicon-cloud-download"></span></a>
                                <a href="rapport.php?id_rapport=8" class="btn btn-default btn-xs" role="button">Voir Discription <span class="glyphicon glyphicon-eye-open"></span></a>
                            </p>
                        </div>
                    </div>
                </div>
<% }%>
					<!--<iframe src="rapports/Sommaire-2.pdf" width="300" height="500" align="middle"></iframe>-->

                <!-- pagination-->


            </div>

        </div>

    </div>

</div>





<script src="js/jquery.js"></script>
<script src="bootstrap/js/bootstrap.js"></script>
<script src="js/ex1.js"></script>



</body>
</html>
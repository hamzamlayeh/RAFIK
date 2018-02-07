<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html >
<head>
    
    <link rel="shortcut icon" type="image/x-icon" href="image/etudiant.png" />
      <title>Ma PFE</title>

    <link rel="stylesheet" href="bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="css/inscri.css">


    
</head>
<body>

<% 
String err=(String)request.getAttribute("msg");
if(err==null)
{
	err="";
}else{
	out.println(err);	
}
%>
<div class="container-full">
    <div class="row" id="bar">
        <div class="col-lg-12 bonde">
                <div class="col-lg-3 ">
                    <p id="titre-logo">rapports</p>
                </div>
                <div class="col-lg-6 " id="span">
                    <img src="image/logo.png" alt="logo">
                    Ma PFE
                </div>
            <div class="col-lg-3 ">
                <a href="connexion" class="btn btn-primary" id="bt">connexion</a>
            </div>
        </div>
    </div>
    <br>
</div>

<br>
<div >

</div>

        <div class=" col-lg-3" style="    margin-left: 34.333333%;">
            <div class="row">
                <div class="col-lg-offset-3 col-lg-5 "><h2  id="h2">Créer votre compte Ma PFE</h2></div>
            </div>
                <form class="well form-horizontal form-group" name="form" id="f" method="post" action="">
                 <legend>Inscription:</legend>
                    <div class="" id="err-nom" >
                         <label for="nom"  >Non:
                            <input type="text" class="form-control input-sm" id="nom"  name="nom" >
                        </label>
                         <span style="display: none;" class="help-block"  id="n"></span>
                     </div>

                    <div class="" id="err-prnom" >
                       <label for="prenom">Prenom:
                           <input type="text" class="form-control input-sm" id="prenom" name="prnom">
                      </label>
                         <span style="display: none;" class="help-block" id="p"  ></span>
                     </div>

                 <div class="" id="err-email" >
                    <label for="email"> Email:
                        <input type="email" class="form-control input-sm" id="email" name="email" >
                    </label>
                        <span style="display: none;" class="help-block" id="m"  ></span>
                </div>

                <div class="" id="err-pass" >
                    <label for="pass"> Mot De Passe:
                        <input type="password" class="form-control input-sm" id="pass" name="pass">
                    </label>
                    <span style="display: none;" class="help-block" id="ps"  ></span>
                </div>


                <br><br>
                <div class="btn btn-block">
                    <button type="submit" name="submit" class="btn btn-success">Créer un compte</button>
                    <button type="reset" class="btn btn-danger">annuler</button>
                </div>
                <br>

                </form>
        </div>

    
<script src="js/jquery.js"></script>
<script src="js/ex1.js"></script>


</body>
</html>

$(document).ready(function(){

    $("#a").click( function() {

        $("#aa").animate({
            height: 'toggle'
        });
    });

    $("#i").click( function() {

        $("#ii").animate({
            height: 'toggle'
        });
    });

    $("#f").click( function() {

        $("#ff").animate({
            height: 'toggle'
        });
    });
    
     $("#o").click( function() {

        $("#oo").animate({
            height: 'toggle'
        });
    });

});

$(document).ready(function(){
    $("#f").submit(function () {

        if ($("#nom").val()===''){
            $("#err-nom").addClass("has-error has-feedback");
            $("#n").text("le champ est vide ").show();

            return false;
        }
        else if(! isNaN($("#nom").val())){
            $("#err-nom").addClass("has-error has-feedback");
            $("#n").text("le nom doit etre une chaine ").show();

            return false;
        }
        else {
            $("#n").hide();
            $("#err-nom").removeClass("has-error has-feedback");

        }

        if ($("#prenom").val()===''){
            $("#err-prnom").addClass("has-error has-feedback");
            $("#p").text("le champ est vide").show();

            return false;
        }
        else if(! isNaN($("#prenom").val())){
            $("#err-prnom").addClass("has-error has-feedback");
            $("#p").text("le nom doit etre une chaine ").show();

            return false;
        }

        else {
            $("#p").hide();
            $("#err-prnomnom").removeClass("has-error has-feedback");

        }

        if ($("#email").val()===''){
            $("#err-email").addClass("has-error has-feedback");
            $("#m").text("le champ est vide").show();

            return false;
        }
        else {
            $("#m").hide();
            $("#err-email").removeClass("has-error has-feedback");

        }
        if ($("#pass").val()===''){
            $("#err-pass").addClass("has-error has-feedback");
            $("#ps").text("le champ est vide").show();

            return false;
        }
        else {
            $("#p").hide();
            $("#err-pass").removeClass("has-error has-feedback");

        }

    });

});


$(document).ready(function () {

   $(".btn btn-success btn-sm").click(function () {

     alert('dd');
   }) ;
});



setInterval("aff()",1000);

aff();
function aff() {
    $.post("recep-rapport.php",function (data) {
        $('.box-content').html(data);

    });
}




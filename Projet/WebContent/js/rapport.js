/**
 * Created by user on 05/08/2017.
 */


$('#f-com').submit(function () {

    var value=$("input[type=text]#id").val();


   var com= $('textarea[id="com"]').val();


    $.post('envoi-commenter.php',{com:com,id_r:value},function (data) {

    });


    $('textarea[id="com"]').val('');
    return false;
});






setInterval("aff()",1000);

aff();
function aff() {
    $.post("envoi-commenter.php",function (data) {
        $('#conteber-com').html(data);

    });
}
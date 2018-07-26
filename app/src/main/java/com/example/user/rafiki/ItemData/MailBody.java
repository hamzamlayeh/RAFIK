package com.example.user.rafiki.ItemData;

public class MailBody {

    public static String getBody(String password) {
        String str;

        str = "<html><body><center><img src=''></center>" +
                "<h3 style='color:#3F51B5;text-align:center;'>"+"Avez-vous oublié votre mot de passe pour RAFIKI ?"+"</h3>" +
                "<h3 style='color:#3F51B5;text-align:center;'>Nous sommes là pour vous aider </h3>" +
                "<br><p>Voici votre mot de passe qui vous permettra de vous connecter à votre compte RAFIKI : <br><b>" +password+"</b></p> "+
                "<p>ATTENTION :<br>" +
                "Si vous n'avez pas demandé de rappeler votre mot de passe, IGNOREZ et EFFACEZ ce courriel immédiatement ! Continuez uniquement si vous souhaitez que votre mot de passe soit rappelé.</p><br>" +
                "<p>Merci ! <br>Equipe RAFIKI </p><br>" +
                "<p>Ceci est un e-mail généré automatiquement, veuillez ne pas y répondre</p>" +
                "</body></html>";
        return str;
    }
}

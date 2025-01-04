package org.example;
public enum Message {

    ENTREE("Entrez deux noms de fichier"),
    TITRE("Arrondissement,Nombre d'interventions,Nombre de parcs\n"),
    EXISTE("Ce fichier n'existe pas.\n"),
    CHEMIN("Le chemin du fichier n'est pas bon"),
    JSON("Le fichier JSON est mal construit"),
    LECTURE("Erreur de lecture des ressources : "),
    RESSOURCE("Ressource introuvable : "),
    CONTENU("Le contenu du fichier est vide"),
    ;

    private String message;
     Message(String message){
        this.message = message;
    }

    public String getMessage(){
         return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Traduit les messages en anglais si
     * l'argument --english est lu.
     */
    public static void obtenirMessage(){
             ENTREE.setMessage("Two file's name is need");
             TITRE.setMessage("District,number of intervention,number of parks\n");
             EXISTE.setMessage("The file doesn't exist.");
             CHEMIN.setMessage("The path's file is not right.");
             JSON.setMessage("The JSON file is not build right");
             LECTURE.setMessage("Playback error of ressources : ");
             RESSOURCE.setMessage("Can not find the ressource : ");
             CONTENU.setMessage("The file is empty");

    }
}

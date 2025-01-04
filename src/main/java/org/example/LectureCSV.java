package org.example;

import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

public class LectureCSV {
  private static boolean estAnglais = false;
  private static String nomCheminEntree;

  public static void setEstAnglais(boolean estAnglais) {
    LectureCSV.estAnglais = estAnglais;
  }

  /**
   * Converti le fichier d'entré en Scanner
   *
   * @param chemin le chemin du fichier
   * @return un Scanner contenant le contenu du fichier
   */
  private static Scanner convertirCsvToScanner(String chemin) {
    Scanner contenu = null;
    File fichier = new File(chemin);
    try {
      contenu = new Scanner(fichier).useDelimiter(",");
    } catch (FileNotFoundException e) {
        System.err.println(Message.CHEMIN.getMessage());
    }

    return contenu;
  }

  /**
   * Lit une ligne du fichier et retourne un objet contenant toutes les données de la ligne
   *
   * @param contenu un scanner contenant le contenu de la ligne
   * @return un objet contenant toutes les données de la ligne
   */
  public static StatistiqueCSV lireUneLigne(Scanner contenu) {
    StatistiqueCSV ligne = null;

    String date = contenu.useDelimiter(",").next();
    String heure = contenu.useDelimiter(",").next();
    String parc = contenu.useDelimiter(",").next();
    String arrondissement = contenu.useDelimiter(",").next();
    String description = contenu.useDelimiter(",").next();
    ligne = new StatistiqueCSV(date, heure, parc, arrondissement, description);
    return ligne;
  }

  /**
   * Vérifie si le premier champ d'une ligne est vide
   *
   * @param ligneS la ligne à vérifier
   * @return true si la ligne est vide,false sinon
   */
  protected static boolean verifierPremierChampVide(String ligneS) {
    return ligneS.indexOf(",") == 0;
  }

  /**
   * Verifie si la ligne contient des champs manquant
   *
   * @param ligneS la ligne à verifier
   * @return le numéro de colone où il y a un problème,5 sinon
   */
  public static int verifierLigneChampManquant(String ligneS) {
    int noColone = 0;
    Scanner ligne = new Scanner(ligneS);
    boolean erreurNonTrouve = true;
    while (ligne.useDelimiter(",").hasNext() && erreurNonTrouve) {
      if (ligne.useDelimiter(",").next().toString().isEmpty()) {
        erreurNonTrouve = false;
      } else {
        noColone++;
      }
    }
    if (verifierPremierChampVide(ligneS)) {
      noColone = 0;
    }
    return noColone;
  }

  /**
   * Isole les String des champs description et arrondissement
   *
   * @param ligne la ligne où les champs à isoler sont présent
   * @return un arraylist contenant les champs description et arrondissement
   */
  public static ArrayList<String> isolerInterventionArrondissement(Scanner ligne) {
    ArrayList<String> tableInterventionArrondissement = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      ligne.useDelimiter(",").next();
    }
    tableInterventionArrondissement.add(ligne.useDelimiter(",").next());
    tableInterventionArrondissement.add(ligne.useDelimiter(",").next());
    return tableInterventionArrondissement;
  }

  /**
   * Envoie une exception si des erreurs de description ou arrondissement sont présentes
   *
   * @param numeroLigne le numéro de la ligne où l'erreur est survenu
   * @param compteurTableau 0 si c'est une erreur d'arrondissement,1 si c'est une erreur de
   *     description
   * @throws ChampContenuException si une erreur de contenu de champ survient
   */
  public static void emettreErreurDeContenu(int numeroLigne, int compteurTableau)
      throws ChampContenuException {
    if (!estAnglais) {
      String messageErreur =
          "Erreur dans le fichier \'"
              + nomCheminEntree
              + "\' à la ligne "
              + numeroLigne
              + " : Le champ ";
      if (compteurTableau == 0) {

        throw new ChampContenuException(
            messageErreur + "\'Arrondissement\' n'est pas \n" + "dans la liste d'arrondissement\n");
      } else {
        throw new ChampContenuException(
            messageErreur + "\'Description\' n'est pas \n" + "dans la liste de description\n");
      }
    } else {
      emettreErreurDeContenuAnglais(numeroLigne, compteurTableau);
    }
  }

  /**
   * Envoie une exception si des erreurs de description ou arrondissement sont présentes en anglais
   *
   * @param numeroLigne le numéro de la ligne où l'erreur est survenu
   * @param compteurTableau 0 si c'est une erreur d'arrondissement,1 si c'est une erreur de
   *     description
   * @throws ChampContenuException si une erreur de contenu de champ survient
   */
    public static void emettreErreurDeContenuAnglais(int numeroLigne, int compteurTableau) throws ChampContenuException{
      String messageErreur = "Error in the file \'" + nomCheminEntree + "\' at lign "+ numeroLigne + " : The field ";
      if (compteurTableau == 0){
        throw new ChampContenuException(
                messageErreur + "\'District\' is not \n" + "in the list of district\n");
      } else {
        throw new ChampContenuException(
                messageErreur + "\'Description\' is not \n" + "in the list of description\n");
      }
      }


  /**
   * Compare et vérifie si les champs description et arrondissement sont dans les fichiers json
   *
   * @param ligne la ligne à comparer
   * @param numeroLigne le numéro de la ligne qui est comparé
   * @throws ChampContenuException si il y a des différences entre les champs et les fichiers json
   */
  public static void verifierLigneComparaison(Scanner ligne, int numeroLigne)
      throws ChampContenuException {
    ArrayList<String> tableInterventionArrondissement = isolerInterventionArrondissement(ligne);
    ArrayList<ArrayList<String>> tableReference = LectureJson.construireDonneeJson();
    boolean estDeffectueuxArrondissement = true;
    int compteurTableau = 0;
    boolean estDeffectueuxIntervention = true;
    while (compteurTableau < 2) {
      for (String motReference : tableReference.get(compteurTableau)) {
        if (tableInterventionArrondissement.get(compteurTableau).equals(motReference)
            && compteurTableau == 0) {
          estDeffectueuxArrondissement = false;
        }
        if (tableInterventionArrondissement.get(compteurTableau).equals(motReference)
            && compteurTableau == 1) {
          estDeffectueuxIntervention = false;
        }
      }
      compteurTableau++;
    }
    if (estDeffectueuxArrondissement) {
      emettreErreurDeContenu(numeroLigne, 0);
    }
    if (estDeffectueuxIntervention) {
      emettreErreurDeContenu(numeroLigne, 1);
    }
  }

  /**
   * Associe le numéro de colone où le champ manque à un message
   *
   * @param ligneS String de la ligne à associer
   * @param numeroLigne le numéro de la ligne à associer
   * @throws ChampManquantException
   */
  protected static void associerErreurAColone(String ligneS, int numeroLigne)
      throws ChampException {
    if (!estAnglais){
      String msgErreur =
          "Erreur dans le fichier \'"
              + nomCheminEntree
              + "\' à la ligne "
              + numeroLigne
              + " : Le champ ";

      switch (verifierLigneChampManquant(ligneS)) {
        case 0:
          throw new ChampManquantException(msgErreur + "\'Date\' est\n" + "manquant.\n");
        case 1:
          throw new ChampManquantException(msgErreur + "\'Heure\' est\n" + "manquant.\n");
        case 2:
          throw new ChampManquantException(msgErreur + "\'Parc\' est\n" + "manquant.\n");
        case 3:
          throw new ChampManquantException(msgErreur + "\'Arrondissement\' est\n" + "manquant.\n");
        case 4:
          throw new ChampManquantException(msgErreur + "\'Description\' est\n" + "manquant.\n");
      }
      } else { associerErreurAColoneAnglais(ligneS,numeroLigne);}
      Scanner ligneComparaison = new Scanner(ligneS);
      verifierLigneComparaison(ligneComparaison, numeroLigne);

  }

  /**
   * Associe le numéro de colone où le champ manque à un message en anglais
   *
   * @param ligneS String de la ligne à associer
   * @param numeroLigne le numéro de la ligne à associer
   * @throws ChampManquantException
   */
  protected static void associerErreurAColoneAnglais(String ligneS, int numeroLigne)
          throws ChampException {
      String msgErreur =
              "Error in the file \'"
                      + nomCheminEntree
                      + "\' at lign "
                      + numeroLigne
                      + " : The field ";

      switch (verifierLigneChampManquant(ligneS)) {
        case 0:
          throw new ChampManquantException(msgErreur + "\'Date\' is\n" + "missing.\n");
        case 1:
          throw new ChampManquantException(msgErreur + "\'Hour\' is\n" + "missing.\n");
        case 2:
          throw new ChampManquantException(msgErreur + "\'Park\' is\n" + "missing.\n");
        case 3:
          throw new ChampManquantException(msgErreur + "\'District\' is\n" + "missing.\n");
        case 4:
          throw new ChampManquantException(msgErreur + "\'Description\' is\n" + "missing.\n");
      }
      }
  /**
   * Lit le contenu du fichier et ajoute les données statistiques à une table
   *
   * @param contenu le contenu en Scanner à lire
   * @return la table de donnée statistique
   * @throws ChampManquantException
   */
  public static ArrayList<StatistiqueCSV> lireContenu(Scanner contenu) throws ChampException {
    ArrayList<StatistiqueCSV> tableDeDonne = new ArrayList<>();
    contenu.nextLine();
    int numeroLigne = 1;
    while (contenu.hasNextLine()) {
      String ligneS = (contenu.nextLine()).toString();
      Scanner ligne = new Scanner(ligneS);
      numeroLigne++;
      associerErreurAColone(ligneS, numeroLigne);
      tableDeDonne.add(lireUneLigne(ligne));
    }
    return tableDeDonne;
  }

  /**
   * Prend le chemin et fait l'ensemble des opération nécessaire pour extraire une table de données
   * statistique
   *
   * @param chemin le chemin du fichier à traiter
   * @return Une table de données statistiques.
   */
  public static ArrayList<StatistiqueCSV> retournerTableDeDonnee(String chemin) {
    nomCheminEntree = chemin;
    ArrayList<StatistiqueCSV> tableDeDonnee = new ArrayList<>();
    Scanner contenu = convertirCsvToScanner(chemin);
    try {
      tableDeDonnee = lireContenu(contenu.reset());
    } catch (ChampException e) {
      System.err.println(e.getMessage());
    }
    return tableDeDonnee;
  }
  public static void VerifierFichierVide(Scanner contenu){
    if(!contenu.hasNext()){
        System.err.println(Message.CONTENU.getMessage());
      System.exit(1);
    }

  }
}

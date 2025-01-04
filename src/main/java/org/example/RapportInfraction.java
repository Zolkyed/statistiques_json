package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class RapportInfraction {
  public static final String MAN = "Ce programme prend un argument optionel '--english' comme premier argument \n"+
                                    "pour rendre le texte du programme en anglais, il faut aussi deux argument, \n"+
          "le premier étant le chemin du fichier d'entrée et le second le chemin du fichier de sortie";
  public static final String MAN_ANGLAIS = "This program takes an optional argument '--english' as ​​first argument\n"+
          "to render the program text in English, you also need two arguments, \n"+
          "the first being the input file path and the second being the output file path";
  public static void main(String[] args) throws FileNotFoundException {

    ArrayList<String> arguments = determinerArguments(args);
    String nomFichierSortie = arguments.get(1);
    String nomFichierEntree = arguments.get(0);
    System.out.println(nomFichierEntree);

    ArrayList<StatistiqueCSV> tableDeDonne = LectureCSV.retournerTableDeDonnee(nomFichierEntree);
    EcritureCSV statistique = new EcritureCSV("", "");
    ArrayList<ArrayList<String>> test = EcritureCSV.trierInformation(tableDeDonne);
    File fichier = statistique.ajouterContenuFichier(tableDeDonne, nomFichierSortie);
  }

  /**
   * Determine le cas à choisir dépendamment des arguments du main
   * @param args les arguments du main
   * @return un arraylist contenant les nom de fichier d'entree et de sortie
   */
  public static ArrayList<String> determinerArguments(String[] args ){
    ArrayList<String> arguments = null;
    if(args.length == 0 || args.length == 1){
      contientZeroAUnArgument(args);

    }else if (args.length == 2){
      arguments = contientDeuxArguments(args);
    }else if (args.length == 3){
      arguments = contientTroisArguments(args);
    }else if (args.length > 3){
      contientPlusDeTroisArguments(args);
    }
    return arguments;
  }

  /**
   * Contient le cas où il y a zero a un argument
   * @param args les arguments du main
   */
  public static void contientZeroAUnArgument(String[] args){
    if (args.length == 0){
      System.err.println(MAN);
      System.exit(-1);
    }else if (args.length == 1){
      if (args[0].equals("--english")){
        System.err.println(MAN_ANGLAIS);
        System.exit(-1);
      }else{
        System.err.println(MAN);
        System.exit(-1);
      }
    }
  }
  /**
   * Contient le cas où il y a deux arguments
   * @param args les arguments du main
   * @return un arraylist contenant les nom de fichier d'entree et de sortie
   */
  public static ArrayList<String> contientDeuxArguments(String[] args){
    String nomFichierEntree = "";
    String nomFichierSortie = "";
    if (args[0].equals("--english")){
      System.err.println("You must provide the input and output file path");
    }else{
      nomFichierEntree = args[0] + nomFichierEntree;
      nomFichierSortie = args[1] + nomFichierSortie;
    }
    ArrayList<String> retour = new ArrayList<>();
    retour.add(nomFichierEntree);
    retour.add(nomFichierSortie);
    return retour;
  }

  /**
   * Contient le cas où il y a trois arguments
   * @param args les arguments du main
   * @return un arraylist contenant les nom de fichier d'entree et de sortie
   */
  public static ArrayList<String> contientTroisArguments(String[] args){
    String nomFichierEntree = "";
    String nomFichierSortie = "";
    if (args[0].equals("--english")){
      nomFichierEntree = args[1] + nomFichierEntree;
      nomFichierSortie = args[2] + nomFichierSortie;
      Message.obtenirMessage();
      LectureCSV.setEstAnglais(true);
    }else {
      System.err.println(MAN);
      System.exit(-1);
    }
    ArrayList<String> retour = new ArrayList<>();
    retour.add(nomFichierEntree);
    retour.add(nomFichierSortie);
    return retour;
  }
  /**
   * Contient le cas où il y a plus de 3 arguments
   * @param args les arguments du main
   */
  public static void contientPlusDeTroisArguments(String[] args){
    if(args[0].equals("--english")){
      System.err.println(MAN_ANGLAIS);
      System.exit(-1);
    }else{
      System.err.println(MAN);
      System.exit(-1);
    }
  }
}


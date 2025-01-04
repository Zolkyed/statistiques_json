package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Cette classe permet de lire des fichiers json et de créer des tableaux contenant les données de
 * ces fichiers.
 */
public class LectureJson {

  public static final String cheminArrondissements = "arrondissements.json";
  public static final String cheminInterventions = "interventions.json";


  /**
   * Construit à partir des fichiers JSON un arraylist de arraylist contenant les informations des
   * deux fichiers
   *
   * @return un arraylist de arraylist contenant les données
   */
  public static ArrayList<ArrayList<String>> construireDonneeJson() {
    ArrayList<ArrayList<String>> tableau = new ArrayList<>();
    try {
      JSONArray arrayJson = convertirStringEnArrayJson(
              convertirFichierJsonAString(cheminArrondissements), "arrondissements"
      );
      tableau.add(extraireDonnee(arrayJson));
      arrayJson = convertirStringEnArrayJson(
              convertirFichierJsonAString(cheminInterventions), "intervention_policiere"
      );
      tableau.add(extraireDonnee(arrayJson));
    } catch (ParseException e) {
      emettreErreurDeParse();
    }
    return tableau;
  }
  public static void emettreErreurDeParse(){
      System.err.println(Message.JSON.getMessage());
  }

  /**
   * Lit le contenu d'un flux d'entrée et retourne sous forme de chaîne de caractères.
   *
   * @param fluxEntree Le flux d'entrée à lire.
   * @param nomRessource Le nom de la ressource.
   * @return Le contenu du flux d'entrée sous forme de chaîne de caractères.
   */
  public static String lireContenuFlux(InputStream fluxEntree, String nomRessource) {
    try (Scanner lecteur = new Scanner(fluxEntree)) {
      String contenuString = "";
      while (lecteur.hasNextLine()) {
        contenuString += lecteur.nextLine();
      }
      return contenuString;
    } catch (Exception e) {
      emettreErreurDeLecture(nomRessource);
    }
    return null;
  }
  public static void emettreErreurDeLecture(String nomRessource){
      System.err.println(Message.LECTURE + nomRessource);
  }

  /**
   * Convertit le contenu d'un fichier JSON en une chaîne de caractères.
   *
   * @param nomRessource Le nom de la ressource JSON à charger.
   * @return Une chaîne de caractères représentant le contenu du fichier JSON.
   */
  public static String convertirFichierJsonAString(String nomRessource) {
    InputStream fluxEntree = LectureJson.class.getClassLoader().getResourceAsStream(nomRessource);
    if (fluxEntree == null) {
      System.err.println(Message.RESSOURCE.getMessage() + nomRessource);
      return null;
    }
    return lireContenuFlux(fluxEntree, nomRessource);
  }
  public static void emettreErreurIntrouvable(String nomRessource){
      System.err.println(Message.RESSOURCE.getMessage() + nomRessource);
  }

  /**
   * Converti une chaine json en Array json
   *
   * @param jsonString la chaine json
   * @param type Une string décrivant le type d'objet json à convertir
   * @return un json array de l'objet
   * @throws ParseException Si la chaine json est mal construite
   */

  public static JSONArray convertirStringEnArrayJson(String jsonString, String type)
          throws ParseException {
    JSONParser parser = new JSONParser();
    JSONObject objet = (JSONObject) parser.parse(jsonString);
    return (JSONArray) objet.get(type);
  }

  /**
   * Ajoute à un arraylist les données contenus dans le json array
   *
   * @param arrayJson le tableau JSON à parcourir
   * @return un arraylist contenant les données du tableau json
   */
  public static ArrayList<String> extraireDonnee(JSONArray arrayJson) {
    ArrayList<String> tableContenu = new ArrayList<>();
    for (int i = 0; i < arrayJson.size(); i++) {
      tableContenu.add((String) arrayJson.get(i));
    }
    return tableContenu;
  }
}

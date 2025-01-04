package org.example;

import java.io.*;
import java.util.ArrayList;

public class EcritureCSV extends StatistiqueCSV {

  public EcritureCSV(String arrondissement, String parc) {
    this.arrondissement = arrondissement;
    this.parc = parc;
  }

  /**
   * Cette méthode trie une liste de type String en ordre alphabétique
   *
   * @param regionEtParc la liste à trier
   * @return la liste triée
   */
  public static void trierAlphabetique(ArrayList<ArrayList<String>> regionEtParc) {
    String arrondissement = "";
    ArrayList<String> region = new ArrayList<>();
    ArrayList<String> region2 = new ArrayList<>();
    for (int i = 1; i < regionEtParc.size(); i++) {
      region = regionEtParc.get(i);
      region2 = regionEtParc.get(i - 1);
      if (region.get(0).compareTo(region2.get(0)) < 0) {
        regionEtParc.set(i, region2);
        regionEtParc.set(i - 1, region);
      }
    }
  }

  /**
   * Cette méthode créee un fichier vide.
   *
   * @param nomFichier le chemin du fichier et son nom
   * @return le fichier vide
   */
  public static File creeFichierVide(String nomFichier) {
    File fichier = new File(nomFichier);
    try {
      if (!fichier.exists()) {
        fichier.createNewFile();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return fichier;
  }

  /**
   * Cette méthode trie les informations pour ne garder que les arrondissements et les parcs dans un
   * tableau de tableau de type String. Trie ensuite le tableau par ordre alphabétique selon les
   * arrondissements.
   *
   * @param statistique l'instance d'ArrayList<> contenant toutes les informations d'une
   *     arrestation.
   * @return une instance d'ArrayList<> contenant les arrondissement uniquement.
   */
  public static ArrayList<ArrayList<String>> trierInformation(
      ArrayList<StatistiqueCSV> statistique) {
    int i = 0;
    ArrayList<ArrayList<String>> arrondissementEtParc = new ArrayList<>();
    while (i < statistique.size()) {
      ArrayList<String> endroit = new ArrayList<>();
      StatistiqueCSV information = statistique.get(i);
      endroit.add(information.getArrondissement());
      endroit.add(information.getParc());
      arrondissementEtParc.add(endroit);
      i++;
    }
    return arrondissementEtParc;
  }

  /**
   * Regarde s'il existe un ou plusieurs parcs par arrondissement dans le tableau
   *
   * @param nombreParc le tableau contenant la récurence des parcs
   * @return le tableau avec le nombre de parc
   */
  public static void observerParc(
      ArrayList<ArrayList<String>> arrondissementEtParc, ArrayList<Integer> nombreParc) {

    int j = 0;
    int cas = 1;
    nombreParc.add(cas);
    for (int i = 1; i < arrondissementEtParc.size(); i++) {
      ArrayList<String> endroit = arrondissementEtParc.get(i);
      String terrain1 = endroit.get(0);
      String parc1 = endroit.get(1);
      ArrayList<String> endroit2 = arrondissementEtParc.get(i - 1);
      String terrain2 = endroit2.get(0);
      String parc2 = endroit2.get(1);
      if (terrain1.equals(terrain2) && parc1.compareTo(parc2) != 0) {
        nombreParc.set(j, cas++);
      }
      if (terrain1.compareTo(terrain2) != 0) {
        cas = 1;
        nombreParc.add(cas);
        j++;
      }
    }
  }

  /**
   * Place les parcs ou les arrondissements dans une ArrayList de type String
   *
   * @param arrondissementEtParc le tableau de tableau de String contenant les parc et les
   *     arrondissement
   * @param cible determine si le tableau de retour aura lea parcs ou les arrondissementd
   * @return tableau de String
   */
  public static ArrayList<String> chercherInformationSpecifique(
      ArrayList<ArrayList<String>> arrondissementEtParc, String cible) {
    ArrayList<String> information = new ArrayList<>();
    ArrayList<String> tabRetour = new ArrayList<>();
    cible.toLowerCase();
    for (int i = 0; i < arrondissementEtParc.size(); i++) {
      information = arrondissementEtParc.get(i);
      if (cible.equals("arrondissement")) {
        tabRetour.add(information.get(0));
      }
      if (cible.equals("parc")) {
        tabRetour.add(information.get(1));
      }
    }
    return tabRetour;
  }

  /**
   * Cette méthode calcul le nombre de délit causer dans un arrondissement ou le nombre de parc dans
   * l'arrondissement.
   *
   * @param arrondissement le tableau contenant les arrondissements
   * @return
   */
  public static ArrayList<Integer> calculerCasParArrondissement(ArrayList<String> arrondissement) {
    int nbrCas = 0;
    ArrayList<Integer> casParRegion = new ArrayList<>();
    for (int i = 0; i < arrondissement.size(); i++) {
      nbrCas = AjouterCas(i, arrondissement, casParRegion);
      casParRegion.add(nbrCas);
    }
    return casParRegion;
  }

  public static int AjouterCas(
      int i, ArrayList<String> arrondissement, ArrayList<Integer> casParRegion) {
    int nbrCas = 1;
    int p = i + 1;
    String region = arrondissement.get(i);
    for (int j = i + 1; j < arrondissement.size(); j++) {
      if (region.equals(arrondissement.get(p))) {
        nbrCas = nbrCas + 1;
        arrondissement.remove(p);
      }
    }
    return nbrCas;
  }

  /**
   * Cette méthode créee un affichage spécifique du contenu d'un tableau de type String
   *
   * @param arrondissement le tableau de type String
   * @return l'affichage
   */
  public static String toString(
      ArrayList<String> arrondissement, ArrayList<Integer> region, ArrayList<Integer> parc) {
    String affichage = Message.TITRE.getMessage();
    for (int i = 0; i < arrondissement.size(); i++) {
      affichage =
          affichage + arrondissement.get(i) + "," + region.get(i) + "," + parc.get(i) + "\n";
    }
    return affichage;
  }

  /**
   * Cette méthode écrit dans un fichier.
   *
   * @param affichage les caractère à écrire.
   * @param fichier le fichie où écrire.
   * @return le fichier avec du contenu.
   * @throws FileNotFoundException
   */
  public static File ecrire(String affichage, File fichier) throws FileNotFoundException {
    try {
      PrintWriter write = new PrintWriter(fichier);
      write.print(affichage);
      write.close();
    } catch (FileNotFoundException e) {
      System.err.println(Message.EXISTE.getMessage());
    }
    return fichier;
  }

  public static File ajouterContenuFichier(
      ArrayList<StatistiqueCSV> statistique, String nomFichierSortie) throws FileNotFoundException {
    File fichier = creeFichierVide(nomFichierSortie);
    ArrayList<ArrayList<String>> arrondissementEtParc = trierInformation(statistique);
    trierAlphabetique(arrondissementEtParc);
    ArrayList<String> arrondissement =
        chercherInformationSpecifique(arrondissementEtParc, "arrondissement");
    ArrayList<String> parc = chercherInformationSpecifique(arrondissementEtParc, "parc");
    ArrayList<Integer> nbrParc = new ArrayList<>();
    observerParc(arrondissementEtParc, nbrParc);
    ArrayList<Integer> region = calculerCasParArrondissement(arrondissement);
    String affichage = toString(arrondissement, region, nbrParc);
    fichier = ecrire(affichage, fichier);
    return fichier;
  }
}

package test.java.org.example;

import java.io.IOException;
import java.util.ArrayList;
import org.example.EcritureCSV;
import org.example.StatistiqueCSV;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import  java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
public class EcritureCSVTest {

  private static ArrayList<StatistiqueCSV> information;
  private static ArrayList<ArrayList<String>> arrondissementEtParc;
  private static ArrayList<String> arrondissement;
  private static ArrayList<String> parc;

  private static ArrayList<Integer> nbrCas;

  private static ArrayList<Integer> casParc;

  @BeforeAll
  public static void setup() {
    information = new ArrayList<>();
    information.add(
        new StatistiqueCSV(
            "2023-09-01", "20:41", "Parc Camille", "Ahuntsic-Cartierville", "Vente de drogues"));
    information.add(
        new StatistiqueCSV(
            "2023-09-03", "21:13", "Parc Camille", "Ahuntsic-Cartierville", "Vente de drogues"));
    information.add(
        new StatistiqueCSV(
            "2023-08-26", "23:11", "Parc Brook", "Pierrefonds-Roxboro", "Vente de drogues"));
    information.add(
        new StatistiqueCSV(
            "2023-09-02", "12:41", "Parc Camille", "Ahuntsic-Cartierville", "Bagarre"));
    information.add(
        new StatistiqueCSV(
            "2023-08-29",
            "07:08",
            "Parc Camille",
            "Ahuntsic-Cartierville",
            "Manifestation illégale"));
    information.add(
        new StatistiqueCSV("2023-09-12", "13:11", "Parc Carignan", "Lachine", "Bagarre"));

    parc = new ArrayList<>();
    parc.add("Parc Camille");
    parc.add("Parc Camille");
    parc.add("Parc Brook");
    parc.add("Parc Camille");
    parc.add("Parc Camille");
    parc.add("Parc Carignan");

    arrondissement = new ArrayList<>();
    arrondissement.add("Ahuntsic-Cartierville");
    arrondissement.add("Ahuntsic-Cartierville");
    arrondissement.add("Pierrefonds-Roxboro");
    arrondissement.add("Ahuntsic-Cartierville");
    arrondissement.add("Ahuntsic-Cartierville");
    arrondissement.add("Lachine");

    nbrCas = new ArrayList<>();
    nbrCas.add(4);
    nbrCas.add(1);
    nbrCas.add(1);

    casParc = new ArrayList<>();
    casParc.add(1);
    casParc.add(1);
    casParc.add(1);
  }

  public static void setInformation(ArrayList<StatistiqueCSV> information) {
    EcritureCSVTest.information = information;
  }

  @Test
  public void testTrierInformationArrondissement() {
    arrondissementEtParc = new ArrayList<>();
    arrondissementEtParc = EcritureCSV.trierInformation(information);
    ArrayList<String> endroit = arrondissementEtParc.get(0);
    Assertions.assertEquals("Ahuntsic-Cartierville", endroit.get(0));
  }

  @Test
  public void testTrierInformationParc() {
    arrondissementEtParc = EcritureCSV.trierInformation(information);
    ArrayList<String> endroit = arrondissementEtParc.get(0);
    Assertions.assertEquals("Parc Camille", endroit.get(1));
  }

  @Test
  public void testTrierAlphabetiqueArrondissement() {
    EcritureCSV.trierAlphabetique(arrondissementEtParc);
    ArrayList<String> endroit = arrondissementEtParc.get(1);
    Assertions.assertEquals("Ahuntsic-Cartierville", endroit.get(0));
  }

  @Test
  public void testTrierAlphabetiqueParc() {
    EcritureCSV.trierAlphabetique(arrondissementEtParc);
    ArrayList<String> endroit = arrondissementEtParc.get(1);
    Assertions.assertEquals("Parc Camille", endroit.get(1));
  }

  @Test
  public void testChercherInformationSpecifiqueArrondissement() {
    arrondissementEtParc = EcritureCSV.trierInformation(information);
    arrondissement =
        EcritureCSV.chercherInformationSpecifique(arrondissementEtParc, "arrondissement");
    Assertions.assertEquals("Ahuntsic-Cartierville", arrondissement.get(0));
  }

  @Test
  public void testChercherInformationSpecifiqueParc() {
    arrondissementEtParc = EcritureCSV.trierInformation(information);
    parc = EcritureCSV.chercherInformationSpecifique(arrondissementEtParc, "parc");
    Assertions.assertEquals("Parc Camille", parc.get(0));
  }

  @Test
  public void testObserverParc() {
    arrondissementEtParc = EcritureCSV.trierInformation(information);
    EcritureCSV.trierAlphabetique(arrondissementEtParc);
    EcritureCSV.observerParc(arrondissementEtParc, casParc);
    Assertions.assertEquals(1, casParc.get(0));
  }

  @Test
  public void testCalculerCasParArrondissement() {
    arrondissement =
        EcritureCSV.chercherInformationSpecifique(arrondissementEtParc, "arrondissement");
    nbrCas = EcritureCSV.calculerCasParArrondissement(arrondissement);
    Assertions.assertEquals(4, nbrCas.get(0));
  }

  @Test
  public void testAjouterCas() {
    int cas = EcritureCSV.AjouterCas(0, arrondissement, nbrCas);
    Assertions.assertEquals(4, nbrCas.get(0));
  }

  @Test
  public void testToString() {
    arrondissementEtParc = EcritureCSV.trierInformation(information);
    EcritureCSV.trierAlphabetique(arrondissementEtParc);
    arrondissement =
        EcritureCSV.chercherInformationSpecifique(arrondissementEtParc, "arrondissement");
    nbrCas = EcritureCSV.calculerCasParArrondissement(arrondissement);
    EcritureCSV.observerParc(arrondissementEtParc, casParc);
    String affichage = EcritureCSV.toString(arrondissement, nbrCas, casParc);
    Assertions.assertEquals(
        "Arrondissement,Nombre d'interventions,Nombre de parcs"
            + "\nAhuntsic-Cartierville,4,1"
            + "\nLachine,1,1"
            + "\nPierrefonds-Roxboro,1,1\n",
        affichage);
  }

  @Test
  public void testCreeFichierVide() throws IOException {
    File fileMock = mock(File.class);
    when(fileMock.exists()).thenReturn(true);

    File FichierVideTest = EcritureCSV.creeFichierVide("FichierVideTest");

    assertTrue(FichierVideTest.exists());
  }
}

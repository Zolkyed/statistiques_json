package test.java.org.example;

import org.example.LectureJson;
import org.json.simple.JSONArray;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class LectureJsonTest {
  private static JSONArray jsonArrondissements;
  private static JSONArray jsonInterventions;

  @BeforeAll
  public static void setup() {
    jsonArrondissements = new JSONArray();
    jsonInterventions = new JSONArray();

    ArrayList<String> arrondissements = new ArrayList<>();
    arrondissements.add("Ahuntsic-Cartierville");
    arrondissements.add("Anjou");
    arrondissements.add("Côte-des-Neiges—Notre-Dame-de-Grâce");

    jsonArrondissements.addAll(arrondissements);

    ArrayList<String> interventions = new ArrayList<>();
    interventions.add("Vol de véhicule à moteur");
    interventions.add("Méfait");
    interventions.add("Vols qualifiés");

    jsonInterventions.addAll(interventions);
  }

  @Test
  public void testTailleTableauDonneesJson() {
    ArrayList<ArrayList<String>> tableauDonnees = LectureJson.construireDonneeJson();
    assertEquals(2, tableauDonnees.size());
  }

  @Test
  public void testContenuJsonArrondissements() {
    ArrayList<ArrayList<String>> tableauDonnees = LectureJson.construireDonneeJson();
    ArrayList<String> jsonArrondissements = tableauDonnees.get(0);
    assertEquals(19, jsonArrondissements.size());
  }

  @Test
  public void testContenuJsonInterventions() {
    ArrayList<ArrayList<String>> tableauDonnees = LectureJson.construireDonneeJson();
    ArrayList<String> interventions = tableauDonnees.get(1);
    assertEquals(11, interventions.size());
  }

  @Test
  public void testExtraireDonneeArrondissements() {
    ArrayList<String> resultatArrondissements = LectureJson.extraireDonnee(jsonArrondissements);
    assertNotNull(resultatArrondissements);
    assertEquals(3, resultatArrondissements.size());
  }

  @Test
  public void testExtraireDonneeInterventions() {
    ArrayList<String> resultatInterventions = LectureJson.extraireDonnee(jsonInterventions);
    assertNotNull(resultatInterventions);
    assertEquals(3, resultatInterventions.size());
  }
}

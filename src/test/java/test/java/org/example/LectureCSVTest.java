package test.java.org.example;

import org.example.ChampException;
import org.example.LectureCSV;
import static org.junit.jupiter.api.Assertions.*;
import org.example.StatistiqueCSV;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import java.util.ArrayList;
import java.util.Scanner;

public class LectureCSVTest {
  @Mock
  private Scanner mockScanner = mock(Scanner.class);
  @BeforeEach
  public void setUp() {
    when(mockScanner.useDelimiter(",")).thenReturn(mockScanner);
  }

  @Test
  public void testChampNonVide() {
    String ligne = ("o,o,o,o,o");
    assertEquals(5, LectureCSV.verifierLigneChampManquant(ligne));
  }

  @Test
  public void testChampVide0() {
    String ligne = (",o,o,o,o");
    assertEquals(0, LectureCSV.verifierLigneChampManquant(ligne));
  }

  @Test
  public void testChampVide1() {
    String ligne = ("o,,o,o,o");
    assertEquals(1, LectureCSV.verifierLigneChampManquant(ligne));
  }

  @Test
  public void testIsolerInterventionArrondissement() {
    Scanner ligne =
        new Scanner("2023-09-01,20:41,Parc Camille,Ahuntsic-Cartierville,Vente de drogues");
    ArrayList<String> reference = new ArrayList<>();
    reference.add("Ahuntsic-Cartierville");
    reference.add("Vente de drogues");
    assertEquals(reference, LectureCSV.isolerInterventionArrondissement(ligne));
  }

  @Test
  public void testEmettreErreurArrondissement() {
    String message = null;
    try {
      LectureCSV.emettreErreurDeContenu(1, 0);
    } catch (Exception e) {
      message = e.getMessage();
    }
    assertEquals(
        "Erreur dans le fichier 'null' à la ligne 1 : Le champ 'Arrondissement' n'est pas \n"
            + "dans la liste d'arrondissement\n",
        message);
  }

  @Test
  public void testVerifierLigneComparaisonDescription() {
    Scanner ligne = new Scanner("o,o,o,Ahuntsic-Cartierville,o");
    String message = null;
    try {
      LectureCSV.verifierLigneComparaison(ligne, 3);
    } catch (Exception e) {
      message = e.getMessage();
    }
    assertEquals(
        "Erreur dans le fichier 'null' à la ligne 3 : Le champ 'Description' n'est pas \n"
            + "dans la liste de description\n",
        message);
  }

  @Test
  public void testFormatLireUneLigne() {
    StatistiqueCSV ligneLue  = LectureCSV.lireUneLigne(mockScanner);
    verify(mockScanner, times(5)).useDelimiter(",");
    verify(mockScanner, times(5)).next();
  }
  @Test
  public void testLireUneLigneValeurs() {
    when(mockScanner.next()).thenReturn("2023-09-01", "20:41", "Parc Camille", "Ahuntsic-Cartierville", "Vente de drogues");
    StatistiqueCSV resultat = LectureCSV.lireUneLigne(mockScanner);

    assertEquals("2023-09-01", resultat.getDate());
    assertEquals("20:41", resultat.getHeure());
    assertEquals("Parc Camille", resultat.getParc());
    assertEquals("Ahuntsic-Cartierville", resultat.getArrondissement());
    assertEquals("Vente de drogues", resultat.getDescription());
  }
  @Test
  public void testVerifierFichierNonVide() {
    when(mockScanner.hasNext()).thenReturn(true);
    LectureCSV.VerifierFichierVide(mockScanner);
    verify(mockScanner, times(1)).hasNext();
  }
  @Test
  public void testLireContenu() throws ChampException {
    when(mockScanner.nextLine())
            .thenReturn("Ligne ignoree Titre Champs Excel")
            .thenReturn("champ1 champ2 champ3")
            .thenReturn("champ4 champ5 champ6")
            .thenReturn("champ7 champ8 champ9")
            .thenReturn("");
    LectureCSV.lireContenu(mockScanner);
  }
}

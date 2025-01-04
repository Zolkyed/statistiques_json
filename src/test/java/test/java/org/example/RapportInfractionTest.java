package test.java.org.example;

import org.example.RapportInfraction;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

class RapportInfractionTest {
    @Test
    public void testDeterminerArgumentsDeuxArguments() {
        String[] args = {"FichierEntree.csv", "FichierSortie.csv"};
        ArrayList<String> resultat = RapportInfraction.determinerArguments(args);
        assertEquals("FichierEntree.csv", resultat.get(0));
        assertEquals("FichierSortie.csv", resultat.get(1));
    }

    @Test
    public void testDeterminerArgumentsTroisArguments() {
        String[] args = {"--english", "FichierEntree.csv", "FichierSortie.csv"};
        ArrayList<String> resultat = RapportInfraction.determinerArguments(args);
        assertEquals("FichierEntree.csv", resultat.get(0));
        assertEquals("FichierSortie.csv", resultat.get(1));
    }
}
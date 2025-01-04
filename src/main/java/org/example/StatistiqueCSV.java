package org.example;

import java.util.*;

public class StatistiqueCSV {
  protected String date;
  protected String heure;
  protected String parc;
  protected String arrondissement;
  protected String description;

  public String getDate() {
    return date;
  }
  public String getHeure() {
    return heure;
  }
  public String getParc() {
    return parc;
  }
  public String getArrondissement() {
    return arrondissement;
  }
  public String getDescription() {
    return description;
  }


  public StatistiqueCSV(
      String date, String heure, String parc, String arrondissement, String description) {
    this.date = date;
    this.heure = heure;
    this.parc = parc;
    this.arrondissement = arrondissement;
    this.description = description;
  }

  public StatistiqueCSV() {}

  @Override
  public String toString() {
    return "StatistiqueCSV{"
        + "date='"
        + date
        + '\''
        + ", heure='"
        + heure
        + '\''
        + ", parc='"
        + parc
        + '\''
        + ", arrondissement='"
        + arrondissement
        + '\''
        + ", description='"
        + description
        + '\''
        + '}';
  }
}

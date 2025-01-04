## Fonctionnalités :

Le fichier de sortie doit produire des statistiques sur le nombre de parcs par arrondissement où il y a eu des interventions policières.

Le fichier de sortie présente la structure suivante :

**Fichier de Sortie :**

| Arrondissement          | Nombre d'interventions | Nombre de parcs |
|-------------------------|------------------------|-----------------|
| Ahuntsic-Cartierville   | 4                      | 1               |
| Lachine                 | 1                      | 1               |
| Pierrefonds-Roxboro     | 1                      | 1               |
| LaSalle                 | 4                      | 2               |

Le fichier de d'entrée présente la structure suivante :

**Fichier d'entrée :**

| Date        | Heure | Parc          | Arrondissement        | Description                      |
|-------------|-------|---------------|-----------------------|----------------------------------|
| 2023-09-01  | 20:41 | Parc Camille  | Ahuntsic-Cartierville | Vente de drogues                 |
| 2023-09-03  | 21:13 | Parc Camille  | Ahuntsic-Cartierville | Vente de drogues                 |
| 2023-08-26  | 23:11 | Parc Brook    | Pierrefonds-Roxboro   | Vente de drogues                 |
| 2023-09-02  | 12:41 | Parc Camille  | Ahuntsic-Cartierville | Bagarre                          |
| 2023-08-29  | 07:08 | Parc Camille  | Ahuntsic-Cartierville | Manifestation illégale           |
| 2023-09-12  | 13:11 | Parc Carignan | Lachine               | Bagarre                          |

## Installation :

### Prérequis :

1. Java Development Kit (JDK) 17
2. Variable d'environnement Java dans le Path

https://www.oracle.com/ca-fr/java/technologies/downloads/#java17

### Installation de la variable d'environnement :

### Sous Windows :

System -> Environment Variables ->  System Variables -> Path -> New

```plaintext
C:\Program Files\Java\jdk-17\bin
```

System -> Environment Variables ->  System Variables -> New...

Nom Variable : `JAVA_HOME`

Valeur Variable :`C:\Program Files\Java\jdk-17`

## Utilisation :

| Paramètre                 | Description                                |
|---------------------------| ------------------------------------------ |
| `--english`               | Active le mode anglais pour le logiciel     |
| `[chemin Fichier Entree]` | Chemin vers le fichier CSV d'entrée    |
| `[chemin Fichier Sortie]` | Chemin vers le fichier CSV de sortie   |

Pour exécuter le logiciel, utilisez la commande suivante :

```powershell
java -jar inf2050-sprint1-1.0-SNAPSHOT-dependencies-all.jar [--english] [chemin Fichier Entree] [chemin Fichier Sortie]
```

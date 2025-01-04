Liste des exigences
===============
- TP.1 : fichier de résultat généré par le logiciel devra contenir la liste des arrondissements présents dans
  le fichier d'entrée, triés en ordre alphabétique, avec le nombre d'interventions policières dans
  l'arrondissement et produire une statistique sur le nombre de parcs par
  arrondissements où il y a eu des interventions policières.
- TP.2 : Produire un titre en anglais quand --english est le premier arguments.
- TP.3 : Envoyer un message expliquant l'utilisation du programme si le nombre d'arguments n'est pas le bon en francais.
- TP.4 : Envoyer un message expliquant l'utilisation du programme si le nombre d'arguments n'est pas le bon en anglais.
- TP.5 : Envoyer un message indiquant que le chemin du fichier d'entrée n'est pas bon en francais.
- TP.6 : Envoyer un message indiquant que le chemin du fichier d'entrée n'est pas bon en anglais.
- TP.7 : Envoyer un message indiquant que le fichier est vide en francais.
- TP.8 : Envoyer un message indiquant que le fichier est vide en anglais.
- TP.9 : Envoyer un message indiquant que la n-ième ligne du champ Description du fichier est erroné en francais.
- TP.10 : Envoyer un message indiquant que la n-ième ligne du champ Description du fichier est erroné en anglais.
- TP.11 : Envoyer un message indiquant que la n-ième ligne du champ Arrondissement du fichier est erroné en francais.
- TP.12 : Envoyer un message indiquant que la n-ième ligne du champ Arrondissement du fichier est erroné en anglais.
- TP.13 : Envoyer un message indiquant que la n-ième ligne du champ Date du fichier est manquant en francais.
- TP.14 : Envoyer un message indiquant que la n-ième ligne du champ Date du fichier est manquant en anglais.
- TP.15 : Envoyer un message indiquant que la n-ième ligne du champ Heure du fichier est manquant en francais.
- TP.16 : Envoyer un message indiquant que la n-ième ligne du champ Heure du fichier est manquant en anglais.
- TP.17 : Envoyer un message indiquant que la n-ième ligne du champ Parc du fichier est manquant en francais.
- TP.18 : Envoyer un message indiquant que la n-ième ligne du champ Parc du fichier est manquant en anglais.
- TP.19 : Envoyer un message indiquant que la n-ième ligne du champ Arrondissement du fichier est manquant en francais.
- TP.20 : Envoyer un message indiquant que la n-ième ligne du champ Arrondissement du fichier est manquant en anglais.
- TP.21 : Envoyer un message indiquant que la n-ième ligne du champ Description du fichier est manquant en francais.
- TP.22 : Envoyer un message indiquant que la n-ième ligne du champ Description du fichier est manquant en anglais.


  
 
Plan de tests
==========
| Nom du fichier + .csv | Fonctionnalité | Résultat attendu                                                 | Description                | Données                                                                                          |                                                                                          
|:---------------------:|:---------------|:-----------------------------------------------------------------|:---------------------------|:-------------------------------------------------------------------------------------------------|
|          C1           | TP.1           | Production du rapport correcte en francais                       | Tout est correcte          | Bon fichier d'entrée avec bon arguments                                                          |
|          C1           | TP.2           | Production du rapport correcte en anglais                        | Tout est correcte          | Bon fichier d'entrée avec bon arguments et --english                                             |
|          C1           | TP.3           | Affichage du message d'utilisation en francais                   | Mauvais nombre d'arguments | 0-1 arguments,3 arguments sans --english et plus de 3 arguments                                  |
|          C1           | TP.4           | Affichage du message d'utilisation en anglais                    | Mauvais nombre d'arguments | --english comme premier argument soit 1,2 ou plus de 3 arguments                                 |  
|          C1           | TP.5           | Message d'erreur du chemin de fichier en francais                | Mauvais argument           | L'argument contenant le chemin du fichier n'est pas bon                                          |                    
|          C1           | TP.6           | Message d'erreur du chemin de fichier en anglais                 | Mauvais argument           | le premier argument est --english et l'argument contenant le chemin du fichier n'est pas bon     |
|          C2           | TP.7           | Message d'erreur fichier vide en francais                        | Fichier vide               | bons arguments, mais le fichier d'entrée est vide                                                |
|          C2           | TP.8           | Message d'erreur fichier vide en anglais                         | Fichier vide               | premier argument : --english et reste bons arguments, mais le fichier d'entrée est vide          |
|          C3           | TP.9           | Message d'erreur de contenu description en francais              | Fichier mal écrit          | Bons arguments,mais erreur à ligne 4 au champ Description                                        |
|          C3           | TP.10          | Message d'erreur de contenu description en anglais               | Fichier mal écrit          | Premier argument : --english ,sinon bons arguments,mais erreur à ligne 4 au champ description    |
|          C4           | TP.11          | Message d'erreur de contenu arrondissement en francais           | Fichier mal écrit          | Bons arguments,mais erreur à ligne 6 au champ Arrondissement                                     |
|          C4           | TP.12          | Message d'erreur de contenu arrondissement en anglais            | Fichier mal écrit          | Premier argument : --english ,sinon bons arguments,mais erreur à ligne 6 au champ arrondissement |
|          C5           | TP.13          | Message d'erreur de champ manquant de date en francais           | Fichier mal écrit          | Bons arguments,mais date manquante à la ligne 5                                                  |
|          C5           | TP.14          | Message d'erreur de champ manquant de date en anglais            | Fichier mal écrit          | Premier argument : --english ,sinon bons arguments,mais date manquante à la ligne 5              |
|          C6           | TP.15          | Message d'erreur de champ manquant de heure en francais          | Fichier mal écrit          | Bons arguments,mais heure manquante à la ligne 6                                                 |
|          C6           | TP.16          | Message d'erreur de champ manquant de heure en anglais           | Fichier mal écrit          | Premier argument : --english ,sinon bons arguments,mais heure manquante à la ligne 6             |
|          C7           | TP.17          | Message d'erreur de champ manquant de Parc en francais           | Fichier mal écrit          | Bons arguments,mais Parc manquant à la ligne 7                                                   |
|          C7           | TP.18          | Message d'erreur de champ manquant de Parc en anglais            | Fichier mal écrit          | Premier argument : --english ,sinon bons arguments,mais Parc manquant à la ligne 7               |
|          C8           | TP.19          | Message d'erreur de champ manquant de Arrondissement en francais | Fichier mal écrit          | Bons arguments,mais Arrondissement manquant à la ligne 2                                         |
|          C8           | TP.20          | Message d'erreur de champ manquant de Arrondissement en anglais  | Fichier mal écrit          | Premier argument : --english ,sinon bons arguments,mais Arrondissement manquant à la ligne 2     |
|          C9           | TP.21          | Message d'erreur de champ manquant de Description en francais    | Fichier mal écrit          | Bons arguments,mais Description manquant à la ligne 4                                            |
|          C9           | TP.22          | Message d'erreur de champ manquant de Description en anglais     | Fichier mal écrit          | Premier argument : --english ,sinon bons arguments,mais Description manquant à la ligne 4        |


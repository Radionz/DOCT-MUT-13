# Spécifications 
[DOCT-MUT] GitHub 13
Dorian BLANC
Manuel PAVONE

La chaîne de production
------

Nous récupérons les sources du projet P à tester, ces sources sont compilées (javac) et testées (JUnit) via les tests du projet afin de récupérer un rapport de test initial.
Ensuite l’étape de génération des mutants commence, notre solution en utilisant Spoon pour la modification des sources utilise ses mutations appliquée avec ses sélecteurs.

<p align="center"> <img src="http://i.imgur.com/0jSwn6U.png"></img></p>

Chaque mutant est testé (JUnit) et fourni un rapport de test au format xml.

<p align="center"> <img src="http://i.imgur.com/TWIzw5P.png"></img></p>

A la fin du processus les données des différents tests sont agrégées et donnent lieu à un rapport sous forme de page html (claire et moderne) permettant de consulter clairement les cas où les mutants ont survécus et d’analyser où se trouve le code incriminé. 

<p align="center"> <img src="http://i.imgur.com/88OCcaZ.png"></img></p>

Liste des mutations
------

| **Nom**       | **Modification**           |
| ------------- |-------------|
| Bomb     | ajouter assert(false) après une instruction |
| Absolue      | Modifier une expression arithmétique e en |e|      |
| Rationel-Arithmetique | Modifier un opérateur relationnel arithmétique par un autre      |
| Arithmetique | Modifier un opérateur arithmétique par un autre      |
| Booléen | Modifier un opérateur booléen par un autre      |
| Variable | Modifier un nom de variable par un autre du même type qui soit conforme aux règles de programmation Java. |
| Variable-constante | Modifier un nom de variable par une constante du bon type .      |
| Constante-constante | Modifier une constante par une autre constante du bon type .      |
| Comparaison | Modifier les opérateurs de comparaison      |
| Major-Minor | Inverser une incrémentation par une décrémentation et vice versa      |
| Logique | Changer les opérateurs logiques      |
| Return | Faire sortir le code d'une méthode avant qu'elle ne se termine      |
| Constructeur | Modifier le contenu d'un constructeur     |
| Instanciation | Supprimer l'instanciation d'un objet     |
| Paramètre | Interchanger les paramètres de même type     |
| Boucle | Changer la condition finale d'une boucle ou effectuer moins de tous pour un for     |
| Déréférencer | Casser l'encapsulation et passer d'attributs à variables locales     |

La liste comprend les opérateurs de base pour effectuer des mutations sur un programme. Par la suite nous ajouterons d’autres types de mutations à notre pool.
Chaque mutation entraînera un changement dans le source du code. Le code une fois compilé, nous pourrons dérouler notre batterie de tests pour vérifier la conformité du programme.	
Il sera possible quand nous aurons atteint un stade suffisamment avancé de notre projet, ajouter des mutations de façon dynamique. L’idée étant de coupler une classe java et un fichier de configuration pour rajouter “à la volée” des nouveaux types de mutations.


Liste de sélécteurs
------

Programme entier
Package
Classe
Méthode
Dans un intervalle de lignes


Manuel d'utilisation
------

Depuis la ligne de commande nous pouvons choisir quelles mutations appliquer avec quel sélecteurs.
Nous pouvons aussi spécifier le répertoire de sauvegardes des rapports xml et du rapport html.

$: mut [[Option] [Name]]...

-s, --selector .............  Choix du selecteur <br>
-t, --type ....................  Choix de la mutation <br>
 => [Complément]

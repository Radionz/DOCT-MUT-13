# Spécifications 
[DOCT-MUT] GitHub 13
Dorian BLANC
Manuel PAVONE

======

##### La chaîne de production

Nous récupérons les sources du projet P à tester, ces sources sont compilées (javac) et testées (JUnit) via les tests du projet afin de récupérer un rapport de test initial.
Ensuite l’étape de génération des mutants commence, notre solution en utilisant Spoon pour la modification des sources utilise ses mutations appliquée avec ses sélecteurs.

<p align="center"> <img src="http://i.imgur.com/QEq3d1S.png"></img></p>

Chaque mutant est testé (JUnit) et fourni un rapport de test au format xml.

<p align="center"> <img src="http://i.imgur.com/TWIzw5P.png"></img></p>

A la fin du processus les données des différents tests sont agrégées et donnent lieu à un rapport sous forme de page html (claire et moderne) permettant de consulter clairement les cas où les mutants ont survécus et d’analyser où se trouve le code incriminé. 

<p align="center"> <img src="http://i.imgur.com/88OCcaZ.png"></img></p>


======

##### Liste des mutations 

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

La liste comprend les opérateurs de base pour effectuer des mutations sur un programme. Par la suite nous ajouterons d’autres types de mutations à notre pool.
Chaque mutation entraînera un changement dans le source du code. Le code une fois compilé, nous pourrons dérouler notre batterie de test pour vérifier la conformité du programme.	
Il sera possible quand nous aurons atteint un stade suffisamment avancé de notre projet, ajouter des mutations de façon dynamique. L’idée étant de coupler une classe java et un fichier de configuration pour rajouter “à la volée” des types de mutation.

======

##### Manuel d'utilisation

Proposer un mécanisme utilisable depuis la ligne de commande qui permette:
D'appliquer des mutations sur du code
De collecter les résultats des tests appliqués aux mutants
De produire une synthèse (une page html) explicitant les résultats de votre analyse par mutation: quels mutants ont résistés, quels mutants ont été tués, ..


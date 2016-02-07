# DOCT-MUT-13

<b> La finalité de notre projet est de <i>spécifier</i> et <i>d'implémenter</i> un environnement de test par mutation capable <i>d'analyser un banc de test</i> en Java. </b>

===

#### L'équipe

| Prénom        | Nom           |
| ------------- |:-------------:|
| Manuel     | Pavone |
| Dorian      | Blanc      |

#### Présentation du sujet

Une application de qualité passe obligatoirement par une bonne couverture de tests unitaires, mais savons nous quantifier leur pertinence, leur qualité ?
De plus une couverture de code par les tests à 100% ne signifie pas du code 100% testé.

Les tests peuvent être utilisés pour mettre en évidence la présence de bugs dans un programme mais jamais leur absence.

L’idée  est de modifier le code source de notre application (créer des bugs, des mutants) et de vérifier que nos tests détectent les modifications (nos tests doivent échouer, tuer les mutants). Si nos tests passent toujours, c’est qu’ils ne sont pas pertinents.



# DOCT-MUT-13 KILL THE MUTANT

![alt text](http://i.imgur.com/ZxieYsT.png "Weapon")

<b> La finalité de notre projet est de <i>spécifier</i> et <i>d'implémenter</i> un environnement de test par mutation capable <i>d'analyser un banc de test</i> en Java. </b>

===

#### L'équipe

| Prénom        | Nom           |
|:-------------:|:-------------:|
| Julien  | Andre |
| Dorian  | Blanc |
| Manuel  | Pavone  |

#### Présentation du sujet

Une application de qualité passe obligatoirement par une bonne couverture de tests unitaires, mais savons nous quantifier leur pertinence, leur qualité ?
De plus une couverture de code par les tests à 100% ne signifie pas du code 100% testé.

Les tests peuvent être utilisés pour mettre en évidence la présence de bugs dans un programme mais jamais leur absence.

La théorie est que si une modification est introduite dans le code source sans que le comportement du programme soit affecté, alors cela indique soit que le code modifié n’est jamais exécuté, soit que les tests sont incapables de détecter la modification.

L’idée  est de modifier le code source de notre application (créer des bugs, des mutants) et de vérifier que nos tests détectent les modifications (nos tests doivent échouer, tuer les mutants). Si nos tests passent toujours, c’est qu’ils ne sont pas pertinents.

#### Environnement de test par mutation

Plus formellement, modifier le programme P en P’ en injectant une modification syntaxique correcte (P’ compile toujours) et idéalement, le comportement de P’ doit être différent de celui de P.

![alt text](http://i.imgur.com/EJ6AkDX.png "Mutant")


Les tests ensuite exécutés doivent être capable de détecter la modification dans la mesure ou celle-ci impacte le fonctionnement du programme.

![alt text](http://i.imgur.com/lVwz1kA.png "Mutant")

On doit donc être en mesure de générer des classes mutantes à partir de classes métiers sources. Une mutation étant une modification du code source, comme par exemple, l’action de remplacer un opérateur par un autre. Voici quelques exemples :
* + ► –
* * ► /
* >= ► ==
* true ► false.

L’ensemble des mutation applicables constitue le “Pool” de mutations.

La génération à proprement parler consiste à parcourir toutes les instructions du code métier et pour chacune déterminer si des mutations sont applicables.
Si oui, chaque mutation donnera naissance à un nouveau mutant, les mutants générés  (&& → || et ++ → --) sont ceux-ci :

![alt text](http://i.imgur.com/U1HvFKm.png "Mutant")

Chaque dérivation mutante est ensuite compilée (à condition bien sur que le programme initial compile lui-même préalablement) en vue de passer le banc de tests.
Pendant le processus de test sur les mutants, chaque exécution génère un rapport de test au format xml qui contient les information relative au test (valide ou non et le détail des tests).
Enfin à l’issu de ce processus est calculé (par classe) le score de mutation = nombre de mutants tués/nombre de mutants.
Le but étant d’avoir un score de 100%.



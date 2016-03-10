# Rapport
---
## Architecture

<p align="center"> <img src="http://i.imgur.com/ms0IKph.png"></img></p>

Tout d'abord nous avons <b>deux projets maven</b>: Le premier contenant les processeurs les processeurs (donc les mutations appliquer sur le code) et le deuxième qui contient le code à tester.

Le Framework que nous avons développé s'intègre automatiquement dans la chaine de build de maven lors des tests. 
La première est le chargement des sources par le plugin spoon en utilisant le pom.xml.

Le choix des processeurs à appliquer se fera dans le fichier mutations.json. En se calquant sur ce fichier, l'excéutable start.sh modifie le pom pour obtenir les mutations choisies.

Lors de l'appel aux processeurs, nous allons vérifier sur quelles classes/méthodes les mutations doivent être appliquées.

Une fois les mutations effectuées, Junit testera les classes générées et nous crééra un rapport.xml. Nous réunissons tous les rapports dans un seul, pour qu'on puisse l'afficher par la suite dans notre index.html.


## Réalisation du framework
### Spoon
Nous utilisons spoon afin d'appliquer faciliment les mutations au code.
### Maven plugin
Nous définissons un plugin maven afin de pouvoir l'exporter et ainsi l'importer via maven dans un projet qui utiliserait nos mutations.
## Forces et faiblesses
### Forces
- Manipulation de la chaine de build et insertion des mutations.  
- Génération d'un rapport HTML des mutations avec différentiel à la manière de git, mais aussi un affichage global (navigation), récapitulatif des mutations.
- Nombreux processeurs (~20).
- Possibilité de spécifier package, classe et méthode ciblée, ainsi qu'un taux (de 0 à 1) correspondant au pourcentage de chance d'effectuer la mutation.
- Gestion d'un fichier JSON de configuration  pour l'exécution des mutations.

### Faiblesses
- Sélection des mutations trop spécifiques (manque d'un processeur qui modifie n'importe quel élément et le remplace par un autre élément correct).
- Nécessité de la présence des processeurs dans un même package.
- Utilisation d'un fichier .sh (bash) pour l'exécution des mutations, il ne marche pas sur Windows sans un powershell (ou équivalent), ce qui rend notre framework dépendant de sa plateforme.

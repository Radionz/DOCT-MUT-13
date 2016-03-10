# Rapport
---
## Architecture
Quatres murs, un plafond et un sol. (des fenêtres avec de la chance)
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

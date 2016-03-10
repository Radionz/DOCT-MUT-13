# Exécution de notre framework au sein de votre projet
---
## Etape 1 : Configuration des mutations 
Pour pouvoir lancer les mutations il faut les spécifier dans un fichier .json (mutations.json par exemple) avec un format particulier comme suit.
```json
{
	"mutations":{
		"mut1":{
			"name":"Processor1",
			"packageName" : "ThePackageContainingYourClass",
			"className": "OneOfYourClassName",
			"methodName": "OneOfYourMethodName",
			"mutation_probability": 0..1
		},
		"mut2":{
			"name":"Processor2",
			"packageName" : "ThePackageContainingYourClass",
			"className": "OneOfYourClassName",
			"methodName": "OneOfYourMethodName",
			"mutation_probability": 0..1
		}...
	}
}
```
nb : "mut1" et "mut2" sont des clés, leur nom n'a aucun impact sur le parsing du json.  
L'attribut "name" est le nom du processeur à utiliser (liste exhaustive : DivProcessor, EqualsProcessor, GEProcessor, GTProcessor, LEProcessor, LEProcessor, LTProcessor, MinusProcessor, MultProcessor, NegProcessor, NotEqualsProcessor, PlusProcessor, PosProcessor, PostDecProcessor, PostIncProcessor, PreDecProcessor, PreIncProcessor) sur la classe donnée (className), contenue dans le package (packageName), ainsi que la méthode donnée (methodName).  
L'attribut "mutation_probability" indique le pourcentage de chance que la mutation se produise, il varie de 0 à 100% (0 à 1).
## Etape 2 : Lancement des mutations
Tout ce qu'il vous reste à faire désormais est de lancer l'exécutable .sh avec le fichier .json en paramètre.  
Ainsi si l'on est à la racine du projet (au même niveau que le pom.xml de votre projet) avec les fichiers run.sh et mutations.json, il suffit d'exécuter la commande suivante.
```sh
run.sh mutations.json
```
Si vous avez correctement indiqué le chemin d'accès de votre navigateur web préféré à la fin du fichier .sh (run.sh ici), une page web comportant des informations sur les mutations de votre code devrait s'ouvrir à la fin de l'exécution.
# Construction de notre framework au sein de votre projet
---
## Vous disposez des sources ?
### Etape 0 : Placer les projets 
Aucune condition à priori, placez simplement votre projet ainsi que le notre en local sur votre ordinateur.  
nb : les deux projets doivent être des projets maven.
### Etape 1 : Construisez notre framework 
Pour cela, lancer le cycle d'installation de maven en vous plaçant à la racine de notre projet.
```sh
$ mvn install
```
Notre plugin maven est maintenant près à utiliser !
### Etape 2 : Import de notre framework dans votre projet 
Plusieurs manipulations sont nécessaires avant de pouvoir lancer notre plugin.
#### Etape 2.1 : Ajout des dépendances maven
Dans une balise plugins au sein de votre projet, 
```xml
<project>
    ...
    <build>
        <plugins></plugins>
    </build>
    ...
</project>
```
ajoutez les plugins suivant
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <version>2.18.1</version>
    <configuration>
        <testFailureIgnore>true</testFailureIgnore>
    </configuration>
</plugin>
<plugin>
    <groupId>fr.inria.gforge.spoon</groupId>
    <artifactId>spoon-maven-plugin</artifactId>
    <version>2.2</version>
    <executions>
        <execution>
            <phase>generate-sources</phase>
            <goals>
                <goal>generate</goal>
            </goals>
        </execution>
    </executions>
    <configuration>
        <processors> 
            <processor>fr.unice.polytech.doct13.processors.PostDecProcessor</processor>
        </processors>
    </configuration>
    <dependencies>
        <dependency>
            <groupId>fr.polytech.doctmut.13</groupId>
            <artifactId>mutation</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>fr.inria.gforge.spoon</groupId>
            <artifactId>spoon-core</artifactId>
            <version>5.0.2</version>
        </dependency>
    </dependencies>
</plugin>
<plugin>
    <groupId>fr.polytech.doctmut.13</groupId>
    <artifactId>mutation</artifactId>
    <version>1.0-SNAPSHOT</version>
    <executions>
        <execution>
            <phase>test</phase>
            <goals>
                <goal>yayo</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```
### Etape 3.1 : Préparation à l'exécution du framework
Ajouter un fichier .sh (run.sh par exemple) à la racine de votre projet.
Avec pour contenu
```sh
#!/bin/bash
# Ici on met l'endroit que nous voulons modifier dans le pom.xml
prec="<processor>.*<\/processor>"
# Emplacement du fichier pom
pom_path="."
# Emplacement de l'html
html_path="$pom_path\target\html-report"
report_final="report.js"
tabchar="	"
> $report_final
echo "var report = '<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>
<mutations>" > $report_final 
while IFS='' read -r line || [[ -n "$line" ]]; do
	if [[ $line == *"name"* ]]
	then
		line=`echo "$line" | sed -e "s|$tabchar||g" -e "s| ||g" -e "s|\".*: *\"||g" -e "s|\",||g"`;
		echo "$line";
		sed -i -e "s/$prec/<processor>fr.unice.polytech.doct13.processors.$line<\/processor>/g" "$pom_path\pom.xml"
		#je lance les tests
		mvn test -f "$pom_path"
		testresult=`grep '<.*>' target/html-report/report.xml | sed -e "s/<?xml.*<mutation>/<mutation name=\"$line\">/"`
		echo "$testresult" >> $report_final
	fi
done < "$1"
echo "</mutations>'" >> $report_final
sed -i -e "s/$/\\\/g" -e "s/<\/mutations>'\\\/<\/mutations>/" $report_final
#"my\path\to\a\browser" "$html_path\index.html"
```
nb : Vous devez remplacer "my\path\to\a\browser" par le chemin d'accès menant à un exécutable permettant d'ouvrir un navigateur web.  
Les résultats des mutations seront ainsi automatiquement affichés sur la page web affichée.
package fr.unice.polytech.doct13.plugin.report;
import fr.unice.polytech.doct13.plugin.report.HTMLhelper;
import org.apache.commons.io.FilenameUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by blanc on 25/02/2016.
 */

@Mojo(name="report")
public class ReportMojo extends AbstractMojo{

    @Parameter(defaultValue = "${project}", required=true,readonly = true)
    private MavenProject project;

    public void execute() throws MojoExecutionException,MojoFailureException{
        getLog().info("HTML Report");
        System.out.println(project.getBasedir()+"/target/surefire-reports");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;

        File dirTarget = new File(project.getBasedir()+"/target/html-report");
        if(!dirTarget.exists()){
            dirTarget.mkdir();
        }

        try {
            List<NodeList> nlList = new ArrayList<NodeList>();
            File htmlReport = new File(project.getBasedir() + "/target/html-report/htmlReport.html");
            for (File fXmlFile : new File(project.getBasedir() + "/target/surefire-reports").listFiles()) {
                String extension = FilenameUtils.getExtension(fXmlFile.getName());
                if (extension.equals("xml")) {
                    if (!htmlReport.exists()) {
                        htmlReport.createNewFile();
                    }
                    dBuilder = dbFactory.newDocumentBuilder();
                    Document doc = dBuilder.parse(fXmlFile);
                    System.out.println("ROOT : " + doc.getDocumentElement().getNodeName());

                    NodeList nl = doc.getElementsByTagName("testcase");
                    nlList.add(nl);
                }
            }
            PrintWriter writer = new PrintWriter(htmlReport.getAbsolutePath(), "UTF-8");


            int mutantVivant = 0;
            int mutantMort = 0;
            String html = "";
            for (NodeList nl : nlList){
                String className = "";
                for (int i = 0; i < nl.getLength(); i++) {
                    Element elem = (Element) nl.item(i);
                    if (className.isEmpty()) {
                        className = elem.getAttribute("classname");
                        html += "<div style='border: 1px solid;background-color: #EEE'>Dans la classe : " + className + "</div>\n";
                        html += "<table id='tableMutants'><tr><td>Mutant Vivant</td><td>Mutant Tue</td></tr>\n";
                    }
                    if (elem.getChildNodes().getLength() == 0) {
                        html += "<tr><td class='aliveMut'>Dans la methode " + elem.getAttribute("name") + "</td><td></td></tr>\n";
                        mutantVivant++;
                    } else {
                        html += "<tr><td></td><td class='deadMut'>Dans la methode " + elem.getAttribute("name") + "</td></tr>\n";
                        mutantMort++;
                    }
                }
                html += "</table>\n";
                html += "<div style='text-align: center;'>Nombre de Mutants vivant : "+mutantVivant+"</div>\n";
                html += "<div style='text-align: center;'>Nombre de Mutants mort : "+mutantMort+"</div>\n";
                float porcentageDeadMut = ((float)mutantMort/((float)mutantMort+(float)mutantVivant))*100;
                float porcentageAliveMut = (((float)mutantVivant/((float)mutantMort+(float)mutantVivant))*100);
                html += "<div style='text-align: center;'>% de Mutants mort : "+porcentageDeadMut+"% </div>\n";
                html += "<div style='text-align: center;'>% de Mutants vivant : "+porcentageAliveMut+"% </div>\n";
            }
            writer.print(HTMLhelper.inHTMLbody(html));

            writer.close();
        }
        catch (IOException e) {e.printStackTrace();}
        catch (ParserConfigurationException e) {e.printStackTrace();}
        catch (SAXException e) {e.printStackTrace();}
    }
}

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
import java.util.HashMap;
import java.util.List;


/**
 * Created by blanc on 25/02/2016.
 */

@Mojo(name = "report")
public class ReportMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("HTML Report");
        System.out.println(project.getBasedir() + "/target/surefire-reports");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;

        File dirTarget = new File(project.getBasedir() + "/target/html-report");
        if (!dirTarget.exists()) {
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

            int aliveMutant = 0;
            int deadMutant = 0;
            String html = "";
            for (NodeList nl : nlList) {
                String className = "";
                for (int i = 0; i < nl.getLength(); i++) {
                    Element elem = (Element) nl.item(i);
                    if (className.isEmpty()) {
                        className = elem.getAttribute("classname");
                        html += "<div style='border: 1px solid;background-color: #EEE'>Class : " + className + "</div>\n";
                        html += "<table id='tableMutants'><tr><td>Alive (Method)</td><td>Dead (Method)</td></tr>\n";
                    }
                    if (elem.getChildNodes().getLength() == 0) {
                        html += "<tr><td class='aliveMut'>" + elem.getAttribute("name") + "</td><td></td></tr>\n";
                        aliveMutant++;
                    } else {
                        html += "<tr><td></td><td class='deadMut'>" + elem.getAttribute("name") + "</td></tr>\n";
                        deadMutant++;
                    }
                }
                html += "</table>\n";
                html += "<div style='text-align: center;'>Alive : " + aliveMutant + "</div>\n";
                html += "<div style='text-align: center;'>Dead : " + deadMutant + "</div>\n";
                float porcentageDeadMut = ((float) deadMutant / ((float) deadMutant + (float) aliveMutant)) * 100;
                float porcentageAliveMut = (((float) aliveMutant / ((float) deadMutant + (float) aliveMutant)) * 100);
            }
            html += "<br/><div id=\'container\' style=\'min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto\'></div>\n";
            writer.print(HTMLhelper.inHTMLbody(HTMLhelper.highchartsPie("Chart Alive / Dead", aliveMutant, deadMutant), html));


            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }
}

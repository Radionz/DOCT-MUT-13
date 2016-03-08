package fr.unice.polytech.doct13.plugin.report;

import org.apache.commons.io.FilenameUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by blanc on 25/02/2016.
 */

@Mojo(name = "report")
public class ReportMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("XML Report");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = null;

        File dirTarget = new File(project.getBasedir() + "/target/html-report");
        if (!dirTarget.exists()) {
            dirTarget.mkdir();
        }

        try {
            List<NodeList> nlList = new ArrayList<NodeList>();
            dBuilder = dbFactory.newDocumentBuilder();
            Document report = dBuilder.newDocument();
            Element rootElement = report.createElement("tests");
            report.appendChild(rootElement);
            for (File file : new File(project.getBasedir() + "/target/surefire-reports").listFiles()) {
                String extension = FilenameUtils.getExtension(file.getName());
                if (extension.equals("xml")) {
                    Document surefireReport = dBuilder.parse(file);
                    NodeList nl = surefireReport.getElementsByTagName("testcase");
                    nlList.add(nl);
                }
            }

            int aliveMutant = 0;
            int deadMutant = 0;
            for (NodeList nl : nlList) {
                String className = "";
                Element classElt = null;
                for (int i = 0; i < nl.getLength(); i++) {
                    Element testCase = (Element) nl.item(i);
                    if (className.isEmpty()) {
                        System.out.println("CLASS");
                        className = testCase.getAttribute("classname");
                        classElt = report.createElement("class");
                        rootElement.appendChild(classElt);

                        Attr attrClassName = report.createAttribute("name");
                        attrClassName.setValue(className);
                        classElt.setAttributeNode(attrClassName);
                    }
                    System.out.println("METHOD");
                    Element methodElt = report.createElement("method");
                    classElt.appendChild(methodElt);

                    Attr attrMethodName = report.createAttribute("name");
                    Attr attrStatus = report.createAttribute("status");
                    attrMethodName.setValue(testCase.getAttribute("name"));
                    methodElt.setAttributeNode(attrMethodName);

                    if (testCase.getChildNodes().getLength() == 0) {
                        aliveMutant++;
                        attrStatus.setValue("alive");
                    } else {
                        deadMutant++;
                        attrStatus.setValue("dead");
                    }
                    methodElt.setAttributeNode(attrStatus);
                }
                float porcentageDeadMut = ((float) deadMutant / ((float) deadMutant + (float) aliveMutant)) * 100;
                float porcentageAliveMut = (((float) aliveMutant / ((float) deadMutant + (float) aliveMutant)) * 100);
            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(report);
            StreamResult result = new StreamResult(new File(project.getBasedir() + "/target/html-report/report.xml"));
            transformer.transform(source, result);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }
}

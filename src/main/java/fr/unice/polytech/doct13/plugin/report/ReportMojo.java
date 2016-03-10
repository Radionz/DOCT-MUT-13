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
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        File baseDir = project.getBasedir();

        File dirTarget = new File(baseDir + "/target/html-report");
        if (!dirTarget.exists()) {
            dirTarget.mkdir();
        }

        try {
            List<NodeList> nlList = new ArrayList<NodeList>();
            dBuilder = dbFactory.newDocumentBuilder();
            Document report = dBuilder.newDocument();
            Element rootElement = report.createElement("mutation");

            report.appendChild(rootElement);

            for (File file : new File(baseDir + "/target/surefire-reports").listFiles()) {
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
                String className = "", name = "", time = "", pathToTest = "", pathToMutant = "", pathToSource = "";
                Element classElt = null;
                for (int i = 0; i < nl.getLength(); i++) {
                    Element testCase = (Element) nl.item(i);
                    if (className.isEmpty()) {
                        className = testCase.getAttribute("classname");

                        classElt = report.createElement("class");
                        rootElement.appendChild(classElt);

                        Attr attrClassName = report.createAttribute("name");
                        attrClassName.setValue(className);
                        classElt.setAttributeNode(attrClassName);

                        pathToSource = getPathToSrcFile(new File(baseDir + "/src/main"), className.replace("Test", "") + ".java");
                        Attr attrClassPathToSource = report.createAttribute("pathToSource");
                        attrClassPathToSource.setValue(pathToSource);
                        classElt.setAttributeNode(attrClassPathToSource);
                        Element codeOriginalElt = report.createElement("codeOriginal");
                        classElt.appendChild(codeOriginalElt);
                        codeOriginalElt.setTextContent(readFile(pathToSource, Charset.defaultCharset()));

                        pathToMutant = getPathToSrcFile(new File(baseDir + "/target/generated-sources/spoon"), className.replace("Test", "") + ".java");
                        Attr attrClassPathToMutant = report.createAttribute("pathToMutant");
                        attrClassPathToMutant.setValue(pathToMutant);
                        classElt.setAttributeNode(attrClassPathToMutant);
                        Element codeMutantElt = report.createElement("codeMutant");
                        classElt.appendChild(codeMutantElt);
                        codeMutantElt.setTextContent(readFile(pathToMutant, Charset.defaultCharset()));

                        pathToTest = getPathToSrcFile(new File(baseDir + "/src/test"), className + ".java");
                        Attr attrClassPathToTest = report.createAttribute("pathToTest");
                        attrClassPathToTest.setValue(pathToTest);
                        classElt.setAttributeNode(attrClassPathToTest);
                        Element codeTestElt = report.createElement("codeTest");
                        classElt.appendChild(codeTestElt);
                        codeTestElt.setTextContent(readFile(pathToTest, Charset.defaultCharset()));
                    }
                    Element methodElt = report.createElement("method");
                    classElt.appendChild(methodElt);

                    name = testCase.getAttribute("name");
                    Attr attrMethodName = report.createAttribute("name");
                    attrMethodName.setValue(name);
                    methodElt.setAttributeNode(attrMethodName);

                    time = testCase.getAttribute("time");
                    Attr attrMethodTime = report.createAttribute("time");
                    attrMethodTime.setValue(time);
                    methodElt.setAttributeNode(attrMethodTime);


                    Attr attrStatus = report.createAttribute("status");
                    methodElt.setAttributeNode(attrStatus);

                    if (testCase.getChildNodes().getLength() == 0) {
                        aliveMutant++;
                        attrStatus.setValue("alive");


                    } else {
                        deadMutant++;
                        attrStatus.setValue("dead");
                    }
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getPathToSrcFile(File baseDir, String name) {
        for (File file : baseDir.listFiles()) {
            if (file.getName().equals(name))
                return file.getPath();
            else if (file.isDirectory()) {
                String result = getPathToSrcFile(file, name);
                if (!result.isEmpty())
                    return result;
            }
        }
        return "";
    }

    static String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }


}

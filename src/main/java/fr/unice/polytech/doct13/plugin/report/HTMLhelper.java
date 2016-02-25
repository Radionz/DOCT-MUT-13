package fr.unice.polytech.doct13.plugin.report;

/**
 * Created by blanc on 25/02/2016.
 */
public class HTMLhelper {

    public static String inHTMLbody(String inside){
        String html = "";
        html += "<html>\n";
        html += "<head>\n";
        html += "<title>Mutation Report</title>\n";
        html += "<style>\n";
        html += "#tableMutants{border:1px solid;margin:0 auto;}" +
                "#tableMutants td{border:1px solid;}" +
                ".aliveMut{background-color:red;}" +
                ".deadMut{background-color:green;}\n";
        html += "</style>\n";
        html += "</head>\n";
        html += "<body>\n";
        html += inside;
        html += "</body>\n";
        html += "</html>\n";
        return html;
    }

}

package fr.unice.polytech.doct13.plugin.report;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by blanc on 25/02/2016.
 */
public class HTMLhelper {

    public static String inHTMLbody(String insideHead, String insideBody) {
        String html = "<!DOCTYPE HTML>";
        html += "<html>\n";
        html += "<head>\n";
        html += "<meta http-equiv='Content-Type' content='text/html; charset=utf-8'>\n";
        html += "<script type='text/javascript' src='https://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js'></script>\n";
        html += "<title>Mutation Report</title>\n";
        html += "<style>\n";
        html += "#tableMutants{border:1px solid;margin:0 auto;}" +
                ".aliveMut{background-color:red;}" +
                ".deadMut{background-color:green;}\n";
        html += "</style>\n";
        html += insideHead;
        html += "</head>\n";
        html += "<body>\n";
        html += insideBody;
        html += "</body>\n";
        html += "</html>\n";
        return html;
    }

    public static String highchartsPie(String title, int aliveMutant, int deadMutant) {

        String html = "<script type='text/javascript'>" +
                "                $(function () {" +
                "            $('#container').highcharts({" +
                "                    chart: {" +
                "                plotBackgroundColor: null," +
                "                        plotBorderWidth: null," +
                "                        plotShadow: false," +
                "                        type: 'pie'" +
                "            }," +
                "            title: {" +
                "                text: '" + title + "'" +
                "            }," +
                "            tooltip: {" +
                "                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'" +
                "            }," +
                "            plotOptions: {" +
                "                pie: {" +
                "                    allowPointSelect: true," +
                "                            cursor: 'pointer'," +
                "                            dataLabels: {" +
                "                        enabled: true," +
                "                                format: '<b>{point.name}</b>: {point.percentage:.1f} %'," +
                "                                style: {" +
                "                                   color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'" +
                "                        }" +
                "                    }" +
                "                }" +
                "            }," +
                "            series: [{" +
                "                name: 'Percentage'," +
                "                        colorByPoint: true," +
                "                        data: [{" +
                "                    name: 'Alive'," +
                "                            y: " + aliveMutant + "}, {" +
                "                    name: 'Dead'," +
                "                            y: " + deadMutant + "}]" +
                "            }]" +
                "            });" +
                "        });" +
                "        </script>" +
                "        </head>" +
                "        <body>" +
                "        <script src='https://code.highcharts.com/highcharts.js'></script>";
        return html;
    }


}

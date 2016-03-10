package fr.unice.polytech.doct13.utils;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Serpe on 09/03/2016.
 */
public class Parser {

    public Map<String, ProcessorParser> mutations;

    public Parser(int i) {
        HashMap<String, ProcessorParser> monproc = new HashMap<String, ProcessorParser>();
        ProcessorParser mon = new ProcessorParser("EqualsProcessor", "packageName", "className", "methodName", 0.4);
        ProcessorParser mon2 = new ProcessorParser("EqualsProcessor", "packageName", "className", "methodName", 0.4);
        monproc.put("mutation1",mon);
        monproc.put("mutation2",mon2);

        Gson gson = new Gson();

        this.mutations = monproc;
    }

    public ProcessorParser getMyProcessor(String mutationName){
        for( String mut : mutations.keySet()){
            if( mutations.get(mut).getName().equals(mutationName)){
                return mutations.get(mut);
            }

        }
        return null;
    }

    public static String readFile(String filename)
    {
        String content = null;
        File file = new File(filename); //for ex foo.txt
        FileReader reader = null;
        try {
            reader = new FileReader(file);
            char[] chars = new char[(int) file.length()];
            reader.read(chars);
            content = new String(chars);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(reader !=null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return content;
    }
}
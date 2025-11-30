package org.example.oqh5nhjson;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;

public class JSONParseOQH5NH {

    public static void main(String[] args) {
        try(FileReader reader = new FileReader("src/orarendoqh5nh.json")) {

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject)jsonParser.parse(reader);

            JSONObject root = (JSONObject)jsonObject.get("TM_orarend");
            JSONArray lessons = (JSONArray)root.get("ora");

            System.out.println("OQH5NH Órarend 2025 ősz:\n");

            for(int i=0; i<lessons.size(); i++) {
                JSONObject lesson = (JSONObject) lessons.get(i);
                JSONObject time = (JSONObject) lesson.get("idopont");
                System.out.println("Tárgy: " + lesson.get("targy"));
                System.out.println("Időpont:" + time.get("nap") + time.get("tol") + "-" + time.get("ig"));
                System.out.println("Helyszín:" + lesson.get("helyszin"));
                System.out.println("Oktató:" + lesson.get("oktató"));
                System.out.println("Szak:" + lesson.get("szak") + "\n");
            }


        } catch (Exception e) {
            System.out.println("Hiba");
        }
    }

}

package org.example.oqh5nhjson;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;

public class JSONWriteOQH5NH {

    public static void main(String[] args) {

        // Gyökérelem (TM_orarend)
        JSONObject root = new JSONObject();
        JSONObject orarend = new JSONObject();
        JSONArray oraLista = new JSONArray();

        // ------------ 1. óra objektum ------------
        JSONObject ora1 = new JSONObject();
        ora1.put("targy", "Programozás I.");
        ora1.put("helyszin", "A1/204");
        ora1.put("oktató", "Dr. Nagy Péter");
        ora1.put("szak", "Mérnökinformatikus");

        JSONObject idopont1 = new JSONObject();
        idopont1.put("nap", "Hétfő");
        idopont1.put("tol", "08:00");
        idopont1.put("ig",  "09:30");
        ora1.put("idopont", idopont1);

        oraLista.add(ora1);

        // ------------ 2. óra objektum ------------
        JSONObject ora2 = new JSONObject();
        ora2.put("targy", "Analízis I.");
        ora2.put("helyszin", "A2/109");
        ora2.put("oktató", "Kiss Júlia");
        ora2.put("szak", "Mérnökinformatikus");

        JSONObject idopont2 = new JSONObject();
        idopont2.put("nap", "Kedd");
        idopont2.put("tol", "10:00");
        idopont2.put("ig",  "11:30");
        ora2.put("idopont", idopont2);

        oraLista.add(ora2);

        // Gyökér JSON struktúra feltöltése
        orarend.put("ora", oraLista);
        root.put("TM_orarend", orarend);

        // ------------ FÁJLBA ÍRÁS ------------
        try (FileWriter file = new FileWriter("src/orarendOQH5NHwrite.json")) {
            file.write(root.toJSONString());
            file.flush();
            System.out.println("Sikeresen létrehozva: orarendOQH5NHwrite.json");
        } catch (Exception e) {
            System.out.println("Hiba: " + e.getMessage());
        }

        // ------------ KONZOLRA KIÍRÁS BLOKKOKBAN ------------
        System.out.println("OQH5NH Órarend (generált adatok):\n");

        for (Object o : oraLista) {
            JSONObject ora = (JSONObject) o;
            JSONObject idopont = (JSONObject) ora.get("idopont");

            System.out.println("=====================================");
            System.out.println("Tárgy:     " + ora.get("targy"));
            System.out.println("Időpont:   " + idopont.get("nap") + " " +
                    idopont.get("tol") + "-" + idopont.get("ig"));
            System.out.println("Helyszín:  " + ora.get("helyszin"));
            System.out.println("Oktató:    " + ora.get("oktató"));
            System.out.println("Szak:      " + ora.get("szak"));
            System.out.println("=====================================\n");
        }
    }
}

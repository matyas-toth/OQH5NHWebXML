package hu.domparse.oqh5nh;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class OQH5NHDOMQuery {

    public static void main(String[] args) {

        try {
            // XML dokumentum beolvasása
            File xmlFile = new File("src/OQH5NH_XML.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // DOM dokumentum létrehozása
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            System.out.println("===== Állatorvosi rendelő XML lekérdezések =====\n");

            // ======================================================
            // 1. Komplex lekérdezés:
            // Minden gazda neve és városa
            // ======================================================
            System.out.println("1. Gazdák neve és városa:");
            NodeList gazdak = doc.getElementsByTagName("gazda");

            for (int i = 0; i < gazdak.getLength(); i++) {
                Element gazda = (Element) gazdak.item(i);

                String nev = gazda.getElementsByTagName("nev").item(0).getTextContent();
                String varos = ((Element) gazda.getElementsByTagName("cim").item(0))
                        .getElementsByTagName("varos").item(0).getTextContent();

                System.out.println(" - " + nev + " (" + varos + ")");
            }
            System.out.println();


            // ======================================================
            // 2. Komplex lekérdezés:
            // Állatok gazdával együtt (kapcsolat gazdaID → allat g_a attributum)
            // ======================================================
            System.out.println("2. Állatok a gazdáikkal együtt:");
            NodeList allatok = doc.getElementsByTagName("allat");

            for (int i = 0; i < allatok.getLength(); i++) {
                Element allat = (Element) allatok.item(i);

                String allatNev = allat.getElementsByTagName("nev").item(0).getTextContent();
                String faj = allat.getElementsByTagName("faj").item(0).getTextContent();

                // Gazda felderítése ID alapján
                String gazdaID = allat.getAttribute("g_a");
                String gazdaNev = "";

                for (int j = 0; j < gazdak.getLength(); j++) {
                    Element gazda = (Element) gazdak.item(j);
                    if (gazda.getAttribute("gazdaID").equals(gazdaID)) {
                        gazdaNev = gazda.getElementsByTagName("nev").item(0).getTextContent();
                    }
                }

                System.out.println(" - " + allatNev + " (" + faj + ") → Gazdája: " + gazdaNev);
            }
            System.out.println();


            // ======================================================
            // 3. Komplex lekérdezés:
            // Minden kezelés listázása:
            // Kezelés dátuma, állat neve, orvos neve, összeg
            // ======================================================
            System.out.println("3. Kezelések (állat + orvos + ár):");

            NodeList kezelesek = doc.getElementsByTagName("kezeles");
            NodeList orvosok = doc.getElementsByTagName("orvos");

            for (int i = 0; i < kezelesek.getLength(); i++) {
                Element kezeles = (Element) kezelesek.item(i);

                String datum = kezeles.getElementsByTagName("datum").item(0).getTextContent();
                int ar = Integer.parseInt(kezeles.getElementsByTagName("ar").item(0).getTextContent());

                // Állat keresése az a_k attributum alapján
                String allatID = kezeles.getAttribute("a_k");
                String allatNev = "";

                for (int j = 0; j < allatok.getLength(); j++) {
                    Element allat = (Element) allatok.item(j);
                    if (allat.getAttribute("allatID").equals(allatID)) {
                        allatNev = allat.getElementsByTagName("nev").item(0).getTextContent();
                    }
                }

                // Orvos keresése o_k attribútum alapján
                String orvosID = kezeles.getAttribute("o_k");
                String orvosNev = "";

                for (int k = 0; k < orvosok.getLength(); k++) {
                    Element orvos = (Element) orvosok.item(k);
                    if (orvos.getAttribute("orvosID").equals(orvosID)) {
                        orvosNev = orvos.getElementsByTagName("nev").item(0).getTextContent();
                    }
                }

                System.out.println(" - " + datum + ": " + allatNev + " kezelése (" + orvosNev + ") → " + ar + " Ft");
            }
            System.out.println();


            // ======================================================
            // 4. Komplex lekérdezés:
            // Állatonként kiírni, milyen gyógyszereket kapott (N:M kapcsolat)
            // ======================================================
            System.out.println("4. Állatok → Gyógyszerek:");

            NodeList kezGyList = doc.getElementsByTagName("kezelesGyogyszer");
            NodeList gyogyszerek = doc.getElementsByTagName("gyogyszer");

            for (int i = 0; i < allatok.getLength(); i++) {
                Element allat = (Element) allatok.item(i);
                String allatID = allat.getAttribute("allatID");
                String allatNev = allat.getElementsByTagName("nev").item(0).getTextContent();

                System.out.println(" • " + allatNev + " gyógyszerei:");

                for (int j = 0; j < kezelesek.getLength(); j++) {
                    Element kezeles = (Element) kezelesek.item(j);

                    if (!kezeles.getAttribute("a_k").equals(allatID)) continue;

                    String kezelesID = kezeles.getAttribute("kezelesID");

                    for (int k = 0; k < kezGyList.getLength(); k++) {
                        Element kapcsolat = (Element) kezGyList.item(k);

                        if (kapcsolat.getAttribute("k_kg").equals(kezelesID)) {

                            String gyID = kapcsolat.getAttribute("gy_kg");
                            String gyNev = "";

                            for (int t = 0; t < gyogyszerek.getLength(); t++) {
                                Element gy = (Element) gyogyszerek.item(t);
                                if (gy.getAttribute("gyogyszerID").equals(gyID)) {
                                    gyNev = gy.getElementsByTagName("nev").item(0).getTextContent();
                                }
                            }

                            System.out.println("    - " + gyNev);
                        }
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("Hiba történt: " + e.getMessage());
        }
    }

}

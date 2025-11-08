package hu.domparse.oqh5nh;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class OQH5NHDOMModify {

    public static void main(String[] args) {
        try {
            // XML dokumentum beolvasása
            File xmlFile = new File("src/OQH5NH_XML.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            System.out.println("===== XML módosítások konzolra =====\n");

            // =============================================
            // 1. Módosítás: Gazda telefonszám módosítása
            // =============================================
            NodeList gazdak = doc.getElementsByTagName("gazda");
            Element elsoGazda = (Element) gazdak.item(0);
            String regiTelefon = elsoGazda.getElementsByTagName("telefonszam").item(0).getTextContent();
            elsoGazda.getElementsByTagName("telefonszam").item(0)
                    .setTextContent("+36309998877"); // új szám
            System.out.println("1. Gazda telefonszám módosítva: " + regiTelefon + " -> " +
                    elsoGazda.getElementsByTagName("telefonszam").item(0).getTextContent());


            // =============================================
            // 2. Módosítás: Állat faj módosítása
            // =============================================
            NodeList allatok = doc.getElementsByTagName("allat");
            Element masodikAllat = (Element) allatok.item(1); // Cirmos
            String regiFaj = masodikAllat.getElementsByTagName("faj").item(0).getTextContent();
            masodikAllat.getElementsByTagName("faj").item(0).setTextContent("Sziámi macska");
            System.out.println("2. Állat faj módosítva: " + regiFaj + " -> " +
                    masodikAllat.getElementsByTagName("faj").item(0).getTextContent());


            // =============================================
            // 3. Módosítás: Orvos beosztás módosítása
            // =============================================
            NodeList orvosok = doc.getElementsByTagName("orvos");
            Element harmadikOrvos = (Element) orvosok.item(2); // Dr. Kiss Márta
            String regiBeosztas = harmadikOrvos.getElementsByTagName("beosztas").item(0).getTextContent();
            harmadikOrvos.getElementsByTagName("beosztas").item(0).setTextContent("Főorvos");
            System.out.println("3. Orvos beosztás módosítva: " + regiBeosztas + " -> " +
                    harmadikOrvos.getElementsByTagName("beosztas").item(0).getTextContent());


            // =============================================
            // 4. Módosítás: Gyógyszer adagolás módosítása
            // =============================================
            NodeList gyogyszerek = doc.getElementsByTagName("gyogyszer");
            Element elsoGyogyszer = (Element) gyogyszerek.item(0); // Amoxicillin
            String regiAdagolas = elsoGyogyszer.getElementsByTagName("adagolas").item(0).getTextContent();
            elsoGyogyszer.getElementsByTagName("adagolas").item(0).setTextContent("20 mg/kg naponta kétszer");
            System.out.println("4. Gyógyszer adagolás módosítva: " + regiAdagolas + " -> " +
                    elsoGyogyszer.getElementsByTagName("adagolas").item(0).getTextContent());


            // =============================================
            // 5. Módosítás: Egészségügyi kiskönyv megjegyzés módosítása
            // =============================================
            NodeList konyvek = doc.getElementsByTagName("egeszsegugyiKonyv");
            Element elsoKonyv = (Element) konyvek.item(0); // Bodri kiskönyve
            String regiMegjegyzes = elsoKonyv.getElementsByTagName("megjegyzes").item(0).getTextContent();
            elsoKonyv.getElementsByTagName("megjegyzes").item(0)
                    .setTextContent("Oltási könyv komplett, új ellenőrzés szükséges");
            System.out.println("5. Kiskönyv megjegyzés módosítva: " + regiMegjegyzes + " -> " +
                    elsoKonyv.getElementsByTagName("megjegyzes").item(0).getTextContent());

        } catch (Exception e) {
            System.out.println("Hiba történt: " + e.getMessage());
        }
    }

}

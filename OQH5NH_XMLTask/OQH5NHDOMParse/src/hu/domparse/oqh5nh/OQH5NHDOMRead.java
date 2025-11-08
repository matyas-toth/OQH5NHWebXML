package hu.domparse.oqh5nh;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class OQH5NHDOMRead {

    public static void main(String[] args) {
        try {
            File xmlFile = new File("src/OQH5NH_XML.xml");

            // DOM dokumentum létrehozása fileból
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringComments(true);
            factory.setIgnoringElementContentWhitespace(true);

            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);

            // Szöveges gyerekelemek összevonása, normalizálás
            doc.getDocumentElement().normalize();

            // Node-ok elmentése egy változóba
            NodeList nodes = doc.getDocumentElement().getChildNodes();

            // Iterálás a node-okon
            for (int i = 0; i < nodes.getLength(); i++) {
                Node n = nodes.item(i);

                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) n;
                    String tag = elem.getTagName();

                    // Gazdák kiírása
                    if (tag.equals("gazda")) {
                        System.out.println("Gazda (ID: " + elem.getAttribute("gazdaID") + ")");
                        System.out.println("Név: " + elem.getElementsByTagName("nev").item(0).getTextContent());

                        Element cim = (Element) elem.getElementsByTagName("cim").item(0);
                        System.out.println("Cím: " +
                                cim.getElementsByTagName("varos").item(0).getTextContent() + ", " +
                                cim.getElementsByTagName("utca").item(0).getTextContent() + ", " +
                                cim.getElementsByTagName("hazszam").item(0).getTextContent()
                        );

                        System.out.println("Telefonszám: " + elem.getElementsByTagName("telefonszam").item(0).getTextContent());
                        System.out.println("Email: " + elem.getElementsByTagName("email").item(0).getTextContent());
                        System.out.println("--------------------------------------------------");
                    }

                    // Állatok kiírása
                    else if (tag.equals("allat")) {
                        System.out.println("Állat (ID: " + elem.getAttribute("allatID") +
                                ", GazdaID: " + elem.getAttribute("g_a") + ")");
                        System.out.println("Név: " + elem.getElementsByTagName("nev").item(0).getTextContent());
                        System.out.println("Faj: " + elem.getElementsByTagName("faj").item(0).getTextContent());
                        System.out.println("Születési dátum: " + elem.getElementsByTagName("szuletesiDatum").item(0).getTextContent());

                        NodeList oltasok = elem.getElementsByTagName("oltas");
                        System.out.print("Oltások: ");
                        for (int j = 0; j < oltasok.getLength(); j++) {
                            System.out.print(oltasok.item(j).getTextContent());
                            if (j < oltasok.getLength() - 1) System.out.print(", ");
                        }
                        System.out.println();
                        System.out.println("--------------------------------------------------");
                    }

                    // Orvosok kiírása
                    else if (tag.equals("orvos")) {
                        System.out.println("Orvos (ID: " + elem.getAttribute("orvosID") + ")");
                        System.out.println("Név: " + elem.getElementsByTagName("nev").item(0).getTextContent());
                        System.out.println("Szakterület: " + elem.getElementsByTagName("szakterulet").item(0).getTextContent());
                        System.out.println("Beosztás: " + elem.getElementsByTagName("beosztas").item(0).getTextContent());
                        System.out.println("--------------------------------------------------");
                    }

                    // Gyógyszerek kiírása
                    else if (tag.equals("gyogyszer")) {
                        System.out.println("Gyógyszer (ID: " + elem.getAttribute("gyogyszerID") + ")");
                        System.out.println("Név: " + elem.getElementsByTagName("nev").item(0).getTextContent());
                        System.out.println("Hatóanyag: " + elem.getElementsByTagName("hatoanyag").item(0).getTextContent());
                        System.out.println("Adagolás: " + elem.getElementsByTagName("adagolas").item(0).getTextContent());
                        System.out.println("--------------------------------------------------");
                    }

                    // Kezelések kiírása
                    else if (tag.equals("kezeles")) {
                        System.out.println("Kezelés (ID: " + elem.getAttribute("kezelesID") +
                                ", ÁllatID: " + elem.getAttribute("a_k") +
                                ", OrvosID: " + elem.getAttribute("o_k") + ")");
                        System.out.println("Dátum: " + elem.getElementsByTagName("datum").item(0).getTextContent());
                        System.out.println("Diagnózis: " + elem.getElementsByTagName("diagnozis").item(0).getTextContent());
                        System.out.println("Ár: " + elem.getElementsByTagName("ar").item(0).getTextContent() + " Ft");
                        System.out.println("--------------------------------------------------");
                    }

                    // Kezelés-Gyógyszer kapcsolóentitások kiírása
                    else if (tag.equals("kezelesGyogyszer")) {
                        System.out.println("Kezeléshez gyógyszer (KezelésID: " + elem.getAttribute("k_kg") +
                                ", GyógyszerID: " + elem.getAttribute("gy_kg") + ")");
                        System.out.println("Adag: " + elem.getElementsByTagName("adag").item(0).getTextContent());
                        System.out.println("Időtartam: " + elem.getElementsByTagName("idotartam").item(0).getTextContent());
                        System.out.println("--------------------------------------------------");
                    }

                    // Egészségügyi Könyvek kiírása
                    else if (tag.equals("egeszsegugyiKonyv")) {
                        System.out.println("Egészségügyi könyv (ID: " + elem.getAttribute("konyvID") +
                                ", ÁllatID: " + elem.getAttribute("a_ek") + ")");
                        System.out.println("Létrehozás dátuma: " + elem.getElementsByTagName("letrehozasDatum").item(0).getTextContent());
                        System.out.println("Megjegyzés: " + elem.getElementsByTagName("megjegyzes").item(0).getTextContent());
                        System.out.println("--------------------------------------------------");
                    }
                }
            }

            // A node lista és tulajdonságaik, gyerekeik kiírása egy új XML fileba
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            transformer.transform(new DOMSource(doc), new StreamResult(new File("src/oqh5nh_xml_domread.xml")));

            System.out.println("\nXML sikeresen kiírva: oqh5nh_xml_domread.xml");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

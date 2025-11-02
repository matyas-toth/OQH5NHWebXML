package domoqh5nh1029;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

class DomRead {

    public static void main(String[] args) {
        try {
            File xmlFile = new File("src/oqh5nhhallgato.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            System.out.println("Gyökér elem: " + doc.getDocumentElement().getNodeName());
            System.out.println("");

            NodeList nList = doc.getElementsByTagName("hallgato");

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) nNode;

                    String id = elem.getAttribute("id");
                    String keresztnev = elem.getElementsByTagName("keresztnev").item(0).getTextContent();
                    String vezeteknev = elem.getElementsByTagName("vezeteknev").item(0).getTextContent();
                    String foglalkozas = elem.getElementsByTagName("foglalkozas").item(0).getTextContent();

                    System.out.println("Hallgató ID: " + id);
                    System.out.println("Keresztnév: " + keresztnev);
                    System.out.println("Vezetéknév: " + vezeteknev);
                    System.out.println("Foglalkozás: " + foglalkozas);
                    System.out.println("");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
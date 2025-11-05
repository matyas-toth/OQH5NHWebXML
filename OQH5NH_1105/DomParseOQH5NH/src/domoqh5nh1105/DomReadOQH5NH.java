package domoqh5nh1105;

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
            File xmlFile = new File("src/orarendoqh5nh.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            System.out.println("Gyökér elem: " + doc.getDocumentElement().getNodeName());
            System.out.println("");

            NodeList nList = doc.getElementsByTagName("ora");

            for (int i = 0; i < nList.getLength(); i++) {
                Node nNode = nList.item(i);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) nNode;

                    String id = elem.getAttribute("id");
                    String tipus = elem.getAttribute("tipus");
                    String targy = elem.getElementsByTagName("targy").item(0).getTextContent();
                    String nap = elem.getElementsByTagName("nap").item(0).getTextContent();
                    String tol = elem.getElementsByTagName("tol").item(0).getTextContent();
                    String ig = elem.getElementsByTagName("ig").item(0).getTextContent();
                    String helyszin = elem.getElementsByTagName("helyszin").item(0).getTextContent();
                    String oktato = elem.getElementsByTagName("oktato").item(0).getTextContent();
                    String szak = elem.getElementsByTagName("szak").item(0).getTextContent();

                    System.out.println("Óra ID: " + id + " (" + tipus + ")");
                    System.out.println("Tárgy: " + targy);
                    System.out.println("Időpont: " + nap + " " + tol + " - " + ig);
                    System.out.println("Helyszín: " + helyszin);
                    System.out.println("Oktató: " + oktato);
                    System.out.println("Szak: " + szak);
                    System.out.println("");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
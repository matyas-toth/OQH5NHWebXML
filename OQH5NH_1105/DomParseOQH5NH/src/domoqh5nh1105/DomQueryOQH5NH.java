package domoqh5nh1105;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

class DomQuery {

    public static void main(String[] args) {
        try {
            File xmlFile = new File("src/oqh5nhhallgato.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            System.out.println("Gyökér elem: " + doc.getDocumentElement().getNodeName());
            System.out.println("------------------------------");

            // Összes <hallgato> elem lekérdezése
            NodeList nodeList = doc.getElementsByTagName("hallgato");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                System.out.println("\nAktuális elem:");
                System.out.println(node.getNodeName());

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elem = (Element) node;

                    // lekérdezzük a vezetéknév elemet
                    String vezeteknev = elem.getElementsByTagName("vezeteknev")
                            .item(0)
                            .getTextContent();

                    System.out.println("vezeteknev: " + vezeteknev);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

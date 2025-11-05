package domoqh5nh1105;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

class DomModify {

    public static void main(String[] args) {
        try {
            File xmlFile = new File("src/oqh5nhhallgato.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            NodeList list = doc.getElementsByTagName("hallgato");

            for (int i = 0; i < list.getLength(); i++) {
                Element elem = (Element) list.item(i);

                // id="01" keresése
                if (elem.getAttribute("id").equals("01")) {

                    elem.getElementsByTagName("keresztnev").item(0).setTextContent("Ádám");
                    elem.getElementsByTagName("vezeteknev").item(0).setTextContent("Szabó");
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(doc);
            StreamResult consoleOutput = new StreamResult(System.out);
            transformer.transform(source, consoleOutput);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

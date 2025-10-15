package domoqh5nh1015;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class DomReadOQH5NH {
    public static void main(String[] args) {
        try {
            // XML fájl betöltése
            File xmlFile = new File("orarendOQH5NH.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            doc.getDocumentElement().normalize();

            System.out.println("Gyökérelem: " + doc.getDocumentElement().getNodeName());
            System.out.println("------------------------------------");

            // Fa bejárása és blokk formában kiírása
            printNode(doc.getDocumentElement(), 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Rekurzív metódus a DOM fa bejárására
    private static void printNode(Node node, int indent) {
        StringBuilder prefix = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            prefix.append("    "); // 4 szóköz
        }

        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element elem = (Element) node;
            System.out.println(prefix + "Elem: " + elem.getNodeName());

            NamedNodeMap attrMap = elem.getAttributes();
            for (int i = 0; i < attrMap.getLength(); i++) {
                Node attr = attrMap.item(i);
                System.out.println(prefix + "  @" + attr.getNodeName() + " = " + attr.getNodeValue());
            }

            NodeList children = node.getChildNodes();
            for (int i = 0; i < children.getLength(); i++) {
                Node child = children.item(i);
                if (child.getNodeType() == Node.TEXT_NODE) {
                    String text = child.getTextContent().trim();
                    if (!text.isEmpty()) {
                        System.out.println(prefix + "  Szöveg: " + text);
                    }
                } else {
                    printNode(child, indent + 1);
                }
            }
        }
    }
}

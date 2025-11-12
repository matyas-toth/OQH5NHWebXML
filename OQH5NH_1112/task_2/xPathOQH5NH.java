package xpathoqh5nh;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

class xPathOQH5NH {

    public static void main(String[] args) {
        try {
            String neptunkod = "OQH5NH";
            
            File xmlFile = new File("OQH5NH_1112/studentOQH5NH.xml");
            System.out.println("XML file betöltve");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);

            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xpath = xPathFactory.newXPath();

            // XPath lekérdezés: válassza ki az id="1" student elemet
            // String xpathQuery = "/class/student[@id='1']";
            Node studentNode = (Node) xpath.evaluate("/class/student[@id='1']", doc, XPathConstants.NODE);

            if (studentNode != null && studentNode.getNodeType() == Node.ELEMENT_NODE) {
                Element studentElement = (Element) studentNode;
                
                // Keresztnév elem megtalálása és módosítása
                NodeList keresztnevList = studentElement.getElementsByTagName("keresztnev");
                if (keresztnevList.getLength() > 0) {
                    Element keresztnevElement = (Element) keresztnevList.item(0);
                    // Módosítás: új keresztnév érték
                    keresztnevElement.setTextContent("Anikó");
                    System.out.println("Keresztnév módosítva: Anna -> Anikó\n");
                }

                // Csak az id="1" student elem kiírása
                System.out.println("=== Módosított student elem (id=1) ===");
                printStudentElement(studentElement);
                
            } else {
                System.out.println("Nem található student elem id='1' attribútummal!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printStudentElement(Element studentElement) {
        System.out.println("Student id=\"" + studentElement.getAttribute("id") + "\"");
        
        NodeList children = studentElement.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.ELEMENT_NODE) {
                Element childElement = (Element) child;
                String tagName = childElement.getTagName();
                String textContent = childElement.getTextContent();
                System.out.println("  " + tagName + ": " + textContent);
            }
        }
    }
}


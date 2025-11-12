package xpathoqh5nh;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
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

            System.out.println("\n=== XPath lekérdezések ===\n");

            // 1. Válassza ki az összes student elemet, amely a class gyermeke!
            System.out.println("1. Az összes student elem, amely a class gyermeke:");
            NodeList result1 = (NodeList) xpath.evaluate("/class/student", doc, XPathConstants.NODESET);
            printNodeList(result1);
            // String xpath1 = "/class/student";

            // 2. Válassza ki azt a student elemet, amely rendelkezik "id" attribútummal és értéke "02"!
            System.out.println("\n2. Student elem id='2' attribútummal:");
            Node result2 = (Node) xpath.evaluate("/class/student[@id='2']", doc, XPathConstants.NODE);
            if (result2 != null) {
                printNode(result2);
            }
            // String xpath2 = "/class/student[@id='2']";

            // 3. Kiválasztja az összes student elemet, függetlenül attól, hogy hol vannak a dokumentumban!
            System.out.println("\n3. Az összes student elem a dokumentumban (bárhol):");
            NodeList result3 = (NodeList) xpath.evaluate("//student", doc, XPathConstants.NODESET);
            printNodeList(result3);
            // String xpath3 = "//student";

            // 4. Válassza ki a második student elemet, amely a class root element gyermeke!
            System.out.println("\n4. A második student elem:");
            Node result4 = (Node) xpath.evaluate("/class/student[2]", doc, XPathConstants.NODE);
            if (result4 != null) {
                printNode(result4);
            }
            // String xpath4 = "/class/student[2]";

            // 5. Válassza ki az utolsó student elemet, amely a class root element gyermeke!
            System.out.println("\n5. Az utolsó student elem:");
            Node result5 = (Node) xpath.evaluate("/class/student[last()]", doc, XPathConstants.NODE);
            if (result5 != null) {
                printNode(result5);
            }
            // String xpath5 = "/class/student[last()]";

            // 6. Válassza ki az utolsó előtti student elemet, amely a class root element gyermeke!
            System.out.println("\n6. Az utolsó előtti student elem:");
            Node result6 = (Node) xpath.evaluate("/class/student[last()-1]", doc, XPathConstants.NODE);
            if (result6 != null) {
                printNode(result6);
            }
            // String xpath6 = "/class/student[last()-1]";

            // 7. Válassza ki az első két student elemet, amelyek a root element gyermekei!
            System.out.println("\n7. Az első két student elem:");
            NodeList result7 = (NodeList) xpath.evaluate("/class/student[position()<=2]", doc, XPathConstants.NODESET);
            printNodeList(result7);
            // String xpath7 = "/class/student[position()<=2]";

            // 8. Válassza ki class root element összes gyermek elemét!
            System.out.println("\n8. A class root element összes gyermek eleme:");
            NodeList result8 = (NodeList) xpath.evaluate("/class/*", doc, XPathConstants.NODESET);
            printNodeList(result8);
            // String xpath8 = "/class/*";

            // 9. Válassza ki az összes student elemet, amely rendelkezik legalább egy bármilyen attribútummal!
            System.out.println("\n9. Az összes student elem legalább egy attribútummal:");
            NodeList result9 = (NodeList) xpath.evaluate("//student[@*]", doc, XPathConstants.NODESET);
            printNodeList(result9);
            // String xpath9 = "//student[@*]";

            // 10. Válassza ki a dokumentum összes elemét!
            System.out.println("\n10. A dokumentum összes eleme:");
            NodeList result10 = (NodeList) xpath.evaluate("//*", doc, XPathConstants.NODESET);
            printNodeList(result10);
            // String xpath10 = "//*";

            // 11. Válassza ki a class root element összes student elemét, amelynél a kor > 20!
            System.out.println("\n11. Student elemek, ahol kor > 20:");
            NodeList result11 = (NodeList) xpath.evaluate("/class/student[kor > 20]", doc, XPathConstants.NODESET);
            printNodeList(result11);
            // String xpath11 = "/class/student[kor > 20]";

            // 12. Válassza ki az összes student elem összes keresztnev or vezeteknev csomópontot!
            System.out.println("\n12. Az összes keresztnev vagy vezeteknev csomópont:");
            NodeList result12 = (NodeList) xpath.evaluate("//student/keresztnev | //student/vezeteknev", doc, XPathConstants.NODESET);
            printNodeList(result12);
            // String xpath12 = "//student/keresztnev | //student/vezeteknev";

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printNodeList(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            printNode(node);
        }
    }

    private static void printNode(Node node) {
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            System.out.println("  - " + node.getNodeName() + 
                (node.hasAttributes() ? " " + getAttributes(node) : "") + 
                ": " + getElementText(node));
        }
    }

    private static String getAttributes(Node node) {
        StringBuilder attrs = new StringBuilder();
        if (node.hasAttributes()) {
            for (int i = 0; i < node.getAttributes().getLength(); i++) {
                Node attr = node.getAttributes().item(i);
                attrs.append(attr.getNodeName()).append("=\"").append(attr.getNodeValue()).append("\" ");
            }
        }
        return attrs.toString().trim();
    }

    private static String getElementText(Node node) {
        StringBuilder text = new StringBuilder();
        NodeList children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            Node child = children.item(i);
            if (child.getNodeType() == Node.TEXT_NODE) {
                String content = child.getNodeValue().trim();
                if (!content.isEmpty()) {
                    text.append(content).append(" ");
                }
            } else if (child.getNodeType() == Node.ELEMENT_NODE) {
                text.append(child.getNodeName()).append("=").append(getElementText(child)).append(" ");
            }
        }
        return text.toString().trim();
    }
}

package domoqh5nh1105;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

class DomModifyOrarend {

    public static void main(String[] args) {
        try {
            File xmlFile = new File("src/orarendoqh5nh.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            NodeList oraList = doc.getElementsByTagName("ora");

            // =========================
            // 1.) hozzáad egy új <oraado> elemet az 1. órához
            // =========================

            Element firstOra = (Element) oraList.item(0);

            Element oraado = doc.createElement("oraado");
            oraado.appendChild(doc.createTextNode("Dr. Szabó Tamás"));
            firstOra.appendChild(oraado);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(doc);

            System.out.println("===== 1. lépés: Óraadó elem hozzáadva =====");
            transformer.transform(source, new StreamResult(System.out));

            // fileba mentés
            StreamResult fileOutput = new StreamResult(new File("src/orarendModify1oqh5nh.xml"));
            transformer.transform(source, fileOutput);


            // =========================
            // 2.) módosítja az összes óra típusát gyakorlat -> elmelet
            // =========================

            for (int i = 0; i < oraList.getLength(); i++) {
                Element ora = (Element) oraList.item(i);
                if (ora.getAttribute("tipus").equals("gyakorlat")) {
                    ora.setAttribute("tipus", "elmelet");
                }
            }

            // struktúra kiírása formázottan konzolra
            System.out.println("\n===== 2. lépés: Minden óra típusa módosítva =====");
            transformer.transform(new DOMSource(doc), new StreamResult(System.out));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

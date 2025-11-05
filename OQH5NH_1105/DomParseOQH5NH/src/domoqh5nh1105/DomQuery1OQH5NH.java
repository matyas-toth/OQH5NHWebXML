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
import java.util.ArrayList;

class DomQueryOrarend {

    public static void main(String[] args) {
        try {
            File xmlFile = new File("src/orarendoqh5nh.xml");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            System.out.println("Gyökér elem: " + doc.getDocumentElement().getNodeName());
            System.out.println("-----------------------------------\n");

            // 1.) kurzusok neve listában (targy elemek)
            NodeList oraLista = doc.getElementsByTagName("ora");
            ArrayList<String> kurzusok = new ArrayList<>();

            for (int i = 0; i < oraLista.getLength(); i++) {
                Element oraElem = (Element) oraLista.item(i);
                kurzusok.add(oraElem.getElementsByTagName("targy").item(0).getTextContent());
            }

            System.out.println("1.) Kurzusnév lista:");
            System.out.println("Kurzusnév: " + kurzusok);
            System.out.println("\n-----------------------------------\n");


            // 2.) első példány kiírása (strukturált) + fájlba mentés
            Element elsoOra = (Element) oraLista.item(0);

            System.out.println("2.) Első kurzus strukturált megjelenítése:");
            System.out.println("ID: " + elsoOra.getAttribute("id"));
            System.out.println("Típus: " + elsoOra.getAttribute("tipus"));
            System.out.println("Tárgy: " + elsoOra.getElementsByTagName("targy").item(0).getTextContent());
            System.out.println("Nap: " + elsoOra.getElementsByTagName("nap").item(0).getTextContent());
            System.out.println("Kezdés: " + elsoOra.getElementsByTagName("tol").item(0).getTextContent());
            System.out.println("Vége: " + elsoOra.getElementsByTagName("ig").item(0).getTextContent());
            System.out.println("Helyszín: " + elsoOra.getElementsByTagName("helyszin").item(0).getTextContent());
            System.out.println("Oktató: " + elsoOra.getElementsByTagName("oktato").item(0).getTextContent());
            System.out.println("Szak: " + elsoOra.getElementsByTagName("szak").item(0).getTextContent());

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            // fájlba mentés
            DOMSource source = new DOMSource(elsoOra);
            StreamResult result = new StreamResult(new File("src/orarendoqh5nhfirst.xml"));
            transformer.transform(source, result);

            System.out.println("\nElső kurzus külön XML fájlba mentve: orarendoqh5nhfirst.xml");
            System.out.println("\n-----------------------------------\n");


            // 3.) oktatók nevei listában
            ArrayList<String> oktatok = new ArrayList<>();

            for (int i = 0; i < oraLista.getLength(); i++) {
                Element oraElem = (Element) oraLista.item(i);
                oktatok.add(oraElem.getElementsByTagName("oktato").item(0).getTextContent());
            }

            System.out.println("3.) Oktatók listája:");
            System.out.println("Oktatók: " + oktatok);
            System.out.println("\n-----------------------------------\n");


            // 4.) Összetett lekérdezés — minden hétfői óra listázása
            System.out.println("4.) Összetett lekérdezés: Minden hétfői óra\n");

            for (int i = 0; i < oraLista.getLength(); i++) {
                Element oraElem = (Element) oraLista.item(i);

                String nap = oraElem.getElementsByTagName("nap").item(0).getTextContent();

                if (nap.equals("Hétfő")) {
                    System.out.println(
                            oraElem.getElementsByTagName("targy").item(0).getTextContent()
                                    + " | " + oraElem.getElementsByTagName("tol").item(0).getTextContent()
                                    + "-" + oraElem.getElementsByTagName("ig").item(0).getTextContent()
                                    + " | " + oraElem.getElementsByTagName("helyszin").item(0).getTextContent()
                    );
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
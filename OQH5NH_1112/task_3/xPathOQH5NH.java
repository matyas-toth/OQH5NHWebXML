package xpathoqh5nh;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
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
            
            File xmlFile = new File("OQH5NH_1112/orarendoqh5nh.xml");
            System.out.println("XML file betöltve: " + xmlFile.getAbsolutePath());

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);

            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath xpath = xPathFactory.newXPath();

            StringBuilder output = new StringBuilder();
            output.append("=== XPath Lekérdezések és Módosítások ===\n\n");
            output.append("Neptun kód: ").append(neptunkod).append("\n\n");

            // ========== 4 XPath LEKÉRDEZÉS ==========
            output.append("========== 1. XPath LEKÉRDEZÉSEK ==========\n\n");

            // Lekérdezés 1: Hétfői órák
            // String xpath1 = "//ora[idopont/nap='Hétfő']";
            output.append("1. Lekérdezés: Hétfői órák\n");
            output.append("   XPath: //ora[idopont/nap='Hétfő']\n");
            NodeList result1 = (NodeList) xpath.evaluate("//ora[idopont/nap='Hétfő']", doc, XPathConstants.NODESET);
            output.append("   Találatok száma: ").append(result1.getLength()).append("\n");
            for (int i = 0; i < result1.getLength(); i++) {
                Element ora = (Element) result1.item(i);
                output.append("   - ").append(getOraInfo(ora)).append("\n");
            }
            output.append("\n");

            // Lekérdezés 2: Agárdi Anita órái
            // String xpath2 = "//ora[oktato='Agárdi Anita']";
            output.append("2. Lekérdezés: Agárdi Anita órái\n");
            output.append("   XPath: //ora[oktato='Agárdi Anita']\n");
            NodeList result2 = (NodeList) xpath.evaluate("//ora[oktato='Agárdi Anita']", doc, XPathConstants.NODESET);
            output.append("   Találatok száma: ").append(result2.getLength()).append("\n");
            for (int i = 0; i < result2.getLength(); i++) {
                Element ora = (Element) result2.item(i);
                output.append("   - ").append(getOraInfo(ora)).append("\n");
            }
            output.append("\n");

            // Lekérdezés 3: Elméleti órák
            // String xpath3 = "//ora[@tipus='elmelet']";
            output.append("3. Lekérdezés: Elméleti órák\n");
            output.append("   XPath: //ora[@tipus='elmelet']\n");
            NodeList result3 = (NodeList) xpath.evaluate("//ora[@tipus='elmelet']", doc, XPathConstants.NODESET);
            output.append("   Találatok száma: ").append(result3.getLength()).append("\n");
            for (int i = 0; i < result3.getLength(); i++) {
                Element ora = (Element) result3.item(i);
                output.append("   - ").append(getOraInfo(ora)).append("\n");
            }
            output.append("\n");

            // Lekérdezés 4: 10:00-tól kezdődő órák
            // String xpath4 = "//ora[idopont/tol='10:00']";
            output.append("4. Lekérdezés: 10:00-tól kezdődő órák\n");
            output.append("   XPath: //ora[idopont/tol='10:00']\n");
            NodeList result4 = (NodeList) xpath.evaluate("//ora[idopont/tol='10:00']", doc, XPathConstants.NODESET);
            output.append("   Találatok száma: ").append(result4.getLength()).append("\n");
            for (int i = 0; i < result4.getLength(); i++) {
                Element ora = (Element) result4.item(i);
                output.append("   - ").append(getOraInfo(ora)).append("\n");
            }
            output.append("\n");

            // ========== 4 MÓDOSÍTÁS ==========
            output.append("========== 2. MÓDOSÍTÁSOK ==========\n\n");

            // Módosítás 1: id="1" óra helyszínének módosítása
            // String xpathMod1 = "/TM_orarend/ora[@id='1']";
            output.append("1. Módosítás: id='1' óra helyszínének módosítása\n");
            Node node1 = (Node) xpath.evaluate("/TM_orarend/ora[@id='1']", doc, XPathConstants.NODE);
            if (node1 != null) {
                Element ora1 = (Element) node1;
                String regiHelyszin = ora1.getElementsByTagName("helyszin").item(0).getTextContent();
                ora1.getElementsByTagName("helyszin").item(0).setTextContent("1. előadó (módosítva)");
                output.append("   Régi helyszín: ").append(regiHelyszin).append("\n");
                output.append("   Új helyszín: 1. előadó (módosítva)\n");
                output.append("   Módosított óra: ").append(getOraInfo(ora1)).append("\n");
            }
            output.append("\n");

            // Módosítás 2: id="2" óra oktatójának módosítása
            // String xpathMod2 = "/TM_orarend/ora[@id='2']";
            output.append("2. Módosítás: id='2' óra oktatójának módosítása\n");
            Node node2 = (Node) xpath.evaluate("/TM_orarend/ora[@id='2']", doc, XPathConstants.NODE);
            if (node2 != null) {
                Element ora2 = (Element) node2;
                String regiOktato = ora2.getElementsByTagName("oktato").item(0).getTextContent();
                ora2.getElementsByTagName("oktato").item(0).setTextContent("Dr. Agárdi Anita");
                output.append("   Régi oktató: ").append(regiOktato).append("\n");
                output.append("   Új oktató: Dr. Agárdi Anita\n");
                output.append("   Módosított óra: ").append(getOraInfo(ora2)).append("\n");
            }
            output.append("\n");

            // Módosítás 3: id="7" óra típusának módosítása
            // String xpathMod3 = "/TM_orarend/ora[@id='7']";
            output.append("3. Módosítás: id='7' óra típusának módosítása\n");
            Node node3 = (Node) xpath.evaluate("/TM_orarend/ora[@id='7']", doc, XPathConstants.NODE);
            if (node3 != null) {
                Element ora3 = (Element) node3;
                String regiTipus = ora3.getAttribute("tipus");
                ora3.setAttribute("tipus", "gyakorlat");
                output.append("   Régi típus: ").append(regiTipus).append("\n");
                output.append("   Új típus: gyakorlat\n");
                output.append("   Módosított óra: ").append(getOraInfo(ora3)).append("\n");
            }
            output.append("\n");

            // Módosítás 4: id="12" óra időpontjának módosítása
            // String xpathMod4 = "/TM_orarend/ora[@id='12']";
            output.append("4. Módosítás: id='12' óra időpontjának módosítása\n");
            Node node4 = (Node) xpath.evaluate("/TM_orarend/ora[@id='12']", doc, XPathConstants.NODE);
            if (node4 != null) {
                Element ora4 = (Element) node4;
                Element idopont = (Element) ora4.getElementsByTagName("idopont").item(0);
                String regiTol = idopont.getElementsByTagName("tol").item(0).getTextContent();
                String regiIg = idopont.getElementsByTagName("ig").item(0).getTextContent();
                idopont.getElementsByTagName("tol").item(0).setTextContent("09:00");
                idopont.getElementsByTagName("ig").item(0).setTextContent("11:00");
                output.append("   Régi időpont: ").append(regiTol).append(" - ").append(regiIg).append("\n");
                output.append("   Új időpont: 09:00 - 11:00\n");
                output.append("   Módosított óra: ").append(getOraInfo(ora4)).append("\n");
            }
            output.append("\n");

            // ========== MÓDOSÍTOTT DOKUMENTUM KIÍRÁSA ==========
            output.append("========== 3. MÓDOSÍTOTT DOKUMENTUM ==========\n\n");
            output.append("Összes óra a módosítások után:\n\n");
            NodeList allOrak = (NodeList) xpath.evaluate("/TM_orarend/ora", doc, XPathConstants.NODESET);
            for (int i = 0; i < allOrak.getLength(); i++) {
                Element ora = (Element) allOrak.item(i);
                output.append(getOraInfo(ora)).append("\n");
            }

            // Konzolra kiírás
            System.out.println(output.toString());

            // Fájlba kiírás
            File outputFile = new File("OQH5NH_1112/task_3/orarend_modositott_eredmeny.txt");
            try (PrintWriter writer = new PrintWriter(new FileWriter(outputFile, false))) {
                writer.print(output.toString());
                System.out.println("\nEredmények elmentve: " + outputFile.getAbsolutePath());
            }

            // XML fájl mentése módosításokkal
            File modifiedXmlFile = new File("OQH5NH_1112/task_3/orarendoqh5nh_modositott.xml");
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(modifiedXmlFile);
            transformer.transform(source, result);
            System.out.println("Módosított XML elmentve: " + modifiedXmlFile.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getOraInfo(Element ora) {
        StringBuilder info = new StringBuilder();
        info.append("ID: ").append(ora.getAttribute("id"));
        info.append(", Típus: ").append(ora.getAttribute("tipus"));
        info.append(", Tárgy: ").append(ora.getElementsByTagName("targy").item(0).getTextContent());
        
        Element idopont = (Element) ora.getElementsByTagName("idopont").item(0);
        info.append(", Nap: ").append(idopont.getElementsByTagName("nap").item(0).getTextContent());
        info.append(", Idő: ").append(idopont.getElementsByTagName("tol").item(0).getTextContent());
        info.append(" - ").append(idopont.getElementsByTagName("ig").item(0).getTextContent());
        
        info.append(", Helyszín: ").append(ora.getElementsByTagName("helyszin").item(0).getTextContent());
        info.append(", Oktató: ").append(ora.getElementsByTagName("oktato").item(0).getTextContent());
        
        return info.toString();
    }
}


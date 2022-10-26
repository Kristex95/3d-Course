import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class MyDOMParser {
    private ArrayList<Gem> gemArrayList = new ArrayList<>();
    MyDOMParser(String path){
        Document doc;
        try{
            doc = buildDocument(path);
        } catch (Exception e) {
            System.out.println("Open parsing error " + e);
            return;
        }

        Node rootNode = doc.getFirstChild();
        NodeList gems = rootNode.getChildNodes();
        for (int i = 0; i < gems.getLength(); i++) {
            if (gems.item(i).getNodeType() != Node.ELEMENT_NODE){
                continue;
            }
            NodeList gemAttributes = gems.item(i).getChildNodes();

            String name = null;
            String preciousness = null;
            String origin = null;
            String color = null;
            int transparency = 0;
            int cutting = 0;
            int value = 0;

            for (int j = 0; j < gemAttributes.getLength(); j++) {
                if (gemAttributes.item(j).getNodeType() != Node.ELEMENT_NODE){
                    continue;
                }
                Node attribute = gemAttributes.item(j);

                switch (gemAttributes.item(j).getNodeName()){
                    case "name":
                        name = attribute.getTextContent();
                        break;
                    case "preciousness":
                        preciousness = attribute.getTextContent();
                        break;
                    case "origin":
                        origin = attribute.getTextContent();
                        break;
                    case "visual_parameters":
                        NodeList visual_params = attribute.getChildNodes();
                        for (int k = 0; k < visual_params.getLength(); k++) {
                            if (visual_params.item(k).getNodeType() != Node.ELEMENT_NODE){
                                continue;
                            }

                            switch (visual_params.item(k).getNodeName()){
                                case "color":
                                    color = visual_params.item(k).getTextContent();
                                    break;
                                case "transparency":
                                    transparency = Integer.parseInt(visual_params.item(k).getTextContent());
                                    break;
                                case "cutting":
                                    cutting = Integer.parseInt(visual_params.item(k).getTextContent());
                                    break;
                            }
                        }
                        break;
                    case "value":
                        value = Integer.parseInt(attribute.getTextContent());
                        break;
                }
            }

            Gem gem = new Gem(name, preciousness, origin, color, transparency, cutting, value);
            gemArrayList.add(gem);
        }
    }

    public ArrayList<Gem> GetParsedArrayList(){ return gemArrayList;}
    private static Document buildDocument(String path) throws Exception{
        File file = new File(path);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        return factory.newDocumentBuilder().parse(file);
    }

}

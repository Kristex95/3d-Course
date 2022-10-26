import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class SaxParserHandler extends DefaultHandler {
    private String currentTagName;
    private boolean isGem = false;
    private boolean isVisualParameters = false;

    private Gem gem;
    private ArrayList<Gem> gemArrayList = new ArrayList<>();

    private String name;
    private String preciousness;
    private String origin;
    private String color;
    private int transparency;
    private int cutting;
    private int value;

    public ArrayList<Gem> getGemArrayList() {
        return gemArrayList;
    }

    @Override
    public void startDocument() throws SAXException {

    }

    @Override
    public void endDocument() throws SAXException {

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentTagName = qName;
        if (currentTagName.equals("gem")) isGem = true;
        else if (currentTagName.equals("visual_parameters")) isVisualParameters = true;
        //System.out.println("Start element " + qName);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("gem")) {
            gem = new Gem(name, preciousness, origin, color, transparency, cutting, value);
            gemArrayList.add(gem);
            isGem = false;
        } else if (qName.equals("visual_parameters")) {
            isVisualParameters = false;
        }
        currentTagName = null;
        // System.out.println("End element " + qName);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (currentTagName == null) return;
        if (isGem && !isVisualParameters) {
            switch (currentTagName) {
                case "name":
                    name = new String(ch, start, length);
                    break;
                case "preciousness":
                    preciousness = new String(ch, start, length);
                    break;
                case "origin":
                    origin = new String(ch, start, length);
                    break;
                case "visual_parameters":
                    break;
                case "value":
                    value = Integer.parseInt(new String(ch, start, length));
                    break;
            }
        } else if (isGem && isVisualParameters) {
            switch (currentTagName) {
                case "color":
                    color = new String(ch, start, length);
                    break;
                case "transparency":
                    transparency = Integer.parseInt(new String(ch, start, length));
                    break;
                case "cutting":
                    cutting = Integer.parseInt(new String(ch, start, length));
                    break;
            }
        }
    }
}

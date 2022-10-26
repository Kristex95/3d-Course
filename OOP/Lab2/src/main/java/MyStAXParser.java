import javax.xml.stream.*;
import javax.xml.stream.events.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

public class MyStAXParser {
    private String name;
    private String preciousness;
    private String origin;
    private String color;
    private int transparency;
    private int cutting;
    private int value;


    private final ArrayList<Gem> gemArrayList = new ArrayList<>();

    private boolean isGem = false;
    private boolean isVisualParameters = false;

    public ArrayList<Gem> parse(String path){
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader eventReader =
                    factory.createXMLEventReader(new FileReader(path));
            StartElement startElement = null;
            while (eventReader.hasNext()){

                XMLEvent event = eventReader.nextEvent();

                if(event.isStartElement()){
                    startElement = (StartElement) event;

                    Iterator<Attribute> iterator = startElement.getAttributes();

                    if(startElement.getName().toString().equals("gem")) isGem = true;
                    if(startElement.getName().toString().equals("visual_parameters")) isVisualParameters = true;
                }
                if (event.isEndElement())
                {
                    EndElement endElement = (EndElement) event;

                    if (endElement.getName().toString().equals("gem"))
                    {
                        gemArrayList.add(new Gem(name, preciousness, origin, color, transparency, cutting, value));
                        isGem = false;
                    }
                    if (endElement.getName().toString().equals("visual_parameters"))
                    {
                        isVisualParameters = false;
                    }
                }
                if(event.isCharacters()){
                    Characters characters = (Characters) event;
                    if(isGem && !isVisualParameters && !characters.isWhiteSpace()){
                        switch (startElement.getName().toString()) {
                            case "name":
                                name = characters.getData();
                                break;
                            case "preciousness":
                                preciousness = characters.getData();
                                break;
                            case "origin":
                                origin = characters.getData();
                                break;
                            case "value":
                                value= Integer.parseInt(characters.getData());
                                break;
                        }
                    } else if (isGem && isVisualParameters && !characters.isWhiteSpace()) {
                        switch (startElement.getName().toString()){
                            case "color":
                                color = characters.getData();
                                break;
                            case "transparency":
                                transparency = Integer.parseInt(characters.getData());
                                break;
                            case "cutting":
                                cutting = Integer.parseInt(characters.getData());
                                break;
                        }
                    }
                }
            }
        } catch (XMLStreamException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return gemArrayList;
    }
}

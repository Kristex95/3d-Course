import org.w3c.dom.Document;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.util.ArrayList;

public class MySAXParser {

    public ArrayList<Gem> parse(String path){
        try{
            File file = new File(path);
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SaxParserHandler defaultHandler = new SaxParserHandler();
            SAXParser parser = factory.newSAXParser();
            parser.parse(file, defaultHandler);
            return defaultHandler.getGemArrayList();

        } catch (Exception e) {
            System.out.println("Open parsing error " + e);
            return null;
        }
    }

}

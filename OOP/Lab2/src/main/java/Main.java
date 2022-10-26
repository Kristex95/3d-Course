import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        MyStAXParser StAXParser = new MyStAXParser();
        ArrayList<Gem> gemArrayList = StAXParser.parse("src/main/java/gems.xml");
        System.out.println(gemArrayList);
    }
}

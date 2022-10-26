import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

public class DOM_Test {
    ArrayList<Gem> gemArrayList = new ArrayList<>();
    MyDOMParser parser = new MyDOMParser("src/main/resources/gems.xml");


    @Test
    public void GemsCountTest(){
        gemArrayList = parser.GetParsedArrayList();
        Assert.assertEquals(3, gemArrayList.size());
    }

    @Test
    public void GemNameTest(){
        gemArrayList = parser.GetParsedArrayList();
        Assert.assertEquals("Topaz", gemArrayList.get(0).getName());
        Assert.assertEquals("Ruby", gemArrayList.get(1).getName());
        Assert.assertEquals("Aquamarine", gemArrayList.get(2).getName());
    }

    @Test
    public void GemPreciousnessTest(){
        gemArrayList = parser.GetParsedArrayList();
        Assert.assertEquals("precious", gemArrayList.get(0).getPreciousness());
        Assert.assertEquals("semi-precious", gemArrayList.get(1).getPreciousness());
        Assert.assertEquals("precious", gemArrayList.get(2).getPreciousness());
    }

    @Test
    public void GemOriginTest(){
        gemArrayList = parser.GetParsedArrayList();
        Assert.assertEquals("Ukraine", gemArrayList.get(0).getOrigin());
        Assert.assertEquals("Austria", gemArrayList.get(1).getOrigin());
        Assert.assertEquals("USA", gemArrayList.get(2).getOrigin());
    }

    @Test
    public void GemVisualParametersTest(){
        gemArrayList = parser.GetParsedArrayList();
        Assert.assertEquals("yellow", gemArrayList.get(0).getColor());
        Assert.assertEquals(50, gemArrayList.get(0).getTransparency());
        Assert.assertEquals(6, gemArrayList.get(0).getCutting());
    }

    @Test
    public void GemValueTest(){
        gemArrayList = parser.GetParsedArrayList();
        Assert.assertEquals(20, gemArrayList.get(0).getValue());
        Assert.assertEquals(5, gemArrayList.get(1).getValue());
        Assert.assertEquals(40, gemArrayList.get(2).getValue());
    }

    @Test
    public void GemSortTest() {
        gemArrayList = parser.GetParsedArrayList();
        Collections.sort(gemArrayList);
        Assert.assertEquals("Ruby", gemArrayList.get(0).getName());
    }
}

import org.junit.Assert;
import org.junit.Test;

public class XSD_Validation_Test {

        @Test
        Assert.assertTrue(XMLValidator.validateXML("src/main/resources/gems.xsd", "src/main/resources/gems.xml"));


}

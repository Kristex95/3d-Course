package sorting_system_test;

import org.junit.Assert;
import org.junit.Test;
import sweets.Sweet;
import container.Package;
import sorting_system.SortingSystem;

import java.util.ArrayList;
import java.util.List;

public class SortingSystemTest {
     SortingSystem system = new SortingSystem();

    @Test
    public void getTotalMass() {
        Package pkg = new Package();
        pkg.AddSweets(new Sweet("Lollipop", 50, 200, 10));
        pkg.AddSweets(new Sweet("Chocolate", 200, 500, 30));
        pkg.AddSweets(new Sweet("Candy", 65, 110, 8));
        pkg.AddSweets(new Sweet("Marshmallow", 300, 425, 60));
        Assert.assertEquals(pkg.GetTotalMass(), 615.0, 0);

        List<Sweet> sweets = new ArrayList<>();
        sweets.add(new Sweet("Lollipop", 50, 200, 10));
        sweets.add(new Sweet("Chocolate", 200, 500, 30));
        sweets.add(new Sweet("Candy", 65, 110, 8));
        sweets.add(new Sweet("Marshmallow", 300, 425, 60));
        Assert.assertEquals(system.getTotalMass(sweets), 615.0, 0);
    }

    @Test
    public void sortByMass() {
        List<Sweet> sweets = new ArrayList<>();
        sweets.add(new Sweet("Chocolate", 200, 500, 30));
        sweets.add(new Sweet("Lollipop", 50, 200, 10));
        sweets.add(new Sweet("Candy", 65, 110, 8));
        sweets.add(new Sweet("Marshmallow", 300, 425, 60));
        sweets = system.sortByMass(sweets);
        Assert.assertEquals(sweets.get(0).getWeight(), 50.0, 0);
    }

    @Test
    public void findBySugarRange() {
        List<Sweet> sweets = new ArrayList<>();
        sweets.add(new Sweet("Chocolate", 200, 500, 30));
        sweets.add(new Sweet("Lollipop", 50, 200, 10));
        sweets.add(new Sweet("Candy", 65, 110, 8));
        sweets.add(new Sweet("Marshmallow", 300, 425, 60));
        Assert.assertEquals(system.findBySugarRange(sweets, 0.0, 9.0).get(0).getSugar(), 8.0, 0);
    }
}
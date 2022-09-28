package sorting_system;

import org.junit.Test;
import sweets.Sweet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SortingSystemTest {
    SortingSystem system = new SortingSystem();

    @Test
    public void getTotalMass() {
        List<Sweet> sweets = new ArrayList<>();
        sweets.add(new Sweet("Lollipop", 50, 200, 10));
        sweets.add(new Sweet("Chocolate", 200, 500, 30));
        sweets.add(new Sweet("Candy", 65, 110, 8));
        sweets.add(new Sweet("Marshmallow", 300, 425, 60));
        assertEquals(system.getTotalMass(sweets), 615.0, 0);
    }

    @Test
    public void sortByMass() {
        List<Sweet> sweets = new ArrayList<>();
        sweets.add(new Sweet("Chocolate", 200, 500, 30));
        sweets.add(new Sweet("Lollipop", 50, 200, 10));
        sweets.add(new Sweet("Candy", 65, 110, 8));
        sweets.add(new Sweet("Marshmallow", 300, 425, 60));
        sweets = system.sortByMass(sweets);
        assertEquals(sweets.get(0).getWeight(), 50.0, 0);
    }

    @Test
    public void findBySugarRange() {
        List<Sweet> sweets = new ArrayList<>();
        sweets.add(new Sweet("Chocolate", 200, 500, 30));
        sweets.add(new Sweet("Lollipop", 50, 200, 10));
        sweets.add(new Sweet("Candy", 65, 110, 8));
        sweets.add(new Sweet("Marshmallow", 300, 425, 60));
        assertEquals(system.findBySugarRange(sweets, 0.0, 9.0).get(0).getSugar(), 8.0, 0);
    }
}
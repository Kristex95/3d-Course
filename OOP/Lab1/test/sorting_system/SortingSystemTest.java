package sorting_system;

import container.Package;
import org.junit.Test;
import sweets.Sweet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SortingSystemTest {
    SortingSystem system = new SortingSystem();

    @Test
    public void getTotalMass() {
        Package pkg = new Package();
        pkg.AddSweets(new Sweet("Lollipop", 50, 200, 10));
        pkg.AddSweets(new Sweet("Chocolate", 200, 500, 30));
        pkg.AddSweets(new Sweet("Candy", 65, 110, 8));
        pkg.AddSweets(new Sweet("Marshmallow", 300, 425, 60));
        assertEquals(pkg.GetTotalMass(), 615.0, 0);
    }

    @Test
    public void sortByMass() {
        Package pkg = new Package();
        pkg.AddSweets(new Sweet("Lollipop", 50, 200, 10));
        pkg.AddSweets(new Sweet("Chocolate", 200, 500, 30));
        pkg.AddSweets(new Sweet("Candy", 65, 110, 8));
        pkg.AddSweets(new Sweet("Marshmallow", 300, 425, 60));
        pkg.SortByMass();
        assertEquals(pkg.GetByID(0).getWeight(), 50.0, 0);
    }

    @Test
    public void findBySugarRange() {
        Package pkg = new Package();
        pkg.AddSweets(new Sweet("Lollipop", 50, 200, 10));
        pkg.AddSweets(new Sweet("Chocolate", 200, 500, 30));
        pkg.AddSweets(new Sweet("Candy", 65, 110, 8));
        pkg.AddSweets(new Sweet("Marshmallow", 300, 425, 60));
        assertEquals(system.findBySugarRange(pkg.getSweetsList(), 0.0, 9.0).get(0).getSugar(), 8.0, 0);
    }
}
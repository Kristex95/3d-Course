package container;

import sorting_system.SortingSystem;
import sweets.Sweet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Package {
    private List<Sweet> sweets = new ArrayList<>();
    private SortingSystem system = new SortingSystem();

    public Package(){
        sweets.add(new Sweet("Lollipop", 50, 200, 10));
        sweets.add(new Sweet("Chocolate", 200, 500, 30));
        sweets.add(new Sweet("Candy", 65, 110, 8));
        sweets.add(new Sweet("Marshmallow", 300, 425, 60));
        sweets.add(new Sweet("Waffles", 213, 360, 45));
        sweets.add(new Sweet("Zephyr", 150, 175, 34));
        sweets.add(new Sweet("Gum", 35, 50, 7));
        sweets.add(new Sweet("Croissant", 190, 220, 20));

        system.getTotalMass(sweets);
        system.getSweets(sweets);
        sweets = system.sortByMass(sweets);
        System.out.println("Sorted by mass");
        system.getSweets(sweets);
        system.findBySugarRange(sweets, 5.0, 10.0);
    }
}

package sorting_system;

import sweets.Sweet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortingSystem {
    public void getSweets(List<Sweet> sweets) {
        for (Sweet sweet : sweets) {
            System.out.println(sweet.toString());
        }
        System.out.println();
    }

    public double getTotalMass(List<Sweet> sweets) {
        double sum = 0.0;
        for (Sweet sweet : sweets) {
            sum += sweet.getWeight();
        }
        System.out.println("Total mass is " + sum + "grams.");
        System.out.println();
        return sum;
    }

    public List<Sweet> sortByMass(final List<Sweet> sweets) {
        return sweets.stream().sorted(Comparator.comparingDouble(Sweet::getWeight)).collect(Collectors.toList());
    }

    public List<Sweet> findBySugarRange(List<Sweet> sweets, Double start, Double end) {
        List<Sweet> sweetList = new ArrayList<>();
        System.out.println("Sweets found by sugar content from " + start + " to " + end + ": ");
        for (Sweet sweet : sweets) {
            if(sweet.getSugar() >= start && sweet.getSugar() <= end){
                sweetList.add(sweet);
                System.out.println(sweet.toString());
            }
        }
        System.out.println();
        return sweetList;
    }
}

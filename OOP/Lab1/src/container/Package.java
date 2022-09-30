package container;

import sorting_system.SortingSystem;
import sweets.Sweet;

import java.util.ArrayList;
import java.util.List;

public class Package {
    private List<Sweet> sweets = new ArrayList<>();
    private SortingSystem system = new SortingSystem();
    public void AddSweets(Sweet sweet){
        sweets.add(sweet);
    }

    public Sweet GetByID(int id){
        if(id<sweets.size()){
            return sweets.get(id);
        }
        return null;
    }
    public Double GetTotalMass(){
        return system.getTotalMass(sweets);
    }

    public void SortByMass(){
        system.sortByMass(sweets);
    }

    public void FindBySugarRange(Double start, Double end){
        system.findBySugarRange(sweets, start, end);
    }

    public void PrintSweets(){
        system.getSweets(sweets);
    }

    public List<Sweet> getSweetsList(){
        return sweets;
    }
}

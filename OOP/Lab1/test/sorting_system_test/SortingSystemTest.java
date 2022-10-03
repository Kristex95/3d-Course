package sorting_system_test;

import container.Package;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.ls.LSOutput;
import sorting_system.SortingSystem;
import sweets.Sweet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SortingSystemTest {
     SortingSystem system = new SortingSystem();

    @Test
    public void getTotalMass() throws IOException {
        Package pkg = new Package();
        try(FileInputStream fileInputStream = new FileInputStream("test/input.json")) {
            JSONArray object = new JSONArray( new String(fileInputStream.readAllBytes()));
            for (int i = 0; i < object.length(); i++){
                pkg.AddSweets(new Sweet(object.getJSONObject(i).getString("Name"), object.getJSONObject(i).getDouble("Mass"), object.getJSONObject(i).getDouble("Energy Value"), object.getJSONObject(i).getDouble("Sugar")));
            }
            Assert.assertEquals(pkg.GetTotalMass(), 615.0, 0);
        }
    }

    @Test
    public void sortByMass() throws IOException {
        Package pkg = new Package();
        try(FileInputStream fileInputStream = new FileInputStream("test/input.json")) {
            JSONArray object = new JSONArray( new String(fileInputStream.readAllBytes()));
            for (int i = 0; i < object.length(); i++) {
                pkg.AddSweets(new Sweet(object.getJSONObject(i).getString("Name"), object.getJSONObject(i).getDouble("Mass"), object.getJSONObject(i).getDouble("Energy Value"), object.getJSONObject(i).getDouble("Sugar")));
            }
            pkg.SortByMass();
            Assert.assertEquals(pkg.GetByID(0).getWeight(), 50.0, 0);
        }
    }

    @Test
    public void findBySugarRange() throws IOException {
        Package pkg = new Package();
        try(FileInputStream fileInputStream = new FileInputStream("test/input.json")) {
            JSONArray object = new JSONArray( new String(fileInputStream.readAllBytes()));
            for (int i = 0; i < object.length(); i++) {
                pkg.AddSweets(new Sweet(object.getJSONObject(i).getString("Name"), object.getJSONObject(i).getDouble("Mass"), object.getJSONObject(i).getDouble("Energy Value"), object.getJSONObject(i).getDouble("Sugar")));
            }
            Assert.assertEquals(system.findBySugarRange(pkg.getSweetsList(), 0.0, 9.0).get(0).getSugar(), 8.0, 0);
        }
    }
}
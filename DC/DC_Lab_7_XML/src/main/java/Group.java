import java.util.ArrayList;

public class Group {
    public String name;
    public int id;

    public Group(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}



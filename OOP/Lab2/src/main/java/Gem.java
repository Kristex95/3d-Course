public class Gem implements Comparable<Gem> {
    private final String name;
    private final String preciousness;
    private final String origin;
    private final String color;
    private final int transparency;
    private final int cutting;
    private final int value;

    public Gem(String name, String preciousness, String origin, String color, int transparency, int cutting, int value) {
        this.name = name;
        this.preciousness = preciousness;
        this.origin = origin;
        this.color = color;
        this.transparency = transparency;
        this.cutting = cutting;
        this.value = value;
    }

    @Override
    public int compareTo(Gem anotherGem){
        return this.value - anotherGem.value; //
    }


    public String getName() {
        return name;
    }

    public String getPreciousness() {
        return preciousness;
    }

    public String getOrigin() {
        return origin;
    }

    public String getColor() {
        return color;
    }

    public int getTransparency() {
        return transparency;
    }

    public int getCutting() {
        return cutting;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Gem{" +
                "name='" + name + '\'' +
                ", preciousness='" + preciousness + '\'' +
                ", origin='" + origin + '\'' +
                ", color='" + color + '\'' +
                ", transparency=" + transparency +
                ", cutting=" + cutting +
                ", value=" + value +
                '}';
    }
}

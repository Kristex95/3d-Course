package sweets;

public class Sweet {
    private String name;
    private Double weight;
    private Double energyValue;
    private Double sugar;

    public Sweet(String name, double weight, double energyValue, double sugar) {
        this.name = name;
        this.weight = weight;
        this.energyValue = energyValue;
        this.sugar = sugar;
    }

    public String getName() {return name;}
    public double getEnergyValue() {return energyValue;}
    public double getWeight() {return weight;}
    public double getSugar() {return sugar;}

    public void setName(String name) { this.name = name;}
    public void setWeight(double weight) { this.weight = weight;}
    public void setEnergyValue(double energyValue) { this.energyValue = energyValue;}
    public void setSugar(Double sugar) {this.sugar = sugar;}

    public String toString(){
        return "Sweet name: " + getName() + ", Weight: " + getWeight() + " grams, Energy Value: " + getEnergyValue() + " calories, Sugar Content: " + getSugar() + " gram.";
    }
}

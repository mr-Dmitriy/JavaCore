package lesson03.part2;

public abstract class Fruit {

    private String name;
    private float weight;

    public Fruit(String name, float weight) {
        this.name = name;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public Float getWeight() {
        return weight;
    }
}

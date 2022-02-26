package lesson03.part2;

import java.util.ArrayList;
import java.util.Arrays;

public class Box<T extends Fruit> {

    ArrayList<T> box;

    public Box(T... fruit) {
        this.box = new ArrayList<>(Arrays.asList(fruit));
    }

    public String getInfo() {
        for (T fruit : box) {
            return " Fruit name: '" + fruit.getName() + "' and number of fruits: " + box.size();
        }
        return "Empty";
    }

    public float getWeight() {
        float weight = 0.0f;
        for (T fruit : box) {
            weight += fruit.getWeight();
        }
        return weight;
    }

    public void transfer(Box<T> fruits) {
        fruits.box.addAll(box);
        box.clear();
    }

    public void add(T fruit) {
        box.add(fruit);
    }

    public boolean compare(Box<? extends Fruit> fruits) {
        return this.getWeight() == fruits.getWeight();
    }
}

package lesson03.part2;

import java.util.ArrayList;

public class HomeWork3p2BoxWithFruit {
    public static void main(String[] args) {
        Box<Apple> boxWithApple = new Box<>(new Apple(), new Apple(), new Apple());
        Box<Orange> boxWithOranges = new Box<>(new Orange(), new Orange());
        Box<Orange> anotherBoxForFruits = new Box<>();

        System.out.println("A box of apples weighs " + boxWithApple.getWeight());
        System.out.println("A box of oranges weight " + boxWithOranges.getWeight());

        System.out.println(boxWithApple.compare(boxWithOranges));

        boxWithOranges.add(new Orange());
        System.out.println("A box of oranges weight after we put one orange " + boxWithOranges.getWeight());
        boxWithOranges.transfer(anotherBoxForFruits);

        System.out.println(boxWithOranges.getWeight());
        System.out.println("Another Box For Fruits has wieght: " + anotherBoxForFruits.getWeight() +
                           " and has inside: "+ anotherBoxForFruits.getInfo()  );

    }
}


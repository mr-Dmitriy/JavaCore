package lesson09;

import java.util.Objects;

public class Course implements CourseInterFace{

    final String name;

    public Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return  name;
    }
}

package lesson09;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.*;
import java.util.Scanner;
import static java.util.Arrays.*;


public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Course> listOfCourses = asList(
                new Course("Art"),
                new Course("Music"),
                new Course("Philology"),
                new Course("Chemistry"),
                new Course("Jurisprudence"),
                new Course("The medicine"),
                new Course("Physics"),
                new Course("Computer techologies"),
                new Course("Biology")
        );

        List<Student> studentList = asList(
                new Student("Svetlana", generateCourseList(listOfCourses)),
                new Student("Rik", generateCourseList(listOfCourses)),
                new Student("Vitya", generateCourseList(listOfCourses)),
                new Student("Vasya", generateCourseList(listOfCourses)),
                new Student("Anna", generateCourseList(listOfCourses)),
                new Student("Rin", generateCourseList(listOfCourses))
        );

        for(Student student : studentList) {
            System.out.println(student);
        }

        System.out.println("\n" + uniqueCourses(studentList));
        System.out.println(smartestStudents(studentList));

        System.out.print("\n" + "Enter course in English: ");
        String nameOfCourse = scanner.nextLine();

        int indexOfElementInListOfCourses = -1;
        for (int i = 0; i < listOfCourses.size(); i++) {
            if (nameOfCourse.equalsIgnoreCase(listOfCourses.get(i).name)) {
                indexOfElementInListOfCourses = i;
                break;
            }
        }
        if (indexOfElementInListOfCourses == -1){
            System.out.println("failed");
        } else {
            System.out.println(courseStudents(studentList, listOfCourses.get(indexOfElementInListOfCourses)));
        }
    }

    private static List<Course> generateCourseList(List<Course> availableCourses){
        Random random = new Random();
        int numberOfCourses = random.nextInt(availableCourses.size() - 1) + 1;
        Collections.shuffle(availableCourses);
        return availableCourses.stream().limit(numberOfCourses).collect(Collectors.toList());
    }

    private static List uniqueCourses(List<Student> studentList) {
        return studentList.stream()
                .map(Student::getAllCourses)
                .flatMap(List::stream)
                .distinct()
                .collect(Collectors.toList());
    }

    private static List smartestStudents(List<Student> studentList) {
        return studentList.stream()
                .sorted(Comparator.comparing(x -> x.getAllCourses().toArray().length, Comparator.reverseOrder()))
                .limit(3)
                .map (Student::getName)
                .collect(Collectors.toList());
    }

    private static List courseStudents(List<Student> studentList, Course course ) {
        return studentList.stream()
                .filter(x -> x.getAllCourses().contains(course))
                .map(Student::getName)
                .collect(Collectors.toList());
    }
}
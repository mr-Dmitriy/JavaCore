package lesson01;

public class Competition {
    public static void main(String[] args) {

        Course course = new Course(250, 60, 1);
        System.out.println( "\n" + course + "\n" );

        Team[] teams = {
                new Team("Vasiliy", 156, 200, 100, 1),
                new Team("Danya", 176, 300, 20, 2),
                new Team("Boris", 165, 250, 70, 1),
                new Team("Ivan", 156, 50, 0, 1),
        };

        for(Team team : teams) {
            System.out.println(team);
            course.doIt(course);
            team.showResults(course);
        }
    }
}

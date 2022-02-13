package lesson01;

public class Course {
    private int distanceforrun;
    private int distanceforswim;
    private int pullupbarheight;



    Course(int distanceforrun, int distanceforswim, int pullupbarheight) {
        this.distanceforrun = distanceforrun;
        this.distanceforswim = distanceforswim;
        this.pullupbarheight = pullupbarheight;
    }

    boolean doIt ( int distanceofrun, int distanceofswim, int hieghtofjump ) {
        if (distanceofrun >= distanceforrun && distanceofswim >= distanceforswim
            && hieghtofjump >= pullupbarheight ) {
            System.out.println("стартовал");
            return true;
        } else {
            System.out.println("стартовал");
            return false;
        }
    }

    @Override
    public String toString() {
        return "Course: " + distanceforrun +
                ", " + distanceforswim + ", " + pullupbarheight;
    }

    public void doIt(Course course) {
    }
}

package lesson01;

public class Team {
    private String name;
    private int height;
    private int maxdistancetorun;
    private int maxdistancetoswim;
    private int maxheighttojump;

    public Team( String name, int height, int maxdistancetorun,
                 int maxdistancetoswim, int maxheighttojump ) {
        this.name = name;
        this.height = height;
        this.maxdistancetorun = maxdistancetorun;
        this.maxdistancetoswim = maxdistancetoswim;
        this.maxheighttojump = maxheighttojump;
    }

    void showResults(Course course) {
        if( course.doIt(maxdistancetorun,maxdistancetoswim,maxheighttojump) == true ){
            System.out.println( name + " прошел испытание !");
        } else
            System.out.println( name + " не прошел испытание !");
    }

    @Override
    public String toString(){
        return name + ", " + height + ", " + maxdistancetorun +
                ", " + maxdistancetoswim + ", " + maxheighttojump;
    }

}

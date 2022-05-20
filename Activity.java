package tracker;

public class Activity {
   public String course;
   public int points;
    public Activity(String course, int points) {
        this.course = course;
        this.points = points;
    }
    public int getPoints() {
        return points;
    }
}

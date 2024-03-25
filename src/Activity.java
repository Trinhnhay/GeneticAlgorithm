import java.util.*;

public class Activity {
    public String name;
    public int expectedEnrollment;
    public List<String> preferredFacilitators;
    public List<String> crossoverFacilitators;
    public String facilitator ;
    public String[] room;
    public String timeSlot;
    public List<Activity> activityList;

    public Activity(String name, int expectedEnrollment,
                    List<String> preferredFacilitators, List<String> crossoverFacilitators,
                    String [] room, String timeSlot, String facilitator){
        this.name = name;
        this.expectedEnrollment = expectedEnrollment;
        this.preferredFacilitators=preferredFacilitators;
        this.crossoverFacilitators = crossoverFacilitators;
        this.room = room;
        this.timeSlot = timeSlot;
        this.facilitator = facilitator;
        printActivity();
    }

    public void printActivity(){
        System.out.println(name);
        System.out.println(expectedEnrollment);
        System.out.println(preferredFacilitators);
        System.out.println(crossoverFacilitators);
        System.out.println(Arrays.toString(room));
        System.out.println(timeSlot);
        System.out.println(facilitator);
    }

}

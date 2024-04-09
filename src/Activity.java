import java.util.*;

public class Activity {

    public String name;
    public int expectedEnrollment;
    public List<String> preferredFacilitators;
    public List<String> crossoverFacilitators;
    public String facilitator ;
    public String[] room;
    public String timeSlot;

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
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public void setFacilitator(String facilitator) {
        this.facilitator = facilitator;
    }

    public void setRoom(String[] room) {
        this.room = room;
    }

    public void printActivity(){
        System.out.println("name:" + name);
        System.out.println("ExpectedEnrollment: "+expectedEnrollment);
        System.out.println("Preferred:"+ preferredFacilitators);
        System.out.println("Crossover: "+ crossoverFacilitators);
        System.out.println("Room: " + Arrays.toString(room));
        System.out.println("Time: "+ timeSlot);
        System.out.println("Facilitator: "+facilitator);
        System.out.println("\n");
    }

    public String writeActivity(){

        return "Name: "+ name +
                "\nExpectedEnrollment: " + expectedEnrollment +
                "\nPreferred: "+ preferredFacilitators +
                "\nCrossover: "+ crossoverFacilitators +
                "\nRoom: " + Arrays.toString(room) +
                "\nTime: "+ timeSlot +
                "\nFacilitator: "+facilitator+ "\n\n";
    }

}

import java.util.*;

public class Population {
    List<Schedule> scheduleList = new ArrayList<>();

    public Population(){
        createPopulation();
        //displayFitnessScore();
    }

    public void createPopulation(){
        for (int i=0; i<500; i++){
            Schedule newSchedule = new Schedule();
            System.out.println("Schedule" + (i+1));
            newSchedule.displaySchedule();
            displayFitnessScore(newSchedule);
            scheduleList.add(newSchedule);

        }
    }

    public void displayFitnessScore(Schedule schedule){
         new FitnessFunction(schedule);

        }
    }



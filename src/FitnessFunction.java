import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.*;

public class FitnessFunction {

    List <Double> fitnessList = new ArrayList<>();
    Schedule schedule;
    Double scheduleFitness =0.0;

    public FitnessFunction(Schedule schedule){
        this.schedule = schedule;
        calculateScheduleFitness();
    }

    public void calculateActivityFitness(){
        List<Activity> activityList = schedule.activityList;
        for (Activity activity : activityList){
            Double fitness = 0.0;
            int numActivityFacilitatorIsSetInATimeSlot=0;
            int numActivityForFacilitator = 0;
            for (Activity subActivity : activityList) {
                if (activity!= subActivity) {
                    //•	Activity is scheduled at the same time in the same room as another of the activities: -0.5
                    if (activity.timeSlot.equals(subActivity.timeSlot) && activity.room[0].equals(subActivity.room[0])) {
                        fitness += (-0.5);
                    }
                    if (activity.timeSlot.equals(subActivity.timeSlot)&& activity.facilitator.equals(subActivity.facilitator))
                        numActivityFacilitatorIsSetInATimeSlot++;
                    if(activity.facilitator.equals(subActivity.facilitator))
                        numActivityForFacilitator++;
                }

                    //◦	Activities is in a room too small for its expected enrollment: -0.5
                    if (Integer.valueOf(activity.room[1])<(activity.expectedEnrollment))
                        fitness+= (-0.5);
                    //◦	Activities is in a room with capacity > 6 times expected enrollment: -0.2
                    else if(Integer.valueOf(activity.room[1])>= 6*(activity.expectedEnrollment))
                        fitness += (-0.4);
                    //◦	Activities is in a room with capacity > 3 times expected enrollment: -0.4
                    else if (Integer.valueOf(activity.room[1])>= 3*(activity.expectedEnrollment))
                        fitness += (-0.2);
                    else//◦	Otherwise + 0.3
                        fitness += 0.3;

                    //	Activity is overseen by a preferred facilitator: + 0.5
                    if (activity.preferredFacilitators.contains(activity.facilitator))
                        fitness += 0.5;
                    //Activities are overseen by another facilitator listed for that activity: +0.2
                    else if (activity.crossoverFacilitators.contains(activity.facilitator))
                        fitness += 0.2;
                    else //Activities are overseen by some other facilitator: -0.1
                        fitness -= 0.1;

                // Facilitator load:
                // Activity facilitator is scheduled for only 1 activity in this time slot: + 0.2
                if (numActivityFacilitatorIsSetInATimeSlot == 0)
                    fitness +=0.2;
                else //Activity facilitator is scheduled for more than one activity at the same time: - 0.2
                    fitness -=0.2;

                // Facilitator is scheduled to oversee more than 4 activities total: -0.5
                if (numActivityForFacilitator>=4)
                    fitness -=0.5;
                //Facilitator is scheduled to oversee 1 or 2 activities*: -0.4
                else if (!activity.facilitator.equals("Tyler")&& (numActivityForFacilitator>=0)&& (numActivityForFacilitator<=1))
                    fitness -=0.4;

                //rules for SLA 191 and SLA 101 in consecutive time slots
                fitness += activitySpecificAdjustment(activityList,activity);

            }

            fitnessList.add(fitness);
        }
    }

    public Double activitySpecificAdjustment(List<Activity> activityList, Activity activity){
        Double score=0.0;

        for (Activity subActivity : activityList) {
            if (activity.name.equals("SLA100A")&& subActivity.name.equals("SLA100B")){
                // The 2 sections of SLA 101 are more than 4 hours apart: + 0.5
                if ( abs(Double.valueOf(activity.timeSlot)- (Double.valueOf(subActivity.timeSlot)))>4)
                    score +=0.5;
                // Both sections of SLA 101 are in the same time slot: -0.5
                if (activity.timeSlot.equals(subActivity.timeSlot))
                    score-=0.5;
            }
            else if (activity.name.equals("SLA191A")&& subActivity.name.equals("SLA191B")){
                // The 2 sections of SLA 191 are more than 4 hours apart: + 0.5
                if ( abs(Double.valueOf(activity.timeSlot)- (Double.valueOf(subActivity.timeSlot)))>4)
                    score +=0.5;
                // Both sections of SLA 191 are in the same time slot: -0.5
                if (activity.timeSlot.equals(subActivity.timeSlot))
                    score-=0.5;
            }

            if ((activity.name.equals("SLA100A")&& subActivity.name.equals("SLA191A"))||
                    (activity.name.equals("SLA100A")&& subActivity.name.equals("SLA191B"))||
                    (activity.name.equals("SLA100B")&& subActivity.name.equals("SLA191A"))||
                    (activity.name.equals("SLA100B")&& subActivity.name.equals("SLA191B"))||
                    (activity.name.equals("SLA191A")&& subActivity.name.equals("SLA100A"))||
                    (activity.name.equals("SLA191A")&& subActivity.name.equals("SLA100B"))||
                    (activity.name.equals("SLA191B")&& subActivity.name.equals("SLA100A"))||
                    (activity.name.equals("SLA191B")&& subActivity.name.equals("SLA100B"))){
                //A section of SLA 191 and a section of SLA 101 are overseen in consecutive time slots (e.g., 10 AM & 11 AM): +0.5
                if ( abs(Double.valueOf(activity.timeSlot)- (Double.valueOf(subActivity.timeSlot)))==1) {
                    score += 0.5;

                    //In this case only (consecutive time slots), one of the activities is in Roman or Beach, and the other isn’t: -0.4
                    String activityRoom = activity.room[0].split(" ")[0];
                    String subActivityRoom = subActivity.room[0].split(" ")[0];
                    if ((activityRoom.equals("Roman") || activityRoom.equals("Beach")) && !subActivityRoom.equals("Roman") && !subActivityRoom.equals("Beach"))
                        score -= 0.4;
                    else if ((subActivityRoom.equals("Roman") || subActivityRoom.equals("Beach")) && !activityRoom.equals("Roman") && !activityRoom.equals("Beach"))
                        score -=0.4;
                }
                //A section of SLA 191 and a section of SLA 101 are taught separated by 1 hour (e.g., 10 AM & 12:00 Noon): + 0.25
                else if(abs(Double.valueOf(activity.timeSlot)- (Double.valueOf(subActivity.timeSlot)))>1)
                    score +=0.25;
                //A section of SLA 191 and a section of SLA 101 are taught in the same time slot: -0.25
                else if (abs(Double.valueOf(activity.timeSlot)- (Double.valueOf(subActivity.timeSlot)))==0) {
                    score -=0.25;
                }
            }
        }
        return score;
    }
    public List <Double> softmax (List <Double> fitnessList){
        List <Double> result = new ArrayList<>();

        Double sum = 0.0;

        // Calculate exponential for each element and sum
        for (Double value : fitnessList){
            sum += Math.exp(value);
        }

        // Calculate softmax for each element
        for (int i = 0; i < fitnessList.size(); i++) {
            result.add (Math.exp(fitnessList.get(i))/sum);
        }

        return result;
    }
    public void calculateScheduleFitness(){
        calculateActivityFitness();
        for (Double fitness : fitnessList) {
            scheduleFitness+=fitness;
        }
    }
}

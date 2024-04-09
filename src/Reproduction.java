import java.util.*;

public class Reproduction {

    public Reproduction(){
    }

    public List<Schedule>  selectSchedulesForReproduction(Population population) {
        List<Schedule> selectedSchedules = new ArrayList<>();

        // Sort the list based on probabilities in descending order
       population.scheduleFitnessList.sort(Comparator.comparingDouble(ScheduleFitness::getScheduleProbability).reversed());

        selectedSchedules.add(population.scheduleFitnessList.get(0).schedule);
        selectedSchedules.add(population.scheduleFitnessList.get(1).schedule);

        return selectedSchedules;
    }

    public List<Activity> produceOffsprings(Population population) {
        List<Activity> newOffspring = new ArrayList<>();
        List<Schedule> selectedSchedules = selectSchedulesForReproduction(population);

        for (int i=0 ; i< selectedSchedules.get(0).activityList.size(); i++){
            if (i%2==0) {
                newOffspring.add(selectedSchedules.get(0).activityList.get(i));
            }
            else {
                newOffspring.add(selectedSchedules.get(1).activityList.get(i));
            }
        }

        return newOffspring;
    }

    public void mutateChildren(List<Activity> offspring){
        double mutationRate = 0.01/2;

        Random random = new Random();

        // Iterate through the activities
        for (int i = 0; i< offspring.size(); i++){
            Activity activity = offspring.get(i);

            // Check if the activity undergoes mutation based on the mutation rate
            double randomDouble=random.nextDouble()/100;

            if (randomDouble< mutationRate) {
                mutateActivity(activity);
                offspring.set(i,activity);
            }

        }
    }
    public void mutateActivity(Activity activity){
        Random random = new Random();
        Room room = new Room();
        TimeSlot timeSlot = new TimeSlot();
        Facilitator facilitators = new Facilitator();

        String[] randomRoom = room.roomList.get(random.nextInt(room.roomList.size()));
        String time = timeSlot.timeSlot[random.nextInt(timeSlot.timeSlot.length)];
        String facilitator = facilitators.facilitator[random.nextInt(facilitators.facilitator.length)];

        activity.setRoom(randomRoom);
        activity.setTimeSlot(time);
        activity.setFacilitator(facilitator);
    }

    public Population replaceOffspringToPopulation(Population population){

        List<Activity> newOffspring= produceOffsprings(population);
        mutateChildren(newOffspring);
        Schedule newSchedule = new Schedule();

        newSchedule.activityList = newOffspring;

        population.scheduleFitnessList.remove(population.scheduleFitnessList.size()-1);

        FitnessFunction fitness= new FitnessFunction(newSchedule);
        double newFitness =  fitness.calculateScheduleFitness();
        double newProbability = calculateNewOffSpringProbability(population.scheduleFitnessList, newFitness);

        ScheduleFitness newScheduleFitness = new ScheduleFitness(newSchedule, newFitness, newProbability);
        population.scheduleFitnessList.add(newScheduleFitness);

        population.scheduleFitnessList.sort(Comparator.comparingDouble(ScheduleFitness::getScheduleProbability).reversed());

        return population;

    }

    public double calculateNewOffSpringProbability( List <ScheduleFitness> scheduleFitnessList, double newFitness){
        double sum =Math.exp(newFitness);
        // Calculate exponential for each element and sum
        for (ScheduleFitness scheduleFitness : scheduleFitnessList) {
            double value = scheduleFitness.scheduleFitness;
            sum += Math.exp(value);
        }
        return  Math.exp(newFitness)/sum;

    }

    public Population improveGeneration(Population population){
        Population  newPopulation = population;

        for (int i = 0 ; i <100; i++){
            newPopulation= replaceOffspringToPopulation(newPopulation);
        }
        newPopulation.scheduleFitnessList.sort(Comparator.comparingDouble(ScheduleFitness::getScheduleProbability).reversed());

        return newPopulation;
    }
}

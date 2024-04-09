import java.util.*;

public class Population {
    public List<Schedule> scheduleList = new ArrayList<>();
    public List <Double> fitnessList = new ArrayList<>();
    public List <ScheduleFitness> scheduleFitnessList = new ArrayList<>();

    public Population(){
        createPopulation();
        convertFitnessToProbabilityDistribution();
    }

    public void createPopulation(){
        for (int i=0; i<500; i++){
            Schedule newSchedule = new Schedule();
            FitnessFunction fitness = new FitnessFunction(newSchedule);
            fitnessList.add(fitness.calculateScheduleFitness());
            scheduleList.add(newSchedule);
        }
    }

    public void convertFitnessToProbabilityDistribution(){

        double sum = 0.0;
        // Calculate exponential for each element and sum
        for (double value : fitnessList) {
            sum += Math.exp(value);
        }

        // Calculate softmax for each element
        for (int i = 0; i < fitnessList.size(); i++) {
            scheduleFitnessList.add(new ScheduleFitness(scheduleList.get(i), fitnessList.get(i),Math.exp(fitnessList.get(i))/sum ));
        }

    }

    }



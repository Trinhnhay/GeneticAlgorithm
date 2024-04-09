public class ScheduleFitness {

    public Schedule schedule;
    public double scheduleFitness;
    public double scheduleProbability;

    public ScheduleFitness(Schedule schedule,double scheduleFitness, double scheduleProbability){
        this.schedule = schedule;
        this.scheduleFitness = scheduleFitness;
        this.scheduleProbability = scheduleProbability;
    }

    public double getScheduleProbability() {
        return scheduleProbability;
    }
}

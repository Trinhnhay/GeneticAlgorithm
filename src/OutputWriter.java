import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;

public class OutputWriter {

    private Population newPopulation ;

    public OutputWriter(){
    }

    public void newGeneration(Population population){
        Reproduction newGeneration =new Reproduction();
       newPopulation=newGeneration.improveGeneration(population);
    }

    public void writeSchedule(Population population) {
        String[][] data = new String[500][4];
        data[0][0]="Original Schedule";
        data[0][1]= "Original Fitness";
        data[0][2]= "New Schedule";
        data[0][3]= "New Fitness";

        population.scheduleFitnessList.sort(Comparator.comparingDouble(ScheduleFitness::getScheduleProbability).reversed());

        //Original population
        for (int i = 0; i<population.scheduleFitnessList.size()-1;i++) {
            data[i + 1][0] = population.scheduleFitnessList.get(i).schedule.writeSchedule();
            data[i + 1][1] = Double.toString(population.scheduleFitnessList.get(i).scheduleFitness);

        }
        newGeneration(population);

        //new population
        for (int i = 0; i<population.scheduleFitnessList.size()-1;i++){
                data[i+1][2] = newPopulation.scheduleFitnessList.get(i).schedule.writeSchedule();
                data[i+1][3] = Double.toString(newPopulation.scheduleFitnessList.get(i).scheduleFitness);

        }

        // Generate the HTML content for the table
        StringBuilder htmlContent = new StringBuilder();
        htmlContent.append("<html><body><table border=\"1\">");

        // Populate the table with data
        for (String[] row : data) {
            htmlContent.append("<tr>");
            for (String cell : row) {
                cell=cell.replace("\n", "<br>");
                htmlContent.append("<td>").append(cell).append("</td>");
            }
            htmlContent.append("</tr>");
        }

        htmlContent.append("</table></body></html>");

        // Define the file path for the Word document
        String filePath = "Result.html";

        // Write the HTML content to a file with .docx extension
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(htmlContent.toString());
            System.out.println("Word document written successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


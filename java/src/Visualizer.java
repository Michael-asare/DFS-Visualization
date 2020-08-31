import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Visualizer {
    private String firstName;
    private String lastName;
    private String startDate;
    private String endDate;
    private String yAxis;
    private String xAxis;
    private String dataFilename;
    private String graphFilename;

    public Visualizer(String firstName, String lastName, String startDate, String endDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Creates a date that will be used as a command line argument
     * @param date a date
     * @return a String date that is in the MM/DD/YYYY format
     */
    public String paramDate(String date) {
        String [] ls = date.split("-");
        int year = Integer.parseInt(ls[0], 10);
        int month = Integer.parseInt(ls[1], 10);
        int day = Integer.parseInt(ls[2], 10);

        return month + "/" + day + "/" + year;
    }

    /**
     * Creates a date that will be used for the json creation command line argument
     * @param date a date
     * @return a String date that is in MM-DD-YYYY
     */
    public String jsonFileDate(String date) {
        String [] ls = date.split("-");
        int year = Integer.parseInt(ls[0], 10);
        int month = Integer.parseInt(ls[1], 10);
        int day = Integer.parseInt(ls[2], 10);

        return month + "-" + day + "-" + year;
    }

    public void createData() {
        runCollectionScript();
    }

    /**
     * Runs the collection_script.py file
     */
    private void runCollectionScript() {
        String relativePath = "../python/src/collection_script.py";
        dataFilename = "../data/" + lastName + "_" + firstName + "_" +
                jsonFileDate(startDate) + "_" + jsonFileDate(endDate) + ".json";
        String name = firstName +  " " + lastName;
        ProcessBuilder processBuilder = new ProcessBuilder("python",
                relativePath, firstName, lastName,
                paramDate(startDate), paramDate(endDate));

        try {
            Process process = processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates the visualization based off the axes given
     * @param yAxis the first argument
     * @param xAxis the second argument
     * @return the filename of the graph to be displayed
     */
    public String createGraphImage(String yAxis, String xAxis) {
        this.yAxis = yAxis;
        this.xAxis = xAxis;
        runVisualizationScript();
        return graphFilename;
    }

    /**
     * Runs the visualization_script.py
     */
    private void runVisualizationScript() {
        String relativePath = "../python/src/visualization_script.py";
        String name = firstName + " " + lastName;


        ProcessBuilder processBuilder = new ProcessBuilder("python", relativePath,
                dataFilename, yAxis, xAxis, name);
        try {
            Process process = processBuilder.start();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        graphFilename = dataFilename.substring(0, dataFilename.length() - 5)
                + "_" + yAxis + "vs" + xAxis + ".jpg";
    }
}

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

    public String paramDate(String date) {
        String [] ls = date.split("-");
        int year = Integer.parseInt(ls[0], 10);
        int month = Integer.parseInt(ls[1], 10);
        int day = Integer.parseInt(ls[2], 10);

        return month + "/" + day + "/" + year;
    }

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
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String read;
            while((read = bufferedReader.readLine()) != null)
                System.out.println(read);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String createGraphImage(String yAxis, String xAxis) {
        this.yAxis = yAxis;
        this.xAxis = xAxis;
        runVisualizationScript();
        return graphFilename;
    }

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

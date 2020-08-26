import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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

    public void createData(String yAxis, String xAxis) {
        this.yAxis = yAxis;
        this.xAxis = xAxis;
        runCollectionScript();
        convertJSONtoCSV();
    }

    private void runCollectionScript() {
        String relativePath = "../python/src/collection_script.py";
        ProcessBuilder processBuilder = new ProcessBuilder("python",
                relativePath, firstName, lastName,
                paramDate(startDate), paramDate(endDate));
        try {
            Process process = processBuilder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void convertJSONtoCSV() {

    }

    public String createGraphImage() {
        return "";
    }

    private void runVisualizationScript() {

    }
}

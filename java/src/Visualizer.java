import java.io.IOException;

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
        ProcessBuilder processBuilder = new ProcessBuilder("python",
                relativePath, firstName, lastName,
                paramDate(startDate), paramDate(endDate));
        try {
            Process process = processBuilder.start();
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        dataFilename = "../data/" + lastName + "_" + firstName + "_" +
                jsonFileDate(startDate) + "_" + jsonFileDate(endDate) + ".json";
    }

    public String createGraphImage(String yAxis, String xAxis) {
        this.yAxis = yAxis;
        this.xAxis = xAxis;
        runVisualizationScript();
        return "";
    }

    private void runVisualizationScript() {

    }
}

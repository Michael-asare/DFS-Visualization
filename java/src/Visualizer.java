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

    public void createData(String yAxis, String xAxis) {
        this.yAxis = yAxis;
        this.xAxis = xAxis;
        runCollectionScript();
        convertJSONtoCSV();
    }

    private void runCollectionScript() {

    }

    private void convertJSONtoCSV() {

    }

    public String createGraphImage() {
        return "";
    }

    private void runVisualizationScript() {

    }
}

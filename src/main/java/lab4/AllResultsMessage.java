package lab4;

import java.util.ArrayList;

public class AllResultsMessage {
    private final String packageId;
    private final ArrayList<String> results;

    public AllResultsMessage(String packageId, ArrayList<String> results) {
        this.packageId = packageId;
        this.results = results;
    }

    public ArrayList<String> getResult() {
        return results;
    }

    public String getPackageId() {
        return packageId;
    }
}

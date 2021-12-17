package lab4;

public class ResultMessage {
    private final String packageId;
    private final String result;

    public ResultMessage(String packageId, String result) {
        this.packageId = packageId;
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public String getPackageId() {
        return packageId;
    }
}

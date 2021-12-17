package lab4;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class TestMessage {
    private final String
            packageId,
            jsScript,
            functionName,
            testName,
            expectedResult;
    private final ArrayList<Object> params;

    @JsonCreator
    public PackageData(String packageId,
                       String functionName,
                       String jsScript,
                       String testName,
                       String expectedResult) {
        this.packageId = packageId;
        this.jsScript = jsScript;
        this.functionName = functionName;
        this.tests = tests;
    }

    public TestMessage(String packageId) {
        this.packageId = packageId;
    }

    public String getPackageId() {
        return packageId;
    }

    public String getJsScript() {
        return jsScript;
    }

    public String getFunctionName() {
        return functionName;
    }

    public ArrayList<TestData> getTests() {
        return tests;
    }
}

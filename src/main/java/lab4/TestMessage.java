package lab4;

import java.util.ArrayList;

public class TestMessage {
    private final String packageId, jsScript, functionName, testName, expectedResult;
    private final ArrayList<Object> params;

    public TestMessage(String packageId,
                       String functionName,
                       String jsScript,
                       String testName,
                       String expectedResult,
                       ArrayList<Object> params) {
        this.packageId = packageId;
        this.jsScript = jsScript;
        this.functionName = functionName;
        this.testName = testName;
        this.expectedResult = expectedResult;
        this.params = params;
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

    public String getTestName() {
        return testName;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public ArrayList<Object> getParams() {
        return params;
    }
}

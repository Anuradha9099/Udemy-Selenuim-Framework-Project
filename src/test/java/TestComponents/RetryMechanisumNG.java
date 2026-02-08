package TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryMechanisumNG implements IRetryAnalyzer {

    // why use retry analyzer? because we can retry the test if it fails.

    int count = 0;
    int maxTry = 3;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (count < maxTry) {
            count++;
            return true;
        }
        return false;
    }
}

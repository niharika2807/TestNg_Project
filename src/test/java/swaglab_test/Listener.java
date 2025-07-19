package swaglab_test;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        if (BaseClass.driver != null) {
            TakesScreenshot screenshot = (TakesScreenshot) BaseClass.driver;
            File source = screenshot.getScreenshotAs(OutputType.FILE);

            String screenshotDir = "screenshots";
            new File(screenshotDir).mkdirs(); // Create directory if not exists

            File target = new File(screenshotDir + File.separator + result.getName() + ".png");
            try {
                FileUtils.copyFile(source, target);
                System.out.println("Screenshot saved: " + target.getAbsolutePath());
            } catch (IOException e) {
                System.out.println("Failed to save screenshot: " + e.getMessage());
            }
        } else {
            System.out.println("WebDriver instance was null. Screenshot not captured.");
        }
    }

    @Override
    public void onTestStart(ITestResult result) {}

    @Override
    public void onTestSuccess(ITestResult result) {}

    @Override
    public void onTestSkipped(ITestResult result) {}

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}

    @Override
    public void onStart(ITestContext context) {}

    @Override
    public void onFinish(ITestContext context) {}
}
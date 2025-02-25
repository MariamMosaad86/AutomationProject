package listeners.testng;


import driverFactory.Driver;
import org.testng.IExecutionListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.AllureReportHelper;
import utilities.ScreenShotManager;

import java.io.IOException;
import java.lang.reflect.Field;

import static utilities.properties.PropertiesManager.ReportConfig;
import static utilities.properties.PropertiesManager.initializeProperties;

public class TestNGListener implements ITestListener, IExecutionListener {

    @Override
    public void onExecutionStart() {
        System.out.println("************* Welcome to selenium Framework **************");
        initializeProperties();

        if (ReportConfig.getProperty("CleanAllureReport").equalsIgnoreCase("true")){
            AllureReportHelper.cleanAllureReport();
            System.out.println("Allure Report Cleaned Successfully");
        }

    }

    @Override
    public void onExecutionFinish() {
        System.out.println("Generating Report..........");
        if (ReportConfig.getProperty("OpenAllureReportAfterExecution").equalsIgnoreCase("true")) {
            try {
                System.out.println("Opening Allure Report");
                Runtime.getRuntime().exec("reportGeneration.bat");
            } catch (IOException e) {
                System.out.println("Unable to generate Allure Report, may be there's an issue in the batch file/commands");
            }
        }
        System.out.println("******** End of Execution *******************");
    }


    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("********* Starting TEST: " + result.getName() + " *************");
    }

    @Override

    public void onTestSuccess(ITestResult result) {
        System.out.println("********* Success of TEST: " + result.getName() + " *************");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed");
        System.out.println("Taking screen shot....");

        Driver driver = null;

        try {

            ThreadLocal<Driver> driverThreadLocal;

            Object currentClass = result.getInstance();
            Field[] fields = result.getTestClass().getRealClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.getType() == Driver.class) {
                    driver = (Driver) field.get(currentClass);
                }
                if (field.getType() == ThreadLocal.class) {
                    driverThreadLocal = (ThreadLocal<Driver>) field.get(currentClass);
                    driver = driverThreadLocal.get();
                }
            }
        } catch (IllegalAccessException exception) {
            System.out.println("Unable to get field, Field should be public");
        }

        assert driver != null;
        ScreenShotManager.captureScreenshot(driver.get(), result.getName());

        System.out.println("********* Failure of Test: " + result.getName() + "  ***********");

//        try {
//            driver = (Driver) result.getTestClass().getRealClass()
//                    .getDeclaredField("driver")
//                    .get(result.getInstance());
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        } catch (NoSuchFieldException e) {
//            throw new RuntimeException(e);
//        }
    }
}
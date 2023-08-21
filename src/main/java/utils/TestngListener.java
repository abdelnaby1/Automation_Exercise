package utils;

import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;

public class TestngListener implements ISuiteListener, ITestListener, IInvokedMethodListener {
    // ISuiteListener

    @Override
    public void onStart(ISuite suite) {
        PropertiesReader.loadProperties();
    }

    @Override
    public void onFinish(ISuite suite) {
        ISuiteListener.super.onFinish(suite);
    }

}

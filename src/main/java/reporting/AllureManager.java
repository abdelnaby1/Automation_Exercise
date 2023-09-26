package reporting;

import com.github.automatedowl.tools.AllureEnvironmentWriter;
import com.google.common.collect.ImmutableMap;
import driverManager.DriverFactory;
import io.qameta.allure.Attachment;
import org.openqa.selenium.TakesScreenshot;
import utils.BrowserInfoUtils;


import static org.openqa.selenium.OutputType.BYTES;

public class AllureManager {
    private AllureManager() {
    }

    public static void setAllureEnvironmentInformation() {
        //                ImmutableMap.<String, String>builder().
//                        put("Test URL", FrameworkConstants.URL_CRM).
//                        put("Target Execution", FrameworkConstants.TARGET).
//                        put("Global Timeout", String.valueOf(FrameworkConstants.WAIT_DEFAULT)).
//                        put("Page Load Timeout", String.valueOf(FrameworkConstants.WAIT_PAGE_LOADED)).
//                        put("Headless Mode", FrameworkConstants.HEADLESS).
//                        put("Local Browser", String.valueOf(Browser.CHROME)).
//                        put("Remote URL", FrameworkConstants.REMOTE_URL).
//                        put("Remote Port", FrameworkConstants.REMOTE_PORT).
//                        build());
        AllureEnvironmentWriter.allureEnvironmentWriter(
                ImmutableMap.<String, String>builder().
                        put("Test URL", System.getProperty("Test.URL")).
                        put("Target Execution", System.getProperty("execution.type")).
                        put("Global Timeout", System.getProperty("webdriver.wait")).
                        put("Local Browser", BrowserInfoUtils.getBrowserInfo()).
                        put("Remote URL", System.getProperty("remote.execution.host")).
                        put("Remote Port",System.getProperty("remote.execution.port")).
                        put("OS",System.getProperty("os.name")).
                        build(), System.getProperty("user.dir")
                        + "/allure-results/");

        System.out.println("Allure Reports is installed.");

    }

    @Attachment(value = "Failed test screenshot", type = "image/png")
    public static byte[] takeScreenshotToAttachOnAllureReport() {
        try {
                return ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(BYTES);
        } catch (Exception ex) {
            ex.getMessage();
        }
        return new byte[0];
    }

    @Attachment(value = "Take step screenshot", type = "image/png")
    public static byte[] takeScreenshotStep() {
        try {
            return ((TakesScreenshot) DriverFactory.getDriver()).getScreenshotAs(BYTES);
        } catch (Exception ex) {
            ex.getMessage();
        }
        return new byte[0];
    }

    @Attachment(value = "Browser Information", type = "text/plain")
    public static String addBrowserInformationOnAllureReport() {
        return BrowserInfoUtils.getOSInfo();
    }


    //Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    public static String saveTextLog(String message) {
        return message;
    }

    //HTML attachments for Allure
    @Attachment(value = "{0}", type = "text/html")
    public static String attachHtml(String html) {
        return html;
    }
}

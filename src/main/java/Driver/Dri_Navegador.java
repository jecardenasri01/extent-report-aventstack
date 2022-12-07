package Driver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;



import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Dri_Navegador {
    WebDriver driver;
    public static ExtentTest test;

    public Dri_Navegador(ExtentTest _test) {
        test = _test;
    }

    public ChromeDriver AbrirNavegador(String url) {


       WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--headless");
      //  options.addArguments("--disable-web-security");
      //  options.addArguments("--ignore-certificate-errors");
       // options.addArguments("--allow-running-insecure-content");
        //options.addArguments("-incognito");
        // options.addArguments("--window-size=1920,1200");
        driver = new ChromeDriver(options);
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(80000, TimeUnit.MILLISECONDS);
        driver.manage().window().maximize();


        return (ChromeDriver) driver;
    }

    public void NuevaVentanaEnfocar(WebDriver driver) {
        try {
            Thread.sleep(8000);
        } catch (Exception fallo) {

        }
        Set<String> s = driver.getWindowHandles();
        Iterator<String> i1 = s.iterator();
        while (i1.hasNext()) {
            String ChildWindow = i1.next();
            driver.switchTo().window(ChildWindow);
            driver.manage().window().maximize();
        }
    }

    public void NuevaVentanaCerrar(WebDriver driver) {
        try {
            Set<String> s = driver.getWindowHandles();
            Iterator<String> i1 = s.iterator();
            driver.close();
            String ChildWindow = i1.next();
            driver.switchTo().window(ChildWindow);
            driver.manage().window().maximize();

        } catch (Exception fallo) {
            test.log(Status.FAIL, "Test fallo");
            test.log(Status.FAIL, fallo.getMessage());
            Assert.fail(fallo.toString());
        }
    }
    public void CambiarZoom(WebDriver driver,int porcentaje) {
        try {
            JavascriptExecutor jse = (JavascriptExecutor)driver;
            jse.executeScript("document.body.style.zoom = '"+porcentaje+"%';");

        } catch (Exception fallo) {
            test.log(Status.FAIL, "Test fallo");
            test.log(Status.FAIL, fallo.getMessage());
            Assert.fail(fallo.toString());
        }
    }
}

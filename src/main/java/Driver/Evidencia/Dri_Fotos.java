package Driver.Evidencia;

import Data.Entidades.Ent_LecturaDatos;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;
import java.util.Date;

public class Dri_Fotos {
    public ExtentTest test;
    public static WebDriver driver;

    public Dri_Fotos(WebDriver _driver, ExtentTest _test) {
        driver = _driver;
        test = _test;
    }

    public void imagenes(String mensaje) {
        try {
            Date date = new Date();
            long timeMilli = date.getTime();
            Ent_LecturaDatos ent_lecturaDatos = new Ent_LecturaDatos(test);
            Screenshot screenshot = new AShot().takeScreenshot(driver);

            String rutaGeneral = ent_lecturaDatos.retornarDatos("rutaFisica", "Pro_GeneralEvidencia");
            String rutaFotos = ent_lecturaDatos.retornarDatos("rutaImagenes", "Pro_GeneralEvidencia");
            String rutaEvidenciaf = rutaGeneral + rutaFotos;


            File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
// Now you can do whatever you need to do with it, for example copy somewhere
            String path = System.getProperty("user.dir") + "/Screenshots/imagen.png";
            FileUtils.copyFile(scrFile, new File(path));
            byte[] imageBytes = IOUtils.toByteArray(new FileInputStream(path));


            test.log(Status.PASS, mensaje, MediaEntityBuilder.createScreenCaptureFromBase64String(Base64.getEncoder().encodeToString(imageBytes)).build());
        } catch (Exception fallo) {
            test.log(Status.FAIL, "Test fallo");
            test.log(Status.FAIL, fallo.getMessage());
            Assert.fail(fallo.toString());
        }
    }

    public void ResaltarElemento(String xpath, String color) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            if (color == "g") {
                js.executeScript("arguments[0].style.border = '3px solid red'", driver.findElement(By.xpath(xpath)));
            } else {
                js.executeScript("arguments[0].style.border = '3px solid black'", driver.findElement(By.xpath(xpath)));

            }

        } catch (Exception fallo) {
            test.log(Status.FAIL, "No se pudo resaltar objeto " + xpath);
        }
    }

}

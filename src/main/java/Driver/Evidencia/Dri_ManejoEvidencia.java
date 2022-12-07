package Driver.Evidencia;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

public class Dri_ManejoEvidencia {
    static ExtentReports report;
    ;
    public static WebDriver driver;

    public Dri_ManejoEvidencia(WebDriver _driver){
        driver = _driver;
    }
    public ExtentReports CrearHtml(String rutaFisica, String rutaImagenes, String nombreHtml) throws IOException {


        // borrar carpeta fotos
        FileUtils.deleteDirectory(new File(rutaFisica + rutaImagenes));
        // crear carpeta fotos
        FileUtils.forceMkdir(new File(rutaFisica + rutaImagenes));

        ExtentReports extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark.html");
        extent.attachReporter(spark);

        return  extent;
    }
}

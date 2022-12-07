package Driver;
import Driver.Evidencia.Dri_Fotos;
import com.aventstack.extentreports.ExtentTest;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.time.Duration;

public class Dri_Global {
    public static WebDriver driver;
    public static ExtentTest test;

    public Dri_Global(WebDriver _driver, ExtentTest _test) {
        driver = _driver;
        test = _test;
    }
    public String GetText(String xpath){
        return driver.findElement(By.xpath(xpath)).getText();
    }
    public void ClickJavaScript(boolean foto, String xpath, String mensaje)   {

        if (foto == true) {
            CambiarColorElemento(mensaje, xpath);
        }

        WebElement element = driver.findElement(By.xpath(xpath));

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].scrollIntoView(true); arguments[0].click()", element);


    }
    public void DobleClickJavaScript(boolean foto, String xpath, String mensaje)   {

        Actions act = new Actions(driver);

        WebElement element = driver.findElement(By.xpath(xpath));
        act.doubleClick(element).perform();


    }
    public void EnfocarElemento(String xpath){
        WebElement element = driver.findElement(By.xpath(xpath));

        element.sendKeys(Keys.TAB);

        driver.findElement(By.xpath(xpath)).click();

    }
    public void Click(boolean foto,String xpath,String mensaje)  {
        EsperarElemento(xpath);
        if(foto==true)
        {
            CambiarColorElemento(mensaje, xpath);
        }
        try{
            driver.findElement(By.xpath(xpath)).click();
             }catch (Exception fallo
        ){
            WebElement element = driver.findElement(By.xpath(xpath));
            JavascriptExecutor executor = (JavascriptExecutor)driver;
            executor.executeScript("arguments[0].click();", element);

        }
    }
    public void LimpiarInput(String xpath){
        driver.findElement(By.xpath(xpath)).clear();
    }
    public void Escribir(String texto, String xpath,String mensaje)  {
        EsperarElemento(xpath);
        driver.findElement(By.xpath(xpath)).clear();
        driver.findElement(By.xpath(xpath)).sendKeys(texto);

        CambiarColorElemento(mensaje,xpath);
    }

    public void EscribirClick(String texto,String xpath,String mensaje) {

        Click(false,xpath,null);
        Escribir(texto,xpath,mensaje);

    }
    public void EscribirTecla(Keys keys,String xpath){
        driver.findElement(By.xpath(xpath)).sendKeys(keys);
    }
    public void EscribirSinBorrar(String texto,String xpath,String mensaje) throws IOException {
        EsperarElemento(xpath);
        driver.findElement(By.xpath(xpath)).sendKeys(texto);
        CambiarColorElemento(mensaje,xpath);
    }
    public void ListaTexto(String valor,String xpath,String mensaje) throws IOException {
        Select lista = new Select(driver.findElement(By.xpath(xpath)));
        lista.selectByVisibleText(valor);
        CambiarColorElemento(mensaje,xpath);
    }
    public void CambiarColorElemento(String mensaje,String xpath)  {

        Dri_Fotos dri_fotos = new Dri_Fotos(driver,test);
        dri_fotos.ResaltarElemento(xpath,"g");

            dri_fotos.imagenes(mensaje);

        dri_fotos.ResaltarElemento(xpath,"r");
    }
    public void Sleep(int tiempo){
        try {
            Thread.sleep(tiempo);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



    public boolean ElementoPresente(String xpath) {
        boolean existe= false;
        try
        {
        if(driver.findElements(By.xpath(xpath)).size()>0)
        {
            existe = true;
        }else
        {
        }
        }

        catch(Exception e)
        {
            System.out.println("No Element");
        }
        return existe;
    }
    public void WaitElement (String xpath){
        WebElement firstResult = new WebDriverWait(driver, Duration.ofMinutes(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }
    public void EsperarElemento(String xpath) {
        boolean existe = false;
        do {
            try {
                boolean enabled = driver.findElement(By.xpath(xpath)).isEnabled();
                boolean displayed = driver.findElement(By.xpath(xpath)).isDisplayed();
                if (enabled && displayed) {
                    existe = ElementoPresente(xpath);
                }
            } catch (Exception fallo) {

            }
        } while (!existe);
    }
    public void EsperarNoExistaElemento(String xpath){
        boolean existe=false;
        do {
            try{
                existe = driver.findElement(By.xpath(xpath)).isDisplayed();
            }catch (Exception fallo){

            }
        }while (existe);

    }
}

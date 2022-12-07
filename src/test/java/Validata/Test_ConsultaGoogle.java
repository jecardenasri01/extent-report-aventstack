package Validata;

import Data.Entidades.Ent_EscrituraEntidad;
import Data.Entidades.General.Ent_General;
import Driver.Dri_Global;
import Driver.Dri_Navegador;
import Driver.Evidencia.Dri_ManejoEvidencia;
import Sql.Sql_AbrirConexion;
import Sql.Sql_Acciones;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.sql.Connection;

public class Test_ConsultaGoogle {
    public static WebDriver driver;
    public static ExtentTest test;
    static ExtentReports report;

    public static ExtentTest test_ConsultarTablaUser;
    public static ExtentTest test_jeisson;
    public static ExtentTest test_eduardo;
    Dri_Navegador dri_navegador;
    Dri_ManejoEvidencia dri_manejoevidencia;

    Ent_EscrituraEntidad ent_escentidad;
    public Connection con;

    Dri_Global dri_global;
    Ent_General ent_general;
    @Before
    public void Antes() throws IOException {
        InstanciasBefore();
        // Obtener datos entidad General
        ent_general = ent_escentidad.Ent_General(ent_general);
        // Crear html
        report = dri_manejoevidencia.CrearHtml(ent_general.rutaFisica, ent_general.rutaImagenes, ent_general.nombreHtml);
        // Abrir navegador
        driver = dri_navegador.AbrirNavegador("https://www.google.com/");

    }
    @After
    public void Despues() {
        report.flush();
        report.removeTest(test);
        driver.close();
        driver.quit();
    }

    public void InstanciasBefore() {
        ent_escentidad = new Ent_EscrituraEntidad(test);
        dri_navegador = new Dri_Navegador(test);
        dri_manejoevidencia = new Dri_ManejoEvidencia(driver);
        ent_general = new Ent_General();
    }
    public void InstanciasBasicas(String nodo){
        switch (nodo) {
            case "primer consulta":
                test_jeisson = test.createNode("primer consulta");
                dri_global = new Dri_Global(driver,test_jeisson);
                break;
            case "segunda consulta":
            test_eduardo = test.createNode("segunda consulta");
                dri_global = new Dri_Global(driver,test_eduardo);
            break;
        }
    }
    @Test
    public void ConsultaGoogleExtent() {
        int consultasgoogle = 2;
        for (int consulta = 0; consulta < consultasgoogle; consulta++) {
            test = report.createTest("busqueda google").createNode("revision").assignAuthor("Jeisson");

            //instancias
            Sql_AbrirConexion sql = new Sql_AbrirConexion();
            con = sql.ConexionSql();
            Sql_Acciones sql_acciones = new Sql_Acciones(con);


            InstanciasBasicas("primer consulta");
            dri_global.Escribir("prueba 1", "//*[@name='q']", "prueba 1 ");
            dri_global.Escribir("selenium 1", "//*[@name='q']", "selenium 1 ");

            InstanciasBasicas("segunda consulta");

            dri_global.Escribir("prueba 2", "//*[@name='q']", "prueba 2");
            dri_global.Escribir("selenium 2", "//*[@name='q']", "selenium 2 ");

            test.log(Status.PASS, "OK");
        }
    }

}

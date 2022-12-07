package Data.Entidades;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


import dev.failsafe.internal.util.Assert;


import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class Ent_LecturaDatos {
    public static ExtentTest test;
    public Ent_LecturaDatos( ExtentTest _test){

        test = _test;
    }
    public String retornarDatos(String dato,String entidad) {
        String archivoconfig=null;
        String datoNuevo=null;
        try{
            Properties propiedades =new Properties();
            InputStream datos =null;
            archivoconfig=RetornarRuta(entidad);
            datos = new FileInputStream(archivoconfig);
            propiedades.load(datos);
            datoNuevo=   propiedades.getProperty(dato);
        }catch (Exception fallo){
            test.log(Status.FAIL, "No se lleno la entidad "+ archivoconfig);
            test.log(Status.FAIL, fallo.getMessage());
            new AssertionError("No se lleno la entidad "+archivoconfig );
        }
        return datoNuevo;
    }
    public String RetornarRuta(String entidad){
        String rutaconfig=null;
        switch (entidad){
            case  "Pro_GeneralEvidencia":
                rutaconfig= "src/main/java/Data/Propiedades/General/Pro_GeneralEvidencia.properties";
                    break;
        }
        return  rutaconfig;
    }
}

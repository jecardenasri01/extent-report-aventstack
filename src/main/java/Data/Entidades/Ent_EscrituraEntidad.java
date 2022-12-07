package Data.Entidades;


import Data.Entidades.General.Ent_General;
import com.aventstack.extentreports.ExtentTest;


public class Ent_EscrituraEntidad {
    Ent_LecturaDatos ent_lecturaDatos;
    public static ExtentTest test;
   public Ent_EscrituraEntidad(ExtentTest _test){
       test = _test;
   }
    public void instanciasBasicas() {
        ent_lecturaDatos = new Ent_LecturaDatos(test);
    }

    public Ent_General Ent_General(Ent_General ent_general) {
        instanciasBasicas();
        String rutaConfig = "Pro_GeneralEvidencia";

         ent_general.rutaFisica = ent_lecturaDatos.retornarDatos("rutaFisica", rutaConfig);
        ent_general.rutaImagenes = ent_lecturaDatos.retornarDatos("rutaImagenes", rutaConfig);
        ent_general.nombreHtml = ent_lecturaDatos.retornarDatos("nombreHtml", rutaConfig);
        return ent_general;
    }




}

package es.tiernoparla.dam.instituto.controller;

import java.io.IOException;
import java.util.List;

import es.tiernoparla.dam.instituto.App;
import es.tiernoparla.dam.instituto.model.Alumno;
import es.tiernoparla.dam.instituto.model.DAOFactory;
import es.tiernoparla.dam.instituto.model.InstitutoDAO;
import es.tiernoparla.dam.instituto.view.FormularioViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class   InstitutoController extends Application {
   
    private InstitutoDAO dao;
    private static Scene scene;

    public InstitutoController(){
        /**
         * Llamo a la factoría para inicializar el dao que me dará acceso a los datos
         */
        dao = DAOFactory.getDAO(DAOFactory.MODO_TEST);

    }


    /// Cargar pantalla inicial
    @Override
    public void start(Stage stage) throws Exception {
        
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view/FormularioView.fxml"));
        Parent root = (Parent)fxmlLoader.load();  

        //Obtengo el controlador de la vista para pasarle una referencia al controlador principal:
        FormularioViewController viewController = fxmlLoader.<FormularioViewController>getController();
        viewController.setInstitutoController(this);
        Scene scene = new Scene(root); 
        stage.setScene(scene);
        stage.show();

        //Incializo la tabla con los datos del modelo.
        viewController.cargaInicialTabla(obtenerAlumnos());
    }


    // Metodos de acceso a los datos

    public List<Alumno> add(Alumno a) throws Exception{
        dao.add(a);
        return dao.obtenerAlumnos();
    }

    public List<Alumno> obtenerAlumnos() throws Exception{
        return dao.obtenerAlumnos();
    }

}

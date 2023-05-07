package es.tiernoparla.dam.instituto;

import es.tiernoparla.dam.instituto.controller.InstitutoController;
import javafx.application.Application;

/**
 * JavaFX App
 */
public class App  {

    public static void main(String[] args) {
        InstitutoController controller = new InstitutoController();
        Application.launch(InstitutoController.class, args);
    }

}
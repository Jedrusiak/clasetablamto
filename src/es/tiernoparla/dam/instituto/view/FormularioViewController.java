package es.tiernoparla.dam.instituto.view;

import java.util.List;

import es.tiernoparla.dam.instituto.controller.InstitutoController;
import es.tiernoparla.dam.instituto.model.Alumno;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class FormularioViewController {

    @FXML
    private Button btnNuevoAlumno;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnModificar;

    @FXML
    private TableColumn colApellido;

    @FXML
    private TableColumn colEdad;

    @FXML
    private TableColumn colNombre;

    @FXML
    private TableView<Alumno> tblAlumnos;

    @FXML
    private TextField txfApellido;

    @FXML
    private TextField txfEdad;

    @FXML
    private TextField txfNombre;


    // Elementos de trabajo con tablas
        // Usaremos este objeto para añadir los nuevos alumnos.
    private ObservableList <Alumno> alumnos;


    //InstitutoController -> Controlador principal de la aplicación
    private InstitutoController institutoController;

    
    @FXML
    public void initialize() throws Exception {
        //Inicializamos la colección de alumnos
        alumnos = FXCollections.observableArrayList();

        //Asociamos las columnas al atributo del modelo -> colNombre a Alumno.nombre;
        this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colApellido.setCellValueFactory(new PropertyValueFactory("apellidos"));
        this.colEdad.setCellValueFactory(new PropertyValueFactory("edad"));

        
        
    }

    public void cargaInicialTabla(List<Alumno> lista ){
        alumnos.addAll(lista);
        //Refresco la tabla de la vista con el contenido de la colección alumnos.
        this.tblAlumnos.setItems(alumnos);
    }

    @FXML
    void agregarAlumno(MouseEvent event) throws Exception {

        try{
            String nombre = txfNombre.getText();
            String apellidos = txfApellido.getText();
            int edad = Integer.parseInt(txfEdad.getText());

            Alumno alumno = new Alumno(nombre, apellidos, edad);

            if(this.alumnos.contains(alumno)){
                mostrarAviso("El alumno ya ha sido insertado previamente", AlertType.ERROR);
            }else{
                
                List<Alumno> lista = institutoController.add(alumno);

                //Añado el alumno a la tabla
                this.alumnos.add(alumno);
                //Refresco la tabla de la vista con el contenido de la colección alumnos.
                this.tblAlumnos.setItems(alumnos);
            }
        }catch(NumberFormatException e){
            mostrarAviso("Error en el campo edad", AlertType.ERROR);
        }
    }

    @FXML
    void eliminar(MouseEvent event) {
        // Devuelve el alumno seleccionado
        Alumno alumno = this.tblAlumnos.getSelectionModel().getSelectedItem();

        if(alumno == null){
            //No hay un alumno de la tabla seleccionado, no se puede borrar.
            mostrarAviso("Debe seleccionar un registro y luego borrarlo", AlertType.ERROR);
        }else{
            //Eliminamos de la colección al alumno seleccionado
            this.alumnos.remove(alumno);

            //Refrescamos la tabla que estamos mostrando
            this.tblAlumnos.refresh();
        }
    }

    @FXML
    void modificar(MouseEvent event) {

        // Devuelve el alumno seleccionado
        Alumno alumno = this.tblAlumnos.getSelectionModel().getSelectedItem();

        if(alumno == null){
            //No hay un alumno de la tabla seleccionado, no se puede modificar.
            mostrarAviso("Debe seleccionar un registro y luego modificarlo", AlertType.ERROR);
        }else{
            // Creamos un objeto Alumno con los datos del formulario
            try{
                String nombre = txfNombre.getText();
                String apellidos = txfApellido.getText();
                int edad = Integer.parseInt(txfEdad.getText());
    
                Alumno alumnoMod = new Alumno(nombre, apellidos, edad);
    
                if(!this.alumnos.contains(alumnoMod)){
                    // Si tabla de alumnos no contiene al alumno modificado
                    //  seteamos los atributos del alumnoModificado (alumnoMod) al alumno seleccionado(alumno)

                    alumno.setNombre(alumnoMod.getNombre());
                    alumno.setApellidos(alumnoMod.getApellidos());
                    alumno.setEdad(alumnoMod.getEdad());

                    //Refrescamos la tabla que estamos mostrando
                    this.tblAlumnos.refresh();
                }else{
                    mostrarAviso("El alumno que ha modificado ya existe", AlertType.ERROR);
                }
            }catch(NumberFormatException e){
                mostrarAviso("Error en el campo edad", AlertType.ERROR);
            }
        }
    }     
    
    @FXML
    void seleccionarAlumno(MouseEvent event) {    

        // Devuelve el alumno seleccionado
        Alumno alumno = this.tblAlumnos.getSelectionModel().getSelectedItem();

        if(alumno == null){
            // No se ha seleccionado nada, por ejemplo click en el espacio vacío.
            // NOTA: Comentario explicativo, esta condición de if sobra.
        }else{
            copiarDatosFormulario(alumno);
        }

    }

    /**
     * Copia los datos del objeto a los textField del formulario
     * @param alumno
     */
    private void copiarDatosFormulario(Alumno alumno){

        this.txfNombre.setText(alumno.getNombre());
        this.txfApellido.setText(alumno.getApellidos());
        this.txfEdad.setText(String.valueOf(alumno.getEdad()));
    }

    private void mostrarAviso(String msg, AlertType tipo){
        Alert alerta = new Alert(tipo);
        alerta.setHeaderText(null);
        alerta.setTitle("Importante");
        alerta.setContentText(msg);
        alerta.showAndWait();

    }

    public void setInstitutoController(InstitutoController controller){
        this.institutoController = controller;
    }
}



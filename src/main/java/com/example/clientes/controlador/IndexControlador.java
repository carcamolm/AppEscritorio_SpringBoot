package com.example.clientes.controlador;
import com.example.clientes.modelo.Cliente;
import com.example.clientes.servicio.ClienteServicio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.*;

@Component
public class IndexControlador implements Initializable {
    private static final Logger logger =
            LoggerFactory.getLogger(IndexControlador.class);
    @Autowired
    private ClienteServicio clienteServicio;

    @FXML
    private TableView<Cliente> clienteTabla;

    @FXML
    private TableColumn<Cliente, Integer> idClienteColumna;

    @FXML
    private TableColumn<Cliente, String> nombreClienteColumna;

    @FXML
    private TableColumn<Cliente, String> domicilioClienteColumna;

    @FXML
    private TableColumn<Cliente, String> telefonoClienteColumna;

    private final ObservableList<Cliente> clienteList =
            FXCollections.observableArrayList();

//Parte 2
    
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtDomicilio;

    @FXML
    private TextField txtTelefono;

//-----------
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        clienteTabla.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        configurarColumnas();
        listarClientes();

    }
    private void configurarColumnas(){
        idClienteColumna.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        nombreClienteColumna.setCellValueFactory(new PropertyValueFactory<>("nombreCliente"));
        domicilioClienteColumna.setCellValueFactory(new PropertyValueFactory<>("domicilioCliente"));
        telefonoClienteColumna.setCellValueFactory(new PropertyValueFactory<>("telefonoCliente"));
    }
    private void listarClientes(){
        logger.info("Ejecutando listado de clientes");
        clienteList.clear();
        clienteList.addAll(clienteServicio.ListarClientes());
        clienteTabla.setItems(clienteList);
    }

    //Parte 2

     public void agregarCliente(){
        if(txtNombre.getText().isEmpty()){
            mostrarMensaje("Error de Validación","Debe proporcionar nombre Cliente");
            txtNombre.requestFocus();
            return;
        }
        else{
            var cliente = new  Cliente();
            recolectarDatosFormulario(cliente);
            //-----Parte2
            cliente.setIdCliente(null);
            //----------------------Pasar a terminar el metodo modificarCliente
            clienteServicio.guardarCliente(cliente);
            mostrarMensaje("Información", "Cliente Agregado");
            limpiarFormulario();
            listarClientes();

        }
    }
   private void recolectarDatosFormulario(Cliente cliente){
       //-----Parte 2----
       if(idClienteInterno!=null)
             cliente.setIdCliente(idClienteInterno);
       //------------------------------------Pase a modificar agregarCliente
       
        cliente.setNombreCliente(txtNombre.getText());
        cliente.setDomicilioCliente(txtDomicilio.getText());
        cliente.setTelefonoCliente(txtTelefono.getText());
   }
//------Parte 2---
public void  modificarCliente(){
        if(idClienteInterno==null){
            mostrarMensaje("Información","Debe seleccionar un registro Cliente");
            return;
        }
        if(txtNombre.getText().isEmpty()){
            mostrarMensaje("Error Validación","Debe ingresar un Cliente");
            txtNombre.requestFocus();
            return;
        }
        var cliente=new Cliente();
        recolectarDatosFormulario(cliente);  //Modificar recolectarDatosFormulario

        //---Parte 2
        clienteServicio.guardarCliente(cliente);
        mostrarMensaje("Información","Cliente Modificado");
        limpiarFormulario();
        listarClientes();
    //----------Pasar a limpiarFormulario establecer como nulo el idClienteInterno
  }

//--------------------------


    
   private void limpiarFormulario(){
       //---Parte 2
       idClienteInterno=null;
       //-----------
       
        txtNombre.clear();
        txtDomicilio.clear();
        txtTelefono.clear();
   }

    private void mostrarMensaje(String titulo, String mensaje){
        Alert alerta=new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    
}

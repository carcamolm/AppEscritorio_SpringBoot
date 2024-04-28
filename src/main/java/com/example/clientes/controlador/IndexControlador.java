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

    

   private void recolectarDatosFormulario(Cliente cliente){
       //-----Parte 2----
       if(idClienteInterno!=null)
             cliente.setIdCliente(idClienteInterno);
       //------------------------------------Pase a modificar agregarCliente
       
        cliente.setNombreCliente(txtNombre.getText());
        cliente.setDomicilioCliente(txtDomicilio.getText());
        cliente.setTelefonoCliente(txtTelefono.getText());
   }
    
   private void limpiarFormulario(){
             
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

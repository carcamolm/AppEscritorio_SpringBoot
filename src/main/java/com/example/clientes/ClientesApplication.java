package com.example.clientes;

import com.example.clientes.presentacion.SistemaClientesFx;
import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientesApplication {

	public static void main(String[] args) {

		//SpringApplication.run(ClientesApplication.class, args);
		Application.launch(SistemaClientesFx.class,args);

	}

}

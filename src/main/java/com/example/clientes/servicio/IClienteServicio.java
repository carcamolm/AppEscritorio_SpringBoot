package com.example.clientes.servicio;
import com.example.clientes.modelo.Cliente;

import java.util.List;
public interface IClienteServicio {
    public List<Cliente> ListarClientes();
    public Cliente buscarClientePorId(Integer idCliente);

    public void guardarCliente(Cliente cliente);
    public void eliminarCliente(Cliente cliente);
}

package edu.unisabana.dyas.sampleprj.dao.mybatis.mappers;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import edu.unisabana.dyas.samples.entities.Cliente;

public interface ClienteMapper {

    // Parte II.1 — @Param("idcli") para usar #{idcli} en el WHERE
    public Cliente consultarCliente(@Param("idcli") int id);

    // Parte II.3 — cada parámetro anotado con @Param
    public void agregarItemRentadoACliente(
            @Param("idc") int id,
            @Param("idi") int idit,
            @Param("fechainicio") Date fechainicio,
            @Param("fechafin") Date fechafin);

    public List<Cliente> consultarClientes();
}
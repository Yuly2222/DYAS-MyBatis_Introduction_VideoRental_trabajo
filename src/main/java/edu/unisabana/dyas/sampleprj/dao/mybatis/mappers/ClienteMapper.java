package edu.unisabana.dyas.sampleprj.dao.mybatis.mappers;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import edu.unisabana.dyas.samples.entities.Cliente;

public interface ClienteMapper {

    /**
     * Consulta un cliente específico por su número de documento.
     * 
     * @param id número de documento del cliente
     * @return Cliente con sus ItemsRentados, Items y TipoItem asociados
     */
    public Cliente consultarCliente(@Param("idcli") int id);

    /**
     * Registra un nuevo ItemRentado asociado al cliente (idc) con el item (idi).
     * 
     * @param id          documento del cliente
     * @param idit        id del item a rentar
     * @param fechainicio fecha de inicio de la renta
     * @param fechafin    fecha de fin de la renta
     */
    public void agregarItemRentadoACliente(
            @Param("idc") int id,
            @Param("idi") int idit,
            @Param("fechainicio") Date fechainicio,
            @Param("fechafin") Date fechafin);

    /**
     * Consulta todos los clientes con sus items rentados.
     * 
     * @return lista de todos los clientes
     */
    public List<Cliente> consultarClientes();
}
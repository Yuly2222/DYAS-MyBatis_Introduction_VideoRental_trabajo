package edu.unisabana.dyas.sampleprj.dao.mybatis.mappers;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import edu.unisabana.dyas.samples.entities.Item;

public interface ItemMapper {

    /**
     * Consulta todos los items con su TipoItem asociado.
     * 
     * @return lista de todos los items
     */
    public List<Item> consultarItems();

    /**
     * Consulta un item específico por su id.
     * 
     * @param id id del item
     * @return Item con su TipoItem asociado
     */
    public Item consultarItem(@Param("iditem") int id);

    /**
     * Inserta un nuevo item en la base de datos.
     * 
     * @param it objeto Item a insertar
     */
    public void insertarItem(@Param("item") Item it);
}
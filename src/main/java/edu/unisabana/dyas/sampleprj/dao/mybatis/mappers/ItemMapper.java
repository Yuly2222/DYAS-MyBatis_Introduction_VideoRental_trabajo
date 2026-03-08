package edu.unisabana.dyas.sampleprj.dao.mybatis.mappers;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import edu.unisabana.dyas.samples.entities.Item;

public interface ItemMapper {

    public List<Item> consultarItems();

    // Parte II.5 — @Param("iditem") para usar #{iditem} en el WHERE
    public Item consultarItem(@Param("iditem") int id);

    // Parte II.4 — @Param("item") para usar #{item.id}, #{item.nombre}, etc.
    public void insertarItem(@Param("item") Item it);
}
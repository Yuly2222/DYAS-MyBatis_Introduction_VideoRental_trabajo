package edu.unisabana.dyas.samples.services.client;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import edu.unisabana.dyas.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.unisabana.dyas.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.unisabana.dyas.samples.entities.Item;
import edu.unisabana.dyas.samples.entities.TipoItem;

public class MyBatisExample {

    public static SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory = null;
        if (sqlSessionFactory == null) {
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream("mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e.getCause());
            }
        }
        return sqlSessionFactory;
    }

    public static void main(String args[]) throws SQLException {
        SqlSessionFactory sessionfact = getSqlSessionFactory();
        SqlSession sqlss = sessionfact.openSession();

        // -------------------------------------------------------
        // PARTE I – consultarClientes()
        // -------------------------------------------------------
        System.out.println("=== PARTE I: Todos los clientes ===");
        ClienteMapper cm = sqlss.getMapper(ClienteMapper.class);
        System.out.println(cm.consultarClientes());

        // -------------------------------------------------------
        // PARTE II.1 – consultarCliente(int id)
        // Usa @Param("idcli") → WHERE c.documento = #{idcli}
        // -------------------------------------------------------
        System.out.println("\n=== PARTE II.1: Consultar cliente por documento ===");
        System.out.println(cm.consultarCliente(123456789));

        // -------------------------------------------------------
        // PARTE II.3 – agregarItemRentadoACliente
        // -------------------------------------------------------
        System.out.println("\n=== PARTE II.3: Agregar item rentado al cliente 555555555 ===");
        cm.agregarItemRentadoACliente(555555555, 1, new Date(), new Date());
        System.out.println("Item rentado agregado. Verificando cliente...");
        System.out.println(cm.consultarCliente(555555555));

        // -------------------------------------------------------
        // PARTE II.4 – insertarItem(Item it)
        // -------------------------------------------------------
        System.out.println("\n=== PARTE II.4: Insertar nuevo Item ===");
        ItemMapper im = sqlss.getMapper(ItemMapper.class);
        TipoItem tipo = new TipoItem(2, "Mueble");
        Item nuevoItem = new Item(tipo, 99, "Silla Gamer",
                "Silla ergonómica para gaming", "2024-01-01",
                3500, "Diario", "Mueble");
        im.insertarItem(nuevoItem);
        System.out.println("Item insertado.");

        // -------------------------------------------------------
        // PARTE II.5 – consultarItems() y consultarItem(int id)
        // -------------------------------------------------------
        System.out.println("\n=== PARTE II.5: Todos los items ===");
        System.out.println(im.consultarItems());

        System.out.println("\n=== PARTE II.5: Consultar item por id ===");
        System.out.println(im.consultarItem(1));

        sqlss.commit();
        sqlss.close();
    }
}
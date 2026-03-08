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

        ClienteMapper cm = sqlss.getMapper(ClienteMapper.class);
        ItemMapper im = sqlss.getMapper(ItemMapper.class);

        // PARTE I: consultarClientes()

        System.out.println("Todos los clientes");
        System.out.println(cm.consultarClientes());

        // PARTE II.1 y II.2: consultarCliente(id) con @Param("idcli")

        System.out.println("\nConsultar cliente por documento");
        System.out.println(cm.consultarCliente(123456789));

        // PARTE II.3: agregarItemRentadoACliente

        System.out.println("\n Agregar item rentado a cliente");
        cm.agregarItemRentadoACliente(555555555, 1, new Date(), new Date());
        System.out.println("Item rentado agregado. Cliente actualizado:");
        System.out.println(cm.consultarCliente(555555555));

        // PARTE II.4: insertarItem con @Param("item")

        System.out.println("\nInsertar nuevo Item");
        TipoItem tipo = new TipoItem(1, "Electrónico");
        Item nuevoItem = new Item(tipo, 99, "Laptop", "Laptop gamer 16GB",
                "2024-01-01", 15000, "Diario", "Electrónico");
        im.insertarItem(nuevoItem);
        System.out.println("Item insertado correctamente.");

        // PARTE II.5: consultarItems() y consultarItem(id)

        System.out.println("\nPARTE II.5: Todos los items");
        System.out.println(im.consultarItems());

        System.out.println("\nConsultar item por id");
        System.out.println(im.consultarItem(1));

        sqlss.commit();
        sqlss.close();
    }
}
package org.example;

import org.example.dao.CategoriaDAO;
import org.example.dao.ClienteDao;
import org.example.dao.PedidoDao;
import org.example.dao.ProdutoDao;
import org.example.modelo.*;
import org.example.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Categoria celulares = new Categoria("CELULARES");
        Produto celular1 = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);
        Produto celular2 = new Produto("Sansung", "Muito Massa", new BigDecimal("1000"), celulares);
        EntityManager em = JPAUtil.getEntityMenager();

        ProdutoDao produtoDao = new ProdutoDao(em);
        CategoriaDAO categoriaDAO = new CategoriaDAO(em);
        em.getTransaction().begin();
        categoriaDAO.cadastrar(celulares);
        produtoDao.cadastrar(celular1);
        produtoDao.cadastrar(celular2);
        em.getTransaction().commit();
        System.out.println(produtoDao.buscarPorId(1l));
        System.out.println(produtoDao.buscarTodos());
        System.out.println(produtoDao.buscarPorNomeDaCategoria(celulares.getNome()));
        em.close();

        EntityManager em1 = JPAUtil.getEntityMenager();
        Cliente cliente = new Cliente("Guilherme", "123456");
        Pedido pedido = new Pedido(cliente);
        pedido.adicionarItem(new ItemPedido(1,celular1,pedido));

        ClienteDao clienteDao = new ClienteDao(em1);
        PedidoDao pedidoDao = new PedidoDao(em1);
        em1.getTransaction().begin();
        clienteDao.cadastrar(cliente);
        pedidoDao.cadastrar(pedido);
        em1.getTransaction().commit();

        System.out.println("Valor total vendido: " + pedidoDao.valorTotalVendido());



    }

}


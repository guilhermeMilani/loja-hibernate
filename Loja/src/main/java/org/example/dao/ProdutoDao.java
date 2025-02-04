package org.example.dao;

import org.example.modelo.Produto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ProdutoDao {
    private EntityManager em;

    public ProdutoDao(EntityManager em) {
        this.em = em;
    }
    public void cadastrar(Produto produto){
        this.em.persist(produto);
    }
    public void atualizar(Produto produto){
        this.em.merge(produto);
    }
    public void remover(Produto produto){
        produto = em.merge(produto);
        this.em.remove(produto);
    }
    public Produto buscarPorId(Long id){
        return em.find(Produto.class,id);
    }
    public List<Produto> buscarTodos(){
        String jpql = "SELECT p FROM Produto p";
        return em.createQuery(jpql, Produto.class).getResultList();
    }

    public List<Produto> buscarPorNome(String nome){
        String jpql = "SELECT p FROM Produto p WHERE p.nome = :nome";
        return em.createQuery(jpql, Produto.class).setParameter("nome", nome).getResultList();
    }
    public List<Produto> buscarPorNomeDaCategoria(String nome){
        String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = :nome";
        return em.createQuery(jpql, Produto.class).setParameter("nome", nome).getResultList();
    }
}

package dao;

import models.Produto;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class ProdutoDao {

    private EntityManager entityManager;

    // constructors

    public ProdutoDao(EntityManager em) {
        this.entityManager = em;
    }

    // methods

    public void atualizar(Produto produto) {
        this.entityManager.merge(produto);
    }

    public Produto buscarPorId(Long id) {
        return entityManager.find(Produto.class, id);
    }

    public List<Produto> buscarPorNome(String nome) {
        return entityManager.createQuery("SELECT p FROM Produto p WHERE p.nome = :nome", Produto.class).setParameter("nome", nome).getResultList();
    }

    public List<Produto> buscarPorNomeDaCategoria(String nome) {
        return entityManager.createQuery("SELECT p FROM Produto p WHERE p.categoria.nome = :nome", Produto.class).setParameter("nome", nome).getResultList();
    }

    public BigDecimal buscarPrecoDoProdutoComNome(String nome) {
        return entityManager.createQuery("SELECT p.preco FROM Produto p WHERE p.nome = :nome", BigDecimal.class).setParameter("nome", nome).getSingleResult();
    }

    public List<Produto> buscarTodos() {
        return entityManager.createQuery("SELECT p FROM Produto p", Produto.class).getResultList();
    }

    public void cadastrar(Produto produto) {
        this.entityManager.persist(produto);
    }

    public void remover(Produto produto) {
        produto = entityManager.merge(produto);

        this.entityManager.remove(produto);
    }
}

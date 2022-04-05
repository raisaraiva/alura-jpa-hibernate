package dao;

import models.Categoria;

import javax.persistence.EntityManager;

public class CategoriaDao {

    private EntityManager entityManager;

    // constructors

    public CategoriaDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // methods

    public void atualizar(Categoria categoria) {
        this.entityManager.merge(categoria);
    }

    public void cadastrar(Categoria categoria) {
        this.entityManager.persist(categoria);
    }

    public void remover(Categoria categoria) {
        categoria = entityManager.merge(categoria);

        this.entityManager.remove(categoria);
    }
}

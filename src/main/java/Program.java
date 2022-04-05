import dao.CategoriaDao;
import dao.ProdutoDao;
import models.Categoria;
import models.Produto;
import utils.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class Program {

    public static void main(String[] args) {
        cadastrarProduto();

        EntityManager entityManager = JPAUtil.getEntityManager();

        ProdutoDao produtoDao = new ProdutoDao(entityManager);

        Produto produto = produtoDao.buscarPorId(1l);

        System.out.println(produto.getPreco());

        List<Produto> produtoList = produtoDao.buscarPorNomeDaCategoria("CELULARES");

        produtoList.forEach(p2 -> System.out.println(produto.getNome()));

        BigDecimal precoDoProduto = produtoDao.buscarPrecoDoProdutoComNome("Xiaomi Redmi");

        System.out.println("Preco do Produto: " + precoDoProduto);
    }

    private static void cadastrarProduto() {
        EntityManager entityManager = JPAUtil.getEntityManager();

        Categoria celulares = new Categoria("CELULARES");
        Produto celular = new Produto("Xiaomi Redmi", "Muito legal", new BigDecimal("800"), celulares);

        ProdutoDao produtoDao = new ProdutoDao(entityManager);
        CategoriaDao categoriaDao = new CategoriaDao(entityManager);

        entityManager.getTransaction().begin();

        categoriaDao.cadastrar(celulares);
        produtoDao.cadastrar(celular);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}

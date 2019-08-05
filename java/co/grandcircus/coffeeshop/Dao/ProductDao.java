package co.grandcircus.coffeeshop.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.grandcircus.coffeeshop.entity.Product;



@Repository
@Transactional
public class ProductDao {
@PersistenceContext
	EntityManager em;

public List<Product> findAll(){
	List<Product> goods = em.createQuery("FROM Product",Product.class) 
			.getResultList();
	return goods;
}

public Product findById(Long id) {
return em.find(Product.class, id);
}

public void create(Product product) {
	em.persist(product);
}

public void update(Product product) {
	em.merge(product);
}

public void delete(Long id) {
	Product product = em.getReference(Product.class,id);
	em.remove(product);
}

}

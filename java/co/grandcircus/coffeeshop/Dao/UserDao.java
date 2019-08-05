package co.grandcircus.coffeeshop.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import co.grandcircus.coffeeshop.entity.User;

@Repository
@Transactional
public class UserDao {
	@PersistenceContext
	EntityManager em;
	
	
	public List<User> findAll(){
	 return em.createQuery("FROM User",User.class) 
				.getResultList();
		
	}
	
	public User findById(Long id) {
		return em.find(User.class, id);
		}

		public void create(User user) {
			em.persist(user);
		}
//edit
		public void update(User user) {
			em.merge(user);
		}
//delete
		public void delete(Long id) {
			User user = em.getReference(User.class,id);
			em.remove(user);
		}



		
}

package ua.com.goit.gojava7.kickstarter.datasource.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ua.com.goit.gojava7.kickstarter.datasource.contract.CategoryDAO;
import ua.com.goit.gojava7.kickstarter.domain.Category;
import ua.com.goit.gojava7.kickstarter.domain.CategoryStatistic;

@Repository
public class CategoryPostgreDAO implements CategoryDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void clear() {
		entityManager.createNamedQuery("Category.removeAll").executeUpdate();
	}

	@Override
	public Category get(Long index) {
		return entityManager.find(Category.class, index);
	}

	@Override
	@Transactional
	public void add(Category element) {
		entityManager.persist(element);
	}

	@Override
	@Transactional
	public void addAll(List<Category> elements) {
		elements.forEach(entityManager::persist);
	}

	@Override
	public List<Category> getAll() {
		return entityManager.createNamedQuery("Category.getAll", Category.class).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CategoryStatistic> getTopDonated(int limit) {
		String query = "select category.id, category.name, " 
				+ "sum(coalesce(payment.sum,0)) as sum " 
				+ "from category "
				+ "left join project on category.id = project.category_id " 
				+ "left join payment on project.id = payment.project_id "
				+ "group by category.id, category.name " 
				+ "order by sum desc, category.name " 
				+ "limit " + limit;
		Query nativeQuery = entityManager.createNativeQuery(query, CategoryStatistic.class);
		return nativeQuery.getResultList();
	}

}

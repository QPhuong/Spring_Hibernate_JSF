package interview.main.animal.dao.impl;

import interview.main.animal.dao.AnimalDao;
import interview.main.animal.entity.Animal;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("animalDao")
public class AnimalDaoImpl implements AnimalDao {

	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Animal> findAnimalByText(String searchText1, String searchText2) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Animal.class);
		
		if (!StringUtils.isEmpty(searchText1) && !StringUtils.isEmpty(searchText2)) {
			Criterion cr1 = Restrictions.like("text", "%" + searchText1 + "%");
			Criterion cr2 = Restrictions.like("text", "%" + searchText2 + "%");
			criteria.add(Restrictions.or(cr1, cr2));
		}else if (!StringUtils.isEmpty(searchText1)) {
			Criterion cr1 = Restrictions.like("text", "%" + searchText1 + "%");
			criteria.add(cr1);
		}else if (!StringUtils.isEmpty(searchText2)) {
			Criterion cr2 = Restrictions.like("text", "%" + searchText2 + "%");
			criteria.add(cr2);
		}
		
		criteria.addOrder(Order.desc("createdAt"));
		criteria.setMaxResults(20);
		return (List<Animal>) criteria.list();
	}

}

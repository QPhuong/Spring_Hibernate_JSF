package interview.test.animal.dao;

import interview.main.animal.dao.AnimalDao;
import interview.main.animal.entity.Animal;

import java.util.List;

import org.dbunit.IDatabaseTester;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate4.SessionFactoryUtils;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

@ContextConfiguration(locations={
		"classpath*:application-context-test.xml"})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class })
@DatabaseSetup("classpath:animal-test-data.xml")
public class TestAnimalDao extends AbstractTestNGSpringContextTests{

	@Autowired
	@Qualifier("sessionFactory")
    public SessionFactory sessionFactory;
	
 
    @Autowired
    @Qualifier("animalDao")
	private AnimalDao animalDao;
    
    @Autowired
    private IDatabaseTester databaseTester;
    
	@BeforeMethod
    protected void init() throws Exception {
		TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(sessionFactory.openSession()));
	}

	@AfterMethod
    void destroy() {
		SessionHolder sessionHolder = (SessionHolder) TransactionSynchronizationManager.unbindResource(sessionFactory);
		SessionFactoryUtils.closeSession(sessionHolder.getSession());
    }
	
    @Test
	public void testFindDog() throws Exception {
		List<Animal> dogs = animalDao.findAnimalByText("dog", "");
		Assert.assertEquals(dogs.size(),2);
	}

    @Test
    public void testFindCat() throws Exception {
		List<Animal> dogs = animalDao.findAnimalByText("dog", "");
		Assert.assertEquals(dogs.size(),2);
	}
    
    @Test
    public void testFindDogOrCat() throws Exception {
		List<Animal> dogs = animalDao.findAnimalByText("dog", "");
		Assert.assertEquals(dogs.size(),2);
	}

}

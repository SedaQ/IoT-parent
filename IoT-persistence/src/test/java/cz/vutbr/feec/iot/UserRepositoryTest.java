package cz.vutbr.feec.iot;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import cz.vutbr.feec.iot.context.PersistenceApplicationContextTest;
import cz.vutbr.feec.iot.dao.UserRepository;
import cz.vutbr.feec.iot.entity.UserEntity;

/**
 * @author Pavel Šeda (441048)
 *
 */
@ContextConfiguration(classes = PersistenceApplicationContextTest.class)
@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class UserRepositoryTest extends AbstractTestNGSpringContextTests {

//	@PersistenceContext
//	private EntityManager em;
//
//	@Autowired
//	private UserRepository userRepository;
//
//	private UserEntity user1;
//	private UserEntity user2;
//
//	@BeforeMethod
//	public void beforeMethod() {
//		user1 = new UserEntity();
//		user1.setEmail("pavelse123123123da@email.cz");
//		user1.setPasswordHash("pavelasdasd");
//
//		user2 = new UserEntity();
//		user2.setEmail("pavels12313123eda2@email.cz");
//		user2.setPasswordHash("pavel2asdasdasdasds");
//
//	}
//
//	@Test
//	public void testCreate() {
//		userRepository.save(user1);
//		userRepository.save(user2);
//		Assert.assertNotNull(em.find(UserEntity.class, user1.getId()));
//		Assert.assertNotNull(em.find(UserEntity.class, user2.getId()));
//	}
//
//	@Test
//	public void testGetAll() {
//		em.persist(user1);
//		em.persist(user2);
//		Assert.assertEquals(userRepository.findAll().size(), 2);
//	}
//
//	@Test
//	public void testFindByEmail() {
//		em.persist(user1);
//		Assert.assertNotNull(userRepository.findByEmail(user1.getEmail()));
//		Assert.assertEquals(userRepository.findByEmail(user1.getEmail()).getEmail(), user1.getEmail());
//	}
//
//	@Test
//	public void testUpdate() {
//		em.persist(user1);
//		user1.setEmail("pavelsedatempemail@email.cz");
//		em.persist(user1);
//		Assert.assertEquals(em.find(UserEntity.class, user1.getId()).getEmail(), user1.getEmail());
//	}
//
//	@Test
//	public void testRemove() {
//		em.persist(user1);
//		Assert.assertNotNull(em.find(UserEntity.class, user1.getId()));
//		userRepository.delete(user1);
//		Assert.assertNull(em.find(UserEntity.class, user1.getId()));
//	}

}

package cz.vutbr.feec.iot.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import cz.vutbr.feec.iot.config.FacadeConfiguration;
import cz.vutbr.feec.iot.dto.user.UserDTO;
import cz.vutbr.feec.iot.entity.RoleEntity;
import cz.vutbr.feec.iot.entity.UserEntity;
import cz.vutbr.feec.iot.mapping.BeanMapping;

/**
 * @author Pavel Šeda
 *
 */
@ContextConfiguration(classes = FacadeConfiguration.class)
public class BeanMappingTest extends AbstractTestNGSpringContextTests {

//	@Autowired
//	private BeanMapping beanMapping;
//
//	@BeforeClass
//	public void beforeClass() {
//	}
//
//	@Test
//	public void testMapEntityToDTO() {
//		UserEntity userEntity = new UserEntity();
//		userEntity.setId(Long.MAX_VALUE);
//		userEntity.setEmail("psop@email.cz");
//		userEntity.setPasswordHash("123!asodapodoaopsdasd");
//
//		RoleEntity role1 = new RoleEntity();
//		role1.setRole("STANDARD");
//
//		RoleEntity role2 = new RoleEntity();
//		role2.setRole("POWER_USER");
//
//		userEntity.addRole(role1);
//		userEntity.addRole(role2);
//
//		UserDTO dto = beanMapping.mapTo(userEntity, UserDTO.class);
//		assertNotNull(dto);
//		assertEquals(userEntity.getEmail(), dto.getEmail());
//		assertEquals(userEntity.getPasswordHash(), dto.getPasswordHash());
//		assertEquals(userEntity.getRoles().size(), dto.getRoles().size());
//	}

}

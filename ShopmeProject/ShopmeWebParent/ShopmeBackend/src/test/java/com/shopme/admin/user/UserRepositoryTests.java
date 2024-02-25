package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
	
	@Autowired
	private UserRepository repo;
	@Autowired
	private TestEntityManager entityManager;
	@Test
    public void testCreateUserWithOneRole() {
    	Role roleAdmin = entityManager.find(Role.class, 1);
    	User userNamHm=new User("nam@gmail.com","nam2024","Nam","Ha Min");
    	userNamHm.addRole(roleAdmin);
    	User savedUser=repo.save(userNamHm);
    	assertThat(savedUser.getId()).isGreaterThan(0);
    }
	
	@Test
	  public void testCreateUserWithTwoRole() {
		User userRavi=new User("ravi@gmail.com","ravi2024","ravi","kumar");
		Role roleEditor=new Role(3);
		Role roleAssistant=new Role(5);
		userRavi.addRole(roleEditor);
		userRavi.addRole(roleAssistant);
		User savedUser=repo.save(userRavi);
		assertThat(savedUser.getId()).isGreaterThan(0);
	  }

}

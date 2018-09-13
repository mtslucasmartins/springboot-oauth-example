package br.com.ottimizza.auth.dao;

import br.com.ottimizza.auth.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<User, Long> {

    User findByUsername(String username);
}

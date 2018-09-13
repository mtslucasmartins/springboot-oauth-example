package br.com.ottimizza.auth.service;

import br.com.ottimizza.auth.model.User;

import java.util.List;

public interface UserService {

    User save(User user);

    List<User> findAll();

    void delete(long id);
}

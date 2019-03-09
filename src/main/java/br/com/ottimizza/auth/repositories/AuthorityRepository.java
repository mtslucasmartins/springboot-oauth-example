package br.com.ottimizza.auth.repositories;

import br.com.ottimizza.auth.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String> {

    Authority findByName(String name);

}

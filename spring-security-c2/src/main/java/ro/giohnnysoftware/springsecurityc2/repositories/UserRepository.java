package ro.giohnnysoftware.springsecurityc2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.giohnnysoftware.springsecurityc2.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByUsername(String username);
}

package ro.giohnnysoftware.ssc6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.giohnnysoftware.ssc6.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findUserByUsername(String username);
}

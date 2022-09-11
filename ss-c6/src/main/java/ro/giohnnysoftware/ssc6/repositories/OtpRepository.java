package ro.giohnnysoftware.ssc6.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.giohnnysoftware.ssc6.entities.Otp;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, Integer> {
        Optional<Otp> findOtpByUsername(String username);
}

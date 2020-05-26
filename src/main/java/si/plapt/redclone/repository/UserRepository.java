package si.plapt.redclone.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import si.plapt.redclone.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);
}
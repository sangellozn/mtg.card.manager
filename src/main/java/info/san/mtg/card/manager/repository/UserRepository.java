package info.san.mtg.card.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import info.san.mtg.card.manager.model.User;

public interface UserRepository extends JpaRepository<User, String> {

}

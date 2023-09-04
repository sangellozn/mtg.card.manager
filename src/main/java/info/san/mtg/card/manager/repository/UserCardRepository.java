package info.san.mtg.card.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import info.san.mtg.card.manager.model.UserCard;

public interface UserCardRepository extends JpaRepository<UserCard, String> {

}

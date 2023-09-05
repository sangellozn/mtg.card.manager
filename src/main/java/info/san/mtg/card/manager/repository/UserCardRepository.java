package info.san.mtg.card.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import info.san.mtg.card.manager.model.Cards;
import info.san.mtg.card.manager.model.User;
import info.san.mtg.card.manager.model.UserCard;

public interface UserCardRepository extends JpaRepository<UserCard, String> {

	boolean existsByUserAndCard(User user, Cards card);
	
}

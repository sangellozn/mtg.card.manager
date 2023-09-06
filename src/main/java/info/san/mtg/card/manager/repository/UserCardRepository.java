package info.san.mtg.card.manager.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import info.san.mtg.card.manager.model.Cards;
import info.san.mtg.card.manager.model.ConditionEnum;
import info.san.mtg.card.manager.model.User;
import info.san.mtg.card.manager.model.UserCard;

public interface UserCardRepository extends JpaRepository<UserCard, String> {

	boolean existsByUserAndCardAndCondition(User user, Cards card, ConditionEnum condition);
	
	Collection<UserCard> findAllByUserAndCardIn(User user, Collection<Cards> cards);
	
}

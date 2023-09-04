package info.san.mtg.card.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import info.san.mtg.card.manager.model.Cards;

public interface CardsRepository extends JpaRepository<Cards, String> {

}

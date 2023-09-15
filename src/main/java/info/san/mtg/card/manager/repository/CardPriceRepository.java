package info.san.mtg.card.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import info.san.mtg.card.manager.model.CardPrice;

public interface CardPriceRepository extends JpaRepository<CardPrice, String> {

}

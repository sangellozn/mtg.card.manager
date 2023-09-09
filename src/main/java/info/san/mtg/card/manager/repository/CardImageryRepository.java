package info.san.mtg.card.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import info.san.mtg.card.manager.model.CardImagery;

public interface CardImageryRepository extends JpaRepository<CardImagery, String> {

}

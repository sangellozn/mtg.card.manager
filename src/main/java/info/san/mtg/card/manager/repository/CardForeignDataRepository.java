package info.san.mtg.card.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import info.san.mtg.card.manager.model.CardForeignData;

public interface CardForeignDataRepository extends JpaRepository<CardForeignData, String> {

}

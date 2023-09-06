package info.san.mtg.card.manager.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import info.san.mtg.card.manager.model.CardForeignData;

public interface CardForeignDataRepository extends JpaRepository<CardForeignData, String> {
	
	Collection<CardForeignData> findAllByIdUuidInAndIdLanguage(Collection<String> uuids, String language);

}

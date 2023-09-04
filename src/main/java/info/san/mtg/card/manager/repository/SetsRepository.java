package info.san.mtg.card.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import info.san.mtg.card.manager.model.Sets;

public interface SetsRepository extends JpaRepository<Sets, String> {

}

package info.san.mtg.card.manager.repository;

import java.time.Instant;
import java.util.Collection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import info.san.mtg.card.manager.model.Cards;
import info.san.mtg.card.manager.model.projection.SearchResultProjection;

public interface CardsRepository extends JpaRepository<Cards, String> {

	@Query(value = """
			select c.uuid as id,
				'CARDS' as type,
				c.number || ' - ' || cfd.name || ' (' || c.setCode || ')'  as label,
				ci.url_small as urlImage
			from cards c 
			inner join cardForeignData cfd on cfd.uuid = c.uuid 
				and cfd."language" = 'French'
			left join cardsImagery ci on ci.card_uuid = c.uuid
			where cfd.name like lower('%' || :query || '%')
			order by cfd.name
			""", 
			countQuery = """
				select count(*)
				from cards c 
				inner join cardForeignData cfd on cfd.uuid = c.uuid 
					and cfd."language" = 'French'
				where cfd.name like lower('%' || :query || '%')
					""",
			nativeQuery = true)
	Page<SearchResultProjection> search(@Param("query") String query, Pageable page);
	
	Collection<Cards> findAllBySetCodeIn(Collection<String> setCodes);
	
	Collection<Cards> findAllByCardImageryIsNull();
	
	@Query("""
		Select c
		From Cards c
		Left Join c.cardPrice cp
		Where (cp.lastUpdated <= :updatedBefore Or cp Is Null)
			And c.set.code In (
				Select us.code
				From User u
				Inner Join u.sets us
			)
			""")
	Collection<Cards> findAllForPriceUpdate(@Param("updatedBefore") Instant updatedBefore);
	
}

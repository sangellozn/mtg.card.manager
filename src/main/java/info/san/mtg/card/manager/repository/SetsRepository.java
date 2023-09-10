package info.san.mtg.card.manager.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import info.san.mtg.card.manager.model.Sets;
import info.san.mtg.card.manager.model.projection.SearchResultProjection;

public interface SetsRepository extends JpaRepository<Sets, String> {
	
	@Query(value = """
			select code as id,
				'SETS' as type,
				name as label,
				keyruneCode as keyruneCode
			from sets
			where name like lower('%' || :query || '%')
				or code = :query
			order by label
			""", nativeQuery = true)
	Collection<SearchResultProjection> search(@Param("query") String query);

}

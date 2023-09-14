package info.san.mtg.card.manager.repository;

import java.util.Collection;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import info.san.mtg.card.manager.model.Cards;
import info.san.mtg.card.manager.model.User;
import info.san.mtg.card.manager.model.UserCard;
import info.san.mtg.card.manager.model.projection.UserCardStatsProjection;
import info.san.mtg.card.manager.model.projection.UserPossessionProjection;

public interface UserCardRepository extends JpaRepository<UserCard, String> {

	Optional<UserCard> findOneByUserUuidAndCardUuidAndConditionAndType(String userUuid, String cardUuid, String condition, String type);
	
	Collection<UserCard> findAllByUserAndCardIn(User user, Collection<Cards> cards);
	
	@Query(value = """
			select mct.code as typeCode, 
				mc.val as condCode, 
				c.uuid as cardUuid, 
				muc.qte as qte
			from mcmUserCard muc 
			inner join cards c on c.uuid = muc.cards_uuid 
			inner join mcmCardType mct on mct.code = muc.card_type 
			inner join mcmCondition mc on mc.val = muc.cond 	
			where c.setCode = :setCode
				and muc.mcmUser_uuid = :userUuid
			""", nativeQuery = true)
	Collection<UserPossessionProjection> findAllByUserUuidAndSetCode(@Param("userUuid") String userUuid, @Param("setCode") String setCode);
	
	@Query(value = """
			select mus.sets_code as code,
				s.name as setName,
				s.totalSetSize as totalCards,
				sum(case when muc.qte > 0 then 1 else 0 end) as possessedCards
			from mcmUser_sets mus
			inner join "sets" s on s.code = mus.sets_code
			inner join cards c on c.setCode = s.code 
			inner join mcmUserCard muc on muc.cards_uuid = c.uuid 
			where mus.mcmUser_uuid = :userUuid
			group by mus.sets_code, s.name, s.totalSetSize
			""", nativeQuery = true)
	Collection<UserCardStatsProjection> findAllStatsByUserUuid(@Param("userUuid") String userUuid);
	
}

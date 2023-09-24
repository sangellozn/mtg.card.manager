package info.san.mtg.card.manager.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import info.san.mtg.card.manager.model.PricesHistory;

public interface PricesHistoryRepository extends JpaRepository<PricesHistory, String> {
	
	@Modifying
	@Query(value = """
			INSERT INTO pricesHistory 
			(setCode, val_eur, val_usd, date_history)
			select c.setCode,
				sum(cp.val_eur * muc.qte),
				sum(cp.val_usd * muc.qte),
				:dateHistory
			from cardsPrices cp 
			inner join cards c on c.uuid = cp.card_uuid
			inner join mcmUserCard muc on muc.cards_uuid = c.uuid 
			group by c.setCode
			""",
			nativeQuery = true)
	public int createHistory(@Param("dateHistory") LocalDate dateHistory);

}

package info.san.mtg.card.manager.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import info.san.mtg.card.manager.mapper.SearchResultMapper;
import info.san.mtg.card.manager.repository.CardsRepository;
import info.san.mtg.card.manager.repository.SetsRepository;
import info.san.mtg.card.manager.rest.dto.model.SearchResultDto;
import info.san.mtg.card.manager.service.ISearchService;

@Service
public class SearchServiceImpl implements ISearchService {
	
	private final CardsRepository cardsRepository;
	
	private final SetsRepository setsRepository;
	
	private final SearchResultMapper searchResultMapper;
	
	public SearchServiceImpl(CardsRepository cardsRepository, SetsRepository setsRepository, SearchResultMapper searchResultMapper) {
		this.cardsRepository = cardsRepository;
		this.setsRepository = setsRepository;
		this.searchResultMapper = searchResultMapper;
	}

	@Override
	@Transactional(readOnly = true)
	public SearchResultDto search(String query) {
		SearchResultDto result = new SearchResultDto();
		
		if (StringUtils.isBlank(query)) {
			return result;
		}
		
		result.addAll(setsRepository.search(query)
				.stream()
				.map(searchResultMapper::map)
				.toList());
		
		result.addAll(cardsRepository.search(query, PageRequest.of(0, 20))
				.stream()
				.map(searchResultMapper::map)
				.toList());
		
		return result;
	}

}

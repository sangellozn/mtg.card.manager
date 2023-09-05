package info.san.mtg.card.manager.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import info.san.mtg.card.manager.rest.dto.model.SearchResultDto;
import info.san.mtg.card.manager.service.ISearchService;

@RestController
@RequestMapping("api/search")
public class SearchController {
	
	private final ISearchService searchService;
	
	public SearchController(ISearchService searchService) {
		this.searchService = searchService;
	}

	@GetMapping
	public SearchResultDto search(@RequestParam("query") String query) {
		return searchService.search(query);
	}

}

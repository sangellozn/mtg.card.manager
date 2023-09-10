package info.san.mtg.card.manager.rest.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import info.san.mtg.card.manager.rest.dto.model.admin.CardImagerySetsUpdateDto;
import info.san.mtg.card.manager.service.IAdministrationService;

@RestController
@RequestMapping("api/admin")
public class AdministrationController {
	
	private final IAdministrationService administrationService;
	
	public AdministrationController(IAdministrationService administrationService) {
		this.administrationService = administrationService;
	}

	@PostMapping("cards/update-imagery")
	public void updateCardsImagery(@RequestBody CardImagerySetsUpdateDto cardImagerySetsUpdateDto) {
		administrationService.updateCardImagery(false, cardImagerySetsUpdateDto);
	}
	
}

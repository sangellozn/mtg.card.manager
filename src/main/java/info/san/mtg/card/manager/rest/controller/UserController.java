package info.san.mtg.card.manager.rest.controller;

import java.util.Collection;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import info.san.mtg.card.manager.rest.dto.model.AddCardDto;
import info.san.mtg.card.manager.rest.dto.model.UserDto;
import info.san.mtg.card.manager.rest.dto.model.sets.SetDto;
import info.san.mtg.card.manager.rest.dto.model.sets.UserSetDto;
import info.san.mtg.card.manager.service.IUserService;

@RestController
@RequestMapping("api/users")
public class UserController {
	
	private final IUserService userService;
	
	public UserController(IUserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public Collection<UserDto> findAll() {
		return userService.findAll();
	}
	
	@PostMapping("{uuid}/cards/{cardUuid}")
	public void addCard(@PathVariable("uuid") String uuid, @PathVariable("cardUuid") String cardUuid) {
		userService.addCard(uuid, cardUuid);
	}
	
	@PostMapping("{uuid}/cards")
	public void addCard(@PathVariable("uuid") String uuid, @RequestBody @Validated AddCardDto addCardDto) {
		userService.addCard(uuid, addCardDto);
	}
	
	@GetMapping("{uuid}/sets")
	public Collection<SetDto> findAllSets(@PathVariable("uuid") String uuid) {
		return userService.findAllSets(uuid);
	}
	
	@GetMapping("{uuid}/sets/{code}")
	public UserSetDto getUserSet(@PathVariable("uuid") String uuid, @PathVariable("code") String code) {
		return userService.getUserSet(uuid, code);
	}
	
	@PostMapping("{uuid}/sets/{code}")
	public void addSet(@PathVariable("uuid") String uuid, @PathVariable("code") String code) {
		userService.addSet(uuid, code);
	}
	
}

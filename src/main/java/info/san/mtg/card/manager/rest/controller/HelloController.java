package info.san.mtg.card.manager.rest.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/hello")
public class HelloController {

	@GetMapping
	public Map<String, String> sayHello() {
		return Map.of("hello", "world");
	}
	
}

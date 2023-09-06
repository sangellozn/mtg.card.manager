package info.san.mtg.card.manager.service.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import info.san.mtg.card.manager.exception.AlreadyExistsException;
import info.san.mtg.card.manager.mapper.CardForeignDataMapper;
import info.san.mtg.card.manager.mapper.SetMapper;
import info.san.mtg.card.manager.mapper.UserCardMapper;
import info.san.mtg.card.manager.mapper.UserMapper;
import info.san.mtg.card.manager.mapper.UserSetMapper;
import info.san.mtg.card.manager.model.CardForeignData;
import info.san.mtg.card.manager.model.Cards;
import info.san.mtg.card.manager.model.ConditionEnum;
import info.san.mtg.card.manager.model.Sets;
import info.san.mtg.card.manager.model.User;
import info.san.mtg.card.manager.model.UserCard;
import info.san.mtg.card.manager.repository.CardForeignDataRepository;
import info.san.mtg.card.manager.repository.CardsRepository;
import info.san.mtg.card.manager.repository.SetsRepository;
import info.san.mtg.card.manager.repository.UserCardRepository;
import info.san.mtg.card.manager.repository.UserRepository;
import info.san.mtg.card.manager.rest.dto.model.AddCardDto;
import info.san.mtg.card.manager.rest.dto.model.UserDto;
import info.san.mtg.card.manager.rest.dto.model.sets.SetDto;
import info.san.mtg.card.manager.rest.dto.model.sets.UserSetDto;
import info.san.mtg.card.manager.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	
	private final UserRepository userRepository;
	
	private final SetsRepository setsRepository;
	
	private final CardsRepository cardsRepository;
	
	private final UserCardRepository userCardRepository;
	
	private final CardForeignDataRepository cardForeignDataRepository;
	
	private final UserMapper userMapper;
	
	private final SetMapper setMapper;
	
	private final UserSetMapper userSetMapper;
	
	private final UserCardMapper userCardMapper;
	
	private final CardForeignDataMapper cardForeignDataMapper;
	
	public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, SetsRepository setsRepository, 
			SetMapper setMapper, CardsRepository cardsRepository, UserCardRepository userCardRepository, UserSetMapper userSetMapper, 
			UserCardMapper userCardMapper, CardForeignDataRepository cardForeignDataRepository, CardForeignDataMapper cardForeignDataMapper) {
		this.userRepository = userRepository;
		this.setsRepository = setsRepository;
		this.cardsRepository = cardsRepository;
		this.userCardRepository = userCardRepository;
		this.userMapper = userMapper;
		this.setMapper = setMapper;
		this.userSetMapper = userSetMapper;
		this.userCardMapper = userCardMapper;
		this.cardForeignDataRepository = cardForeignDataRepository;
		this.cardForeignDataMapper = cardForeignDataMapper;
	}

	@Override
	@Transactional(readOnly = true)
	public Collection<UserDto> findAll() {
		return userMapper.map(userRepository.findAll(Sort.by("name")));
	}

	@Override
	@Transactional
	public void addSet(String userUuid, String setCode) {
		Sets set = setsRepository.getReferenceById(setCode);
		User user = userRepository.getReferenceById(userUuid);
		
		user.getSets().add(set);
		set.getUsers().add(user);
		
		setsRepository.save(set);
		userRepository.save(user);
	}

	@Override
	@Transactional
	public Collection<SetDto> findAllSets(String userUuid) {
		User user = userRepository.getReferenceById(userUuid);
		
		Map<String, SetDto> setsByCode = user.getSets().stream().collect(Collectors.toMap(Sets::getCode, setMapper::map));
		Set<String> toExclude = new HashSet<>();
		
		setsByCode.forEach((key, set) -> {
			if (set.getParentCode() != null && setsByCode.containsKey(set.getParentCode())) {
				setsByCode.get(set.getParentCode()).getSetsEnfants().add(set);
				toExclude.add(set.getCode());
			}
		});
		
		return setsByCode.values().stream().filter(set -> !toExclude.contains(set.getCode())).toList();
	}

	@Override
	@Transactional
	public void addCard(String userUuid, String cardUuid) {
		User user = userRepository.getReferenceById(userUuid);
		Cards card = cardsRepository.getReferenceById(cardUuid);
		
		if (userCardRepository.existsByUserAndCardAndCondition(user, card, ConditionEnum.M)) {
			throw new AlreadyExistsException("The card is already linked to this user.");
		}
		
		user.getSets().add(card.getSet());
		card.getSet().getUsers().add(user);
		
		UserCard userCard = new UserCard();
		
		userCard.setCard(card);
		userCard.setCondition(ConditionEnum.M);
		userCard.setQte(1);
		userCard.setQteFoil(0);
		userCard.setUser(user);

		setsRepository.save(card.getSet());
		userRepository.save(user);
		userCardRepository.save(userCard);
	}

	@Override
	@Transactional(readOnly = true)
	public UserSetDto getUserSet(String uuid, String code) {
		User user = userRepository.getReferenceById(uuid);
		Sets set = setsRepository.getReferenceById(code);
		
		UserSetDto result = userSetMapper.map(set);

		for (UserCard uc : userCardRepository.findAllByUserAndCardIn(user, set.getCards())) {
			result.getCardByUuid(uc.getCard().getUuid()).getPossessions().add(userCardMapper.map(uc));
		}
		
		for (CardForeignData cfd : cardForeignDataRepository.findAllByIdUuidInAndIdLanguage(set.getCards().stream().map(Cards::getUuid).toList(), "French")) {
			result.getCardByUuid(cfd.getId().getUuid()).setCardForeignData(cardForeignDataMapper.map(cfd));
		}
		
		return result;
	}

	@Override
	@Transactional
	public void addCard(String userUuid, AddCardDto addCardDto) {
		User user = userRepository.getReferenceById(userUuid);
		Cards card = cardsRepository.getReferenceById(addCardDto.getCardUuid());
		
		if (userCardRepository.existsByUserAndCardAndCondition(user, card, addCardDto.getCondition())) {
			throw new AlreadyExistsException("The card is already linked to this user.");
		}
		
		user.getSets().add(card.getSet());
		card.getSet().getUsers().add(user);
		
		UserCard userCard = new UserCard();
		
		userCard.setCard(card);
		userCard.setCondition(addCardDto.getCondition());
		userCard.setQte(addCardDto.getQte());
		userCard.setQteFoil(addCardDto.getQteFoil());
		userCard.setUser(user);

		setsRepository.save(card.getSet());
		userRepository.save(user);
		userCardRepository.save(userCard);
	}

}

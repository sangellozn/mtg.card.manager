package info.san.mtg.card.manager.service.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import info.san.mtg.card.manager.mapper.CardForeignDataMapper;
import info.san.mtg.card.manager.mapper.SetMapper;
import info.san.mtg.card.manager.mapper.UserMapper;
import info.san.mtg.card.manager.mapper.UserSetMapper;
import info.san.mtg.card.manager.model.CardForeignData;
import info.san.mtg.card.manager.model.CardTypeEnum;
import info.san.mtg.card.manager.model.Cards;
import info.san.mtg.card.manager.model.ConditionEnum;
import info.san.mtg.card.manager.model.Sets;
import info.san.mtg.card.manager.model.User;
import info.san.mtg.card.manager.model.UserCard;
import info.san.mtg.card.manager.model.projection.UserPossessionProjection;
import info.san.mtg.card.manager.repository.CardForeignDataRepository;
import info.san.mtg.card.manager.repository.CardsRepository;
import info.san.mtg.card.manager.repository.SetsRepository;
import info.san.mtg.card.manager.repository.UserCardRepository;
import info.san.mtg.card.manager.repository.UserRepository;
import info.san.mtg.card.manager.rest.dto.model.AddCardDto;
import info.san.mtg.card.manager.rest.dto.model.UpdateUserCardInfoDto;
import info.san.mtg.card.manager.rest.dto.model.UserDto;
import info.san.mtg.card.manager.rest.dto.model.sets.SetDto;
import info.san.mtg.card.manager.rest.dto.model.sets.UserSetDto;
import info.san.mtg.card.manager.rest.dto.model.sets.UserSetDto.CardDto.UserCardDto;
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
	
	private final CardForeignDataMapper cardForeignDataMapper;
	
	public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, SetsRepository setsRepository, 
			SetMapper setMapper, CardsRepository cardsRepository, UserCardRepository userCardRepository, UserSetMapper userSetMapper, 
			CardForeignDataRepository cardForeignDataRepository, CardForeignDataMapper cardForeignDataMapper) {
		this.userRepository = userRepository;
		this.setsRepository = setsRepository;
		this.cardsRepository = cardsRepository;
		this.userCardRepository = userCardRepository;
		this.userMapper = userMapper;
		this.setMapper = setMapper;
		this.userSetMapper = userSetMapper;
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
		
		user.getSets().add(card.getSet());
		card.getSet().getUsers().add(user);
		
		UserCard userCard = userCardRepository.findOneByUserUuidAndCardUuidAndConditionAndType(userUuid, cardUuid, ConditionEnum.M.name(), CardTypeEnum.NORMAL.name())
				.orElseGet(UserCard::new);
		
		if (userCard.getCard() == null) {
			userCard.setCard(card);
			userCard.setCondition(ConditionEnum.M.name());
			userCard.setType(CardTypeEnum.NORMAL.name());
			userCard.setUser(user);
		}
		
		userCard.setQte(userCard.getQte() + 1);

		setsRepository.save(card.getSet());
		userRepository.save(user);
		userCardRepository.save(userCard);
	}

	@Override
	@Transactional(readOnly = true)
	public UserSetDto getUserSet(String uuid, String code) {
		Sets set = setsRepository.getReferenceById(code);
		
		UserSetDto result = userSetMapper.map(set);
		result.fillCardPossession();

		for (UserPossessionProjection up : userCardRepository.findAllByUserUuidAndSetCode(uuid, code)) {
			UserCardDto possession = result.getCardByUuid(up.getCardUuid()).getUserCardByType(CardTypeEnum.valueOf(up.getTypeCode()));
			ConditionEnum condition = ConditionEnum.valueOf(up.getCondCode());
			
			switch (condition) {
				case M -> possession.setQteM(up.getQte());
				case NM -> possession.setQteNM(up.getQte());
				case EX -> possession.setQteEX(up.getQte());
				case GD -> possession.setQteGD(up.getQte());
				case LP -> possession.setQteLP(up.getQte());
				case PL -> possession.setQtePL(up.getQte());
				case POOR -> possession.setQtePoor(up.getQte());
				default -> throw new IllegalArgumentException("Unexpected value: " + condition);
			}
		}
		
		result.getCards().removeIf(card -> !NumberUtils.isCreatable(card.getNumber()));
		
		for (CardForeignData cfd : cardForeignDataRepository.findAllByIdUuidInAndIdLanguage(set.getCards().stream().map(Cards::getUuid).toList(), "French")) {
			result.getCardByUuid(cfd.getId().getUuid()).setCardForeignData(cardForeignDataMapper.map(cfd));
		}
		
		Collections.sort(result.getCards(), (left, right) -> {
			return Integer.valueOf(left.getNumber()).compareTo(Integer.valueOf(right.getNumber()));
		});
		
		return result;
	}

	@Override
	@Transactional
	public void addCard(String userUuid, AddCardDto addCardDto) {
		User user = userRepository.getReferenceById(userUuid);
		Cards card = cardsRepository.getReferenceById(addCardDto.getCardUuid());
		
		user.getSets().add(card.getSet());
		card.getSet().getUsers().add(user);
		
		UserCard userCard = userCardRepository.findOneByUserUuidAndCardUuidAndConditionAndType(userUuid, addCardDto.getCardUuid(), ConditionEnum.M.name(), CardTypeEnum.NORMAL.name())
				.orElseGet(UserCard::new);
		
		userCard.setCard(card);
		userCard.setCondition(addCardDto.getCondition().name());
		userCard.setType(addCardDto.getType().name());
		userCard.setQte(addCardDto.getQte());
		userCard.setUser(user);

		setsRepository.save(card.getSet());
		userRepository.save(user);
		userCardRepository.save(userCard);
	}

	@Override
	@Transactional
	public void updateUserCardInfo(String uuid, UpdateUserCardInfoDto updateUserCardInfoDto) {
		User user = userRepository.getReferenceById(uuid);
		Cards card = cardsRepository.getReferenceById(updateUserCardInfoDto.getCardUuid());
		
		for (ConditionEnum condition : Arrays.stream(ConditionEnum.values()).filter(e -> e != ConditionEnum.UNKNOWN).toList()) {
			UserCard userCard = userCardRepository.findOneByUserUuidAndCardUuidAndConditionAndType(uuid, updateUserCardInfoDto.getCardUuid(), condition.name(), updateUserCardInfoDto.getType())
					.orElseGet(UserCard::new);
			
			if (userCard.getCard() == null) {
				user.getUserCards().add(userCard);
				
				userCard.setCard(card);
				userCard.setCondition(condition.name());
				userCard.setType(updateUserCardInfoDto.getType());
				userCard.setUser(user);
			}
			
			switch (condition) {
				case M -> userCard.setQte(updateUserCardInfoDto.getQteM());
				case EX -> userCard.setQte(updateUserCardInfoDto.getQteEX());
				case GD -> userCard.setQte(updateUserCardInfoDto.getQteGD());
				case NM -> userCard.setQte(updateUserCardInfoDto.getQteNM());
				case LP -> userCard.setQte(updateUserCardInfoDto.getQteLP());
				case PL -> userCard.setQte(updateUserCardInfoDto.getQtePL());
				case POOR -> userCard.setQte(updateUserCardInfoDto.getQtePoor());
				default -> throw new IllegalArgumentException("Unexpected value: " + condition);
			}
			
			userCardRepository.save(userCard);
		}
		
		userRepository.save(user);
	}

}

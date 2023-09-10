package info.san.mtg.card.manager.service;

import info.san.mtg.card.manager.rest.dto.model.admin.CardImagerySetsUpdateDto;

public interface IAdministrationService {
	
	/**
	 * Permet la mise à jour de l'imagerie des cartes.
	 * 
	 * @param force {@code true} si l'on doit re-télécharger les images pour les cartes ayant déjà un 
	 * enregistrement dans la talbe cardImagery, {@code false} sinon.
	 * 
	 * @param liste des sets à mettre à jour. Dans le cas où la liste est vide, tous les sets sont concernés.
	 */
	void updateCardImagery(boolean force, CardImagerySetsUpdateDto cardImagerySetsUpdateDto);

}

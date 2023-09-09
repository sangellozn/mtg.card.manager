package info.san.mtg.card.manager.service;

public interface IAdministrationService {
	
	/**
	 * Permet la mise à jour de l'imagerie des cartes.
	 * 
	 * @param force {@code true} si l'on doit re-télécharger les images pour les cartes ayant déjà un 
	 * enregistrement dans la talbe cardImagery, {@code false} sinon.
	 */
	void updateCardImagery(boolean force);

}

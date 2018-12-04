package fr.adaming.service;

import java.util.List;


import fr.adaming.model.Client;
import fr.adaming.model.Commande;


public interface ICommandeService {
	
	public List<Commande> getAllCom();
	
	public List<Commande> getAllComByClient(Client c);    // Historique des commandes du client
	
	public Commande getCom(Commande com, Client c);
	
	public Commande addCom(Commande com, Client c);
	
	public int updateCom(Commande com, Client c);
	
	public int deleteCom(Commande com, Client c);     // ?? genre il peut supprimer jusqu'� 2 jours apr�s la date de la commande

}

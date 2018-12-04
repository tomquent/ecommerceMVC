package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IClientDao;
import fr.adaming.dao.ICommandeDao;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;

@Service("comService")
@Transactional
public class CommandeServiceImpl implements ICommandeService {

	// Asso UML en JAVA
	@Autowired
	private ICommandeDao comDao;

	// Asso UML en JAVA
	@Autowired
	private IClientDao clDao;

	public void setComDao(ICommandeDao comDao) {
		this.comDao = comDao;
	}

	public void setClDao(IClientDao clDao) {
		this.clDao = clDao;
	}

	// METHODES

	// M�thode getAllCommande

	@Override
	public List<Commande> getAllCom() {
		return comDao.getAllCom();
	}

	// M�thode getAllComByClient

	@Override
	public List<Commande> getAllComByClient(Client c) {
		return comDao.getAllComByClient(c);
	}

	// M�thode getCommande
	@Override
	public Commande getCom(Commande com, Client c) {
		Client cl1 = clDao.getClient(c);
		Client cl2 = com.getClient();
		if (cl1 == cl2) {
			Commande comOut = comDao.getCom(com);
			return comDao.getCom(comOut);
		} else {
			return null;
		}
	}

	// M�thode addCommande
	@Override
	public Commande addCom(Commande com, Client c) {
		// Lier la commande au client
		com.setClient(c);
		return comDao.addCom(com);
	}

	// M�thode updateCommande
	@Override
	public int updateCom(Commande com, Client c) {
		Client cl = clDao.getClient(c);
		if (cl.getId() == com.getClient().getId()) {
			Commande comOut = comDao.getCom(com);
			return comDao.updateCom(comOut);
		} else {
			return 0;
		}
	}

	// M�thode deleteCommande
	@Override
	public int deleteCom(Commande com, Client c) { // Faire que le client ne peut supprimer sa commande que 1j apr�s
													// dans MB
		if (com.getClient().getId() == c.getId()) {
			return comDao.deleteCom(com);
		} else {
			return 0;
		}

	}

}

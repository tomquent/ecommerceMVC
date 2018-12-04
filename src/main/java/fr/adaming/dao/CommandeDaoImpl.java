package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.Produit;


@Repository
public class CommandeDaoImpl implements ICommandeDao {

	// Transformation de l'association UML en JAVA
	@Autowired
	private SessionFactory sf;
	
	// Setter pour l'injection de d�pendance (m�me si avec @autowired c'est pas n�cessaire normalement)
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	// ****************** M�thodes *****************

	// M�thode getAllCommande

	@Override
	public List<Commande> getAllCom() {
		Session s = sf.getCurrentSession();
		String req = "FROM Commande c";
		Query query = s.createQuery(req);
		return query.list();
	}
	
	// M�thode getAllCommandeByClient
	
	@Override
	public List<Commande> getAllComByClient(Client c) {
		Session s = sf.getCurrentSession();
		
		// Requete HQL et passage des params
		String req = "FROM Commande c WHERE c.client.id=:pIdCl";
		Query query = s.createQuery(req);
		
		query.setParameter("pIdCl", c.getId());
		
		return query.list();
	}

	// M�thode getCommande
	@Override
	public Commande getCom(Commande com) {
		Session s = sf.getCurrentSession();
		
		// Requete HQL et passage des params
		String req = "FROM Commande c WHERE c.id=:pId";
		Query query = s.createQuery(req);
		query.setParameter("pId", com.getIdCom());
		
		return (Commande) query.uniqueResult();
	}

	// M�thode addCommande
	@Override
	public Commande addCom(Commande com) {
		Session s =sf.getCurrentSession();
		
		s.save(com);
	
		return com;
	}

	// M�thode updateCommande
	@Override
	public int updateCom(Commande com) {
		Session s =sf.getCurrentSession();
		
		// Requete HQL et passage des params
		String req = "UPDATE Commande c SET c.date=:pDate, c.client=:pClient, c.lignesCommandes=:pLc WHERE c.idCom=:pCId";
		Query query=s.createQuery(req);		

		query.setParameter("pDate" , com.getDate());    
		query.setParameter("pClient" , com.getClient());
		query.setParameter("pLc" , com.getLignesCommandes());
		query.setParameter("pCId", com.getIdCom());
		
		return query.executeUpdate();
	}

	// M�thode deleteCommande
	@Override
	public int deleteCom(Commande com) {
		Session s =sf.getCurrentSession();
		
		// Requete HQL (et donc Query de HQL!!) et passage des params // REQUETE HQL obligatoire pour delete CAR s.delete retourne void donc on peut pas savoir si il le fait
		String req="DELETE Commande c WHERE c.idCom=:pCId";
		Query query=s.createQuery(req);	

		query.setParameter("pCId" , com.getIdCom());  

		return query.executeUpdate();
	}

}

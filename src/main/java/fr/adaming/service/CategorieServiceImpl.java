package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.service.ICategorieService;
import fr.adaming.dao.ICategorieDao;
import fr.adaming.model.Categorie;

@Service("catService")
@Transactional
public class CategorieServiceImpl implements ICategorieService {

	// Association UML en JAVA
	@Autowired
	private ICategorieDao catDao;

	public void setCatDao(ICategorieDao catDao) {
		this.catDao = catDao;
	}

	// M�thode

	// M�thode getAllCategorie
	@Override
	public List<Categorie> getAllCategories() {

		if (catDao.getAllCategories() != null) {
			return catDao.getAllCategories();
		} else {
			return null;
		}
	}

	// M�thode getCategorie
	@Override
	public Categorie getCategorie(Categorie cat) {
		return catDao.getCategorie(cat);
	}

	// M�thode addCategorie
	@Override
	public Categorie addCategorie(Categorie cat) {
		return catDao.addCategorie(cat);

	}

	// M�thode updateCategorie
	@Override
	public int updateCategorie(Categorie cat) {
		return catDao.updateCategorie(cat);
	}

	// M�thode deleteCategorie
	@Override
	public int deleteCategorie(Categorie cat) {
		return catDao.deleteCategorie(cat);
	}
}

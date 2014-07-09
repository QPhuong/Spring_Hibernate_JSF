package interview.main.animal.service.impl;

import interview.main.animal.dao.AnimalDao;
import interview.main.animal.entity.Animal;
import interview.main.animal.service.AnimalService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("animalService")
public class AnimalServiceImpl implements AnimalService {

	@Autowired
	private AnimalDao animalDao;
	
	@Transactional
	@Override
	public List<Animal> searchAnimalByText(String searchText1, String searchText2) {
		return animalDao.findAnimalByText(searchText1, searchText2);
	}

}

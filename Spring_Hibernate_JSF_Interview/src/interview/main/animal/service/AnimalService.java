package interview.main.animal.service;

import interview.main.animal.entity.Animal;

import java.util.List;

public interface AnimalService {

	public List<Animal> searchAnimalByText(String searchText1, String searchText2);
}

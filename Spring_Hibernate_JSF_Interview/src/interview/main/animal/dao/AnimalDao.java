package interview.main.animal.dao;

import interview.main.animal.entity.Animal;

import java.util.List;


public interface  AnimalDao {

	public List<Animal> findAnimalByText(String searchText1, String searchText2);
}

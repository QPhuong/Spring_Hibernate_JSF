package interview.main.animal.bean;

import interview.main.animal.entity.Animal;
import interview.main.animal.service.AnimalService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean(name="animalBean")
@ViewScoped
public class AnimalManagedBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -572900227696121887L;

	@ManagedProperty(value="#{animalService}")
	AnimalService animalService;
	
	private String searchText;
	List<Animal> animalList;
	
	@PostConstruct
	public void initialize() {
		searchText = "dog";
		this.getAnimal(searchText);
	}
	
	public void getAnimal(String searchText) {
		this.searchText = searchText;
		animalList = searchAnimal(searchText);
	}
	
	public void refresh() {
		animalList = searchAnimal(this.searchText);
	}
	
	private List<Animal> searchAnimal(String searchText) {
		switch(searchText.trim().toLowerCase()) {
		case "dog":
			return animalService.searchAnimalByText("dog", "");
		case "cat":
			return animalService.searchAnimalByText("cat", "");
		case "both":
			return animalService.searchAnimalByText("dog", "cat");
		default:
			return new ArrayList<Animal>();
		}
	}
	public List<Animal> getAnimalList() {
		return animalList;
	}
	public void setAnimalList(List<Animal> animalList) {
		this.animalList = animalList;
	}
	public AnimalService getAnimalService() {
		return animalService;
	}
	public void setAnimalService(AnimalService animalService) {
		this.animalService = animalService;
	}
	
	
}

package es.uniovi.asw.business;

import java.util.List;

import es.uniovi.asw.model.Category;

public interface CategoryService {

	//CRUD
	public Category addCategory (Category category);
	public void deleteCategory (Category category);
	public void updateCategory (Category category);
	public Category findCategory (Long categoryId);
	
	//Otros
	public List<Category> findAllCategories();
}
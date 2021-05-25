package com.devsuperior.dscatalog.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.entities.Category;
import com.devsuperior.dscatalog.repositories.CategoryRepository;
import com.devsuperior.dscatalog.services.exceptions.EntityNotFoundException;

/* Esta notation já prepara para determinar que o scrping vai gerenciar 
 * Outras opções seriam: @Component  @Repository
 * 
 * */

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository repository; 
	
	@Transactional( readOnly = true )
	public List<CategoryDTO> findAll(){
		List<Category> list = repository.findAll();
		List<CategoryDTO> ListDto = list.stream().map( x  -> new CategoryDTO(x) ).collect(Collectors.toList());
		/* o código acima do map faz exatamente o que faria nas linhas abaixo que estão em comentário.
		List<CategorDTO> listDto = new ArrayList<>();
		for( Category cat: list ) {
			listDto.add( new CategoryDTO(cat));
		}
		*/
		return ListDto;
	}
	@Transactional( readOnly = true )
	public CategoryDTO findById(Long id) {
		Optional <Category> obj = repository.findById(id);
		Category entity = obj.orElseThrow( ()->  new EntityNotFoundException("Entity not found"));
		return new CategoryDTO(entity);
	}
	@Transactional
	public CategoryDTO insert(CategoryDTO dto) {
		Category entity = new Category();
		entity.setName( dto.getName());
		entity = repository.save(entity);
		return new CategoryDTO( entity);
	}
}

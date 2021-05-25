package com.devsuperior.dscatalog.resources;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devsuperior.dscatalog.dto.CategoryDTO;
import com.devsuperior.dscatalog.services.CategoryService;

@RestController
@RequestMapping (value = "/categories")
public class CategoryResource {
	
	@Autowired
	private CategoryService service;
	@GetMapping
	public ResponseEntity <List<CategoryDTO> >findAll(){
		
		/*List<Category> list = new ArrayList<>();
		list.add(new Category ( 1L, "BOOKS"));
		list.add(new Category ( 2L, "ELECTRONICS"));
		*/
		List<CategoryDTO> list = service.findAll();
		return  ResponseEntity.ok().body(list);
		
	}
	
	@GetMapping( value="/{id}")
	public ResponseEntity <CategoryDTO >findById(@PathVariable Long id){
		
		CategoryDTO dto = service.findById(id);
		return  ResponseEntity.ok().body(dto);
		
	}
	@PostMapping
	public ResponseEntity <CategoryDTO > insert( @RequestBody CategoryDTO dto){
		dto = service.insert(dto);
		// O comando abaixo tem objetivo gerar uma url de consulta que será retornado no 
		// header da requisição com código 201 - created. 
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand( dto.getId()).toUri();
	    return  ResponseEntity.created(uri).body(dto);
	}
	
	@PutMapping( value = "/{id}")
	public ResponseEntity <CategoryDTO > update(@PathVariable Long id, @RequestBody CategoryDTO dto){
		dto = service.update(id, dto);
		//URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand( dto.getId()).toUri();
		return  ResponseEntity.ok().body(dto);
	}
	@DeleteMapping( value = "/{id}")
	public ResponseEntity <Void> detete(@PathVariable Long id){
		// como não tem corpo na requisição, então o retorno é Void
		service.delete(id);
		return  ResponseEntity.noContent().build();
	}

	
}

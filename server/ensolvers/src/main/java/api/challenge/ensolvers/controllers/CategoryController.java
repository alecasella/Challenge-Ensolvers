package api.challenge.ensolvers.controllers;

import api.challenge.ensolvers.dto.CategoryDTO;
import api.challenge.ensolvers.exceptions.ResourceNotFoundException;
import api.challenge.ensolvers.services.CategoryServiceImp;
import api.challenge.ensolvers.services.interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    @Autowired
    ICategoryService categoryService;

    @PostMapping("/{note_id}")
    public ResponseEntity<CategoryDTO> saveCategory(@PathVariable(value="note_id") int note_id, @RequestBody CategoryDTO categoryDTO) throws ResourceNotFoundException {
        return new ResponseEntity<>(categoryService.save(note_id, categoryDTO), HttpStatus.OK);
    }

    @GetMapping("/{note_id}")
    public ResponseEntity<List<CategoryDTO>> getCategoriesByNoteId(@PathVariable(value="note_id") int note_id) throws ResourceNotFoundException{
        return new ResponseEntity<>(categoryService.getCategoriesByNoteId(note_id), HttpStatus.OK);
    }
}

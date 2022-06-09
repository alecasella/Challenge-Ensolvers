package api.challenge.ensolvers.services;

import api.challenge.ensolvers.dto.CategoryDTO;
import api.challenge.ensolvers.dto.NoteDTO;
import api.challenge.ensolvers.exceptions.ResourceNotFoundException;
import api.challenge.ensolvers.models.Category;
import api.challenge.ensolvers.models.Note;
import api.challenge.ensolvers.repositories.CategoryRepository;
import api.challenge.ensolvers.repositories.NoteRepository;
import api.challenge.ensolvers.services.interfaces.ICategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImp implements ICategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    ModelMapper modelMapper;


    @Override
    public List<CategoryDTO> getAll() {
        List<Category> categories = categoryRepository.findAll();

        return categories.stream().map(category -> modelMapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
    }

    public CategoryDTO save(int note_id, CategoryDTO categoryDTO) throws ResourceNotFoundException {
        Category category = modelMapper.map(categoryDTO, Category.class);

        Note note = noteRepository.findById(note_id)
                .orElseThrow(() -> new ResourceNotFoundException("The note doesnt exists"));

        category.setNote(note);

        category = categoryRepository.save(category);

        return modelMapper.map(category, CategoryDTO.class);

    }

    @Override
    public List<CategoryDTO> getCategoriesByNoteId(int note_id) throws ResourceNotFoundException {
        List<Category> categories = categoryRepository.findByNoteId(note_id);

        if(categories.isEmpty()) throw new ResourceNotFoundException("Not founds categories with that id");

        return categories.stream().map(category -> modelMapper.map(category, CategoryDTO.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteCategory(int category_id) {
        categoryRepository.deleteById(category_id);
    }

}

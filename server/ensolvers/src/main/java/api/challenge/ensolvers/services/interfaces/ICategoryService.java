package api.challenge.ensolvers.services.interfaces;

import api.challenge.ensolvers.dto.CategoryDTO;
import api.challenge.ensolvers.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ICategoryService {
    CategoryDTO save(int note_id, CategoryDTO categoryDTO) throws ResourceNotFoundException;

    List<CategoryDTO> getCategoriesByNoteId(int note_id) throws ResourceNotFoundException ;

    void deleteCategory(int category_id);

    List<CategoryDTO> getAll();
}

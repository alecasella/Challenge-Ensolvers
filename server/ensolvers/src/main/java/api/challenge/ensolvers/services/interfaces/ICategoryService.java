package api.challenge.ensolvers.services.interfaces;

import api.challenge.ensolvers.dto.CategoryDTO;
import api.challenge.ensolvers.exceptions.ResourceNotFoundException;

public interface ICategoryService {
    CategoryDTO save(int note_id, CategoryDTO categoryDTO) throws ResourceNotFoundException;
}

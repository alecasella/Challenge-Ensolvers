package api.challenge.ensolvers.repositories;

import api.challenge.ensolvers.models.Category;
import api.challenge.ensolvers.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByNoteId(int note_id);

    Category findByDescription(String description);
}

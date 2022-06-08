package api.challenge.ensolvers.repositories;

import api.challenge.ensolvers.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Integer> {
}

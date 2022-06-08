package api.challenge.ensolvers.repositories;

import api.challenge.ensolvers.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Integer> {
    Optional<Note> findByTitle(String title);

}

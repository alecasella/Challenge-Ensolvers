package api.challenge.ensolvers.services.interfaces;

import api.challenge.ensolvers.dto.NoteDTO;
import api.challenge.ensolvers.exceptions.AlreadyExistsException;
import api.challenge.ensolvers.exceptions.ResourceNotFoundException;

import java.util.List;

public interface INoteService {
    List<NoteDTO> getAll();

    NoteDTO addNote(NoteDTO noteDTO) throws AlreadyExistsException;

    NoteDTO findNoteById(int note_id) throws ResourceNotFoundException;

    void updateNote(int note_id, NoteDTO updatedNote) throws ResourceNotFoundException;

    void deleteNote(int note_id) throws ResourceNotFoundException;

    List<NoteDTO> getNoteByCategoryDescription(String description) throws ResourceNotFoundException;
}

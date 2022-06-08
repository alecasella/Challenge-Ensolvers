package api.challenge.ensolvers.services.interfaces;

import api.challenge.ensolvers.dto.NoteDTO;
import api.challenge.ensolvers.exceptions.AlreadyExistsException;

import java.util.List;

public interface INoteService {
    List<NoteDTO> getAll();

    NoteDTO addNote(NoteDTO noteDTO) throws AlreadyExistsException;
}

package api.challenge.ensolvers.services;

import api.challenge.ensolvers.dto.NoteDTO;
import api.challenge.ensolvers.exceptions.AlreadyExistsException;
import api.challenge.ensolvers.exceptions.ResourceNotFoundException;
import api.challenge.ensolvers.models.Category;
import api.challenge.ensolvers.models.Note;
import api.challenge.ensolvers.repositories.CategoryRepository;
import api.challenge.ensolvers.repositories.NoteRepository;
import api.challenge.ensolvers.services.interfaces.INoteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoteServiceImp implements INoteService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<NoteDTO> getAll() {
        List<Note> notes = noteRepository.findAll();

        return notes.stream().map(note -> modelMapper.map(note, NoteDTO.class)).collect(Collectors.toList());
    }

    @Override
    public NoteDTO addNote(NoteDTO noteDTO) throws AlreadyExistsException {
        Note note = modelMapper.map(noteDTO, Note.class);

        if(noteRepository.findByTitle(note.getTitle()).isPresent()){
            throw new AlreadyExistsException("The title of the note is already taken");
        }
        note.setState(true);

        Note newNote = noteRepository.save(note);

        return modelMapper.map(newNote, NoteDTO.class);
    }

    @Override
    public NoteDTO findNoteById(int note_id) throws ResourceNotFoundException {
        Note note = noteRepository.findById(note_id)
                .orElseThrow(() -> new ResourceNotFoundException("The note doesnt exists"));

        return modelMapper.map(note, NoteDTO.class);
    }

    @Override
    public void updateNote(int note_id, NoteDTO updatedNote) throws ResourceNotFoundException {
        Note note = noteRepository.findById(note_id)
                .orElseThrow(() -> new ResourceNotFoundException("The note doesnt exists"));


        note.setTitle(updatedNote.getTitle());
        note.setContent(updatedNote.getContent());
        note.setState(updatedNote.getState());

        noteRepository.save(note);
    }

    @Override
    public void deleteNote(int note_id) throws ResourceNotFoundException {
        Note note = noteRepository.findById(note_id)
                .orElseThrow(() -> new ResourceNotFoundException("The note doesnt exists"));

        noteRepository.delete(note);
    }

    @Override
    public List<NoteDTO> getNoteByCategoryDescription(String description) throws ResourceNotFoundException  {

        Category category = categoryRepository.findByDescription(description);

        Note note = noteRepository.findByCategories(category).orElseThrow(() -> new ResourceNotFoundException("The note doesnt exists"));

        List<NoteDTO> aux = new ArrayList<>();
        aux.add(modelMapper.map(note, NoteDTO.class));

        return aux;
    }

}

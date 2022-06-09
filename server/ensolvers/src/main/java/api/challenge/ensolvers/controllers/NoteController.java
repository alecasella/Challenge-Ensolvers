package api.challenge.ensolvers.controllers;

import api.challenge.ensolvers.dto.NoteDTO;
import api.challenge.ensolvers.exceptions.AlreadyExistsException;
import api.challenge.ensolvers.exceptions.ResourceNotFoundException;
import api.challenge.ensolvers.services.NoteServiceImp;
import api.challenge.ensolvers.services.interfaces.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    INoteService noteService;

    @GetMapping
    public ResponseEntity<List<NoteDTO>> getAll(){
        List<NoteDTO> notes = noteService.getAll();

        return notes.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).body(notes) : ResponseEntity.ok(notes);
    }

    @GetMapping("/{note_id}")
    public ResponseEntity<NoteDTO> getNoteById(@PathVariable(value = "note_id") int note_id) throws ResourceNotFoundException{
        return new ResponseEntity<>(noteService.findNoteById(note_id), HttpStatus.OK);
    }

    @PutMapping("/{note_id}")
    public ResponseEntity updateNote(@PathVariable(value = "note_id") int note_id, @RequestBody NoteDTO updatedNote) throws ResourceNotFoundException{
        noteService.updateNote(note_id, updatedNote);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping
    public ResponseEntity<NoteDTO> saveNote(@RequestBody NoteDTO noteDTO) throws AlreadyExistsException {
        return new ResponseEntity<>(noteService.addNote(noteDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{note_id}")
    public ResponseEntity deleteNote(@PathVariable(value="note_id") int note_id) throws ResourceNotFoundException{
        noteService.deleteNote(note_id);
        return new ResponseEntity<>("Delete success", HttpStatus.OK);
    }
}

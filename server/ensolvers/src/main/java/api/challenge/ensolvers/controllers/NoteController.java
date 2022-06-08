package api.challenge.ensolvers.controllers;

import api.challenge.ensolvers.dto.NoteDTO;
import api.challenge.ensolvers.exceptions.AlreadyExistsException;
import api.challenge.ensolvers.services.NoteServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    @Autowired
    NoteServiceImp noteService;

    @GetMapping
    public ResponseEntity<List<NoteDTO>> getAll(){
        List<NoteDTO> notes = noteService.getAll();

        return notes.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).body(notes) : ResponseEntity.ok(notes);
    }

    @PostMapping
    public ResponseEntity<NoteDTO> addNote(@RequestBody NoteDTO noteDTO) throws AlreadyExistsException {
        return new ResponseEntity<>(noteService.addNote(noteDTO), HttpStatus.OK);
    }

}

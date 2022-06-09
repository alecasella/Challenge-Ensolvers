import React, { useContext, useEffect, useState } from "react";
import Axios from 'axios'
import { useNavigate } from "react-router-dom";
import {
     Container
} from 'reactstrap';


const Available_Notes = () => {
    const [notes, setNotes] = useState([]);

    const navigate = useNavigate();

    useEffect(() => {
          getAllNotes();
      },[])
    
      const getAllNotes = () => {
        try {
          Axios.get(`http://localhost:8080/api/notes`).then((resp => {
              setNotes(resp.data.filter(note => note.state));
          }))
        } catch (e) { console.log(e); }
      }
    
    const addNote = (e) => {
        e.preventDefault();

        navigate(`/add-update-note`);
    
    }

    const editNote = (id) => {
        
        navigate(`/add-update-note/${id}`);
    
    }

    const archivedNotes = (e) => {
        e.preventDefault();
        navigate(`/archived_notes`);
    
    }

    const archiveNote = (id, title, content, state) => {
        const result = window.confirm('Are you sure you want to archive this note?');
        const newState = state ? false : true;
        const note = {
            title: title,
            content: content,
            state: newState
        }
        if (result) {
            try {
                    Axios.put(`http://localhost:8080/api/notes/${id}`, note).then((resp => {
                        getAllNotes();
                }))
            } catch (e) { console.log(e); }      
         } 
    }

    return (
        <Container>

            <div className="row m-1">
                <div className="col">
                    <div className="h1 text-center mx-auto mt-4">
                        <u> My Notes</u>
                        <button onClick={addNote} type="button" className="btn btn-success ms-4">Create Note</button>
                        <button onClick={archivedNotes} type="button" className="btn btn-link">Archived Notes</button>

                    </div>
                </div>
            </div>

            <div className="p-2 mx-4 border-black-25 border-bottom" />
            {
                notes != "" ?
                    (
                        notes.map((e, i) => (
                            <div className="row mx-1 px-5 pb-3 w-60" key={i}>
                                <div className="col mx-auto">
                                    <div className="row px-3 align-items-center todo-item rounded">

                                        <div className="col px-1 m-1 d-flex align-items-center">
                                            <a href="#" className=" text-decoration-none text-dark link-info form-control-lg  rounded px-3" >{e.title}</a>
                                        </div>

                                        <div className="col-auto m-1 p-0 todo-actions">

                                            <div className="container d-flex text-align-center justify-content-end">
                                                <button onClick={(id) => (editNote(e.id))} className="btn btn-primary btn-block mx-2">Edit</button>
                                                <button onClick={(id, title, content, state) => (archiveNote(e.id, e.title, e.content, e.state))} className="btn btn-danger btn-block">Archive</button>
                                            </div>

                                        </div>
                                        <div className="p-2 mx-4 border-black-25 border-bottom"></div>

                                    </div>
                                </div>
                            </div>
                        
                        ))
                    )
                    :
                    (
                       <p  className="h4 text-center mx-auto mt-4">Empty List</p>
                    )
            }
        </Container>
    );

}
export default Available_Notes;
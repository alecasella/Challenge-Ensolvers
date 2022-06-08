import React, { useContext, useEffect, useState } from "react";
import Axios from 'axios'
import { useNavigate } from "react-router-dom";
import {
    Table, Container
} from 'reactstrap';


const List_Notes = () => {
    const [notes, setNotes] = useState([]);

    const navigate = useNavigate();

    useEffect(() => {
          getAllNotes();
      },[])
    
      const getAllNotes = () => {
        try {
          Axios.get(`http://localhost:8080/api/notes`).then((resp => {
              setNotes(resp.data);
          }))
        } catch (e) { console.log(e); }
      }
    
    const addNote = (e) => {
        e.preventDefault();

        navigate(`/add-update-note`);
    
    }

    return (
        <Container>

            <div className="row m-1">
                <div className="col">
                    <div className="h1 text-center mx-auto mt-4">
                        <u> My Notes</u>
                        <button onClick={addNote} type="button" className="btn btn-secondary m-5">Create Note</button>

                    </div>
                </div>
            </div>

            <div className="p-2 mx-4 border-black-25 border-bottom" />
            {
                notes != [] ?
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
                                                <button className="btn btn-primary btn-block mx-2">Edit</button>
                                                <button className="btn btn-danger btn-block">Delete</button>
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
                       <p>Empty List</p>
                    )
            }

        </Container>


    );

}
export default List_Notes;
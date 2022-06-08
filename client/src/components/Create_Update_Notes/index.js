
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import Axios from 'axios'

import {
   Container
} from 'reactstrap';

const Create_Update_Notes = () => {
    const [title, setTitle] = useState("");
    const [content, setContent] = useState("");
    
    const navigate = useNavigate();

    const addNote = (e) => {
        e.preventDefault();
    
        const note = {
          title: title,
          content: content
        }
    
        try {
          Axios.post(`http://localhost:8080/api/notes`, note).then((resp => {
            navigate(`/notes`);
            alert('Success');
          }))
        } catch (e) { console.log(e); }
    
      }
    

    const goBack = (e) => {
        e.preventDefault();
    
        navigate(`/notes`);
    
      }
    
    return (
        <Container>

            <div className="row m-1">
                <div className="col">
                    <div className="h1 text-center mx-auto mt-4">
                        <u> Create/Edite Note</u>
                    </div>
                </div>
            </div>

            <div className="row m-1 p-3">
                <div className="col col-7 mx-auto">
                    <div className="row bg-white shadow-sm p-2 add-todo-wrapper align-items-center justify-content-center">
                        <div className="col">
                            <input onChange={(e) => setTitle(e.target.value)} value={title} className=" form-control form-control-lg border-0 add-todo-input " type="text" placeholder="Title" />
                            <input onChange={(e) => setContent(e.target.value)} value={content} className=" form-control form-control-lg border-0 add-todo-input  mt-2" type="text" placeholder="Content" />

                        </div>

                        <div className="col-auto px-0 mx-0 mr-2">
                            <button onClick={addNote} type="button" className="btn btn-primary">Save</button>
                            <button onClick={goBack} type="button" className="btn btn-secondary m-2">Cancel</button>
                        </div>
                    </div>
                </div>
            </div>

        </Container>


    );

}
export default Create_Update_Notes;;
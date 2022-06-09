
import React, { useState, useEffect } from "react";
import { useNavigate, useParams } from "react-router-dom";
import Axios from 'axios'

import {
    Container, Row, Col
} from 'reactstrap';

const Create_Update_Notes = () => {
    const [title, setTitle] = useState("");
    const [content, setContent] = useState("");
    const [state, setState] = useState("");
    const [description, setDescription] = useState("");
    const [categories, setCategories] = useState([]);

    const { id } = useParams();

    const navigate = useNavigate();


    useEffect(() => {
        if (id) {
            findNoteToEdit(id);
            findCategoriesOfNote(id);
        }
    }, [])

    const findNoteToEdit = (id) => {
        try {
            Axios.get(`http://localhost:8080/api/notes/${id}`).then((resp => {
                setTitle(resp.data.title);
                setContent(resp.data.content);
                setState(resp.data.state);
            }))
        } catch (e) { console.log(e); }
    }

    const findCategoriesOfNote = (id) => {
        try {
            Axios.get(`http://localhost:8080/api/categories/${id}`).then((resp => {
                setCategories(resp.data);
            }))
        } catch (e) { console.log(e); }
    }

    const deleteCategoryOfNote = (idCategory) => {
        try {
            Axios.delete(`http://localhost:8080/api/categories/${idCategory}`).then((resp => {
                findCategoriesOfNote(id);
                alert("Success");
            }))
        } catch (e) { console.log(e); }
    }

    const addCategory = (e) => {
        e.preventDefault();

        const category = {
            description: description
        }

        try {
            Axios.post(`http://localhost:8080/api/categories/${id}`, category).then((resp => {
                findCategoriesOfNote(id);
                setDescription("");
                alert("Success");
            }))
        } catch (e) { console.log(e); }
    }

    const addOrEditeNote = (e) => {
        e.preventDefault();
        let uri = "http://localhost:8080/api/notes";
        const note = {
            title: title,
            content: content
        }
        try {
            !id ?
                Axios.post("http://localhost:8080/api/notes", note).then((resp => {
                    navigate(`/notes`);
                    alert('Success');
                }))
                :
                note.state = state;
            Axios.put(`http://localhost:8080/api/notes/${id}`, note).then((resp => {
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
                <div className="col col-6 mx-auto">
                    <div className="row bg-white shadow-sm p-2 align-items-center justify-content-center">
                        <div className="col">
                            <input onChange={(e) => setTitle(e.target.value)} value={title} className=" form-control form-control-lg border-0" type="text" placeholder="Title" />
                            <textarea onChange={(e) => setContent(e.target.value)} value={content} rows="4" className=" form-control form-control-lg border-0  mt-2" type="text" placeholder="Content" />

                        </div>

                        <div className="col-auto px-0 mx-0 mr-2">
                            <button onClick={addOrEditeNote} type="button" className="btn btn-primary">Save</button>
                            <button onClick={goBack} type="button" className="btn btn-secondary m-2">Cancel</button>
                        </div>
                    </div>
                </div>
            </div>

            <Row>
                {
                    id ?
                        (
                            <Col>
                                <div className="row m-1">
                                    <div className="col">
                                        <div className="h3 text-center mt-2">
                                            <u> Categories</u>
                                        </div>
                                    </div>
                                </div>

                                <div className="row m-1 p-3">
                                    <div className="col col-6 mx-auto">
                                        <div className="row bg-white rounded shadow-sm p-2 add-todo-wrapper align-items-center justify-content-center">
                                            <div className="col">
                                                <input onChange={(e) => setDescription(e.target.value)} value={description} className=" form-control form-control-lg border-0" type="text" placeholder="Category Description" />
                                            </div>

                                            <div className="col-auto px-0 mx-0 mr-2">
                                                <button onClick={addCategory} type="button" className="btn btn-primary">Add</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </Col>
                        ) : (<></>)

                }
            </Row>
            {
                id ?
                    (
                        categories.map((e, i) => (
                            <div className="row p-3" key={i}>
                                <div className="col col-5 mx-auto">
                                    <div className="row bg-white shadow-sm p-2 align-items-center justify-content-center">
                                        <div className="col">
                                            <p className=" text-decoration-none text-dark link-info form-control-lg rounded px-3" >{e.description}</p>

                                        </div>

                                        <div className="col-auto px-0 mx-0 mr-2">
                                            <button onClick={(idCategory) => deleteCategoryOfNote(e.id)} type="button" className="btn btn-danger">x</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        )
                        )
                    ) : (<></>)


            }

        </Container>


    );

}
export default Create_Update_Notes;;
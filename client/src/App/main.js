import React from 'react';
import '../css/App.css';
import {
    BrowserRouter as Router,
    Route, Routes,
} from 'react-router-dom';
import {
    Container,
} from 'reactstrap';

import Notes from '../components/List_Notes';
import Create_Update_Note from '../components/Create_Update_Notes';
import Header from './header';

const Main = () => {
    return (
        <Container fluid>
            <Router>
                <Header />

                <Routes>
                    <Route exact path="/notes" element={<Notes />}> </Route>
                    <Route exact path="/add-update-note" element={<Create_Update_Note />}> </Route>
                    <Route exact path="/add-update-note/:id" element={<Create_Update_Note />}> </Route>

                </Routes>

            </Router>
        </Container>
    );
};

export default Main;
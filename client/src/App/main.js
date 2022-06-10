import React from 'react';
import '../css/App.css';
import {
    BrowserRouter as Router,
    Route, Routes,
} from 'react-router-dom';
import {
    Container,
} from 'reactstrap';

import Available_Notes from '../components/Available_Notes';
import Archived_Notes from '../components/Archived_Notes';
import Login from '../components/Login';
import Create_Update_Note from '../components/Create_Update_Notes';
import Logout from '../components/Logout';
import Header from './header';

import AuthProvider from '../contexts/auth'


const Main = () => {
    return (
        <AuthProvider>

            <Container fluid>
                <Router>

                    <Header />
                    <Routes>
                        <Route exact path="/" element={<Login />}> </Route>
                        <Route exact path="/notes" element={<Available_Notes />}> </Route>
                        <Route exact path="/add-update-note" element={<Create_Update_Note />}> </Route>
                        <Route exact path="/add-update-note/:id" element={<Create_Update_Note />}> </Route>
                        <Route exact path="/archived_notes" element={<Archived_Notes />}> </Route>
                        <Route exact path="/logout" element={<Logout />}></Route>
                    </Routes>

                </Router>
            </Container>
        </AuthProvider>

    );
};

export default Main;
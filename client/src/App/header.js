import { useContext } from 'react';
import {
    Navbar,
    NavbarBrand,
    Nav,
    NavItem,
    NavLink,
    Container,
} from 'reactstrap';


import { AuthContext } from '../contexts/auth';

const NavBar = () => {

    const { loggedUser } = useContext(AuthContext);

    return (
        <>
            {
                loggedUser ? (


                    <Navbar color="light" light expand="md">
                        <Container>

                            <NavbarBrand href="/notes">Home</NavbarBrand>
                        </Container>
                        <Nav tabs className="nav-bar-items">
                            <NavItem>
                                <NavLink href="/logout" >LogOut</NavLink>
                            </NavItem>
                        </Nav>
                    </Navbar>
                )
                    :
                    (
                        <Navbar color="light" light expand="md">

                            <NavbarBrand href="/">Home</NavbarBrand>
                        </Navbar>

                    )
            }
        </>
    );
};

export default NavBar;
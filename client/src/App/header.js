import {
    Navbar,
    NavbarBrand,

} from 'reactstrap';


const NavBar = () => {

    return (
        <Navbar color="light" light expand="md">

            <NavbarBrand href="/notes">Home</NavbarBrand>
        </Navbar>

    );
};

export default NavBar;
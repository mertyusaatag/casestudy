import Button from 'react-bootstrap/Button';
import Container from 'react-bootstrap/Container';
import Form from 'react-bootstrap/Form';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';
import {Link} from "react-router-dom";
import axios from "axios";
import React, {useState} from "react";
import {Col, Row} from "react-bootstrap";
import MovieCard from "./MovieCard";
import DetailPage from "./Pagess/DetailPage";
import {changeState} from "../store/panel";
import {useDispatch} from "react-redux";

function NavbarComponent() {

    const [word, setWord] = useState('');
    const [data,setData] = useState({});
    const dispatch = useDispatch();
    const handleChange = (e) => {
        setWord(e.target.value);
    }

    const searchHandler = async (event) => {
        event.preventDefault();
        const response = await axios.get(`http://localhost:8080/movie/title/${word}`, {
            headers: {
                'Access-Control-Allow-Origin': '*',
                'Content-Type': 'application/json;charset=UTF-8'
            }
        });

        setData(response.data);

        dispatch(
            changeState(response.data))
    }

    return (
        <Navbar bg="light" expand="lg">
            <Container fluid>
                <Navbar.Brand href="/">Case Study FE</Navbar.Brand>
                <Navbar.Toggle aria-controls="navbarScroll" />
                <Navbar.Collapse id="navbarScroll">
                    <Nav
                        className="me-auto my-2 my-lg-0"
                        style={{ maxHeight: '100px' }}
                        navbarScroll
                    >
                        <Nav.Link><Link to='/'>
                            Home
                        </Link></Nav.Link>
                        <NavDropdown title="Category" id="navbarScrollingDropdown">
                            <Link to='/series'>
                            <NavDropdown.Item title={"Movies"} href={"/series"} >
                                    Series
                            </NavDropdown.Item>
                            </Link>
                               <NavDropdown.Item title={"Movies"} href={'/movie'} >
                                   Movies
                               </NavDropdown.Item>

                        </NavDropdown>

                        <NavDropdown title="Admin" id="navbarScrollingDropdown">
                            <NavDropdown.Item >
                                <Link to='/charts'>
                                    Charts
                                </Link>
                            </NavDropdown.Item>

                        </NavDropdown>
                    </Nav>
                    <Form className="d-flex" onSubmit={searchHandler}>
                        <Form.Control
                            type="search"
                            placeholder="Search"
                            className="me-2"
                            aria-label="Search"
                            value={word}
                            onChange={handleChange}
                        />
                        <Button type="submit">Search</Button>
                    </Form >
                </Navbar.Collapse>
            </Container>
        </Navbar>
    );
}

export default NavbarComponent;
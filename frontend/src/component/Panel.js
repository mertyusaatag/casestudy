import Table from 'react-bootstrap/Table';
import Container from "react-bootstrap/Container";
import React, {useEffect, useState} from "react";
import axios from "axios";
import TableComponent from "./TableComponent";
import {Col, Row} from "react-bootstrap";
import Button from "react-bootstrap/Button";
import AddModal from "./AddModal";
import {useDispatch, useSelector} from "react-redux";
import {changeState, fetchMovies, filterState, setMovies} from "../store/panel";
import Modal from "react-bootstrap/Modal";
import Form from "react-bootstrap/Form";

function Panel() {
    let movies = useSelector(state => state.movies);
    const dispatch = useDispatch();
    const [word, setWord] = useState('');
    const [searchedWord, setSearchedWord] = useState([]);
    const [clicked, setClicked] = useState(false);
    const [invalid, setInvalid] = useState(false);
    const [loaded, setLoaded] = useState(false);
    const [message, setMessage] = useState('');



    const handleChange = (e) => {
        e.preventDefault();
        setWord(e.target.value);

        if (word.length == 0) {
            setInvalid(true)
            setMessage('Invalid Form, Input can not be empty')
            return
        }
        if (word.length > 10) {
            setInvalid(true)
            setMessage('Invalid Form, Input can not be more than 10 characters')
            return
        }


        movies = movies.filter((movie) =>
            movie.title.toLowerCase().includes(word.toLowerCase())
        );

        dispatch(filterState(movies));

        //setWord('');
        setMessage('')
        setClicked(true);
    }

    const handleSubmit = (e) => {
        e.preventDefault();


        if (word.length == 0) {
            setInvalid(true)
            setMessage('Invalid Form, Input can not be empty')
            return
        }
        if (word.length > 10) {
            setInvalid(true)
            setMessage('Invalid Form, Input can not be more than 10 characters')
            return
        }

        console.log(movies)
        setWord('');
        setMessage('')
        setClicked(true);
    }

      useEffect(() => {
          dispatch(fetchMovies());
      },[]);


    const [show , setShow] = useState(false);

    const handleClose = () => setShow(false)
    const handleShow = () => setShow(true);



    return (
        <Container>
            <Row>
                <Form onSubmit={handleSubmit}>
                    <input type="text" value={word} onChange={handleChange} placeholder='Search...' />
                    {invalid && <p className='errorMessage'>{message}</p>}

                    <button type='submit'>Submit</button>
                </Form>
            </Row>
            <Row>
                <Col>

                </Col>
                <Col style={{ textAlign : "right" , paddingRight : 0}}>
                    <Button variant="outline-success" onClick={handleShow}>Click For Adding New Movie</Button>
                </Col>

                <Modal show={show} onHide={handleClose} size="lg">
                    <AddModal/>

                </Modal>

            </Row>
            <Row>
        <Table striped bordered hover>
            <thead>
            <tr>
                <th>Title</th>
                <th>Actors</th>
                <th>Type</th>
                <th>Imdb Rating</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            {movies.map(d => <TableComponent {...d}/>)}
            </tbody>
        </Table>
            </Row>
        </Container>
    );
}

export default Panel;
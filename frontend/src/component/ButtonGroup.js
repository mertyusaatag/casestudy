import Button from 'react-bootstrap/Button';
import UpdateModal from "./AddModal";
import {useEffect, useState} from "react";
import Modal from "react-bootstrap/Modal";
import Form from 'react-bootstrap/Form';
import axios from "axios";
import Swal from 'sweetalert2'
import {fetchMovies} from "../store/panel";
import {useDispatch} from "react-redux";

function Buttons(data) {

    const [enteredTitle , setEnteredTitle] = useState("");
    const [enteredYear , setEnteredYear] = useState("");
    const [enteredGenre , setEnteredGenre] = useState("");
    const [enteredLanguage , setEnteredLanguage] = useState("");
    const [enteredPlot , setEnteredPlot] = useState("");
    const [enteredActors , setEnteredActors] = useState("");
    const [enteredImdbRating , setEnteredImdbRating] = useState("");

    const dispatch = useDispatch();

    const titleChangeHandler = (event) => {
        setEnteredTitle(event.target.value);
    }

    const yearChangeHandler = (event) => {
        setEnteredYear(event.target.value);
    }
    const genreChangeHandler = (event) => {
        setEnteredGenre(event.target.value);
    }
    const languageChangeHandler = (event) => {
        setEnteredLanguage(event.target.value);
    }
    const plotChangeHandler = (event) => {
        setEnteredPlot(event.target.value);
    }

    const actorsChangeHandler = (event) => {
        setEnteredActors(event.target.value);
    }

    const imdbRatingChangeHandler = (event) => {
        setEnteredImdbRating(event.target.value);
    }



    const [show,setShow] = useState(false);

    const handleClose = () => setShow(false)
    const handleShow = () => setShow(true);


    const sendData = async (movie) => {
       const response = await axios.put(`http://localhost:8080/movie/${data.imdbId}`, movie, {headers: {
                'Access-Control-Allow-Origin': '*',
                'Content-Type': 'application/json;charset=UTF-8'
            }})


        return response;

    }

    const submitHandler = async (event) => {
        event.preventDefault();

        const movie = {
            title: enteredTitle,
            year: enteredYear,
            genre: enteredGenre,
            language: enteredLanguage,
            plot: enteredPlot,
            actors: enteredActors,
            imdbRating: enteredImdbRating
        }


        setShow(false);
        try {

            const response = await sendData(movie)
            dispatch(fetchMovies());

            Swal.fire({
                icon: 'success',
                title: 'Success',
                text: 'Updated successfully',
            })


        } catch (err) {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Something went wrong!',
            })
        }


    };
    const deleteMovie  = async () =>{
            await axios.delete(`http://localhost:8080/movie/imdb/${data.imdbId}`, {headers: {
                    'Access-Control-Allow-Origin': '*',
                    'Content-Type': 'application/json;charset=UTF-8'
                }})
    }
    const deleteHandler = () => {
        Swal.fire({
            title: 'Are you sure?',
            text: "You won't be able to revert this!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, delete it!'
        }).then(async (result) => {
            if (result.isConfirmed) {
                try {
                    await deleteMovie()
                    dispatch(fetchMovies());

                    Swal.fire(
                        'Deleted!',
                        'Your file has been deleted.',
                        'success'
                    )
                } catch (err) {
                    console.log(err);
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: err,
                    })

                }

            }
        })
    }
    return (
        <>
            <Button variant="warning" onClick={handleShow}>Update</Button>{' '}
            <Button variant="danger" onClick={deleteHandler}>Delete</Button>{' '}

            <Modal show={show} onHide={handleClose}>
                <Modal.Header closeButton>
                    <Modal.Title>You are updating : {data.title}</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Form>
                        <Form.Group className="mb-3" controlId="formBasicEmail">
                            <Form.Label>Enter Title</Form.Label>
                            <Form.Control type="text" placeholder={data.title} onChange={titleChangeHandler} />
                            <Form.Text className="text-muted">
                                Please Enter Title
                            </Form.Text>
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="formBasicEmail">
                            <Form.Label>Year</Form.Label>
                            <Form.Control type="text" placeholder={data.years} onChange={yearChangeHandler} />
                            <Form.Text className="text-muted">
                                Please Enter Year
                            </Form.Text>
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="formBasicEmail">
                            <Form.Label>Genre</Form.Label>
                            <Form.Control type="text" placeholder={data.genre} onChange={genreChangeHandler} />
                            <Form.Text className="text-muted">
                                We'll never share your email with anyone else.
                            </Form.Text>
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="formBasicEmail">
                            <Form.Label>Language</Form.Label>
                            <Form.Control type="text" placeholder={data.language}  onChange={languageChangeHandler}/>
                            <Form.Text className="text-muted">
                                We'll never share your email with anyone else.
                            </Form.Text>
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="formBasicEmail">
                            <Form.Label>Plot</Form.Label>
                            <Form.Control as="textarea" placeholder={data.plot} onChange={plotChangeHandler} />
                            <Form.Text className="text-muted">
                            </Form.Text>
                        </Form.Group>

                        <Form.Group className="mb-3" controlId="formBasicPassword">
                            <Form.Label>Actors</Form.Label>
                            <Form.Control type="text" placeholder={data.actors} onChange={actorsChangeHandler}/>
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="formBasicPassword">
                            <Form.Label>Imdb Rating</Form.Label>
                            <Form.Control type="text" placeholder={data.imdbRating}  onChange={imdbRatingChangeHandler}/>
                        </Form.Group>
                    </Form>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary" onClick={handleClose}>
                        Close
                    </Button>
                    <Button variant="primary" onClick={submitHandler}>
                        Save Changes
                    </Button>
                </Modal.Footer>
            </Modal>
        </>
    );
}

export default Buttons;
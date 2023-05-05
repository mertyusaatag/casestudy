import Button from 'react-bootstrap/Button';
import Modal from 'react-bootstrap/Modal';
import {useState} from "react";
import Form from "react-bootstrap/Form";
import {Col, Row} from "react-bootstrap";
import Container from "react-bootstrap/Container";
import axios from "axios";
import {fetchMovies} from "../store/panel";
import Swal from "sweetalert2";
import {useDispatch} from "react-redux";

function AddModal() {

    const [show,setShow] = useState(false);
    const handleClose = () => setShow(false);

    const dispatch = useDispatch();

    const [validated,setValidated] = useState(false);

    const [enteredTitle , setEnteredTitle] = useState("");
    const [enteredYear , setEnteredYear] = useState("");
    const [enteredGenre , setEnteredGenre] = useState("");
    const [enteredLanguage , setEnteredLanguage] = useState("");
    const [enteredPlot , setEnteredPlot] = useState("");
    const [enteredActors , setEnteredActors] = useState("");
    const [enteredImdbRating , setEnteredImdbRating] = useState("");
    const [enteredRate, setEnteredRate] = useState("");
    const [enteredMetaScore , setEnteredMetaScore] = useState('');
    const [enteredType, setEnteredType] = useState("");
    const [enteredRuntime, setEnteredRuntime] = useState("");
    const [enteredDirector, setEnteredDirector] = useState("");
    const [enteredComingSoon, setEnteredComingSoon] = useState(false);
    const [enteredReleased, setEnteredReleased] = useState("");
    const [enteredCountry, setEnteredCountry] = useState("");
    const [enteredAwards, setEnteredAwards] = useState("");
    const [enteredWriter, setEnteredWriter] = useState("");
    const [enteredImdbVotes , setEnteredImdbVotes] = useState("")
    const [enteredImdbId , setEnteredImdbId] = useState("")




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
    const ratedChangeHandler = (event) => {
        setEnteredRate(event.target.value);
    }
    const runTimeChangeHandler = (event) => {
        setEnteredRuntime(event.target.value);
    }
    const directorChangeHandler = (event) => {
        setEnteredDirector(event.target.value);
    }
    const writerChangeHandler = (event) => {
        setEnteredWriter(event.target.value);
    }
    const awardsChangeHandler = (event) => {
        setEnteredAwards(event.target.value);
    }
    const metaScoreChangeHandler = (event) => {
        setEnteredMetaScore(event.target.value);
    }
    const imdbVotesChangeHandler = (event) => {
        setEnteredImdbVotes(event.target.value);
    }
    const comingSoonChangeHandler = (event) => {
        console.log(event.target.value);
        setEnteredComingSoon(event.target.value);
    }
    const releasedChangeHandler = (event) => {
        setEnteredReleased(event.target.value);
    }
    const countryChangeHandler = (event) => {
        setEnteredCountry(event.target.value);
    }
    const typeChangeHandler = (event) => {
        setEnteredType(event.target.value);
    }
    const imdbIdChangeHandler = (event) => {
        setEnteredImdbId(event.target.value);
    }


    const sendData = async (movie) => {
        const response = await axios.post(`http://localhost:8080/movie`, movie, {headers: {
                'Access-Control-Allow-Origin': '*',
                'Content-Type': 'application/json;charset=UTF-8'
            }})


        return response;

    }

    const submitHandler = async (event) => {
        const form = event.currentTarget;
        if (form.checkValidity() === false) {
            event.preventDefault();
            event.stopPropagation();
        }

        setValidated(true);

        const movie = {
            title: enteredTitle,
            years: enteredYear,
            genre: enteredGenre,
            language: enteredLanguage,
            plot: enteredPlot,
            actors: enteredActors,
            imdbRating: enteredImdbRating,
            rated: enteredRate,
            director: enteredDirector,
            released: enteredReleased,
            metaScore: enteredMetaScore,
            type: enteredType,
            runTime: enteredRuntime,
            comingSoon: enteredComingSoon,
            country: enteredCountry,
            awards: enteredAwards,
            writer: enteredWriter,
            imdbVotes: enteredImdbVotes,
            imdbId:enteredImdbId,
            poster: "https://m.media-amazon.com/images/I/314t8YNB69L.png 50w, https://m.media-amazon.com/images/I/314t8YNB69L.png"
        }

        try {

            await sendData(movie);
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


    }




    return (

      <>
            <Modal.Header closeButton>
                <Modal.Title>Add Movie</Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <Container>
                    <Row>
                        <Col xs={12} md={6}>
                            <Form noValidate validated={validated} onSubmit={submitHandler}>
                                <Form.Group className="mb-3" controlId="imdbId" onChange={imdbIdChangeHandler}>
                                    <Form.Label>Imdb Id</Form.Label>
                                    <Form.Control type="text" placeholder="Enter title / Must be unique" required/>
                                    <Form.Control.Feedback type="invalid">
                                        Please provide a Imdb Id
                                    </Form.Control.Feedback>
                                </Form.Group>
                                <Form.Group className="mb-3" controlId="title" onChange={titleChangeHandler}>
                                    <Form.Label>Title</Form.Label>
                                    <Form.Control type="text" placeholder="Enter title" required/>
                                    <Form.Control.Feedback type="invalid">
                                        Please provide a Title
                                    </Form.Control.Feedback>
                                </Form.Group>

                                <Form.Group className="mb-3" controlId="year" onChange={yearChangeHandler}>
                                    <Form.Label>Year</Form.Label>
                                    <Form.Control type="text" placeholder="Enter Year" />
                                </Form.Group>

                                <Form.Group className="mb-3" controlId="genre" onChange={genreChangeHandler}>
                                    <Form.Label>Genre</Form.Label>
                                    <Form.Control type="text" placeholder="Enter Genre" />
                                </Form.Group>

                                <Form.Group className="mb-3" controlId="rated" onChange={ratedChangeHandler}>
                                    <Form.Label>Rated</Form.Label>
                                    <Form.Control type="text" placeholder="Enter Rated" />
                                </Form.Group>

                                <Form.Group className="mb-3" controlId="runtime" onChange={runTimeChangeHandler}>
                                    <Form.Label>Runtime</Form.Label>
                                    <Form.Control type="text" placeholder="Enter Runtime" />
                                </Form.Group>

                                <Form.Group className="mb-3" controlId="director">
                                    <Form.Label>Director</Form.Label>
                                    <Form.Control type="text" placeholder="Enter Director" required onChange={directorChangeHandler}/>
                                    <Form.Control.Feedback type="invalid">
                                        Please provide a Director
                                    </Form.Control.Feedback>
                                </Form.Group>

                                <Form.Group className="mb-3" controlId="writer">
                                    <Form.Label>Writer</Form.Label>
                                    <Form.Control type="text" placeholder="Enter Writer" required onChange={writerChangeHandler}/>
                                    <Form.Control.Feedback type="invalid">
                                        Please provide a Writer
                                    </Form.Control.Feedback>
                                </Form.Group>
                                <Form.Group className="mb-3" controlId="language">
                                    <Form.Label>Language</Form.Label>
                                    <Form.Control type="text" placeholder="Enter Language" onChange={languageChangeHandler} />
                                </Form.Group>

                            </Form>
                        </Col>
                        <Col xs={12} md={6}>
                            <Form noValidate validated={validated} onSubmit={submitHandler}>
                                <Form.Group className="mb-3" controlId="awards">
                                    <Form.Label>Awards</Form.Label>
                                    <Form.Control type="text" placeholder="Enter Awards" onChange={awardsChangeHandler}/>
                                </Form.Group>

                                <Form.Group className="mb-3" controlId="metaScore">
                                    <Form.Label>MetaScore</Form.Label>
                                    <Form.Control type="text" placeholder="Enter MetaScore" onChange={metaScoreChangeHandler}/>
                                </Form.Group>
                                <Form.Group className="mb-3" controlId="imdbRating">
                                    <Form.Label>Imdb Rating</Form.Label>
                                    <Form.Control type="text" placeholder="Enter Imdb Rating" required onChange={imdbRatingChangeHandler}/>
                                    <Form.Control.Feedback type="invalid">
                                        Please provide a Imdb Rating
                                    </Form.Control.Feedback>
                                </Form.Group>
                                <Form.Group className="mb-3" controlId="imdbVotes">
                                    <Form.Label><span>Imdb Votes</span></Form.Label>
                                    <Form.Control type="text" placeholder="Imdb Votes" onChange={imdbVotesChangeHandler}/>
                                </Form.Group>
                                <Form.Group className="mb-3" controlId="type">
                                    <Form.Label>Type</Form.Label>
                                    <Form.Check
                                        inline
                                        label="Movie"
                                        value="movie"
                                        name="group2"
                                        type="radio"
                                        id={`inline-radio-1`}
                                        onChange={typeChangeHandler}
                                    />
                                    <Form.Check
                                        inline
                                        label="Series"
                                        value="series"
                                        name="group2"
                                        type="radio"
                                        id={`inline-radio-2`}
                                        onChange={typeChangeHandler}
                                    />
                                    <Form.Control.Feedback type="invalid">
                                        Please provide a Type
                                    </Form.Control.Feedback>
                                </Form.Group>
                                <Form.Group className="mb-3" controlId="comingSoon">
                                    <Form.Label>Coming Soon  </Form.Label>
                                    <Form.Check
                                        inline
                                        label="Yes"
                                        value={true}
                                        name="group1"
                                        type="radio"
                                        id={`inline-radio-1`}
                                        onChange={comingSoonChangeHandler}
                                    />
                                    <Form.Check
                                        inline
                                        label="No"
                                        value={false}
                                        name="group1"
                                        type="radio"
                                        id={`inline-radio-2`}
                                        onChange={comingSoonChangeHandler}
                                    />
                                </Form.Group>
                                <Form.Group className="mb-3" controlId="released">
                                    <Form.Label>Released</Form.Label>
                                    <Form.Control type="text" placeholder="Enter Released" onChange={releasedChangeHandler}/>
                                </Form.Group>
                                <Form.Group className="mb-3" controlId="actors">
                                    <Form.Label>Actors</Form.Label>
                                    <Form.Control type="text" placeholder="Enter Actors" onChange={actorsChangeHandler}/>
                                </Form.Group>
                                <Form.Group className="mb-3" controlId="country">
                                    <Form.Label>Country</Form.Label>
                                    <Form.Control type="text" placeholder="Enter Country" onChange={countryChangeHandler}/>
                                </Form.Group>
                            </Form>
                        </Col>
                    </Row>

                    <Row>
                        <Form.Group className="mb-3" controlId="plot">
                            <Form.Label>Plot</Form.Label>
                            <Form.Control as="textarea"  placeholder="Lorem Ipsum is simply dummy text of the printing
                             and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,
                              when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived
                            Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsu" onChange={plotChangeHandler} />
                            <Form.Text className="text-muted" >
                            </Form.Text>
                        </Form.Group>
                        <Form.Group className="mb-3" controlId="poster">
                            <Form.Label>Poster</Form.Label>
                            <Form.Control type="file" placeholder="Enter Poster" />
                        </Form.Group>

                    </Row>

                </Container>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="primary" onClick={submitHandler}>
                    Save Changes
                </Button>
            </Modal.Footer>
      </>
    );
}

export default AddModal;
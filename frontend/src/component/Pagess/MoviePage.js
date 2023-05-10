
import React, {useEffect} from "react";
import MovieCard from "../MovieCard";
import Container from "react-bootstrap/Container";
import {Col, Image, Row} from "react-bootstrap";
import {useDispatch, useSelector} from "react-redux";
import {fetchMovies} from "../../store/panel";



export default function MoviePage() {
    // const [data, setData]= useState([])
    const movies = useSelector(state => state.movies);
    const dispatch = useDispatch();

    useEffect(() => {
        dispatch(fetchMovies());
    },[]);

    return(
        <Container style={{backgroundImage:"url(../resource/bg.jpg)"}}>
            <Row>
        {movies.map(d => <Col><MovieCard {...d}/></Col>)}
            </Row>

</Container>
    );
}

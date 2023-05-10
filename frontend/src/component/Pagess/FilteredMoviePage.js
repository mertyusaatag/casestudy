
import React, {useEffect, useState} from "react";
import MovieCard from "../MovieCard";
import Container from "react-bootstrap/Container";
import {Col, Row} from "react-bootstrap";
import { useParams } from 'react-router-dom';
import {useDispatch, useSelector} from "react-redux";
import {fetchMovieByFilter} from "../../store/panel";


export default function MoviePage(prop) {
    const movies = useSelector(state => state.movies);
    const dispatch = useDispatch();

    let {filmType} = useParams();

    useEffect(() => {;
        dispatch(fetchMovieByFilter(filmType));
    },[filmType]);

    return(
        <Container lg={true}>
            <Row>
                {movies.map(d => <Col><MovieCard {...d}/></Col>)}
            </Row>
        </Container>
    );
}

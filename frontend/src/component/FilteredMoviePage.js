
import React, {useEffect, useState} from "react";
import axios from "axios";
import MovieCard from "./MovieCard";
import {map} from "react-bootstrap/ElementChildren";
import Container from "react-bootstrap/Container";
import {Col, Row} from "react-bootstrap";
import { useParams } from 'react-router-dom';


export default function MoviePage(prop) {
    const [data, setData]= useState([])

    let {filmType} = useParams();

    const getData = async () => {
        const {data} = await  axios.get(`http://localhost:8080/movie/${filmType}`, {headers: {
                'Access-Control-Allow-Origin': '*',
                'Content-Type': 'application/json;charset=UTF-8'
            }})
        setData(data);
    }

    useEffect(() => {
        getData();
    },[filmType]);

    return(
        <Container lg={true}>
            <Row>
                {data.map(d => <Col><MovieCard {...d}/></Col>)}
            </Row>
        </Container>
    );
}

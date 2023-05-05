
import React, {useEffect, useState} from "react";
import axios from "axios";
import "../css/movie.css"
import {Link, useParams} from "react-router-dom";
import {Card, Col, Row, Tab, Tabs} from "react-bootstrap";
import Container from "react-bootstrap/Container";
import Button from "react-bootstrap/Button";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faStar} from "@fortawesome/free-solid-svg-icons";



export default function DetailPage() {
    const [data, setData]= useState([])

    let {imdbId} = useParams();

    const getData = async () => {
        const {data} = await  axios.get(`http://localhost:8080/movie/detail/${imdbId}`, {headers: {
                'Access-Control-Allow-Origin': '*',
                'Content-Type': 'application/json;charset=UTF-8'
            }})

        setData(data);
    }

    useEffect(() => {
        getData();
    },[imdbId]);

    return(
        <Container fluid  style={{fontSize:"medium"}}>
        <div>
            <div className='movie-header' style={{backgroundColor:"#B5D3FF"}}>
                <span className='movie-title'>{data.title}</span>
                <span className='movie-detail genre'><div>{data.genre}</div></span>
                <span className='movie-detail year'>{data.released}<div></div></span>
                <span className='movie-detail duration'><div>{data.runTime}</div></span>

        </div >
            <div className='col movie-content'>
                <div>
                    <div className='movie-content-header'>
 <span className='movie-rating' style={{fontSize:25,fontWeight:"bold"}}>
                    Rating: {data.imdbRating}<FontAwesomeIcon icon={faStar} bounce size="xs" style={{color: "#b6d73c",}} />
                   <div> </div>
                    </span>
                    </div>
                </div>
                </div>
            </div>

            <Row>
                <Col md="3" sm={12}>
                    <Card style={{ width: '25rem', height: '38rem', marginBottom: '1rem' , boxShadow:"7px 5px 5px gray" }}>
                        <Card.Img variant="top" src={data.poster} style={{width:'100%',height:'100%'}}/>
                    </Card>
                </Col>
                <Col md="9" sm="12">
                    <Row>
                    <div className="ratio ratio-21x9" style={{height: "30rem", width:"70rem", float:"inherit" }}>
                        <iframe
                            src="https://www.youtube.com/embed/vlDzYIIOYmM"
                            title="YouTube video"
                            allowFullScreen
                        ></iframe>
                    </div>
                    </Row>
                    <Row style={{paddingTop:"1rem"}}>
                        <Tabs
                            defaultActiveKey="profile"
                            id="fill-tab-example"
                            className="mb-3"
                            fill
                        >
                            <Tab eventKey="home" title="Plot">
                                {data.plot}
                            </Tab>
                            <Tab eventKey="profile" title="Actors">
                                {data.actors}
                            </Tab>
                            <Tab eventKey="longer-tab" title="Writer">
                                {data.writer}
                            </Tab>

                        </Tabs>
                    </Row>
                </Col>
            </Row>
            <div className='movieContainer'>


            </div>
        </Container>

    );
}

import React from "react";
import {Card} from "react-bootstrap";
import Button from "react-bootstrap/Button";
import '../css/card.css'
const MovieCard = (movie) => {
    return (
        <div className='messi'>
        <Card style={{ width: '18rem', height: '43rem', marginBottom: '1rem' , boxShadow:"7px 5px 5px gray" }}>
            <Card.Img variant="top" src={movie.poster} className='ronaldo' />
            <Card.Body style={{ display: 'flex', flexDirection: 'column', justifyContent:'space-between' }}>
               <>
                   <Card.Title>{movie.title}</Card.Title>
                   <Card.Text>
                       {movie.plot}
                   </Card.Text>
               </>
                <Button variant="primary" href={`/detail/${movie.imdbId}`}>Detail Page</Button>
            </Card.Body>
        </Card>
        </div>
    );
};

export default MovieCard;
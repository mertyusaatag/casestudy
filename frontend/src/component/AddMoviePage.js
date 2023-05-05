import React from "react";
import {Card, Col, Row} from "react-bootstrap";
import Button from "react-bootstrap/Button";
import '../css/card.css'
import Form from "react-bootstrap/Form";
import Container from "react-bootstrap/Container";


const addMovie = () => {
    return (

       <Row>
           <Col md={3}>

           </Col>
           <Col md={6}>
               Lorem Ipsum Nedir?
               Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, adı bilinmeyen bir matbaacının bir hurufat numune kitabı oluşturmak üzere bir yazı galerisini alarak karıştırdığı 1500'lerden beri endüstri standardı sahte metinler olarak kullanılmıştır. Beşyüz yıl boyunca varlığını sürdürmekle kalmamış, aynı zamanda pek değişmeden elektronik dizgiye de sıçramıştır. 1960'larda Lorem Ipsum pasajları da içeren Letraset yapraklarının yayınlanması ile ve yakın zamanda Aldus PageMaker gibi Lorem Ipsum sürümleri içeren masaüstü yayıncılık yazılımları ile popüler olmuştur.
           </Col>
           <Col md={3}>

           </Col>

       </Row>
    );
};

export default addMovie;
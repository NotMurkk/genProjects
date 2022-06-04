import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import {Link} from 'react-router-dom'
import Button from '@mui/material/Button';

import { useParams } from 'react-router-dom';
import React, { useEffect, useState }from 'react';
import ImgCarousel from '../ImageSlider/ImageCarousel';

const PaperStyle = { height:'auto', width:'95%', margin: "20px auto"}
const PaperStyle2 = { minHeight:'600px', width:'90%', margin: "20px auto"}
const PaperStyle3 = { height:'auto', width:'0%', margin: "20px auto"}
const PaperStyle2Edited = { height:'500px', width:'40%', margin: "20px auto"}
const PaperStyle4 = { height:'87% ', width:'98%', margin: "auto auto"}

export default function SelectedBreweryEvents(){

    const [events, setEvents] = useState([]);
    const [breweries, setBreweries] = useState([]);
    const{ id } = useParams();


    function getEvents(){
        fetch("http://localhost:8081/newsAndEvents/all")
        .then(res => res.json())
        .then((result) => {
            setEvents(result);
            }
        )
    }
    const filteredEvents = events.filter(event =>{
        return event.breweryId == id;
    })

    function getBreweries(){
        fetch("http://localhost:8081/brewery/all")
        .then(res => res.json())
        .then((result) => {
            setBreweries(result);
            }
        )
    }

    const filteredBrewery = breweries.filter(brewery =>{
        return brewery.breweryId == id;
    })

    useEffect(() =>{
        getEvents();
        getBreweries();
    },[])

    return(
        <div>
            <>
            <Grid>
            
                <Paper elevation={0} style = {PaperStyle}>
                <Typography
            variant="h2"
            noWrap
            justifyContent="center"
            sx={{
              mr: 2,
              display: { xs: 'none', md: 'flex' },
              fontFamily: 'monospace',
              fontWeight: 700,
              letterSpacing: '.3rem',
              color: '#2E3B55',
              textDecoration: 'none',
            }}
          >
            {filteredBrewery.map(brewery =>(
                <div key ={brewery}>
                    NAME: {brewery.name} 
                    </div>
                    ))}

          </Typography>
                <Paper elevation={0} style = {PaperStyle2}>
                <Paper elevation={0} style = {PaperStyle3}>
                  
                    </Paper>
                    <Paper elevation={7} style = {PaperStyle2}>
                        <br/>
                    <Paper elevation={0} style = {PaperStyle4}>
                    <Grid
                        container
                        direction="row"
                        justifyContent="space-around"
                        alignItems="center"
                        >
                    {filteredEvents.map(event =>(
                <Paper elevation= {9} style = {{ minHeight:'180px',width:'40%' , margin:'10px', padding: "15px", textAlign: "center"}} key ={event}>
                  
                    <Grid container direction="row" justifyContent="flex-start" alignItems="flex-start">
                    <Typography
                            variant="h6"
                            component='a'
                            sx={{
                            display: { xs: 'none', md: 'flex' },
                            fontFamily: 'monospace',
                            fontWeight: 900,
                            color: '#2E3B55',
                            textDecoration: "none",
                            }}
                        > 
                    {event.eventName}: {event.eventDate} 

                    </Typography>
                    <Typography
                            variant="h6"
                            sx={{
                            display: { xs: 'none', md: 'flex' },
                            fontFamily: 'monospace',
                            fontWeight: 900,
                            color: '#2E3B55',
                            textDecoration: "none",
                            }}
                        > {event.eventDescription}
                    </Typography>
                    
                    </Grid>
                </Paper>
            ))}
            </Grid>
            <br/>
                    </Paper>
                    </Paper>
                    </Paper>
                </Paper> 
            </Grid>
            </>
        </div>
    )
}

import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Grid';
import ImgCarousel from '../ImageSlider/ImageCarousel';
import Typography from '@mui/material/Typography';
import {Link} from 'react-router-dom'
import { useParams } from 'react-router-dom';
import React, { useEffect, useState }from 'react';


const PaperStyle = { height:'auto', width:'95%', margin: "20px auto"}
const PaperStyle2 = { height:'auto', width:'90%', margin: "20px auto"}
const PaperStyle3 = { height:'750px', width:'90%', margin: "20px auto"}
const PaperStyle2Edited = { height:'500px', width:'500px', margin: "20px auto"}
const PaperStyle4 = { height:'98%', width:'98%', margin: "5px auto"}

export default function SelectedBreweryHome(){

    const [beers, setBeers] = useState([]);
    const [breweries, setBreweries] = useState([]);
    const [events, setEvents] = useState([]);
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


    function getBeers(){
        fetch("http://localhost:8081/beer/all")
        .then(res => res.json())
        .then((result) => {
            setBeers(result);
            }
        )
    }

    

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

    
    

    const filterdBeers = beers.filter(beer =>{
        return beer.breweryId == id;
    })


    useEffect(() =>{
        getEvents();
        getBeers();
        getBreweries();
    },[])

    

    return(
        
        <div>
           
            <>
            
            <Grid>
                <Paper elevation={0} style = {PaperStyle}>
                
            
                <Paper elevation={5} style = {PaperStyle2}>
                    <br/>
                    {filteredBrewery.map(brewery =>(
                <div key ={brewery}>
                    <Typography
                            variant="h2"
                            noWrap
                            justifyContent="center"
                            sx={{
                            display: { xs: 'none', md: 'flex' },
                            fontFamily: 'San Francisco',
                            fontWeight: 900,
                            color: '#274785',
                            }}
                        > 
                                {brewery.name}
                            </Typography> 
                    </div>
                    ))}
                <Paper elevation={0} style = {PaperStyle3}>
                <Grid container direction="row" justifyContent="center" alignItems="center" >
                    </Grid>

                    
                    <ImgCarousel/>
                   

                    
                    </Paper>
                    <Grid container direction="row" justifyContent="center" alignItems="center" >
                    <Paper elevation={5} style = {PaperStyle2Edited}>
                    <Paper elevation={5} style = {PaperStyle4}>
                    <Typography
            variant="h2"
            noWrap
            component="a"
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
          ><Link
          to={{
              pathname: `/selectedbrewery/${id}/menu`,
          }}
          >
          MENU
      </Link>
          </Typography>
                
                    <Grid container direction="row" justifyContent="center" alignItems="center">   
                         {filterdBeers.map((beer,index) =>(
                             index < 7 && (
                <Paper elevation= {2} style = {PaperStyle4} key ={beer}>
                    <Grid container direction="row" justifyContent="center" alignItems="center">
                    <Typography
                         component="a"
                            variant="p"
                            noWrap
                            justifyContent="center"
                            sx={{
                            display: { xs: 'none', md: 'flex' },
                            fontFamily: 'monospace',
                            fontWeight: 900,
                            color: '#2E3B55',
                            textDecoration: "none",
                            }}
                        > 
                    {beer.name} 
                    </Typography><br/> <br/>
                    </Grid>

                </Paper>)
            ))}
                    </Grid>
                    </Paper>
                    </Paper>
                    <Paper elevation={5} style = {PaperStyle2Edited}>
                    <Paper elevation={5} style = {PaperStyle4}>
                    <Typography
            variant="h2"
            noWrap
            component="a"
            justifyContent="center"
            sx={{
              mr: 2,
              display: { xs: 'none', md: 'flex' },
              fontFamily: 'monospace',
              fontWeight: 700,
              letterSpacing: '.3rem',
              color: '#2E3B55',
              textDecoration: "none",
            }}
          ><Link
          to={{
              pathname: `/selectedbrewery/${id}/OurEvents`,
          }}
          >
          EVENTS
      </Link>
          </Typography>
                    <Grid container direction="row" justifyContent="center" alignItems="center">   
                    {filteredEvents.map((event,index) =>(
                             index < 7 && (
                <Paper elevation= {2} style = {PaperStyle4} key ={event}>
                    <Grid container direction="row" justifyContent="center" alignItems="center">
                    <Typography
                         component="a"
                            variant="p"
                            noWrap
                            justifyContent="center"
                            sx={{
                            display: { xs: 'none', md: 'flex' },
                            fontFamily: 'monospace',
                            fontWeight: 900,
                            color: '#2E3B55',
                            textDecoration: "none",
                            }}
                        >{event.eventName}
                    </Typography> <br/><br/>
                    </Grid>

                </Paper>)
            ))}

                    </Grid>
                    </Paper>
                    </Paper>
                    </Grid>
                    
                    

                    
                    </Paper>
                </Paper> 
            </Grid>
            </>
        </div>
    )
}

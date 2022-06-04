import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import {Link} from 'react-router-dom'
import Button from '@mui/material/Button';
import React, { useEffect, useState }from 'react';
import TextField from '@mui/material/TextField';
import { useParams } from 'react-router-dom';
import axios from 'axios'
import { baseUrl } from '../../Shared/baseUrl'

const PaperStyle = { height:'auto', width:'95%', margin: "20px auto"}
const PaperStyle2 = { height:'auto', width:'90%', margin: "20px auto"}
const PaperStyle3 = { height:'auto', width:'60%', margin: "20px auto"}
const PaperStyle2Edited = { height:'500px', width:'40%', margin: "20px auto"}
const PaperStyle4 = { height:'98%', width:'98%', margin: "5px auto"}
const PaperStyle2Events = { height:'auto', width:'40%', margin: "20px auto"}
const PaperStyle4Events = { height:'98%', width:'98%', margin: "5px auto"}

export default function MyBreweryManage(props){
  const{ id } = useParams();
    const [beers, setBeers] = useState([]);
    const [breweries, setBreweries] = useState([]);
    const [events, setEvents] = useState([]);

    const [name, setBeerName] = useState("");
    const [type, setBeerType] = useState("");
    const [abv, setBeerABV] = useState();
    const [image, setBeerImage] = useState("");
    const [description, setBeerDescription] = useState("");
    const breweryId = id;

    const [eventName, setEventName] = useState("");
    const [eventDate, setEventDate] = useState("");
    const [eventDescription, setEventDescription] = useState("");
    
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
  function deleteEvent(event) {
    fetch(baseUrl + "/newsAndEvents/delete/" + event , {
      method: 'DELETE',
      headers: {
        'Authorization' : `Bearer ${props.props.token.token}`}
  })
      .then((response) => {
          return response.text();
      })
      .then((data) => {
        getEvents();
      })
  }
    
    
    
    function getBeers(){
      fetch("http://localhost:8081/beer/all")
      .then(res => res.json())
      .then((result) => {
          setBeers(result);
          }
      )
  }
  const filterdBeers = beers.filter(beer =>{
    return beer.breweryId == id;
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

function deleteBeerItem(beerId) {
  fetch(baseUrl + "/beer/delete/" + beerId , {
    method: 'DELETE',
    headers: {
      'Authorization' : `Bearer ${props.props.token.token}`}
})
    .then((response) => {
        return response.text();
    })
    .then((data) => {
      getBeers();
    })
}

  useEffect(() =>{
    getEvents();
    getBeers();
    getBreweries();
  },[])


const handleClick=(e) =>{
        e.preventDefault()
        const beer = {breweryId, name, type, abv, image, description}
      if(name !== "" && type !== "" && abv > 0 && abv < 100 && description !== "" ){
        axios.post('http://localhost:8081/beer/create',
          {breweryId, name, type, abv, image, description}, 
        {headers: {
          'Authorization' : `Bearer ${props.props.token.token}`}
        })
        getBeers();
        getBeers();
        alert("Item Created")
      }else{
        alert("Error Creating Item")
      }
        
}

const handleEventSubmit =(e) =>{
  e.preventDefault()
  if(eventName !== "" && eventDate !== "" && eventDescription !== ""){
axios.post('http://localhost:8081/newsAndEvents/create',
    {breweryId, eventName, eventDate, eventDescription}, 
  {headers: {
    'Authorization' : `Bearer ${props.props.token.token}`}
  })
  getEvents();
  getEvents();
  alert("Event Created")
  }else{
    alert("Error Creating Event")
  }
  
}

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
                    {brewery.name} DASHBOARD
                    </div>
                    ))} 
          </Typography>
                <Paper elevation={5} style = {PaperStyle2}>
                <Paper elevation={0} style = {PaperStyle3}>
                <Grid container direction="row" justifyContent="center" alignItems="center" >
                        
                <Typography
            variant="h5"
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
          >Mange Your Brewery
          </Typography>
                    </Grid>

                    
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
          >
            ADD ITEMS 
          </Typography>
                    <Grid container direction="row" justifyContent="center" alignItems="center">   
                    <form>
                <Grid container spacing={1}>
                  <Grid xs={12} sm={6} item>
                    <TextField placeholder="Enter Beer Name" label="Beer Name" variant="outlined" fullWidth value={name} onChange={(e) => setBeerName(e.target.value)} required />
                  </Grid>
                  <Grid xs={12} sm={6} item>
                    <TextField placeholder="Enter Beer Type" label="Beer Type" variant="outlined" fullWidth value={type} onChange={(e) => setBeerType(e.target.value)} required />
                  </Grid>
                  <Grid item xs={12} sm={12}>
                  <TextField type="number" value={abv} onChange={(e) => setBeerABV(e.target.value)} 
                                placeholder="Enter ABV" label="ABV" variant="outlined"
                                InputProps={{ inputProps: { min: "1", max: "100", step: "1" } }}
                                fullWidth required />
                  </Grid>
                  <Grid item xs={12}>
                    <TextField label="Beer Description" multiline rows={4} placeholder="Enter Brewery Descriptioon" variant="outlined" fullWidth  value={description} onChange={(e) => setBeerDescription(e.target.value)} required />
                  </Grid>
                  <Grid item xs={12}>
                    <Button type="submit" variant="contained" color="primary" fullWidth onClick={handleClick}>Add Item</Button>
                  </Grid>
  
                </Grid>
              </form>

                        
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
              textDecoration: 'none',
            }}
          >
            ADD EVENTS
          </Typography>
                    <Grid container direction="row" justifyContent="center" alignItems="center">   
                    <form>
                <Grid container spacing={1}>
                  <Grid xs={12} sm={6} item>
                    <TextField placeholder="Enter Event Name" label="Event Name" variant="outlined" fullWidth  value={eventName} onChange={(e) => setEventName(e.target.value)} required />
                  </Grid>
                  <Grid xs={12} sm={6} item>
                    <TextField  type="date" placeholder="Enter Event Date" label="Event Date" variant="outlined" fullWidth required  value={eventDate} onChange={(e) => setEventDate(e.target.value)}/>
                  </Grid>
                  <Grid item xs={12}>
                    <TextField label="Event Description" multiline rows={7} placeholder="Enter Event Description" variant="outlined" fullWidth value={eventDescription} onChange={(e) => setEventDescription(e.target.value)} required />
                  </Grid>
                  <Grid item xs={12}>
                    <Button type="submit" variant="contained" color="primary" fullWidth onClick={handleEventSubmit} >Add Event</Button>
                  </Grid>
  
                </Grid>
              </form>

                        
                    </Grid>
                    </Paper>
                    </Paper>
                    </Grid>





                    <Grid
  container
  direction="row"
  justifyContent="center"
  alignItems="flex-start"
>
                    <Paper elevation={5} style = {PaperStyle2Events}>
                    <Paper elevation={0} style = {PaperStyle4Events}>
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
          >
            MANAGE MENU
          </Typography>
                    <Grid container direction="row" justifyContent="flex-end" alignItems="center">   
                    {/* <img src={image7}></img> */}
                    {filterdBeers.map((beer,index) =>(
                              (
                <Paper elevation= {5} style = {PaperStyle4} key ={beer}>
                    <Grid container direction="column" justifyContent="flex-start" alignItems="flex-start">
                    <Typography
                            variant="p"
                            noWrap
                            sx={{
                            display: { xs: 'none', md: 'flex' },
                            fontFamily: 'monospace',
                            fontWeight: 900,
                            color: '#2E3B55',
                            textDecoration: "none",
                            }}
                        > 
                    NAME: {beer.name} 
                    </Typography>
                    <Typography
                            variant="p"
                            component="a"
                            noWrap
                            sx={{
                            display: { xs: 'none', md: 'flex' },
                            fontFamily: 'monospace',
                            fontWeight: 900,
                            color: '#2E3B55',
                            textDecoration: "none",
                            }}
                        > 
                    ABV: { beer.abv}% ABV
                    </Typography>
                    <Typography
                            variant="p"
                            component="a"
                            noWrap
                            sx={{
                            display: { xs: 'none', md: 'flex' },
                            fontFamily: 'monospace',
                            fontWeight: 900,
                            color: '#2E3B55',
                            textDecoration: "none",
                            }}
                        > 
                    TYPE :{ beer.type} 
                    </Typography>
                    <Grid container direction="row" justifyContent="flex-end" alignItems="center">   
                        <Button onClick={()=> deleteBeerItem(beer.beerId) }>
                            <Typography variant='p'>
                            DELETE ITEM
                            </Typography>
                        </Button>
                    </Grid>
                    </Grid>
                    

                </Paper>)
            ))}
                    
                    </Grid>
                    </Paper>
                    </Paper>


                    
                    <Paper elevation={5} style = {PaperStyle2Events}>
                    <Paper elevation={0} style = {PaperStyle4Events}>
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
          >
            MANAGE EVENTS
          </Typography>
                    <Grid container direction="row" justifyContent="flex-end" alignItems="center">   
                    {/* <img src={image7}></img> */}
                    {filteredEvents.map((event,index) =>(
                      (
                        <Paper elevation= {5} style = {PaperStyle4} key ={event}>
                    <Grid container direction="column" justifyContent="flex-start" alignItems="flex-start">
                    <Typography variant="p"
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
                        EVENT NAME:  {event.eventName}
                    </Typography>
                    <Typography variant="p"
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
                        EVENT DATE:  {event.eventDate}
                    </Typography>
                    <Grid container direction="row" justifyContent="flex-end" alignItems="center">   
                        <Button onClick={()=> deleteEvent(event.newsEventsId) }>
                            <Typography variant='p'>
                            DELETE ITEM
                            </Typography>
                        </Button>
                    </Grid>
                    </Grid>

                </Paper>
                      )
                    ))}
                   
                    
                    </Grid>
                    </Paper>
                    </Paper>
                    </Grid>
                    <br/>
                    </Paper>
                </Paper> 
            </Grid>
           
            </>
        </div>
    )
}

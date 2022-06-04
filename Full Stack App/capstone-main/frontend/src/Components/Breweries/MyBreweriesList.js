import {Link} from 'react-router-dom'
import Button from '@mui/material/Button';
import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import React, { useEffect, useState }from 'react';
import { baseUrl } from '../../Shared/baseUrl'

const PaperStyle4 = { height:'98%', width:'98%', margin: "5px auto"}

const PaperStyle4Events = { height:'98%', width:'98%', margin: "5px auto"}

export default function MyBreweriesList({user,props}){
    const [breweries, setBreweries] = useState([]);
    function getBreweries(){
        fetch("http://localhost:8081/brewery/all")
        .then(res => res.json())
        .then((result) => {
            setBreweries(result);
            }
        )
    }
   const filteredBreweries = breweries.filter(brewery =>{
    return brewery.ownerId === props;
    })
    function deleteBrewery(breweryID) {
        fetch(baseUrl + "/brewery/delete/" + breweryID , {
          method: 'DELETE',
          headers: {
            'Authorization' : `Bearer ${user.token}`}
      })
          .then((response) => {
              return response.text();
          })
          .then((data) => {
            getBreweries();
          })
      }



    useEffect(() =>{
        getBreweries();
    },[])


    return(
        <div>
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
            MANAGE BREWERIES
          </Typography>
                    <Grid container direction="row" justifyContent="flex-end" alignItems="center">   
                    {/* <img src={image7}></img> */}
                    <Paper elevation={0} style = {PaperStyle4}>
                    {filteredBreweries.map(brewery =>(
                <Paper elevation={3} style = {PaperStyle4}><div key ={brewery}>
                    <Typography
                            variant="h6"
                            noWrap
                            component="a"
                            justifyContent="flex-start"
                            sx={{
                            display: { xs: 'none', md: 'flex' },
                            fontFamily: 'San Francisco',
                            fontWeight: 900,
                            color: '#2E3B55',
                            textDecoration: 'none',
                            }}
                        > 
                                {brewery.name}
                            </Typography> 
                    <Grid container direction="row" justifyContent="flex-end" alignItems="center">   
                        <Button>
                            <Typography variant='p'>
                                <Link 
                                to={{
                                    pathname: `/MyBrewery/${brewery.breweryId}/manage`,
                                }}
                                >
                                MANAGE BREWERY</Link>
                            </Typography>
                        </Button>
                        <Grid container direction="row" justifyContent="flex-end" alignItems="center">   
                        <Button onClick={()=> deleteBrewery(brewery.breweryId) }>
                            <Typography 
                            sx={{
                                display: { xs: 'none', md: 'flex' },
                                fontFamily: 'monospace',
                                fontWeight: 900,
                                color: '#2E3B55',
                                }}
                            variant='p'>
                            DELETE BREWERY
                            </Typography>
                        </Button>
                    </Grid>
                    </Grid>
                    </div></Paper>
                    ))}<br/>
                    </Paper>
                    </Grid>
                    </Paper>
        </div>
    )
}
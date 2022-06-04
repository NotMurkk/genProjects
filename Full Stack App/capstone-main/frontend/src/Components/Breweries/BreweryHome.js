import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Grid';
import ImgCarousel from '../ImageSlider/ImageCarousel';
import Typography from '@mui/material/Typography';
import React, { useEffect, useState }from 'react';
import Button from '@mui/material/Button';
import SelectedBrewery from './SelectedBreweryHome';
import axios from 'axios'
import { baseUrl } from '../../Shared/baseUrl'
import {Link} from 'react-router-dom'


const PaperStyle = { height:'auto', width:'95%', margin: "20px auto"}
const PaperStyle2 = { height:'auto', width:'90%', margin: "20px auto"}
const PaperStyle3 = { height:'900px', width:'90%', margin: "20px auto"}
const PaperStyle4 = { height:'auto', width:'99%', margin: "auto auto"}




export default function BreweryHome(props){
    
    const [breweries, setBreweries] = useState([]);
    const breweryName = "Test Brewery";
    
    function getBreweries(){
        fetch("http://localhost:8081/brewery/all")
        .then(res => res.json())
        .then((result) => {
            setBreweries(result);
            }
        )
    }

    useEffect(() =>{
        getBreweries();
    },[])

    
    function addToFavorites(breweryName){
        axios.post(baseUrl + "/brewery/favorites/" + props.props + "/add/" + breweryName)
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
              fontFamily: 'San Francisco',
              fontWeight: 600,
              letterSpacing: '.3rem',
              color: '#2E3B55',
              textDecoration: 'none',
            }}
          >
            DRINK WHERE YOUR BEER IS MADE 
            
          </Typography>
                <Paper elevation={0} style = {PaperStyle2}>
                <Paper elevation={0} style = {PaperStyle3}>
                    <ImgCarousel/>
                   

                    <Typography
            variant="h2"
            noWrap
            justifyContent="center"
            sx={{
              mr: 2,
              display: { xs: 'none', md: 'flex' },
              fontFamily: 'San Francisco',
              fontWeight: 700,
              letterSpacing: '.3rem',
              color: '#2E3B55',
              textDecoration: 'none',
            }}
          >
            FIND A BREWERY NEAR YOU
          </Typography>
                    </Paper>
                    <Paper elevation={0} style = {PaperStyle2}>
                        <br/>
                    <Paper elevation={0} style = {PaperStyle4}>
                        
                    {breweries.map(brewery =>(
                <Paper elevation= {9} style = {{margin:'10px', padding: "15px", textAlign: "center"}} key ={brewery.breweryId}>
                    
                    
                    <Typography
                            variant="h4"
                            noWrap
                            justifyContent="flex-start"
                            sx={{
                            display: { xs: 'none', md: 'flex' },
                            fontFamily: 'San Francisco',
                            fontWeight: 900,
                            color: '#274785',
                            }}
                        > 
                                {brewery.name}
                            </Typography> 
                            <Typography
                            variant="p"
                            noWrap
                            justifyContent="flex-start"
                            sx={{
                            display: { xs: 'none', md: 'flex' },
                            fontFamily: 'monospace',
                            fontWeight: 900,
                            color: '#abc1eb',
                            }}
                        > 
                                {brewery.address}
                            </Typography>
                            <Typography
                            variant="subtitle2: 'h6'"
                            Wrap
                            justifyContent="center"
                            sx={{
                            display: { xs: 'none', md: 'flex' },
                            fontFamily: 'San Francisco',
                            fontWeight: 900,
                            color: '#2E3B55',
                            }}
                        > 
                                {brewery.description}
                            </Typography>
                            <Grid container direction="row" justifyContent="flex-end" alignItems="center">   
                        
                        
                    </Grid>
                    
                    <Grid container direction="row" justifyContent="flex-end" alignItems="center">   
                        <Button>
                        <Typography
                            variant="p"
                            noWrap
                            justifyContent="center"
                            sx={{
                            display: { xs: 'none', md: 'flex' },
                            fontFamily: 'monospace',
                            fontWeight: 900,
                            color: '#2E3B55',
                            }}
                        >
                                
                            <Link
                                to={{
                                    pathname: `/brewery/${brewery.breweryId}`,
                                }}
                                >
                                VISIT
                                </Link>
                            </Typography>
                        </Button> 
                        
                        
                    </Grid>
                    <Grid container direction="row" justifyContent="flex-end" alignItems="center">  
                    <Button onClick ={()=> addToFavorites(brewery.name)}>
                        <Typography
                            variant="p"
                            noWrap
                            justifyContent="center"
                            sx={{
                            display: { xs: 'none', md: 'flex' },
                            fontFamily: 'monospace',
                            fontWeight: 900,
                            color: '#2E3B55',
                            }}
                        > 
                                ADD TO FAVORITES
                            </Typography>
                        </Button> 
                        </Grid>

                </Paper>
            ))}
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

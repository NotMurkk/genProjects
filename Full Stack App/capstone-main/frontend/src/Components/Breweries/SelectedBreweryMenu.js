import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Grid';
import ImgCarousel from '../ImageSlider/ImageCarousel';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import axios from 'axios'
import { useParams } from 'react-router-dom';
import React, { useEffect, useState }from 'react';
import { baseUrl } from '../../Shared/baseUrl'
import Box from '@mui/material/Box';
import Modal from '@mui/material/Modal';
import TextField from '@mui/material/TextField';
import { Description } from '@mui/icons-material';
import { height } from '@mui/system';
import ReviewModal from './ReviewModal';



const PaperStyle = { height:'auto', width:'95%', margin: "20px auto"}
const PaperStyle2 = { height:'auto', width:'90%', margin: "20px auto"}
const PaperStyle3 = { height:'850px', width:'100%', margin: "40px auto"}
const PaperStyle2Edited = { minHeight: 350, width:'30%', margin: "20px auto"}
const PaperStyle4 = { height:'650px', width:'48%', margin: "auto auto"}

const LeaveStyle = { height:'50%', width:'80%', margin: "10px auto"}
const ReviewStyle = { height:'99%', width:'80%', margin: "10px auto"}

export default function SelectedBreweryMenu(props){

    const [beers, setBeers] = useState([]);
    const [breweries, setBreweries] = useState([]);
    const [rating, setRating] = useState();
    const [review, setReview] = useState("");
    const{ id } = useParams();
    const author = props.props.user.username;
    const beer_id = 1;
    const handleReviewClick=(e) =>{
        e.preventDefault()
      
        axios.post('http://localhost:8081/review/create',
          {beer_id, author, rating, review},
          {headers: {
            'Authorization' : `Bearer ${props.props.token.token}`}
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
        getBeers();
        getBreweries();
    },[])

    function addToFavorites(beerName){
        axios.post(baseUrl + "/beer/favorites/" + props.props.user.username + "/add/" + beerName)
    }
    
    const style = {
        position: 'absolute',
        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        bgcolor: 'background.paper',
        border: '2px solid #000',
        boxShadow: 13,
        height:'70%',
        pt: 2,
        px: 4,
        pb: 3,
      };
    
      const [open, setOpen] = React.useState(false);
        const handleOpen = () => {
            setOpen(true);
        };
        const handleClose = () => {
            setOpen(false);
        };


      const [isReadMoreShown, setReadMoreShown] = useState(true);

      const toggleBtn = () =>{
          setReadMoreShown(prevState => !prevState)
      }

    return(
        <div>
            <>
            <Grid>
                <Paper elevation={5} style = {PaperStyle}>
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
                <Paper elevation={0} style = {PaperStyle2}>
                <Paper elevation={0} style = {PaperStyle3}>
                <Grid container direction="row" justifyContent="center" alignItems="center" >
                    </Grid>

                    
                    <ImgCarousel/>
                   

                    <Typography
                    
            variant="h2"
            noWrap
            justifyContent="center"
            sx={{
              mr: 2,
              display: { xs: 'none', md: 'flex' },
              fontFamily: 'monospace',
              fontWeight: 700,
              letterSpacing: '.2rem',
              color: '#2E3B55',
              textDecoration: 'none',
            }}
          >
            VIEW OUR BEERS BELOW  
          </Typography> 
                    </Paper>
                    <Grid container direction="column" justifyContent="center" alignItems="center" >
                    <Grid container direction="row" justifyContent="center" alignItems="center">   
                         {filterdBeers.map(beer =>(
                <Paper elevation={5} style = {PaperStyle2Edited} key = {beer}>
                    <Grid container direction="row" justifyContent="flex-end" alignItems="center">   
                    <Button onClick ={()=> addToFavorites(beer.name)}>
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
                                ADD TO FAVORITE
                            </Typography>
                        </Button>
                    </Grid>
                    
                    <Grid container direction="column" justifyContent="flex-start" alignItems="flex-start">
                    <Typography
                            variant="h6"
                            noWrap
                            sx={{
                            display: { xs: 'none', md: 'flex' },
                            fontFamily: 'monospace',
                            fontWeight: 900,
                            color: '#2E3B55',
                            textDecoration: "none",
                            }}
                        > 
                    {beer.name} 
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
                    { beer.abv}% ABV
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
                    { beer.type} 
                    </Typography>
                    
                    {isReadMoreShown ?<Typography
                            variant="subtitle2: 'h6'"
                            Wrap
                            justifyContent="center"
                            sx={{
                            display: { xs: 'none', md: 'flex' },
                            fontFamily: 'San Francisco',
                            fontWeight: 900,
                            color: '#2E3B55',
                            }}
                        > {beer.description.substr(0,300)} </Typography> :  <Typography
                        variant="subtitle2: 'h6'"
                        Wrap
                        justifyContent="center"
                        sx={{
                        display: { xs: 'none', md: 'flex' },
                        fontFamily: 'San Francisco',
                        fontWeight: 900,
                        color: '#2E3B55',
                        }}
                    > {beer.description} </Typography>}
                    <Button onClick={toggleBtn}>
                        {isReadMoreShown ? <Typography
                            variant="p"
                            noWrap
                            justifyContent="center"
                            sx={{
                            display: { xs: 'none', md: 'flex' },
                            fontFamily: 'monospace',
                            fontWeight: 900,
                            color: '#909ba7',
                            }}
                        > 
                                READ MORE
                            </Typography> : <Typography
                            variant="p"
                            noWrap
                            justifyContent="center"
                            sx={{
                            display: { xs: 'none', md: 'flex' },
                            fontFamily: 'monospace',
                            fontWeight: 900,
                            color: '#909ba7',
                            }}
                        > 
                                READ LESS
                            </Typography>} 
                        </Button><br/>
                        
                    <Grid container direction="row" justifyContent="flex-end" alignItems="center">   
                    <ReviewModal name={props.props} props={beer.beerId}/>
                    </Grid>
                    </Grid>
                    
                </Paper>
            ))}
                    </Grid>
                  
                    </Grid>
                    
                    </Paper>
                </Paper> 
            </Grid>
            </>
        </div>
    )
}

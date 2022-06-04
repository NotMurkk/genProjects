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
import Rating from '@mui/material/Rating';
import PersonIcon from '@mui/icons-material/Person';
import Avatar from '@mui/material/Avatar';
import Stack from '@mui/material/Stack';

const PaperStyle = { height:'auto', width:'95%', margin: "20px auto"}
const PaperStyle2 = { height:'auto', width:'70%', margin: "20px auto"}
const PaperStyle3 = { height:'auto', width:'60%', margin: "20px auto"}
const PaperStyle2Edited = { minHeight: 350, width:'30%', margin: "20px auto"}
const PaperStyle4 = { height:'650px', width:'48%', margin: "auto auto"}

const LeaveStyle = { height:'50%', width:'80%', margin: "10px auto"}
const ReviewStyle = { height:'99%', width:'80%', margin: "10px auto"}


export default function ReviewModal({name,props}){
    
    const [reviews, setReviews] = useState([]);
    const [rating, setRating] = useState();
    const [review, setReview] = useState("");
    const beer_id = props;
    
    const author = name.user.username;
    const handleReviewClick=(e) =>{
        e.preventDefault()
      if(rating  < 6 && rating > 0){
          axios.post('http://localhost:8081/review/create',
          {beer_id, author, rating, review},
          {headers: {
            'Authorization' : `Bearer ${name.token.token}`}
          })
          alert("Review Created");
        }else{
            alert("Please enter a number between 1-5");
        }
          
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

    function getReviews(){
        fetch("http://localhost:8081/review/all")
        .then(res => res.json())
        .then((result) => {
            setReviews(result);
            }
        )
    
    }
    const filteredReviews = reviews.filter(review =>{
        return review.beer_id == props;
    })


    const [open, setOpen] = React.useState(false);
        const handleOpen = () => {
            setOpen(true);
        };
        const handleClose = () => {
            setOpen(false);
        };

    useEffect(() =>{
        getReviews();

    },[])

   
    return(
        <>
<Grid container direction="row" justifyContent="flex-end" alignItems="center">   
                        
                    <Button onClick={handleOpen}>
                        <Typography
                            variant="p"
                            component="a"
                            noWrap
                            justifyContent="center"
                            sx={{
                            display: { xs: 'none', md: 'flex' },
                            fontFamily: 'monospace',
                            fontWeight: 900,
                            color: '#2E3B55',
                            }}
                        >REVIEWS</Typography>
                    </Button> 
                            
                    
                        <Modal
                            open={open}
                            onClose={handleClose}   
                            aria-labelledby="parent-modal-title"
                            aria-describedby="parent-modal-description"
                        >
                            <Box sx={{ ...style, width: '1300px'}}>
                            <Grid
                                container
                                direction="row"
                                justifyContent="center"
                                alignItems="center"
                                >
                                 <Paper elevation={0} style = {PaperStyle4} >
                                 <Typography
                            variant="h3"
                            noWrap
                            justifyContent="center"
                            sx={{
                            display: { xs: 'none', md: 'flex' },
                            fontFamily: 'monospace',
                            fontWeight: 900,
                            color: '#2E3B55',
                            }}
                        > 
                                REVIEWS
                            </Typography>
                            <Paper elevation={0}>
                            {filteredReviews.map((review, index) =>(
                                index < 6  &&  (
                                 <Paper elevation={5}  style = {ReviewStyle} key = {review}>
                                     
                                     <Stack
  direction="row"
  justifyContent="flex-start"
  alignItems="center"
  spacing={2}
>
                            <Avatar> {review.author.substr(0,1)}</Avatar>
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
                                {review.author}
                            </Typography>
                                </Stack>
                                <Rating name="read-only" value={review.rating} readOnly /><br/>
                                {
                                    review.review.length === 0 ? null : 
                                    <Typography
                                        variant="p"
                                        justifyContent="flex-start"
                                        sx={{
                                        display: { xs: 'none', md: 'flex' },
                                        fontFamily: 'monospace',
                                        fontWeight: 500,
                                        color: '#2E3B55',
                                        }}
                                    > 
                                    "{review.review}"
                                    </Typography>
                                }
                                
                                   
                                </Paper>)
                                    ))}

                            </Paper>
                                 </Paper>
                                 <Paper elevation={0} style = {PaperStyle4} >
                                 <Typography
                            variant="h3"
                            noWrap
                            justifyContent="center"
                            sx={{
                            display: { xs: 'none', md: 'flex' },
                            fontFamily: 'monospace',
                            fontWeight: 900,
                            color: '#2E3B55',
                            }}
                        > 
                                LEAVE REVIEW
                            </Typography>
                            <Paper elevation={0} style={LeaveStyle}>
                            <Grid container direction="row" justifyContent="center" alignItems="flex-center">   
                    <form>
                            <Grid container spacing={1}>
                            
                            <Grid xs={12} item>
                                <TextField type="number" value={rating} onChange={(e) => setRating(e.target.value)} 
                                placeholder="Enter Rating" label="Rating" variant="outlined"
                                InputProps={{ inputProps: { min: "1", max: "5", step: "1" } }}
                                fullWidth required />
                            </Grid>
                            <Grid item xs={12}>
                                <TextField label="Review" multiline rows={4} value={review} onChange={(e) => setReview(e.target.value)} 
                                placeholder="Enter Beer Review" variant="outlined" fullWidth  />
                            </Grid>
                            <Grid item xs={12}>
                                <Button type="submit" variant="contained" color="primary" fullWidth onClick={handleReviewClick} >Add Item</Button>
                            </Grid>
            
                            </Grid>
                        </form>

                    </Grid>

                            </Paper>
                                 </Paper>
                            </Grid>
                            </Box>
                        </Modal>
                    </Grid>
                    </>
    )
}
import React from 'react';
import { Card, CardContent } from '@mui/material'
import Button from '@mui/material/Button';
import Stack from '@mui/material/Stack';
import Avatar from '@mui/material/Avatar';
import LockIcon from '@mui/icons-material/Lock';
import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Grid';
import TextField from '@mui/material/TextField';
import Typography from '@mui/material/Typography';
import EmailIcon from '@mui/icons-material/Email';
import ImgSlider from '../ImageSlider/Slider';
import HomeAppbar from '../Pages/HomeAppbar';

const PaperStyle = {height:'70vh', width:450, margin: "30px auto"}
const AvatarStyle = {backgroundColor: '#2E3B55'}
function ContactUs() {


    return (
      <div>
        <HomeAppbar/>
        <ImgSlider/>
          <Grid>
                <Paper elevation={10} style = {PaperStyle}>
                <Grid  align="center">
                         <br/>
                <Avatar style = {AvatarStyle}><EmailIcon/></Avatar>
                <br/>
                <h2>Contact Us</h2>
                <br/>
                <Typography variant="body2" color="textSecondary" component="p" gutterBottom>
                Fill up the form and our team will get back to you within 24 hours.
                </Typography>
                <Card style={{ maxWidth: 450, padding: "50px 5px", margin: "0 auto" }}>
                    
            <CardContent>
            <form>
                <Grid container spacing={1}>
                  <Grid xs={12} sm={6} item>
                    <TextField placeholder="Enter first name" label="First Name" variant="outlined" fullWidth required />
                  </Grid>
                  <Grid xs={12} sm={6} item>
                    <TextField placeholder="Enter last name" label="Last Name" variant="outlined" fullWidth required />
                  </Grid>
                  <Grid item xs={12}>
                    <TextField type="email" placeholder="Enter email" label="Email" variant="outlined" fullWidth required />
                  </Grid>
                  <Grid item xs={12}>
                    <TextField type="number" placeholder="Enter phone number" label="Phone" variant="outlined" fullWidth required />
                  </Grid>
                  <Grid item xs={12}>
                    <TextField label="Message" multiline rows={4} placeholder="Type your message here" variant="outlined" fullWidth required />
                  </Grid>
                  <Grid item xs={12}>
                    <Button type="submit" variant="contained" color="primary" fullWidth>Submit</Button>
                  </Grid>
  
                </Grid>
              </form>
            </CardContent>
          </Card>
                </Grid>
                </Paper> 
                </Grid>
        
      </div>
    );
  }
  
  export default ContactUs;
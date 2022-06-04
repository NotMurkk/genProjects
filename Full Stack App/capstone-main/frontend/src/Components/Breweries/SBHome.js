import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Grid';
import ImgCarousel from '../ImageSlider/ImageCarousel';
import Typography from '@mui/material/Typography';
import React from "react";

const PaperStyle = { height:'auto', width:'95%', margin: "20px auto"}
const PaperStyle2 = { height:'auto', width:'90%', margin: "20px auto"}
const PaperStyle3 = { height:'auto', width:'60%', margin: "20px auto"}
const PaperStyle2Edited = { height:'500px', width:'500px', margin: "20px auto"}
const PaperStyle4 = { height:'98%', width:'98%', margin: "5px auto"}
class SBHome extends React.Component {
    state = {
      user: null
    }

    componentDidMount () {
      const { id } = this.props.match.params
  
      fetch(`http://localhost:8081/brewery/${id}`)
        .then((user) => {
          this.setState(() => ({ user }))
        })
    }
    
    render() {
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
            *BREWERY NAME GOES HERE*
          </Typography>
                <Paper elevation={5} style = {PaperStyle2}>
                <Paper elevation={0} style = {PaperStyle3}>
                <Grid container direction="row" justifyContent="center" alignItems="center" >
                        *BREWERY IMAGES GO HERE*
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
            href="/SelectedBrewery/menu"
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
            MENU 
          </Typography>
                    <Grid container direction="row" justifyContent="center" alignItems="center">   
                        <Typography align='center'>
                            We're a project brewery that likes to push boundaries and experiment with methods and ingredients!
                        </Typography>
                        <Typography align='center'>
                            We're a project brewery that likes to push boundaries and experiment with methods and ingredients!
                        </Typography>
                        <Typography align='center'>
                            We're a project brewery that likes to push boundaries and experiment with methods and ingredients!
                        </Typography>

                        <Typography align='center'>
                            We're a project brewery that likes to push boundaries and experiment with methods and ingredients!
                        </Typography>
                        <Typography align='center'>
                            We're a project brewery that likes to push boundaries and experiment with methods and ingredients!
                        </Typography>
                        <Typography align='center'>
                            We're a project brewery that likes to push boundaries and experiment with methods and ingredients!
                        </Typography>
                        <Typography align='center'>
                            We're a project brewery that likes to push boundaries and experiment with methods and ingredients!
                        </Typography>
                        <Typography align='center'>
                            We're a project brewery that likes to push boundaries and experiment with methods and ingredients!
                        </Typography>

                        
                    </Grid>
                    </Paper>
                    </Paper>
                    <Paper elevation={5} style = {PaperStyle2Edited}>
                    <Paper elevation={5} style = {PaperStyle4}>
                    <Typography
            variant="h2"
            noWrap
            component="a"
            href="/SelectedBrewery/events"
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
            EVENTS 
          </Typography>
                    <Grid container direction="row" justifyContent="flex-end" alignItems="center">   
                    {/* <img src={image7}></img> */}
                        <Typography align='center'>
                        From our annual 4/05 Day celebration to an entire festival dedicated to our mixed fermentation sours, we believe in making memorable experiences while enjoying beer.
                        </Typography>
                        
                    </Grid>
                    </Paper>
                    </Paper>
                    </Grid>
                    
                    

                    <Grid  align="left">
                    <p>
                        fasdfa
                        sdf
                        Afd
                        a
                        df
                        a
                        defamationa
                    </p>
                    </Grid>
                    </Paper>
                </Paper> 
            </Grid>
            </>
        </div>
        )
    }
  }
  export default SBHome;
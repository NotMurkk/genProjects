import { Component } from 'react'
import {Link} from 'react-router-dom'
import {connect} from 'react-redux'
import {withRouter} from 'react-router-dom'
import {addToken, addUser} from '../../Redux/actionCreators'
import {baseUrl} from '../../Shared/baseUrl'
import axios from 'axios'
import Button from '@mui/material/Button';
import Stack from '@mui/material/Stack';
import Avatar from '@mui/material/Avatar';
import LockIcon from '@mui/icons-material/Lock';
import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Grid';
import TextField from '@mui/material/TextField';
import Typography from '@mui/material/Typography';
import Box from '@mui/material/Box';
import ImgSlider from '../ImageSlider/Slider'
import HomeAppbar from '../Pages/HomeAppbar'



const mapDispatchToProps = (dispatch) => ({
    addToken: () =>  dispatch(addToken()),
    addUser: () => dispatch(addUser()) 
});

const PaperStyle = {Padding: '50px 20px', height:'70vh', width:450, margin: "20px auto"}
const AvatarStyle = {backgroundColor: '#2E3B55'}

class Login extends Component {
    
    constructor(props){
        super(props);
        this.state = {
            username: '',
            password: ''
        }
        this.handleInputChange = this.handleInputChange.bind(this);
    }
    

    handleLogin = async () => {
        const data = { username: this.state.username, password: this.state.password };
        

        const userWithToken = await axios.post(baseUrl + '/login', data)

        
        await this.props.dispatch(addToken(userWithToken.data.token))
        await this.props.dispatch(addUser(userWithToken.data.user));
    }

    handleInputChange = (event) => {
        event.preventDefault()
        this.setState({
            [event.target.name]: event.target.value
        })
    }

    render(){
        return(
            <div>
                <HomeAppbar/>
                <ImgSlider/>
                <Grid>
                <Paper elevation={10} style = {PaperStyle}>
                     <Grid align="center">
                         <br/>
                <Avatar style = {AvatarStyle}><LockIcon/></Avatar>
                <br/>
                <h2>Please Sign In</h2>
                
                <br/>
                
                <TextField style = {{width: 300}}   label ="Username" placeholder ="Username" type="text" id="username" 
                    name="username"  v-model="user.username" variant="standard" 
                    onChange={this.handleInputChange} required />
                    <br/>
                <TextField style = {{width: 300}} label ="Password" placeholder ="Password" type ="password" id="password"
                    name="password"   v-model="user.password" variant="standard" 
                    onChange={this.handleInputChange} required />



                {/* <label class="sr-only">Username</label>
                <input
                    type="text"
                    id="username"
                    name="username"
                    class="form-control"
                    placeholder="Username"
                    v-model="user.username"
                    onChange={this.handleInputChange}
                    required
                />
                <label class="sr-only">Password</label>
                <input
                    type="password"
                    id="password"
                    name="password"
                    class="form-control"
                    placeholder="Password"
                    v-model="user.password"
                    onChange={this.handleInputChange}
                    required
                /> */}
                <br/>
                <Stack  spacing={2} direction="column">
                <br/> 
                <Box textAlign='center'>
                <Button style = {{ width: 300}} size="large" variant="contained" color="primary" type="submit" onClick={this.handleLogin}> Sign in</Button>
                </Box>
                <Typography> Need an Account? 
                    <Link to="/register"> Sign up Here</Link>
                </Typography>
                
                </Stack>
                </Grid>
                </Paper> 
                </Grid>
            </div>
        )
    }
}

export default withRouter(connect(mapDispatchToProps)(Login));
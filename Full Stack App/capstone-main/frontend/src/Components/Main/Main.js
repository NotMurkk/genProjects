import {Component} from 'react'
import {Switch, Route, Redirect, Link} from 'react-router-dom'
import Login from '../Login/Login'
import Register from '../Register/Register'
import Home from '../Home/Home'
import { addToken, deleteUser, fetchBreweries } from '../../Redux/actionCreators'
import {connect} from 'react-redux'
import {withRouter} from 'react-router-dom'
import Footer from '../Pages/Footer'
import ContactUs from '../Contact/Contact'
import TermsOfService from '../TOS/TermsOfService'
import PrivacyPolicy from '../Privacy/PrivacyPolicy'
import BreweryHome from '../Breweries/BreweryHome'
import SelectedBreweryMenu from '../Breweries/SelectedBreweryMenu'
import SelectedBreweryHome from '../Breweries/SelectedBreweryHome'
import SelectedBreweryEvents from '../Breweries/SelecttedBreweryEvents'
import PageAppBar from '../Pages/Appbar'
import MyBreweries from '../Breweries/MyBrweriesClass'
import FavoriteBreweries from '../Breweries/FavoriteBreweries'
import FavoriteBeers from '../Breweries/FavoriteBeers'
import MyBreweryManage from '../Breweries/ManageMyBrewery'

const mapStateToProps = state => {
    return {
        token: state.token,
        user: state.user,
        breweries: state.breweries,
        beers: state.beers,
    }
}

const mapDispatchToProps = (dispatch) => ({
    addToken: () => { dispatch(addToken()) },
    deleteUser: () => { dispatch(deleteUser())},
    fetchBreweries: () => { dispatch(fetchBreweries())},
});



class Main extends Component {
    constructor(props){
        super(props);
    }

    componentDidMount() {
        this.props.fetchBreweries();
    }

    handleLogout = () => {
        this.props.addToken("")
        this.props.deleteUser()
    }

    render(){
        // this.props.user.authorities[0].name === "ROLE_USER" ? userstuff || this.props.user.authorities[0].name === "ROLE_admin" ? admin stuff : no role stuff 
        return(
            <div>
                {this.props.token.token !== undefined ?
                        <div>
                            <PageAppBar props={this.props.user.username} />
                            <Redirect to='/breweryhome'/>
                            <Route path='/favorite/beers' component={() => <FavoriteBeers props={this.props.user.username}/>}/>
                            <Route path='/favorite/breweries' component={() => <FavoriteBreweries props={this.props.user.username}/>}/>
                            <Route path='/breweryhome' component={() => <BreweryHome props={this.props.user.username}/>}/>
                            <Route path="/MyBrewery/:id/manage"  component={() => <MyBreweryManage props={this.props}/>}/>
                            <Route path='/MyBreweries' component={() => <MyBreweries props={this.props}/>} />
                            <Route path='/selectedbrewery/:id/Menu'  component={this.props.token.token !== undefined ? () => <SelectedBreweryMenu props={this.props}/> : null}/>
                            <Route path='/selectedbrewery/:id/OurEvents' component={this.props.token.token !== undefined ? () => <SelectedBreweryEvents/> : null}/>
                            <Route path="/brewery/:id" component={this.props.token.token !== undefined ? () => <SelectedBreweryHome/> : null}/>
                        </div>  
                    :   
                     <Switch> 
                        <Route path='/home' component={this.props.token.token !== undefined ? () => <Home/> : null}/>
                            <Route path='/register'component={() => <Register/>}/>
                            <Route path='/login' component={() => <Login/>}/>
                            <Route path='/contact' component={() => <ContactUs/>}/>
                            <Route path='/privacy' component={() => <PrivacyPolicy/>}/>
                            <Route path='/terms-of-service' component={() => <TermsOfService/>}/>
                            <Redirect to='/login'/>
                    </Switch>   
                }
               
                
                <Footer/>
            </div>
        )
    }
} 

export default withRouter(connect(mapStateToProps, mapDispatchToProps)(Main));
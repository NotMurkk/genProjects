# Full Stack Application 

This document walks through how the project was set up. It also explains the project's features, such as React Router, Redux, and authentication.
## Features 

```
Brewers: 
----------------------
Brewers are able to create and manage breweries.
Brewers are able to add beer to the selected brewery.
Brewers are able to manage News and Events/

Consumers: 
----------------------
Consumers are able to view a Brewery's Beer List
Consumers are able to View Beer information.
Coonsumers are able to review a beer. 
Consumers are able to like a brewery/beer. 
```
## Future Features
```
Brewers: 
----------------------
Brewers are able to add an image to their brewery(on creation/ after creation)
Brewers are able to add an image to their beer/event(on creation/after creation)

Consumers: 
----------------------
Consumers are able to share their list of favorite beers/breweries.
Consumers are able to leave a review for the selected brewery. 
```
## Project setup

The first thing you'll need to do is to download the dependencies by running this command:

```
npm install
```


The React frontend communicates with this API endpoint to authenticate and register users.

The last thing to do is start the back-end application before you run the front-end application. Start the application with the following command:

```
npm start
```

## Authentication

When you first run the project and visit the base URL, you're taken to a login page. This is because the home route `/` is secured by default. If you look in `/src/Components/Main/Main.js`, you'll see the following code:

```
<Switch> 
    <Route path='/home' component={this.props.token.token !== undefined ? () => <Home/> : null}/>
    <Route path='/register'component={() => <Register/>}/>
    <Route path='/login' component={() => <Login/>}/>
    <Route path='/contact' component={() => <ContactUs/>}/>
    <Route path='/privacy' component={() => <PrivacyPolicy/>}/>
    <Route path='/terms-of-service' component={() => <TermsOfService/>}/>
    <Redirect to='/login'/>
</Switch>   
```

The line that reads `<Redirect to='/login'/>` tells the Router to navigate to the `/login` path by default and the other `<Route/>` tags tell you which components will be loaded depending on the `to`

path variable. If you look at the `<Route/>` component with the path `/home` you'll see the component has a condition `this.props.token.token !== undefined`. This is to ensure that the home component

is only loaded if the user is authorized.



### Redux

The state for this application is stored and managed in various files you'll find in the folder labeled `Redux`. The application has two state objects: token and user. When a user logs in, 

back-end API authorizes the request with the given credentials and then, upon successful login, the response object is parsed and then stored in the two state objects.
```
    handleLogin = async () => {
        const data = { username: this.state.username, password: this.state.password };
        

        const userWithToken = await axios.post(baseUrl + '/login', data)

    
        await this.props.dispatch(addToken(userWithToken.data.token))
        await this.props.dispatch(addUser(userWithToken.data.user));
    }
```
### Login

When you reach the `/login` route, you'll see the login page.

When you fill in a username and password and click the "Sign In" button, the method `handleLogin()` is called. The `handleLogin()` method uses the axios to send a `POST` request to my API's `/login` route.

If a successful response is returned, the response is parsed into the token and user objects and then functions are dispatched to send those objects to the Redux store.



Once the `handleLogin()` method finishes updating the store by committing the mutations, the Main component recognizes the token and redirects the user to the Home component. They'll be able to see the homepage because they're authenticated.

### Logout

When a user is logged in, they will see a `logout` button on the top right drop down menu. The logout button erases the token from the Redux store and deauthorizes the user.



### Register

When you reach the `/register` route, you'll see the registration page. Like the login page.

When you fill in a username, password, confirm the password, a role, and click the "Create Account" button, the method `handleSubmit()` is called. This calls the `handleLogin()`. This passes  your user details to your back-end application's REST API to create a new user:

```
   handleSubmit = () => {
        const data = {username: this.state.username, password: this.state.password, confirmPassword: this.state.confirmPassword, role: this.state.role}
        if(this.state.password === this.state.confirmPassword){
            axios.post(baseUrl + "/register", data)
            alert("You may now login");
        }else{
            alert("Passwords Do Not Match");
        }
    }
```

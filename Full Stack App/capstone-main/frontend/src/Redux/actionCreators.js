import * as ActionTypes from './actionTypes';
import { baseUrl } from '../Shared/baseUrl';

export const addToken = (token) => ({
    type: ActionTypes.ADD_TOKEN,
    payload: token
});

export const addUser = (user) => ({
    type: ActionTypes.ADD_USER,
    payload: user
})

export const deleteUser = () => ({
    type: ActionTypes.DELETE_USER
})

export const fetchBreweries = () => (dispatch) => {
    return fetch(baseUrl + '/brewery/all')
        .then(response => {
            if (response.ok) {
                return response;
            }
            else {
                var error = new Error(response.statusText)
                throw error;
            }
        },
            error => {
            var errmess = new Error(error.message);
            throw errmess;
        })
    .then(response => response.json())
    .then(breweries => dispatch(addBreweries(breweries)))

}

    export const addBreweries = (breweries) => ({
        type: ActionTypes.ADD_BREWERIES,
        payload: breweries
    });

    
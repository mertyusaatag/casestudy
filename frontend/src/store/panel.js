// import { createSlice } from "@reduxjs/toolkit"
//
// const panelSlice = createSlice({
//     name:'panel',
//     initialState:{
//         movies: []
//     },
//     reducers : {
//         addMovieToList(state,action)
//         {
//             state.movies = action.payload.data
//         }    }
//     }
// )
//
// export const {addMovieToList} = panelSlice.actions;
// export default panelSlice.reducer;

import { createSlice } from '@reduxjs/toolkit';
import axios from 'axios';

export const fetchMovies = () => async (dispatch) => {
    try {
        const response = await axios.get("http://localhost:8080/movie", {headers: {
                'Access-Control-Allow-Origin': '*',
                'Content-Type': 'application/json;charset=UTF-8'
            }});

        dispatch(setMovies(response.data));
    } catch (error) {
        console.error(error);
    }
};

export const changeStates = (data) =>  () => {
        changeState(data);

};

const moviesSlice = createSlice({
    name: 'movies',
    initialState: [],
    reducers: {
        setMovies: (state, action) => {
            return action.payload;
        },
        changeState : (state,action) =>{
            state = [];
            state.push(action.payload);
            return state
        },
        filterState : (state,action,filter) => {
            state = action.payload;
            return state;
        }
    },
});

export const { setMovies,changeState,filterState } = moviesSlice.actions;

export default moviesSlice.reducer;
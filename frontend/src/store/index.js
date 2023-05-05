import {configureStore} from "@reduxjs/toolkit"
import moviesReducer from "./panel"
export default configureStore({
    reducer:{
        movies : moviesReducer
    }
})

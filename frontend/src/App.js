import MoviePage from "./component/Pagess/MoviePage";
import FilteredMoviePage from "./component/Pagess/FilteredMoviePage";
import NavBar from "./component/NavBar";
import { Routes, Route,  } from "react-router-dom";
import Panel from "./component/Panel";
import DetailPage from "./component/Pagess/DetailPage";
import Footer from "./component/Footer";
import AddMoviePage from "./component/Pagess/AddMoviePage";
function App() {
  return (
          <div className="App">
              <NavBar />
              <Routes>
                  <Route path="/" element={<MoviePage />}>
                  </Route>
                  <Route path="/:filmType" element={<FilteredMoviePage />}>
                  </Route>
                  <Route path="/charts" element={<Panel />}>
                  </Route>
                  <Route path="/detail/:imdbId" element={<DetailPage />}>
                  </Route>
              </Routes>
              <Footer/>
              </div>
  );
}

export default App;

// function App() {
//     return (
//
//         <div className="App">
//             <NavBar></NavBar>
//             <MoviePage/>
//         </div>
//     );
// }
//import logo from './logo.svg';
import './App.css';
//import Home from './components/Home';
import Employees from './components/Employee'
import {BrowserRouter as Router,Route,Routes} from 'react-router-dom'


function App(){
  return (
    <div className = "design">
    <div className = "App">
      <Router>
        <Routes>
          <Route path = '/' element = {<Employees/>}/>
        </Routes>
      </Router>
    </div>
    </div>
  )
}
// function App() {
//   return (
//     <div className="App">
//       <header className="App-header">
//         <img src={logo} className="App-logo" alt="logo" />
//         <p>
//           Edit <code>src/App.js</code> and save to reload.
//         </p>
//         <a
//           className="App-link"
//           href="https://reactjs.org"
//           target="_blank"
//           rel="noopener noreferrer"
//         >
//           Learn React
//         </a>
//       </header>
//     </div>
//   );
// }
export default App;

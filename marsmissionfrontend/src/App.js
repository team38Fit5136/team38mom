import React from 'react';
import Button from 'react-bootstrap/Button'
import Login from './components/Login';
import Home from './components/Home'
import Navigation from './components/Navigation'
import CreateMission from './components/CreateMission'

import { BrowserRouter, Route, Switch } from 'react-router-dom'

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <h1 className="m-3 d-flex justify-content-center">Mission to Mars Portal</h1>
        <Switch>
          <Route path="/" component={Login} exact></Route>
          <Route path="/home" component={Home} exact></Route>
          <Route path="/navigation" component={Navigation} exact></Route>
          <Route path="/createmission" component={CreateMission} exact></Route>
        </Switch>
        {/* <h2>Hello World, how are you</h2>  */}
        {/* <Button varitant='primary'>Bootstrap btn</Button> */}
        {/* <Login></Login> */}
        {/* <Home></Home> */}
      </div>
    </BrowserRouter>
  );
}

export default App;

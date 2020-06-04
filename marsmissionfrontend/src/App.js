import React from 'react';
import Button from 'react-bootstrap/Button'
import Login from './components/Login';
import Home from './components/Home'
import Navigation from './components/Navigation'
import CreateMission from './components/CreateMission'

import { BrowserRouter, Route, Switch } from 'react-router-dom'
import { Navbar } from 'react-bootstrap';

function App() {
  return (
    <BrowserRouter>
      <div className="App">
        <Navbar bg="light">
          <h3 className="d-flex justify-content-center" style={{ marginLeft: "auto" }}>Mission to Mars Portal</h3>
          <Button
            style={{ marginLeft: "auto" }}
            href="/">Logout</Button>
        </Navbar>
        <Switch>
          <Route path="/" component={Login} exact></Route>
          <Route path="/home" component={Home} exact></Route>
          <Route path="/navigation" component={Navigation} exact></Route>
          <Route path="/createmission" component={CreateMission} exact></Route>
        </Switch>
      </div>
    </BrowserRouter>
  );
}

export default App;

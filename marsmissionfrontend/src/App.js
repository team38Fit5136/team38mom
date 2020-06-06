import React, { Component } from 'react';
import Button from 'react-bootstrap/Button'
import Routes from './Routes'
import { Navbar } from 'react-bootstrap';
import AppContext from './lib/contextLib'

export default class App extends Component {
  state = {
    isAuthenicated: false,
    hasAuthenicated: this.hasAuthenicated
  }

  hasAuthenicated = (isAuthenicated) => {
    this.setState({ isAuthenicated })
  }

  render() {
    return (

      <div className="App">
        <Navbar bg="light">
          <h3 className="d-flex justify-content-center" style={{ marginLeft: "auto" }}>Mission to Mars Portal</h3>
          <Button
            style={{ marginLeft: "auto" }}
            href="/">Logout</Button>
        </Navbar>
        <AppContext.Provider value={this.state}>
          <Routes />
        </AppContext.Provider>
      </div>
    )
  }
}
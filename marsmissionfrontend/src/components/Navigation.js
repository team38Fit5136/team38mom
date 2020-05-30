import React, { Component } from 'react'
import { NavLink } from 'react-router-dom'
import { Navbar, Nav } from 'react-bootstrap'

export default class Navigation extends Component {
    render() {
        return (
            <div>
                {/* <Navbar bg="dark" expand="lg"> */}
                {/* <Navbar.Toggle aria-controls="basic-navbar-nav" /> */}
                {/* <Navbar.Collapse id="basic-navbar-nav" /> */}
                <Nav className="flex-column">
                    <NavLink className="d-inline p-2 bg-dark text-white" to="/createmission">Create Mission</NavLink>
                    <NavLink className="d-inline p-2 bg-dark text-white" to="/">Edit Mission</NavLink>
                    <NavLink className="d-inline p-2 bg-dark text-white" to="/">Create Mission</NavLink>
                    <NavLink className="d-inline p-2 bg-dark text-white" to="/">Create Criteria</NavLink>
                    <NavLink className="d-inline p-2 bg-dark text-white" to="/">Logout</NavLink>
                </Nav>

                {/* </Navbar> */}
            </div>
        )
    }
}

import React, { Component } from 'react'
import { NavLink } from 'react-router-dom'
import { Nav } from 'react-bootstrap'

const style = {
    alignContent: "center",
}

export default class Navigation extends Component {
    render() {
        return (
            <div>
                <Nav className="flex-column" style={style}>
                    <NavLink className="d-inline p-2 bg-dark text-white" to="/createmission">Create Mission</NavLink>
                    <NavLink className="d-inline p-2 bg-dark text-white" to="/">Edit Mission</NavLink>
                    <NavLink className="d-inline p-2 bg-dark text-white" to="/">Create Criteria</NavLink>
                </Nav>
            </div>
        )
    }
}

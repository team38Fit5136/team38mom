import React, { Component } from "react"
import axios from "axios"
import useAppContext from '../lib/contextLib'
import { Redirect } from 'react-router-dom';

export default class Login extends Component {
  static contextType = useAppContext

  constructor(props) {
    super(props);

    //Value to be set deavlared later in form
    this.state = {
      userID: "",
      password: "",
      loginErrors: "",
      isAuthenticated: false,

    }

    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleChange = this.handleChange.bind(this);
  }

  componentDidMount() {
    console.log(this.state)
    // this._Auth()
    // console.log(this.context)
    // const context = this.context
    // console.log(context)
  }

  // _Auth() {
  //   const context = this.contextType
  //   context.hasAuthenicated(this.state.isAuthenticated)
  // }

  // to get the value for what user type 
  handleChange(event) {
    this.setState({
      [event.target.name]: event.target.value
    });
  }

  handleSubmit(e) {
    axios({
      method: "get",
      url: 'http://localhost:8081/mom/user/profile?',
      params: {
        userID: this.state.userID,
        passwd: this.state.password
      }
    })
    .then(response => {
      console.log(response)
      if (response.data.status === "Success" && response.data.responseMsg.length !== 0) {
        console.log("Authenticated")
        this.setState({
          isAuthenticated: true
        })
        // this.context.hasAuthenicated(this.state.isAuthenticated)
        // console.log(this.state)
      } else if (this.state.userID === "" || this.state.password === "") {
        alert("Username or password not filled")
      } else {
        alert("Incorrect username or password")
      }
    })
    .catch(err => {
      console.log(err)
    })
    e.preventDefault()
  }

  render() {
    if (this.state.isAuthenticated) {
      return <Redirect push to="/navigation" />
    }
    return (
      <div>
        <form className="m-3 d-flex justify-content-center" onSubmit={this.handleSubmit}>
          <input
            type="userID"
            name="userID"
            placeholder="Email or user ID"
            // The value comes from the state declared above
            value={this.state.userID}
            onChange={this.handleChange}
            required
          />

          <input
            type="password"
            name="password"
            placeholder="Password"
            value={this.state.password}
            onChange={this.handleChange}
            required
          />

          <button type="submit" onClick={this.handleSubmit}>
            {/* <NavLink to="/navigation" exact>Login</NavLink> */}
            Login
          </button>
        </form>
      </div>
    );
  }
}

// export default Login
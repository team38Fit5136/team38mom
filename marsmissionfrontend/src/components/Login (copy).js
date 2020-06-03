import React, { Component } from "react";
// import axios from "axios";
import { NavLink } from 'react-router-dom'

export default class Login extends Component {
  constructor(props) {
    super(props);

    //Value to be set deavlared later in form
    this.state = {
      email: "",
      password: "",
      loginErrors: ""
    };

    this.handleSubmit = this.handleSubmit.bind(this);
    this.handleChange = this.handleChange.bind(this);
  }

  // to get the value for what user type 
  handleChange(event) {
    this.setState({
      [event.target.name]: event.target.value
    });
  }

  handleSubmit(event) {
    const { email, password } = this.state;
    /*
        axios
          .post(
            "http://localhost:3001/sessions",
            {
              user: {
                email: email,
                password: password
              }
            },
            { withCredentials: true }
          )
          .then(response => {
            if (response.data.logged_in) {
              this.props.handleSuccessfulAuth(response.data);
            }
          })
          .catch(error => {
            console.log("login error", error);
          });
        event.preventDefault();
        */
  }

  render() {
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

          <button type="submit">
            <NavLink to="/navigation" exact>Login</NavLink>
          </button>
        </form>
      </div>
    );
  }
}

// export default Login
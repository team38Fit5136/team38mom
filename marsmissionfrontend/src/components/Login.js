import React, { Component } from "react";
import axios from "axios";
import { NavLink } from 'react-router-dom'

export default class Login extends Component {
  constructor(props) {
    super(props);

    //Value to be set deavlared later in form
    this.state = {
      userID: "",
      password: "",
      
    };

    // this.handleSubmit = this.handleSubmit.bind(this);
    // this.handleChange = this.handleChange.bind(this);
  }

  // to get the value for what user type 
  handleChange=(event)=> {
    const target = event.target;
    const name = target.name;
    const value = target.value;
    
    this.setState({
      [name]: value
    });
  };


    handleSubmit=(event)=> {
      event.preventDefault();
      console.log('Statessdfsf',this.state);  
          axios
            ({
              method:'get',
              url:'http://localhost:8081/mom/user/profile?',
              params:{
                userID:15,
                passwd:'admin1'
              }
            }).then(res => console.log(res))
            .catch(err => console.error(err))
          
    };
  
  render() {
    //  check what values are entered
    // console.log('State',this.state);
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
            {/* <NavLink to="/navigation" exact>Login</NavLink> */}
            Login
          </button>
        </form>
      </div>
    );
  }
}

// export default Login







  // refreshList(){
  //   fetch('http://localhost:8081/mom/user/profile?userID=15&passwd=admin1')
  //   .then(response=> response.json())
  //   .then(data=>{
  //     this.setState({login:data})
  //   });
    
  // }
  
import React, { Component, useState } from "react"
import axios from "axios"
import { NavLink } from 'react-router-dom'
import AppContext from '../lib/contextLib'
import useAppContext from '../lib/contextLib'

export default function Login() {
  const [userID, setUserID] = useState("")
  const [password, setPassword] = useState("")
  const [isAuthenticated, hasAuthenicated] = useState(false)
  
  async function handleSubmit(e) {
    console.log(userID, password)
    axios({
      method: "get",
      url: 'http://localhost:8081/mom/user/profile?',
      params: {
        userID: userID,
        passwd: password
      }
    })
    .then(response => {
      console.log(response)
      if (response.data.status == "Success") {
        console.log(isAuthenticated)
        console.log("Authenticated")
        hasAuthenicated(true)
        
      }
    })
    .catch(err => {
      console.log(err)
    })
    e.preventDefault()
    return (
      console.log(isAuthenticated)
    )
  }

  return (
    <div>
      <form className="m-3 d-flex justify-content-center" onSubmit={handleSubmit}>
        <input
          type="userID"
          name="userID"
          placeholder="Email or user ID"
          // The value comes from the state declared above
          value={userID}
          onChange={e => setUserID(e.target.value)}
          required
        />

        <input
          type="password"
          name="password"
          placeholder="Password"
          value={password}
          onChange={e => setPassword(e.target.value)}
          required
        />

        <button type="submit" onClick={handleSubmit}>
          {/* <NavLink to="/navigation" exact>Login</NavLink> */}
            Login
          </button>
      </form>
    </div>
  )
}
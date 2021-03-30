import React, { useState } from "react";
import { Redirect } from "react-router-dom";
import axios from 'axios';
import { useAuth } from "../context/auth";

function Login() {
    const [isLoggedIn, setLoggedIn] = useState(false);
    const [isError, setIsError] = useState(false);
    const [username, setUserName] = useState("");
    const [password, setPassword] = useState("");
    const { setAuthTokens } = useAuth();
  
    function postLogin(event) {
      event.preventDefault();
      axios.post("http://localhost:8081/auth/signin", {
        username,
        password
      }).then(result => {
        if (result.status === 200) {
          setAuthTokens(result.data);
          setLoggedIn(true);
          console.log(result.data)
        } else {
          setIsError(true);
        }
      }).catch(e => {
        console.log(e.message)
        setIsError(true);
      });
    }

  return (
    <div className="container pt-3">
      {isLoggedIn && <Redirect to="/bookList" />}
      <h2>Login to the Online catalog books</h2>
      <form>
      <div className="form-group">
        <label for="username">User Name:</label>
        <input className="form-control" type="email"
          value={username}
          onChange={e => {
            setUserName(e.target.value);
          }}
          placeholder="username"
        />
        </div>
        <div className="form-group">
        <label for="pwd">Password:</label>
        <input className="form-control"  type="password"
          value={password}
          onChange={e => {
            setPassword(e.target.value);
          }}
          placeholder="password"
        />
        </div>
        <div className="d-flex flex-row-reverse">
        <button type="button" className="btn btn-secondary btn-lg" onClick={postLogin}>Enter</button>
        </div>
      </form>
      { isError && <h3 className="alert alert-danger">The username or password provided were incorrect!</h3> }
      </div>
  );
}

export default Login;
import React, { useState } from "react"
import { BrowserRouter as Router, Link, Route } from "react-router-dom";
import {BookList} from './pages/BookList';
import Login from "./pages/Login";
import {AuthContext} from "./context/auth";
import PrivateRoute from "./PrivateRoute";
import {EditPage} from "./pages/EditPage"

function App(props) {
  const existingTokens = JSON.parse(localStorage.getItem("user"));
  const [authTokens, setAuthTokens] = useState(existingTokens);
  
  const setTokens = (data) => {
    localStorage.setItem("user", JSON.stringify(data));
    setAuthTokens(data);
  }

  return (
    <AuthContext.Provider value={{ authTokens, setAuthTokens: setTokens }}>
      <Router>
      <Route path="/login" component={Login} />
      <PrivateRoute path="/bookList" component={BookList} />
      <PrivateRoute path="/edit" component={EditPage} /> 
    </Router>
    </AuthContext.Provider>
  );
}

export default App;
import React, { useState } from "react"
import { BrowserRouter as Router, Link, Redirect, Route } from "react-router-dom";
import {BookList} from './pages/book/BookList';
import {AuthorList} from './pages/author/AuthorList';
import Login from "./pages/Login";
import {AuthContext} from "./context/auth";
import PrivateRoute from "./PrivateRoute";
import {EditAuthorPage} from "./pages/author/EditAuthorPage"
import CreateAuthorPage from "./pages/author/CreateAuthorPage";
import {EditBookPage} from "./pages/book/EditBookPage"
import CreateBookPage from "./pages/book/CreateBookPage";

function App(props) {
  const existingTokens = JSON.parse(localStorage.getItem("user"));
  const [authTokens, setAuthTokens] = useState(existingTokens);
  const [bookForEdit,setBookForEdit] = useState(null);
  const [authorForEdit,setAuthorForEdit] = useState(null);
  
  const setTokens = (data) => {
    localStorage.setItem("user", JSON.stringify(data));
    setAuthTokens(data);
  }

  return (
    <AuthContext.Provider value={{ authTokens, setAuthTokens: setTokens }}>
      <Router>
      <div className="row pb-5 pt-3">
                <div className="col">
                  <Link to="/bookList"><button className="btn btn-secondary btn-lg pg  btn-block">Books</button></Link>
                </div>
                <div className="col">
                  <Link to="/authorList"> <button className="btn btn-secondary btn-lg pg  btn-block">Authors</button></Link>
                </div>
                </div>
      {bookForEdit && <Redirect to="/edit/book" />}
      <Route path="/login" component={Login} />
      <PrivateRoute path="/bookList" component={() => <BookList setBookForEdit={setBookForEdit}/>} />
      <PrivateRoute path="/create/book" component={CreateBookPage} /> 
      {bookForEdit && <PrivateRoute path="/edit/book" component={() => <EditBookPage book={bookForEdit}/>}/>}

      {authorForEdit && <Redirect to="/edit/author" />}
      <PrivateRoute path="/authorList" component={() => <AuthorList setAuthorForEdit={setAuthorForEdit}/>} />
      <PrivateRoute path="/create/author" component={CreateAuthorPage} /> 
      {authorForEdit && <PrivateRoute path="/edit/author" component={() => <EditAuthorPage author={authorForEdit}/>}/>}
    </Router>
    </AuthContext.Provider>
  );
}

export default App;
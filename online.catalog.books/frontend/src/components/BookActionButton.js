import React, { Component } from 'react';
import { SplitButton, Dropdown } from 'react-bootstrap';
import axios from 'axios';
import authHeader from "../context/authHeader";
import { Redirect } from 'react-router';

class BookActionButton extends Component {
  constructor(props){
    super(props);
    this.state = {
      action:props.bookListAction
    }
  }

  chooseAction = (action, evt) => {
    this.setState({action:action});
  }

  act = (event) => {
    if(this.state.action == "edit"){
      console.log(this.props.book)
      axios.post("http://localhost:8081/api/v1/books",this.props.book,{
        headers :  authHeader()
      }).then(result => {
        console.log(result)
      }).catch(e => {
        console.log(e.message)
      });

      return <Redirect to="/edit" />
    } else if (this.state.action == "delete") {
      console.log(this.props.book)
      axios.delete(`http://localhost:8081/api/v1/books/${this.props.book.id}`,{
        headers : authHeader()
      }).then(result => {
        console.log(result)
      }).catch(e => {
        console.log(e.message)
      });
    } else {
      console.log(authHeader())
    axios.put(`http://localhost:8081/api/v1/users/bookList/${this.state.action}/${this.props.book.id}`,null,{
      headers :  authHeader()
    }).then(result => {
      console.log(result)
    }).catch(e => {
      console.log(e.message)
    });
    }

    console.log("refresh");
    this.props.getBooksInButton();
  }
  
  render() {
    return (        
    <div>
      <SplitButton
        key='down'
        id='dropdown-button-drop-down'
        drop='down'
        variant="secondary"
        title={this.state.action}
        onClick={this.act}
      >
          <Dropdown.Item eventKey={this.props.bookListAction} onSelect={this.chooseAction}>{this.props.bookListAction}</Dropdown.Item>
          <Dropdown.Item eventKey="edit" onSelect={this.chooseAction}>edit</Dropdown.Item>
          <Dropdown.Item eventKey="delete" onSelect={this.chooseAction}>delete</Dropdown.Item>
      </SplitButton>
    </div>
    );
    
  }
}

export default BookActionButton;
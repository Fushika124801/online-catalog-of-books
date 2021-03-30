import React, { Component } from 'react';
import { SplitButton, Dropdown } from 'react-bootstrap';
import axios from 'axios';
import authHeader from "../context/authHeader";

class AuthorActionButton extends Component {
  constructor(props){
    super(props);
    this.state = {
      action:"edit",
      isClickedEdit:false
    }
  }

  chooseAction = (action, evt) => {
    evt.preventDefault();
    this.setState({action:action});
  }

  setIsClickedEdit = (isClickedEdit) => {
    this.setState({isClickedEdit:isClickedEdit});
  }

  act = (event) => {
    event.preventDefault();
    if(this.state.action == "edit"){
      this.props.setAuthorForEdit(this.props.author);
      this.setIsClickedEdit(true);
    } else if (this.state.action == "delete") {
      console.log(this.props.author)
      axios.delete(`http://localhost:8081/api/v1/authors/${this.props.author.id}`,{
        headers : authHeader()
      }).then(result => {
        console.log(result)
      }).catch(e => {
        console.log(e.message)
      });
    }
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
         <Dropdown.Item eventKey="edit" onSelect={this.chooseAction}>edit</Dropdown.Item>
          <Dropdown.Item eventKey="delete" onSelect={this.chooseAction}>delete</Dropdown.Item>
      </SplitButton>
    </div>
    );
    
  }
}

export default AuthorActionButton;
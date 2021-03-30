import React, { Component } from "react"
import { Link } from "react-router-dom";
import axios from 'axios';
import AuthorActionButton from "../../components/AuthorActionButton";
import authHeader from "../../context/authHeader";

export class AuthorList extends Component{
    constructor(props){
        super(props);
        this.state = {
            authors : [],
        }
    }

    setAuthors = (authors) => {
        this.setState({authors:authors});
    }

    getAllAuthors = () => {
        axios.get("http://localhost:8081/api/v1/authors", {
            headers :  authHeader()
          }).then(result => {
            if (result.status === 200) {
              this.setAuthors(result.data)
            } else {
                console.log("Oops! Not 200 status code")
            }
          }).catch(e => {
            console.log(e)
          });
    }

    componentDidMount() {
        this.getAllAuthors();
    }

    render () {
        return (
            <div className="container">
                <div className="row pb-5">
                <div className="d-flex flex-row-reverse pt-5 pb-5">
                    <Link to="/create/author"><button className="btn btn-success btn-lg pg">Create new author</button></Link>
                </div>
                </div>
            <table className="table table-bordered">
            <thead className="thead-light">
            <tr>
                <th>ID</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Birthday</th>
                <th>Sex</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
                {this.state.authors.map((author, index) => 
                    <tr key={index}>
                        <th>{author.id}</th>
                        <th>{author.firstName}</th>
                        <th>{author.lastName}</th>
                        <th>{author.birthday}</th>
                        <th>{author.sex}</th>
                        <th><AuthorActionButton author={author} setAuthorForEdit={this.props.setAuthorForEdit} /></th>
                    </tr>
                )}
            </tbody>
            </table>
            </div>
        );
    }
}
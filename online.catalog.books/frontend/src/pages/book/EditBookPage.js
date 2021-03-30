import { Component } from "react"
import axios from 'axios';
import authHeader from "../../context/authHeader";
import { Redirect } from "react-router-dom";

export class EditBookPage extends Component{
    constructor(props){
        super(props);
        this.state = {
            name: "",
            yearPublication: null,
            publishingHouse: "",
            authorsOfBook: [],
            authors: [],
            isCreated : false
        }
    }

    setIsCreated = (isCreated) =>{
        this.setState({isCreated:isCreated})
    }

    setName = (evt) => {
        this.setState({name:evt.target.value})
    }

    setYearPublication = (evt) =>{
        this.setState({yearPublication:evt.target.value})
    }

    setPublishingHouse = (evt) =>{
        this.setState({publishingHouse:evt.target.value})
    }

    setAuthorsOfBook = (evt) =>{
        let authorsOfBook = Array.from(evt.target.selectedOptions, option => option.value);
        console.log(authorsOfBook)
        this.setState({authorsOfBook:authorsOfBook})
    }

    setAuthors = (authors) =>{
        this.setState({authors:authors})
    }

    componentDidMount() {
        axios.get("http://localhost:8081/api/v1/authors", {
            headers :  authHeader()
          }).then(result => {
            if (result.status === 200) {
              this.setAuthors(result.data)
              console.log(this.state.authors)
            } else {
                console.log("Oops! Not 200 status code")
            }
          }).catch(e => {
            console.log(e)
          });
    }

    editBook = () => {
        let authorsOfBook = this.state.authorsOfBook.map(function(idOfAuthor){
            return {
                id:idOfAuthor
            }
        });

        axios.put(`http://localhost:8081/api/v1/books/${this.props.book.id}`, {
            "name" : this.state.name,
            "yearPublication" : this.state.yearPublication,
            "publishingHouse" : this.state.publishingHouse,
            "authors" : authorsOfBook
        }, {
            headers : authHeader()
          }).then(result => {
            if (result.status === 200) {
              console.log("AllRight")
              this.setIsCreated(true)
            } else {
                console.log("Oops")
                console.log(result)
            }
          }).catch(e => {
            console.log(e)
          });
    }

    render()
    {
        return (
            <div>
                {this.state.isCreated && <Redirect to="/bookList" />}
            <div className="container">
                <label for="usr">Name:</label>
                <input type="text" class="form-control w-50" onInput={this.setName}></input>
                <br/>
                <label for="usr">Year:</label>
                <input type="number" class="form-control w-50" onInput={this.setYearPublication}></input>
                <br/>
                <label for="usr">Publishing house:</label>
                <input type="text" class="form-control w-50" onInput={this.setPublishingHouse}></input>
                <br/>
                <label for="sel1">Authors:</label>
                <select multiple class="form-control" onChangeCapture={this.setAuthorsOfBook}>
                    {this.state.authors.map((author,index) => 
                        <option key={index} value={author.id}>{author.firstName} {author.lastName}</option>
                    )}
                </select>
            </div>
            <div className="d-flex pt-5  flex-row-reverse pb-5 w-50">
                <button className="btn btn-success btn-lg mr-5" onClick={this.editBook}>Edit book</button>
            </div>
            </div>
        )
    }
}
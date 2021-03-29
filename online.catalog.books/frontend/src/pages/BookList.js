import React, { Component } from "react"
import { Link } from "react-router-dom";
import axios from 'axios';
import BockActionButton from "../components/BookActionButton";
import authHeader from "../context/authHeader";

export class BookList extends Component{
    constructor(props){
        super(props);
        this.state = {
            books : [],
            isBookListEnabled : false
        }
    }

    setBooks = (books) => {
        this.setState({books:books});
    }

    getBookList = () => {
        axios.get("http://localhost:8081/api/v1/users/bookList", {
            headers :  authHeader()
          }).then(result => {
            if (result.status === 200) {
              this.setBooks(result.data)
              console.log(this.state.books)
            } else {
                console.log("Oops! Not 200 status code")
            }
          }).catch(e => {
            console.log(e)
          });
    }

    getAllBooks = () => {
        axios.get("http://localhost:8081/api/v1/books", {
            headers :  authHeader()
          }).then(result => {
            if (result.status === 200) {
              this.setBooks(result.data)
              console.log(this.state.books)
            } else {
                console.log("Oops! Not 200 status code")
            }
          }).catch(e => {
            console.log(e)
          });
    }

    getBooks = () => {
        console.log(authHeader())
        if(this.state.isBookListEnabled){
            this.getBookList();
        } else{
            this.getAllBooks();
        }
    }

    componentDidMount() {
        this.getBooks();
    }

    setBookListEnabled = (evt) => {
        evt.preventDefault()
        this.setState({isBookListEnabled: true });
        this.getBookList();
    }

    
    setBookListDisabled = (evt) => {
        evt.preventDefault()
        this.setState({isBookListEnabled: false });
        this.getAllBooks();
    }


    render () {
        return (
            <div className="container">
                <div className="d-flex flex-row-reverse pt-5 pb-5">
                    <Link to="/create/book"><button className="btn btn-success btn-lg pg">Create new book</button></Link>
                </div>
                <div className="row pb-5">
                <div className="col">
                    <button className={this.state.isBookListEnabled ? "btn btn-outline-primary btn-lg pg" : "btn btn-primary btn-lg pg"} onClick={this.setBookListDisabled}>All books</button>
                </div>
                <div className="col">
                    <button className={!this.state.isBookListEnabled ? "btn btn-outline-primary btn-lg pg" : "btn btn-primary btn-lg pg"} onClick={this.setBookListEnabled}>My books</button>
                </div>
                </div>
            <table className="table table-bordered">
            <thead className="thead-light">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Year publication</th>
                <th>Publishing house</th>
                <th>Authors</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
                {this.state.books.map((book, index) => 
                    <tr key={index}>
                        <th>{book.id}</th>
                        <th>{book.name}</th>
                        <th>{book.yearPublication}</th>
                        <th>{book.publishingHouse}</th>
                        <th>
                            <ul>{book.authors.map((author,index) => <li key={index}>{author.firstName} {author.lastName}</li>)}
                            </ul>
                        </th>
                        <th><BockActionButton getBooksInButton={this.getBooks} bookListAction={this.state.isBookListEnabled ? "remove" : "add"} book={book}/></th>
                    </tr>
                )}
            </tbody>
            </table>
            </div>
        );
    }
}
import React, { Component } from "react"
import { Link } from "react-router-dom";
import axios from 'axios';
import { BookActionButton} from "../components/BookActionButton"
import authHeader from "../context/authHeader";

export class BookList extends Component{
    constructor(props){
        super(props);
        this.state = {
            tickets : [],
        }
    }

    setTikcets = (tickets) => {
        this.setState({tickets:tickets});
    }

    componentDidMount() {
        console.log(authHeader())
        axios.get("http://localhost:8081/api/v1/books", {
            headers :  authHeader()
          }).then(result => {
            if (result.status === 200) {
              this.setTikcets(result.data)
              console.log(this.state.tickets)
            } else {
                console.log("Oops! Not 200 status code")
            }
          }).catch(e => {
            console.log(e)
          });
      }

    render () {
        return (
            <div className="container">
                <div className="d-flex flex-row-reverse pt-5 pb-5">
                    <Link to="/create"><button className="btn btn-success btn-lg pg">Create new ticket</button></Link>
                </div>
                <div className="row pb-5">
                <div className="col">
                    <button className="btn btn-primary btn-lg pg">All tickets</button>
                </div>
                <div className="col">
                    <button className="btn btn-primary btn-lg pg">All tickets</button>
                </div>
                </div>
            <table className="table table-bordered">
            <thead className="thead-light">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Disered Date</th>
                <th>Urgency</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
                {this.state.tickets.map((ticket, index) => 
                    <tr key={index}>
                        <th>{ticket.id}</th>
                        <th>{ticket.name}</th>
                        <th>{ticket.desiredResolutionDate}</th>
                        <th>{ticket.urgency}</th>
                        <th>{ticket.state}</th>
                        <th><BookActionButton ticket={ticket}/></th>
                    </tr>
                )}
            </tbody>
            </table>
            </div>
        );
    }
}
import { Component } from "react"
import axios from 'axios';
import authHeader from "../../context/authHeader";
import { Redirect } from "react-router-dom";

export default class CreateAuthorPage extends Component{
    constructor(props){
        super(props);
        this.state = {
            firstName: "",
            lastName: "",
            birtday: null,
            sex: "UNDEFINED",
            isCreated : false
        }
    }

    setIsCreated = (isCreated) =>{
        this.setState({isCreated:isCreated})
    }

    setFirstName = (evt) => {
        this.setState({firstName:evt.target.value})
    }

    setLastName = (evt) => {
        this.setState({lastName:evt.target.value})
    }

    setBirthday = (evt) => {
        this.setState({birtday:evt.target.value})
    }

    setSex = (evt) => {
        console.log(evt.target.value)
        this.setState({sex:evt.target.value})
    }


    createAuthor = () => {


        axios.post("http://localhost:8081/api/v1/authors", {
            "firstName":this.state.firstName,
            "lastName":this.state.lastName,
            "birtday":this.state.birtday,
            "sex":this.state.sex
        }, {
            headers : authHeader()
          }).then(result => {
            if (result.status === 201) {
              console.log("AllRight")
              this.setIsCreated(true)
            } else {
                console.log("Oops")
            }
          }).catch(e => {
            console.log(e)
          });
    }

    render()
    {
        return (
            <div>
                {this.state.isCreated && <Redirect to="/authorList" />}
            <div className="container">
                <label for="usr">FirstName:</label>
                <input type="text" class="form-control w-50" onInput={this.setFirstName}></input>
                <br/>
                <label for="usr">LastName:</label>
                <input type="text" class="form-control w-50" onInput={this.setLastName}></input>
                <br/>
                <label for="usr">Birthday</label>
                <input type="date" class="form-control w-50" onInput={this.setBirthday}></input>
                <br/>
                <label for="sel1">Sex:</label>
                <select class="form-control" onSelect={this.setSex}>
                        <option value="MALE">MALE</option>
                        <option value="MALE">FEMALE</option>
                        <option selected value="MALE">UNDEFINED</option>
                </select>
            </div>
            <div className="d-flex pt-5  flex-row-reverse pb-5 w-50">
                <button className="btn btn-success btn-lg mr-5" onClick={this.createAuthor}>Create new author</button>
            </div>
            </div>
        )
    }
}
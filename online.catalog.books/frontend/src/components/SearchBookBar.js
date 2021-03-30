import React, { useState } from "react"
import axios from 'axios';
import authHeader from "../context/authHeader";

export default function SearchBookBar(props){
    const [name,setName] = useState("");
    const [publishingHouse,setPublishingHouse] = useState("");
    const [yearPublication,setYearPublication] = useState(null);
    const [firstNameOfAuthor,setFirstNameOfAuthor] = useState("");
    const [lastNameOfAuthor,setLastNameOfAuthor] = useState("");
    const [sexOfAuthor,setSexOfAuthor] = useState("");
    const [birthdayOfAuthor,setBirthdayOfAuthor] = useState(null);

    const setOnChangeName = evt => {
        setName(evt.target.value);
    }

    const setOnChangePublishingHouse = evt => {
        setPublishingHouse(evt.target.value);
    }

    const setOnChangeYearPublication = evt => {
        setYearPublication(evt.target.value);
    }

    const setOnChangeFirstNameOfAuthor = evt => {
        setFirstNameOfAuthor(evt.target.value);
    }

    const setOnChangeLastNameOfAuthor = evt => {
        setLastNameOfAuthor(evt.target.value);
    }

    const setOnChangeSexOfAuthor = (evt) => {
        setSexOfAuthor(evt.target.value);
    }

    const setOnChangeBirthdayOfAuthor = evt => {
        setBirthdayOfAuthor(evt.target.value);
    }
    
        const getParams = () => {

            let params = {
                name,
                publishingHouse,
            }
            
            if(yearPublication !== null){
                params.yearPublication = yearPublication;
            } 
            if(firstNameOfAuthor !== ""){
                params.firstNameOfAuthor = firstNameOfAuthor;
            }
            if(lastNameOfAuthor !== ""){
                params.lastNameOfAuthor = lastNameOfAuthor;
            }
            if(sexOfAuthor !== ""){
                params.sexOfAuthor = sexOfAuthor;
            }
            if(birthdayOfAuthor !== null){
                params.birthdayOfAuthor = birthdayOfAuthor;
            } 

            console.log("params");
            console.log(params);

            return params;
        }

        const searchBooks = (evt) => {
            evt.preventDefault();
            let url = `http://localhost:8081/api/v1/books/search?`;
            let params = getParams();

            axios.get(url, {
                params,
                headers :  authHeader()
              }).then(result => {
                if (result.status === 200) {
                    console.log("search")
                    console.log(result.data);
                  props.setBooks(result.data);
                } else {
                    console.log("Oops! Not 200 status code");
                }
              }).catch(e => {
                console.log(e);
              });
            
        }
    

    return(
        <div>
            <form class="form-inline my-2 my-lg-0 pb-4">
                <div>
                    <input class="form-control mr-sm-2" type="search" placeholder="Name" aria-label="Search" onChange={setOnChangeName}/>
                    <input class="form-control mr-sm-2" type="search" placeholder="Publishing house" aria-label="Search" onChange={setOnChangePublishingHouse}/>
                    <input class="form-control mr-sm-2" type="number" placeholder="Year publication" aria-label="Search" onChange={setOnChangeYearPublication}/>    
                </div>
                <div>
                    <input class="form-control mr-sm-2" type="search" placeholder="First name of Author" aria-label="Search" onChange={setOnChangeFirstNameOfAuthor}/>
                    <input class="form-control mr-sm-2" type="search" placeholder="Lastt name of Author" aria-label="Search" onChange={setOnChangeLastNameOfAuthor}/>
                    <input class="form-control mr-sm-2" type="date" placeholder="Birthday of author" aria-label="Search" onChange={setOnChangeBirthdayOfAuthor}/>
                    <select class="form-control" onChange={setOnChangeSexOfAuthor}>
                        <option  value="MALE">MALE</option>
                        <option value="FEMALE">FEMALE</option>
                        <option value="MALE">UNDEFINED</option>
                        <option selected value=""></option>
                    </select>    
                </div>
                 <button class="btn btn-outline-success my-2 my-sm-0 ml-2 btn-lg" type="submit" onClick={searchBooks}>Search</button>
            </form>
        </div>
    );
}
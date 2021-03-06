import {SERVER_URL} from './constants.js'

const URL = SERVER_URL;

function getAllPersons(){
  return fetch(URL + "api/Persons/count")
      .then(res => handleHttpErrors(res))
}

function getPersonById(id){
  return fetch(URL + "api/Persons/" + id)
      .then(res => handleHttpErrors(res))
}

function getPersonCount() {
  return fetch(URL + "api/Persons/count")
      .then(res => handleHttpErrors(res))
}

function addPerson(person){
  const option = makeOptions("POST", person);
  return fetch(URL + "api/Persons/", option)
      .then(res => handleHttpErrors(res))
}


function editPerson(person, id){
  const option = makeOptions("PUT", person);
  return fetch(URL + "api/Persons/" + id, option)
      .then(res => handleHttpErrors(res))
}


function deletePerson(id){
  const option = makeOptions("DELETE", "");
  return fetch(URL + "api/Persons/" + id, option)
      .then(res => handleHttpErrors(res))
}

function getPersonByHobby(hobby){
  return fetch(URL + "api/Persons/hobby/?hobby=" + hobby)
      .then(res => handleHttpErrors(res))
}

function getPersonByZip(zip){
  return fetch(URL + "api/Persons/zip/?zip=" + zip)
      .then(res => handleHttpErrors(res))
}

//Options for POST, PUT og DELETE
function makeOptions(method, body) {
  var opts =  {
    method: method,
    headers: {
      "Content-type": "application/json",
      "Accept": "application/json"
    }
  }
  if(body){
    opts.body = JSON.stringify(body);
  }
  return opts;
}

//Error handling
function handleHttpErrors(res){
  if(!res.ok){
    //Print error message on website
    //document.getElementById("errorMsg").innerHTML = "Status code: " + res.status + ", message: " + res.statusText;
    //Reset input fields
    //document.getElementById("findUserInput").value = "";

    //console.log(res);
    return Promise.reject({status: res.status, fullError: res.json() })
  }
  return res.json();
}

const personFacade = {
  getAllPersons,
  getPersonById,
  addPerson,
  editPerson,
  deletePerson,
  getPersonByHobby,
  getPersonByZip,
  getPersonCount
}

export default personFacade;
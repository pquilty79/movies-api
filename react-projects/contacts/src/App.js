import React from "react";
import Contact from './Contact';
import './App.css';
import contacts from './contacts'



function createCard(contact){
    return <Contact key={contact.key} name={contact.name} img={contact.imgURL} phone={contact.phone} email={contact.email} />
}


function App() {
  return <div>
    <header className="heading">
    <h1>Contacts</h1>
  </header>
    {contacts.map(createCard)}
  </div>
}
export default App;

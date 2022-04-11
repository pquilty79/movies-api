import React from 'react';
import Avatar from './Avatar';
import Details from './Details';

function Contact(props) {
    return (
        <div className="App">
            <div className="card">
                <div className="top">
                    <p className="name">{props.name}</p>
                    <Avatar img={props.img} />
                </div>
                <div className="bottom">
                    <Details detailPhone={props.phone} detailEmail={props.email}/>
                </div>
            </div>
        </div>
    );
}

export default Contact;
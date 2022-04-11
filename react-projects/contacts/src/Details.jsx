import React from 'react';

function Details(props) {
    return <div className="info">
        <p>{props.detailPhone}</p>
        <p>{props.detailEmail}</p>
    </div>
}

export default Details;
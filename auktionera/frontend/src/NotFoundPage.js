import React from 'react';
import { Link } from "react-router-dom";

function NotFoundPage() {
    return (
        <div>
            <h3>Page not found :(</h3>
            <h4>...or maybe you just destroyed internet.</h4>
            <p>
                <Link to="/dashboard">Back to home</Link>
            </p>
        </div>
    );
}
export default NotFoundPage;
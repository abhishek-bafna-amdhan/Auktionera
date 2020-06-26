import React from 'react';
import { NavLink } from "react-router-dom";

function Header() {
    const activeStyle = { color: "purple"};
    return (
        <nav>
            <NavLink activeStyle={activeStyle} to="/dashboard">Home</NavLink>{" | "}
            <NavLink activeStyle={activeStyle} to="/auctions">Auctions</NavLink>{" | "}
            <NavLink activeStyle={activeStyle} to="/about">About</NavLink>
        </nav>
    )
}
export default Header;
import React from 'react';
import logo from './logo.svg';
import './App.css';
import { BrowserRouter, Route } from "react-router-dom";
import login from "./login";
import dashboard from "./Dashboard";
import interceptors from "./Interceptors";
import auction from "./Auction";

function App() {
  return (
    <div className="App">
      <header className="App-header">       
        <img src={logo} className="App-logo" alt="logo" />
        <BrowserRouter>
          <Route exact path="/" component={login} />
          <Route exact path="/dashboard" component={dashboard} />
          <Route exact path="/auction" component={auction}/>
        </BrowserRouter>
      </header>
    </div>
  );
}

export default App;

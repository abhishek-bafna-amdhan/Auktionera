import React from 'react';
import logo from './logo.svg';
import './App.css';
import { BrowserRouter, Route } from "react-router-dom";
import login from "./login";
import dashboard from "./Dashboard";

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <BrowserRouter>
          <Route exact path="/" component={login} />
          <Route exact path="/dashboard" component={dashboard} />
        </BrowserRouter>
        
        <img src={logo} className="App-logo" alt="logo" />

      </header>
    </div>
  );
}

export default App;

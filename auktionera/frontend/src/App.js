import React from 'react';
import logo from './logo.svg';
import './App.css';
import { Switch , Route } from "react-router-dom";
import login from "./login";
import dashboard from "./Dashboard";
import interceptors from "./Interceptors";
import Header from "./components/Header";
import NotFoundPage from "./NotFoundPage";
import AuctionList from "./AuctionList";
import AboutPage from "./components/AboutPage";

function App() {
  return (
    <div className="App">
      <Header />
        <Switch>
          <Route exact path="/" component={login} />
          <Route path="/dashboard" component={dashboard} />
          <Route path="/auctions" component={AuctionList}/>
          <Route path="/about" component={AboutPage}/>
          <Route component={NotFoundPage} />
        </Switch>
    </div>
  );
}

export default App;

import React, { useState, useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { Router, Switch, Route, Link } from "react-router-dom";

import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";

import Login from "./components/Login";
import Home from "./components/Home";
import AdminHomePage from './components/AdminHomePage'
import ManagerHomePage from './components/ManagerHomePage'
import CustomerHomePage from './components/CustomerHomePage'
import { logout } from "./actions/auth";
import { clearMessage } from "./actions/message";
import AdminApp from './AdminApp';
import ManagerApp from './ManagerApp';
import CustomerApp from './CustomerApp';

import { history } from "./helpers/history";

const App = () => {

  const { user: currentUser } = useSelector((state) => state.auth);
  const dispatch = useDispatch();

  useEffect(() => {
    history.listen((location) => {
      dispatch(clearMessage()); // clear message when changing location
    });
  }, [dispatch]);


  const logOut = () => {
    dispatch(logout());
  };

  return (
    <Router history={history}>
      <div>
        {/* <nav className="navbar navbar-expand navbar-dark bg-dark"> */}
          
          {currentUser ? (
            <div className="navbar navbar-expand-lg navbar-dark bg-dark float-right">
              <li className="container-fluid nav-link pt-1 pb-3 mt-1">
                <a href="/login" className="text-white" onClick={logOut}>
                  Logout
                </a>
              </li>
            </div>
          ) : (
            <div  className="navbar navbar-expand-lg navbar-dark bg-dark">
              <li className="container-fluid pt-2 pb-2 mt-2 mb-2">
                <Link to={"/login"} className="text-white">
                  Login
                </Link>
              </li>
            </div>
          )}
        {/* </nav> */}

        <div className="">
          <Switch>
            <Route exact path={["/", "/home"]} component={Home} />
            <Route exact path="/login" component={Login} />
            {/* <Route path="/admin" component={AdminHomePage} /> */}
            <Route path="/admin" component={AdminApp} />
            <Route path="/customer" component={CustomerApp} />
            <Route path="/manager" component={ManagerApp} />
          </Switch>
        </div>
      </div>
    </Router>
  );
};

export default App;
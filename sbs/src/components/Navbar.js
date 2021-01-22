import React from 'react';
import {BrowserRouter as Router,Route,Link ,NavLink} 
from "react-router-dom";
//import axios from "axios";
//import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
class Navbar extends React.Component
{
    render()
    {
        return(
           <div>
            <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
                <div className="container-fluid">
                    <div className="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                        <li className="navbar-brand pl-4 ml-4">
                        <a className="nav-link active" aria-current="page" href="#"><i className="fas fa-swatchbook"> SBS</i></a>
                        </li>
                        <li className="navbar-brand pl-4 ml-4">
                        <NavLink className="nav-link active" aria-current="page" exact to = "/ShowAllProduct">Home</NavLink>
                        </li>
                        <li className="navbar-brand">
                        <NavLink className="nav-link" exact to = "/SideBar">DashBoard</NavLink>
                        </li>
                        <li className="navbar-brand">
                        <NavLink className="nav-link" exact to="/ViewBooking">Bookings</NavLink>
                        </li>
                        <li className="navbar-brand">
                        <NavLink className="nav-link" exact to="/ViewDeliveryStatus">Delivery Status</NavLink>
                        </li>
                        <li className="navbar-brand">
                        <form className="d-flex">
                        <input className="form-control me-2" type="search" placeholder="Search" aria-label="Search"/>
                        <button className="btn btn-outline-light" type="submit">Search</button>
                        </form>
                        </li>
                        <li className="navbar-brand">
                        <a className="nav-link" href="#">Logout</a>
                        </li>
                    </ul>
                  
                    </div>
                </div>
            </nav>
        </div>
        )
    
    }
    
}

export default Navbar;
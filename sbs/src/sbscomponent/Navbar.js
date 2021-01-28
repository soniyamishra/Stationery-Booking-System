import React from 'react';
import { BrowserRouter as Router, Route, Link, NavLink }
    from "react-router-dom";
//import axios from "axios";
//import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
class Navbar extends React.Component {
    render() {
        return (
            <div>
                <nav class="navbar navbar-expand-md navbar-dark bg-dark">
                    <div class="mx-auto order-0">
                        <li className="navbar-brand ml-4 pl-4">
                            <a className="nav-link active text-white" aria-current="page" href="#"><i className="fas fa-swatchbook"> SBS</i></a>
                        </li>
                    </div>
                    <div class="navbar-collapse collapse w-100 order-3 dual-collapse2">
                        <ul class="navbar-nav ml-auto">

                            <li className="navbar-brand mr-4 pr-4">
                                <NavLink className="nav-link" aria-current="page" exact to="/ShowAllProduct">Home</NavLink>
                            </li>

                            <li className="navbar-brand mr-4 pr-4">
                                <NavLink className="nav-link" exact to="/SideBar">DashBoard</NavLink>
                            </li>

                            <li className="navbar-brand mr-4 pr-4">
                                <NavLink className="nav-link" exact to="/ViewBooking">Bookings</NavLink>
                            </li>
                            <li className="navbar-brand mr-4 pr-4">
                                <NavLink className="nav-link" exact to="/ViewDeliveryStatus">Delivery Status</NavLink>
                            </li>
                            {/*    <li className="navbar-brand my-2 my-lg-0">
                        <form className="d-flex">
                        <input className="form-control me-2" type="search" placeholder="Search" aria-label="Search"/>
                        <button className="btn btn-outline-light" type="submit">Search</button>
                        </form>
                        </li> */}
                        </ul>
                    </div>
                </nav>
            </div>
        )

    }

}

export default Navbar;
import React from 'react';
import {BrowserRouter as Router,Route,Link ,NavLink} 
from "react-router-dom";
import AddDeliveryStatus from './AddDeliveryStatus';
//import axios from "axios";
//import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import AddProduct from './AddProduct';
import AddReview from './AddReview';
import ManageProduct from './ManageProduct';
import UpdateDeliveryStatus from './UpdateDeliveryStatus';
import AddDeliveryComponent from "./AddDeliveryComponent";
import UpdateDeliveryComponent from "./UpdateDeliveryComponent";
import Updatecount from './Updatecount';
import ProductApproval from './ProductApproval';
import DeleteProduct from './DeleteProduct';
import ExportExcel from './ExportExcel';
import ExportPdf from '../ExportPdf';

class SideBar extends React.Component
{
    render()
    {
        return(<div>
            <Router>
            <div className="sidenav">
                <NavLink exact to="/AddProduct">Add Product</NavLink>   
                <NavLink exact to="/ManageProduct"> Update Products</NavLink>
                <NavLink exact to="/DeleteProduct"> Delete Products</NavLink>
                <NavLink exact to="/ProductApproval"> Manage Orders</NavLink>  
                <NavLink exact to="/AddDeliveryStatus"> Add Delivery Status</NavLink>
                <NavLink exact to="/UpdateDeliveryStatus">Update Delivery Status</NavLink>
                <NavLink exact to="/ExportExcel" >Export To Excel <i class="fa fa-file-excel-o" aria-hidden="true"></i> </NavLink>
                <NavLink exact to="/src/ExportPdf">Export To Pdf <i class="fa fa-file-pdf-o" aria-hidden="true"></i></NavLink>
            </div>
            <div className="main">
                <Route path="/AddProduct" component={AddProduct}></Route>
                <Route path="/ManageProduct" component={ManageProduct}></Route>
                <Route path="/ProductApproval" component={ProductApproval}></Route>
                <Route path="/AddDeliveryStatus" component={AddDeliveryStatus}></Route>
                <Route path="/UpdateDeliveryStatus" component={UpdateDeliveryStatus}></Route>
                <Route path="/Bookings" component={AddReview}></Route>
                <Route path="/AddDeliveryComponent/:id" component={AddDeliveryComponent}></Route>
                <Route path="/UpdateDeliveryComponent/:id" component={UpdateDeliveryComponent}></Route>
                <Route path="/DeleteProduct" component={DeleteProduct}></Route>
                <Route path="/Updatecount/:id" component={Updatecount}></Route>
                <Route path="/ExportExcel" component={ExportExcel}></Route>
                <Route path="/src/ExportPdf" component={ExportPdf}></Route>
            </div>
            </Router>
        </div>)
        
    }
}

export default SideBar;
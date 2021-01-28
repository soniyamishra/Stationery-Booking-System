import logo from './logo.svg';
import './App.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import Navbar from "./sbscomponent/Navbar";

import './sidebar.css';
import {BrowserRouter as Router,Route,Link ,NavLink ,Redirect}
from "react-router-dom";
import ShowAllProduct from "./sbscomponent/ShowAllProduct";
import ProductBooking from "./sbscomponent/ProductBooking";
import UpdateDeliveryStatus from "./sbscomponent/UpdateDeliveryStatus";
import AddDeliveryStatus from "./sbscomponent/AddDeliveryStatus";
import ViewBooking from "./sbscomponent/ViewBooking";
import DeleteBooking from "./sbscomponent/DeleteBooking";
import AddReview from './sbscomponent/AddReview';
import UpdateReview from './sbscomponent/UpdateReview';
import ShowReviews from './sbscomponent/ShowReviews';
import ViewDeliveryStatus from './sbscomponent/ViewDeliveryStatus';
import CustomerNavbar from './sbscomponent/CustomerNavbar';
// import ExportPdf from './ExportPdf';

function CustomerApp(props) {
  return (
    <div>
        <Router>
        <CustomerNavbar/>
         
          <Route exact path="/">
            <Redirect to="/ShowAllProduct" component={ShowAllProduct}/>
          </Route>
          <Route path="/ShowAllProduct" component={ShowAllProduct}></Route>
          {/* <Route path="/Sidebar" component={ManagerSideBar}></Route> */}
          <Route path="/ProductBooking/:id" component={ProductBooking}></Route>
          <Route path="/UpdateDeliveryStatus" component={UpdateDeliveryStatus}></Route>
          <Route path="/AddDeliveryStatus" component={AddDeliveryStatus}></Route>
          <Route path="/ViewBooking" component={ViewBooking}></Route>
          <Route path="/products/delete/:id"   component={DeleteBooking}     />
          <Route path="/ViewDeliveryStatus" component={ViewDeliveryStatus}></Route>
          {/* <Route path="/ExportPdf" component={ExportPdf}></Route> */}
          <Route path="/DeleteBooking" component={DeleteBooking}></Route>
          <Route path="/AddReview/:id" component={AddReview}></Route>
          <Route path="/ShowReviews/:id" component={ShowReviews}></Route>
          <Route path="/UpdateReview/:id" component={UpdateReview}></Route>
          {/*<Route path="/ConfirmBooking/:BookingId" component={ConfirmBooking} />
          <Route path="/CancelBooking/:BookingId" component={Cancelooking} />
          <Route path="/UpdateBooking/:BookingId" component={UpdateBooking} />*/}
        </Router>
    </div>
  );
}

export default CustomerApp;

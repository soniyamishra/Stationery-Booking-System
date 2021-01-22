import logo from './logo.svg';
import './App.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import Navbar from "./components/Navbar";
import Sidebar from "./components/SideBar"
import './sidebar.css';
import {BrowserRouter as Router,Route,Link ,NavLink}
from "react-router-dom";
import ShowAllProduct from "./components/ShowAllProduct";
import ProductBooking from "./components/ProductBooking";
import UpdateDeliveryStatus from "./components/UpdateDeliveryStatus";
import AddDeliveryStatus from "./components/AddDeliveryStatus";
import ViewBooking from "./components/ViewBooking";
import DeleteBooking from "./components/DeleteBooking";
import AddReview from './components/AddReview';
import UpdateReview from './components/UpdateReview';
import ViewDeliveryStatus from './components/ViewDeliveryStatus';


function App(props) {
  return (
    <div>
        <Router>
        <Navbar/>
          <Route path="/ShowAllProduct" component={ShowAllProduct}></Route>
          <Route path="/Sidebar" component={Sidebar}></Route>
          <Route path="/ProductBooking" component={ProductBooking}></Route>
          <Route path="/UpdateDeliveryStatus" component={UpdateDeliveryStatus}></Route>
          <Route path="/AddDeliveryStatus" component={AddDeliveryStatus}></Route>
          <Route path="/ViewBooking" component={ViewBooking}></Route>
          <Route path="/products/delete/:id"   component={DeleteBooking}     />
          <Route path="/AddReview" component={AddReview}></Route>
          <Route path="/UpdateReview" component={UpdateReview}></Route>
          <Route path="/ViewDeliveryStatus" component={ViewDeliveryStatus}></Route>
          {/*<Route path="/ConfirmBooking/:BookingId" component={ConfirmBooking} />
          <Route path="/CancelBooking/:BookingId" component={Cancelooking} />
          <Route path="/UpdateBooking/:BookingId" component={UpdateBooking} />*/}
        </Router>
    </div>
  );
}

export default App;

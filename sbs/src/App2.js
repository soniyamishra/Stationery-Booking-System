import logo from './logo.svg';
import './App.css';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import Navbar from "./components/Navbar";
import Sidebar from "./components/SideBar"
import './sidebar.css';
import {BrowserRouter as Router,Route,Link ,NavLink ,Redirect}
from "react-router-dom";
import ShowAllProduct from "./components/ShowAllProduct";
import ProductBooking from "./components/ProductBooking";
import UpdateDeliveryStatus from "./components/UpdateDeliveryStatus";
import AddDeliveryStatus from "./components/AddDeliveryStatus";
import ViewBooking from "./components/ViewBooking";
import DeleteBooking from "./components/DeleteBooking";
import AddReview from './components/AddReview';
import UpdateReview from './components/UpdateReview';
import ShowReviews from './components/ShowReviews';
import ViewDeliveryStatus from './components/ViewDeliveryStatus';
import ExportPdf from './ExportPdf';

function App2(props) {
  return (
    <div>
        <Router>
        <Navbar/>
         
          <Route exact path="/">
            <Redirect to="/ShowAllProduct" component={ShowAllProduct}/>
          </Route>
          <Route path="/ShowAllProduct" component={ShowAllProduct}></Route>
          <Route path="/Sidebar" component={Sidebar}></Route>
          <Route path="/ProductBooking/:id" component={ProductBooking}></Route>
          <Route path="/UpdateDeliveryStatus" component={UpdateDeliveryStatus}></Route>
          <Route path="/AddDeliveryStatus" component={AddDeliveryStatus}></Route>
          <Route path="/ViewBooking" component={ViewBooking}></Route>
          <Route path="/products/delete/:id"   component={DeleteBooking}     />
          <Route path="/ViewDeliveryStatus" component={ViewDeliveryStatus}></Route>
          <Route path="/ExportPdf" component={ExportPdf}></Route>
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

export default App2;

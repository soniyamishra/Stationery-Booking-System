import React,{Component} from 'react';
//import ReactDOM from 'react-dom';
import axios from 'axios';
import authHeader from "../services/auth-header";

export class ViewDeliveryStatus extends Component{
  
  constructor(props)
    {
        super(props); 
        this.state={AllDeliveryData:[],
          currentUser:JSON.parse(localStorage.getItem("user")),
        }
    }
   
  componentDidMount()
    {
            console.log(" Add product componentDidMount excecuted");
            axios.get(`http://localhost:8080/deliverytracking/userid/${this.state.currentUser.id}`, { headers: authHeader() })
            .then((responseDeliveryData)=>{console.log(responseDeliveryData);
            this.setState({AllDeliveryData:responseDeliveryData.data})
            console.log(this.state.AllDeliveryData)
            console.log(this.state.currentUser.id)
            })
            .catch((error)=>{console.log("Some error in product read data ");
            })
    }
    homePage = (event) => {
    // event.preventDefault();
    // alert("send to home page");
    this.props.history.push("/deliverytracking");
  };
  
    render()
    {
      var deliveryList=this.state.AllDeliveryData.map(
        (delivery,index)=>
        {
        return (    
            <tr key={delivery.id}>
                <td> {delivery.productBooking.bookingId} </td>
                <td> {delivery.productBooking.product.productName} </td>
                <td> {delivery.productBooking.product.productPrice} </td>
                <td> {delivery.productBooking.productQuantity} </td>
                <td> {delivery.productBooking.address}  {delivery.productBooking.city} </td>
                <td> {delivery.delieveryStatus} </td> 
            </tr>)
        }
    );
        return (
    
            <div className= "container-sm mt-4 pd-4">  
            <div class="card">
            <div class="card-header">
               <h4>View Delivery Status </h4>
            </div>
            <div class="card-body"></div>
            <div className="mb-3">
              <table class="table table-striped table-hover" border="2">
               <thead>
                 <tr>
                   <th scope="col" className="bg-dark text-white">Booking ID</th>
                   <th scope="col" className="bg-dark text-white">Product Name</th>
                   <th scope="col" className="bg-dark text-white">Product Price</th>
                   <th scope="col" className="bg-dark text-white">Product Quantity</th>
                   <th scope="col" className="bg-dark text-white">Delivery At</th>
                   <th scope="col" className="bg-dark text-white">Delivery Status</th>
                 </tr>
               </thead>
              <tbody>
               {deliveryList}
              </tbody>
             </table>
          </div>
         </div>
       </div> );
      }
  }
export default ViewDeliveryStatus;
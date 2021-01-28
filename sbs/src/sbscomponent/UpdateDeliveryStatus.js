import React,{Component} from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import authHeader from "../services/auth-header";

export class UpdateDeliveryStatus extends Component
{
    constructor(props)
    {
        super(props); 
        this.state={AllDeliveryData:[]
        }
    }
   
  componentDidMount()
    {
            console.log(" Update Status componentDidMount excecuted");
            axios.get(`http://localhost:8080/deliverytracking`, { headers: authHeader() })
            .then((responseDeliveryData)=>{console.log(responseDeliveryData);
            this.setState({AllDeliveryData:responseDeliveryData.data})
            })
            .catch((error)=>{console.log("Some error in product read data ");
            
            })
    }
  handleChange(event)
  {   
      this.setState({
          ...this.state,
          [event.target.name] : event.target.value
      })
  }
   
    render()
    {
        var deliveryList=this.state.AllDeliveryData.map(
            (delivery,index)=>
                {
                   return (    
                       <tr key={delivery.deliveryTrackingId}>
                        <td> {delivery.productBooking.bookingId} </td>
                        <td> {delivery.deliveryTrackingId} </td>
                        <td>{delivery.productBooking.product.productName}</td>
                        <td> {delivery.delieveryStatus} </td>
                        <td><Link exact to = {`/UpdateDeliveryComponent/${delivery.deliveryTrackingId}`} button className="btn btn-success"> Update </Link></td>
                        
                        </tr>)
                }
            );
         return(
           <div className= "container-sm mt-4 pd-4">  
              <div class="card">
              <div class="card-header">
                <h4>Update Delivery Status </h4>
              </div>
              <div class="card-body"></div>
            <div className="mb-3">
                <table class="table table-striped table-hover" border="2">
                    <thead>
                        <tr>
                            <th scope="col" className="bg-dark text-white">Booking ID</th>
                            <th scope="col" className="bg-dark text-white">Delivery Tracking ID</th>
                            <th scope="col" className="bg-dark text-white">Product Name</th>
                            <th scope="col" className="bg-dark text-white">Delivery Status</th>
                            <th scope="col" className="bg-dark text-white">Action</th>
                            </tr>
                    </thead>
                <tbody>
                {deliveryList}
               </tbody>
        </table>
        </div>
    </div>
</div>)
 }
}
export default UpdateDeliveryStatus;
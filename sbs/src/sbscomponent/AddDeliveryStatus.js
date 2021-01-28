import React,{Component} from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
import authHeader from "../services/auth-header";

export class AddDeliveryStatus extends Component
{
  constructor(props)
    {
        super(props); 
        this.state={AllDeliveryData:[]
        }
    }
   
  componentDidMount()
    {
            console.log(" Add product componentDidMount excecuted");
            axios.get(`http://localhost:8080/productbooking`, { headers: authHeader() })
            .then((responseDeliveryData)=>{console.log(responseDeliveryData);
            this.setState({AllDeliveryData:responseDeliveryData.data})
            })
            .catch((error)=>{
              console.log("Some error in Delivery read data ");
              
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
               <tr key={delivery.bookingId}>
                <td> {delivery.bookingId} </td>
                <td>{delivery.product.productName}</td>
                <td> {delivery.approvalStatus} </td>
                <td><Link exact to={`/AddDeliveryComponent/${delivery.bookingId}`} button className="btn btn-success">Add</Link></td>
                </tr>)
        }
    );
    return(
            <div className= "container-sm mt-4 pd-4">  
            <div class="card">
            <div class="card-header">
              <h4>Add Delivery Status </h4>
              </div>
              <div class="card-body"></div>
            <div className="mb-3">
            <table class="table table-striped table-hover" border="2">
              <thead>
                <tr>
                  <th scope="col" className="bg-dark text-white">Booking ID</th>
                  <th scope="col" className="bg-dark text-white">Product Name</th>
                  <th scope="col" className="bg-dark text-white">Approval Status</th>
                  <th scope="col" className="bg-dark text-white">Action</th>
                </tr>
              </thead>
              <tbody>
               
                {deliveryList}
              </tbody>
          </table>
        </div>
    </div>
  </div> )
  }
}
export default AddDeliveryStatus;
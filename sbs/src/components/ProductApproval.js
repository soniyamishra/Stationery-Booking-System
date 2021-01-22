import React from "react"
import axios  from "axios";
import {BrowserRouter as Router,Route,Link ,NavLink} 
from "react-router-dom";
class ProductApproval extends React.Component
{
     constructor(props)
    {
        super(props);    
        this.state={Allbooking:[],errMsg:""}  
     
    }

    componentDidMount()
    {
        console.log(" In ShowAllEmpComponent  componentDidMount  excecuted");
        axios.get(" http://localhost:3000/bookings")       
        .then((responseBookingData)=>{console.log(responseBookingData);
        this.setState({Allbooking:responseBookingData.data})
        })
        .catch((error)=>{console.log("Some error  data ");
         this.setState({errMsg:"Error In Reading Data"})})

    }
    render()
    {  
        var AllbookingList=this.state.Allbooking.map(
            (booking,index)=>
            {
              return (
              <tr key={booking.id}>
                  <td>{booking.id}</td>
                  <td>{booking.ProductQuantity}</td>
                  <td>{booking.ProductId}</td>
                  <td>{booking.ProductCount}</td>
                  <td>{booking.ApprovalStatus}</td>
                  
              
              <td><Link className="btn btn-outline-primary mr-1"
                    to={`/`}>CONFIRM</Link>
              </td>
              <td><Link className="btn btn-outline-primary mr-1"
                    to={`/`}>CANCEL</Link>
              </td>
              <td><Link className="btn btn-outline-primary mr-1" 
                    to={`/`}>UPDATE</Link>
              </td>
              </tr>)
         
            }
       );
        return( 
          <div className="py-4">
          <table class="table border shadow">
          <thead class="thead-dark">
            <h4 align="center" > All Product Bookings </h4>  
          </thead> 
          <tbody>
            
            <table class="table border shadow">  
            <tr> 
                <th scope="col">BookingID</th>
                <th scope="col">ProductQuantity</th>
                <th scope="col">ProductId</th> 
                <th scope="col">ProductCount</th>  
                <th scope="col">ApprovalStatus</th>  
                <th colSpan='3'>Action</th>    
            </tr>  
               {AllbookingList} 
            </table> 
           
            </tbody>   
            </table>
        </div>
        )
    }
}
export default ProductApproval;

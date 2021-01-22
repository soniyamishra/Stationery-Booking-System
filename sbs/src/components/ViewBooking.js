import React from "react"
import axios  from "axios";
import {BrowserRouter as Router,Route,Link ,NavLink} 
from "react-router-dom";
class ViewBooking extends React.Component
{
     constructor(props)
    {
        super(props);    
        this.state={AllBooking:[],errMsg:""}  
        this.deleteBooking=this.deleteBooking.bind(this)  ;
    }
    deleteBooking(id)
    {
        console.log("successfully cancelled"+id);
    }
    componentDidMount()
    {
        console.log(" In ShowAllEmpComponent  componentDidMount  excecuted");
        axios.get(" http://localhost:3000/proBookings")       
        .then((responseBooking)=>{console.log(responseBooking);
        this.setState({AllBooking:responseBooking.data})
        })
        .catch((error)=>{console.log("Some error in product booking ");
         this.setState({errMsg:"Error In product booking"})})

    }
    render()
    {  
        var bookingList=this.state.AllBooking.map(
            (bok,index)=>
            {
              return (<tr key={bok.id}>
                  <td>{bok.id}</td>
                  <td>{bok.quantity}</td>
                  <td>{bok.address}</td>
                  <td>{bok.city}</td>
                  <td>{bok.state1}</td>
                  <td>{bok.zipcode}</td>
                  <td>
                  <Link className="btn btn-outline-primary mr-2"  to={`/proBookings/delete/${bok.id}`}>Booking Cancle </Link>
                  </td>
                  </tr>)
            }
       );
        return( 
            <div className="py-4">
            <table class="table border shadow" >
            <thead class="thead-dark">
              <h4  align="center" padding="200px"> All Bookings </h4>  
            </thead>
              <tbody> 
            <table border="1" class="table border shadow">  
            <tr> 

                <th>Booking ID</th>
                <th>Product Quantity</th>
               <th>Address</th> 
               <th>City</th> 
               <th>State</th> 
               <th>Zipcode</th>   
               <th>Action</th>            
            </tr>  
               { bookingList} 
            </table> 
            </tbody>  
            </table>
        </div>
        )
    }
}
export default ViewBooking;
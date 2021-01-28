import React,{Component} from 'react';
import axios  from "axios";
import authHeader from "../services/auth-header";

class ProductApproval extends Component{
  constructor(props)
  {
        super(props); 
        this.state={product_booking:[],errMsg:""};
        this.handleCancel=this.handleCancel.bind(this) ;
        this.handleConfirm=this.handleConfirm.bind(this);
        this.handleUpdate=this.handleUpdate.bind(this);

  }
//==========================GET ALL BOOKINGS==============================================================================================================
  componentDidMount()
  {
        console.log(" Add productApproval componentDidMount excecuted");
        axios.get(`http://localhost:8080/productbooking`, { headers: authHeader() })
        .then((responseBookingData)=>{console.log(responseBookingData);
        this.setState({product_booking:responseBookingData.data,}) })
        .catch((error)=>{console.log("Some error in productApproval read data ");
      })
  }
//=============================================================================================================

//=========================CANCEL BOOKING====================================================================================================================
  
  handleCancel =(id) =>{
  
    console.log(this.state.product_booking)
    for(const product_booking of this.state.product_booking){
    if(product_booking.bookingId == id){ 
        console.log(product_booking);
        const url =`http://localhost:8080/CancelBooking/`;
        axios.put((url),{ ...product_booking}, { headers: authHeader() })

        
        .then(
          ()=>{
                alert("Product Booking Is Cancelled");
                this. componentDidMount();
          })
        .catch((error) => {
              alert(error.response.data.message);
        });
        break;
      }
  }
}
//==========================CONFORM BOOKING====================================================================================================================
  
handleConfirm =(id) =>{
 
  console.log(this.state.product_booking)
  for(const product_booking of this.state.product_booking){
    console.log(this.state.product_booking.bookingId)
    if(product_booking.bookingId == id){
      console.log(product_booking);
      const url =`http://localhost:8080/ConformBooking/`;
      axios.put((url),{ ...product_booking}, { headers: authHeader() })
      .then(
        ()=>{
              alert("Product Booking Is Confirmed");
              this. componentDidMount();
      })
      .catch((error) => {
      alert(error.response.data.message);
      });
   }
  }
}

//=========================UPDATE BOOKING======================================================================================================================
  
handleUpdate =(id) =>{
 
  console.log(this.state.product_booking)
  for(const product_booking of this.state.product_booking){
  
  if(product_booking.bookingId == id){
      console.log(product_booking);

      const url =`http://localhost:8080/UpdateBooking/`;
      axios.put((url),{ ...product_booking}, { headers: authHeader() })
      .then(
        ()=>{
              alert("Booking Approval Status Is Updated to Confirm");
              this. componentDidMount();
      })
      .catch((error) => {
        alert(error.response.data.message);
        this.props.history.push("/ProductApproval");
      });
      break;
    }
  }
}  
//============================================================================================================
    
    render()
    {
      var AllbookingList=this.state.product_booking.map(
        (booking,index)=>
        {
        return (    
            <tr key={booking.bookingId}>
                  <td>{booking.bookingId}</td>
                  <td>{booking.productQuantity}</td>
                  <td>{booking.product.productId}</td>
                  <td>{booking.product.productCount}</td>
                  <td>{booking.approvalStatus}</td>
                  <td><button className="btn btn-success" type="button" 
                      onClick={() => { this.handleConfirm(`${booking.bookingId}`) }}>CONFIRM </button></td>
                  <td><button className="btn btn-danger" type="button" 
                      onClick={() => { this.handleCancel(`${booking.bookingId}`) }}>CANCEL </button></td>
                  <td><button className="btn btn-primary" type="button" 
                      onClick={() => { this.handleUpdate(`${booking.bookingId}`) }}>UPDATE </button></td>
            </tr>)
        }
    );
        return( 
          <div className="py-4">
          <h2 align="center" > All Product Bookings </h2>  
          <table class="table table-striped table-hover" border="2">
          <tr> 
              <th scope="col" className = "bg-dark text-white" >Booking
                                ID</th>
              <th scope="col" className = "bg-dark text-white" >Product
                                Quantity</th>
              <th scope="col" className = "bg-dark text-white">Product
                                Id</th> 
              <th scope="col" className = "bg-dark text-white">Product
                              Count</th>  
              <th scope="col" className = "bg-dark text-white">Approval
                              Status</th>  
              <th colSpan='3' className = "bg-dark text-white">Action</th>    
          </tr>  
             {AllbookingList}
          </table>
      </div>
      )
    }
  }
export default ProductApproval;
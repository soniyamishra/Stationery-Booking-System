import React,{Component} from 'react';
import axios  from "axios";

class ProductApproval extends Component{
  constructor(props)
  {
        super(props); 
        this.state={product_booking:[],errMsg:""};
    //this.handleChange= this.handleChange.bind(this);
    this.handleCancel=this.handleCancel.bind(this) ;
    this.handleConfirm=this.handleConfirm.bind(this);
    this.handleUpdate=this.handleUpdate.bind(this);

  }
//==========================GET ALL BOOKINGS==============================================================================================================
  componentDidMount()
  {
        console.log(" Add productApproval componentDidMount excecuted");
        axios.get(`http://localhost:2211/productbooking`)
        .then((responseBookingData)=>{console.log(responseBookingData);
        this.setState({product_booking:responseBookingData.data,}) })
        .catch((error)=>{console.log("Some error in productApproval read data ");
      })
  }

  homePage = (event) => {
      this.props.history.push("/ProductApproval");
  };

//=========================CANCEL BOOKING====================================================================================================================
  
  handleCancel =(id) =>{
    alert(id)
    console.log(this.state.product_booking)
    for(const product_booking of this.state.product_booking){
    if(product_booking.bookingId == id){
       
        
        console.log(product_booking);
        const url =`http://localhost:2211/CancelBooking/`;
        axios.put((url),{ ...product_booking})
        .then((responseData) => {
                    console.log(responseData);
            alert("BOOKING CANCEL SUCCESSFULLY")
            this.props.history.push("/ProductApproval")
        })
        .catch((error) => {
          alert(error.response.data.message);
       // .catch((error) => {
         //   console.log(error);
           // console.log("Some error in CancelBooking");
        })
        break;
    }
  }
}
//==========================CONFORM BOOKING====================================================================================================================
  
handleConfirm =(id) =>{
  alert(id)
  console.log(this.state.product_booking)
  for(const product_booking of this.state.product_booking){
    console.log(this.state.product_booking.bookingId)
    if(product_booking.bookingId == id){
      console.log(product_booking);
      const url =`http://localhost:2211/ConformBooking/`;
      axios.put((url),{ ...product_booking})
      .then((responseData) => {
                  console.log(responseData);
         // alert("BOOKING CONFIRM SUCCESSFULLY")
          this.props.history.push("/ProductApproval");
    })
    .catch((error) => {
      alert(error.response.data.message);
   // .catch((error) => {
     //     console.log(error);
       //   console.log("Some error in ConfirmBooking");
    })
    break;
  }
  
}
}

//=========================UPDATE BOOKING======================================================================================================================
  
handleUpdate =(id) =>{
  alert(id)
  console.log(this.state.product_booking)
  for(const product_booking of this.state.product_booking){
  
  if(product_booking.bookingId == id){
      console.log(product_booking);

      const url =`http://localhost:2211/UpdateBooking/`;
      axios.put((url),{ ...product_booking})
      .then((responseData) => {
                  console.log(responseData);
            //alert("BOOKING UPDTAED SUCCESSFULY!")
            this.props.history.push("/ProductApproval");
      })
      .catch((error) => {
        alert(error.response.data.message);
     // .catch((error) => {
       //   console.log(error);
         // console.log("Some error in UpdateBooking");
      })

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





/*import React,{Component} from 'react';
import axios  from "axios";
import {NavLink} from "react-router-dom";
import { render } from '@testing-library/react';

class ProductApproval extends Component{
  constructor(props)
    {
        super(props); 
        this.state={
            bookingId:"",
            productQuantity:"",
            approvaStatus:"",
            productId:"",
            productCount:"",
            product_booking: []

        }
        this.handleChange= this.handleChange.bind(this);
        this.handleSubmit=this.handleSubmit.bind(this) ;
    };
}
    componentDidMount()
    {
            console.log(" Add product componentDidMount excecuted");
            axios.get(`http://localhost:2211/productbooking`)
            .then((responseBookingData)=>{console.log(responseBookingData);
            this.setState({product_booking:responseBookingData.data,
            })
            })
            .catch((error)=>{console.log("Some error in productBooking read data ");
            })
    }
     handleChange(event)
    {   
        this.setState({
           approvalStatus :this.state.approvalStatus
        })
    }
    handleSubmit(event)
    {
        axios.get(`http://localhost:2211/productbooking/`)
        .then((responseData) => {
          console.log(responseData);
          this.setState({product_booking:responseData.data})
        }).catch((error) => {
          console.log(error);
              console.log("Some error in read booking id");
      })
      
      console.log(this.state.product_booking.productQuantity)
        
      const url =`http://localhost:2211/CancelBooking/`;
        axios.put(url,{  
          bookingId:this.state.product_booking.bookingId
          //bookingId:this.state.product_booking.bookingId,
          }).then((responseData) => {
            console.log(responseData);
          }).catch((error) => {
            console.log(error);
      console.log("Some error in cancel Booking ");
        })
  }
    
        render()
        {
            return 
            <p>Data deleted</p>
        }
        */
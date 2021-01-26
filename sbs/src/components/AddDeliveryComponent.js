import React,{Component} from 'react';
//import ReactDOM from 'react-dom';
import axios from 'axios';

export class AddDeliveryComponent extends Component{

    state = {
        bookingId: "",
        delieveryStatus: "",
        productBooking: [],    
        
    };

    handleChange(event)
    {   
        this.setState({
            ...this.state,
            [event.target.name] : event.target.value
        })
    }

    componentDidMount()
{
        console.log(this.props.match.params.id);
        axios.get(`http://localhost:2211/productbooking/id/${this.props.match.params.id}`)
        .then((productBookingData)=>{console.log(productBookingData);
            this.setState({productBooking:productBookingData.data 
            })
          console.log(this.state.productBooking);
        })

      
        .catch((error)=>{console.log(error);
        this.setState({errMsg:"Error In Reading Product Booking Data"})} );
        this.handleChange=this.handleChange.bind(this);
        this.handleSubmit=this.handleSubmit.bind(this);
        
       
}
   
    // handleSubmit = (event)=>{
    //     event.preventDefault();
    //     axios.post(`http://localhost:2211/deliverytracking`, {
    //         delieveryStatus:this.state.delieveryStatus,
    //         productBooking:{
    //             bookingId:this.productBooking.bookingId
    //         }
    //       })
    //       .then(function (response) {
    //         console.log(response);
    //       })
    //       .catch(function (error) {
    //         console.log(error);
    //       })

    // }

    handleSubmit = async (event) => {
        event.preventDefault();
        let AllDelivery = {
            delieveryStatus:this.state.delieveryStatus,
            productBooking:{
                bookingId:this.state.productBooking.bookingId
            }
        };
        await axios
        .post("http://localhost:2211/deliverytracking", AllDelivery)
        .then((data) => {
            alert("Delivery added successfully")
        })
        .catch((error) => {
          alert(error.response.data.message);
        });
      console.log(this.state.productBooking);
      console.log(this.state.delieveryStatus);
    };

    render()
    {
        return(
            <div className= "container-sm mt-4 pd-4">  
            <div class="card">
              <div class="card-header">
                <h4>Add Delivery Status </h4>
              </div>
              <div class="card-body"></div>
            <form>
             
                <label className="form-label">Booking Id : </label>
                <input type="text" className="form-control" name="bookingId" value={this.state.productBooking.bookingId}
            onChange={this.handleChange}   readOnly/>
          
            {/* <div class="mb-3">
                <label class="form-label">Approval Status : </label>
                <input type="text" class="form-control" name="approvalStatus" value={this.state.AllDeliveryData.approvalStatus}
            onChange={this.handleChange} class="form-control" id="approvalStatus" required readOnly/>
            </div> */}
            
                <label className="form-label">Delivery Status : </label>
                <input type="text"  name="delieveryStatus"
            onChange={this.handleChange} className="form-control" id="delieveryStatus" required />
            
            <button className="btn btn-success" type="submit" onClick={this.handleSubmit}>Add</button>
            
        </form>
        </div>
        </div>)
        }
    }
export default AddDeliveryComponent;
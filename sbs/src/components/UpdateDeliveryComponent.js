import React,{Component} from 'react';
import axios from 'axios';
import { Form, Button} from 'react-bootstrap';

export class UpdateDeliveryComponent extends Component
{
  constructor(props)
    {
        super(props); 
        this.state={AllDeliveryData:[],
          delieveryStatus:""

        }
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit=this.handleSubmit.bind(this);
    }


   
componentDidMount()
{
        console.log(this.props.match.params.id);
        axios.get(`http://localhost:2211/deliverytracking/id/${this.props.match.params.id}`)
        .then((responseDeliveryData)=>{console.log(responseDeliveryData);
            this.setState({AllDeliveryData:responseDeliveryData.data

            
            })
          console.log(this.state.AllDeliveryData);
        })

      
        .catch((error)=>{console.log(error);
        this.setState({errMsg:"Error In Reading delivery Data"})} );
        
       
}
handleChange(event)
{   
  this.setState({
    delieveryStatus:event.target.value
  } )
  console.log(this.state.AllDeliveryData.delieveryStatus);
}
handleSubmit = (event)=>{
    //event.preventDefault();
    console.log(`http://localhost:2211/deliverytracking/`);
    axios.put(`http://localhost:2211/deliverytracking/`, {
       deliveryTrackingId:this.state.AllDeliveryData.deliveryTrackingId,
       delieveryStatus:this.state.delieveryStatus
      })
      .then((response)=> {
        console.log(response);
      })
      .catch((error) =>{
        console.log(error);
        console.log("Some error in updating Delivery Status");
        alert(error.response.data.message);
      })

}
    render()
    {
      return(
        
        <div className= "container-sm mt-4 pd-4">  
            <div class="card">
              <div class="card-header">
                <h4>Update Delivery Status </h4>
              </div>
              <div class="card-body"></div> 
        
        <form>
                    
          <label for="deliveryTrackingId" className="form-label">Delivery ID</label>
          <input type="text" className="form-control" id="deliveryTrackingId" value={this.state.AllDeliveryData.deliveryTrackingId} name="deliveryTrackingId" readOnly/>
          <label for="delieveryStatus" className="form-label">Delivery Status</label>
          <input type="text" placeholder={this.state.AllDeliveryData.delieveryStatus} name="delieveryStatus" className="form-control" id="delieveryStatus" onChange={this.handleChange} required/>
          <br></br>
          <button className="btn btn-success" type="submit" onClick={this.handleSubmit}>Update</button>
        </form>
    </div>
    </div>)
  }

}
export default UpdateDeliveryComponent;
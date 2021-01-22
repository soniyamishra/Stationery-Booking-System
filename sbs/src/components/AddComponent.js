import React,{Component} from 'react';
//import ReactDOM from 'react-dom';
import axios from 'axios';

export class AddComponent extends Component{

    constructor(props)
    {
        super(props)
        this.state={productData:[]}
    }
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
            <div class="mb-3">
                <label class="form-label">Booking Id : </label>
                <input type="bookingId" class="form-control"/>
            </div>
            <div class="mb-3">
                <label class="form-label">Approval Status : </label>
                <input type="approvalStatus" class="form-control"/>
            </div>
            <div class="mb-3">
                <label class="form-label">Delivery Status : </label>
                <input type="deliveryStatus" class="form-control"/>
            </div>
            <button className="btn btn-success" type="submit">Add</button>
            
        </form>
        </div>
        </div>)
        }
    }
export default AddComponent;
import React,{Component} from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

export class AddDeliveryStatus extends Component
{
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
          <div className="mb-3">
            <table class="table">
              <thead>
                <tr>
                  <th scope="col">Booking ID</th>
                  <th scope="col">Approval Status</th>
                  <th scope="col">Action</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <th scope="row">1</th>
                  <td>Confirm</td>
                  <td><Link exact to="/AddComponent" button className="btn btn-success">Add</Link></td>
                </tr>
                <tr>
                  <th scope="row">2</th>
                  <td>Confirm</td>
                  <td><Link exact to="/AddComponent" button className="btn btn-success">Add</Link></td>
                </tr>
              </tbody>
          </table>
        </div>
      </form>
    </div>
  </div> )
  }
}
export default AddDeliveryStatus;
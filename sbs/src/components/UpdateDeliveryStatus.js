import React,{Component} from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';


export class UpdateDeliveryStatus extends Component
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
                <h4>Update Delivery Status </h4>
              </div>
            <div class="card-body"></div>

            <form>
              <div className="mb-3">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">Booking ID</th>
                            <th scope="col">Delivery Tracking ID</th>
                            <th scope="col">Delivery Status</th>
                            <th scope="col">Action</th>
                            </tr>
                    </thead>
                <tbody>
                <tr>
                    <th>1</th>
                    <td>1</td>
                    <td>Pending</td>
                    <td><Link exact to = "/UpdateComponent" button className="btn btn-success"> Update </Link></td>
                </tr>
                <tr>
                    <th>2</th>
                    <td>2</td>
                    <td>Packed</td>
                    <td><Link exact to = "/UpdateComponent" button className="btn btn-success"> Update </Link></td>
                </tr>
            </tbody>
        </table>
        </div>
    </form>
    </div>
</div>)
 }
}
export default UpdateDeliveryStatus;
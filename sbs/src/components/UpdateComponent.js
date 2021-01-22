import React,{Component} from 'react';
import axios from 'axios';
import { Form, Button} from 'react-bootstrap';

export class UpdateComponent extends Component
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
                <h4>Update Product </h4>
              </div>
              <div class="card-body"></div>
        <Form>
        <Form.Group controlId="Booking Id">
            <Form.Label> Booking ID : </Form.Label>
            <Form.Control type="text" name="Id" placeholder="Booking Id" />
        </Form.Group>

        <Form.Group controlId="DeliveryId">
            <Form.Label> Delivery ID : </Form.Label>
            <Form.Control type="text" name="Id" placeholder="Delivery Id" />
        </Form.Group>

        <Form.Group controlId="DeliveryStatus">
            <Form.Label> Delivery Status : </Form.Label>
            <Form.Control type="text" name="Id" placeholder="Delivery Status" />
        </Form.Group>
        
        <button className="btn btn-success" type="submit">Update</button>
            
      </Form>
    </div>
    </div>)
  }

}
export default UpdateComponent;
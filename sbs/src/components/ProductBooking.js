import React from "react";
import axios  from "axios";
import 'bootstrap/dist/css/bootstrap.min.css';
import { Form, Button} from 'react-bootstrap';

class ProductBooking extends React.Component
{   
  
     constructor(props)
    {
        super(props);  
        this.state=
        {
          quantity:"",
          address:"",
          city:"",
          state1:"",
          zipcode:"",
            empObj:{"id":0,"quantity":0,"address": "","city": "","state1": "","zipcode": 0}
        }  
        this.handleChange=this.handleChange.bind(this)  ;
        this.handleSubmit=this.handleSubmit.bind(this)  ;
    }

    handleChange(event)
    {
          console.log("......."+[event.target.name]);
          this.setState({[event.target.name]:event.target.value})
    }
    handleSubmit (event) 
    {
       
     let   tempEmpObj={
       "quantity":this.state.quantity,
       "address":this.state.address,
        "city":this.state.city,
        "state1":this.state.state1,
        "zipcode":this.state.zipcode }
        console.log("....."+tempEmpObj);
        this.setState({empObj:tempEmpObj},
            ()=>alert("booking successful"));
        axios.post("http://localhost:3000/proBookings", tempEmpObj)
            .then((empdataRes) => {
              console.log("data added"+empdataRes.data.message)})
            .catch((emperror) => {
              alert(emperror.response.data.message);             
            });    
    }
    
    render()
    {  
      
        return( 
        <div>   
         
  <Form  onSubmit={this.handleSubmit}>
  
        <Form.Group controlId="quantity">
        <Form.Label>Product Quantity</Form.Label>
        <Form.Control type="text" name="quantity" placeholder="Product Quantity" onChange={this.handleChange} />
        <Form.Text className="text-muted">
        </Form.Text>
      </Form.Group>


      <Form.Group controlId="address">
        <Form.Label>Product Address</Form.Label>
        <Form.Control type="text" name="address" placeholder="Address" onChange={this.handleChange} />
      </Form.Group>

  
 
      <Form.Group controlId="city">
        <Form.Label>city</Form.Label>
        <Form.Control type="text" name="city"placeholder="city" onChange={this.handleChange} />
      </Form.Group>

      <Form.Group controlId="state1">
        <Form.Label>state1</Form.Label>
        <Form.Control type="text" name="state1"placeholder="state1" onChange={this.handleChange} />
      </Form.Group>

      <Form.Group controlId="zipcode">
        <Form.Label>zipcode</Form.Label>
        <Form.Control type="text" name="zipcode"placeholder="zipcode" onChange={this.handleChange} />
      </Form.Group>
 
      <Button variant="primary" type="submit">
          Book
      </Button>
  </Form>

       
        </div>
        )
        
    }
    
}

export default ProductBooking;
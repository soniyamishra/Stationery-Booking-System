
import React from "react";
import axios  from "axios";
import 'bootstrap/dist/css/bootstrap.min.css';
// import logindata from "./login";
import authHeader from "../services/auth-header";
import { Form, Button} from 'react-bootstrap';

class ProductBooking extends React.Component
{   
    state = {
      bookingId: "",
      productQuantity: "",
      address: "",
      city: "",
      state:"",
      zipcode:"",
      productId: "",
      product: [],
      
      user:[],
      currentUser:JSON.parse(localStorage.getItem("user")),

      
  };

  componentDidMount() {
    axios.get(`http://localhost:8080/product/${this.props.match.params.id}`, { headers: authHeader() }).then((responseData) => {
        console.log(responseData);
        this.setState({ product: responseData.data })

    }).catch((error) => {
        console.log("Some error in reading the data ");
        this.setState({ errMsg: "Error In Reading product Data" })
    })
    this.handleChange=this.handleChange.bind(this);
    this.handleSubmit=this.handleSubmit.bind(this);
}

handleChange(event)
{   
    this.setState({
        ...this.state,
        [event.target.name] : event.target.value,
        id:this.state.currentUser.id,
        
    })

    console.log(this.state.city);
}

  handleSubmit = async (event) => {
    event.preventDefault();
    let Allbooking = {
      bookingId: this.state.bookingId,
      productQuantity: this.state.productQuantity,
      address: this.state.address,
      city: this.state.city,
      state: this.state.state,
      zipcode: this.state.zipcode,
      product: {
        productId: this.state.product.productId,
      },
      user: {
        id:this.state.currentUser.id,
      },
    };

    await axios
      .post("http://localhost:8080/productbooking", Allbooking, { headers: authHeader() })
      .then((data) => {
      alert("BOOKING SUCCESSFULY!")
      this.props.history.push("/ViewBooking")
    })
      .catch((error) => {
        alert(error.response.data.message);
      });
    console.log(this.state.product.productId);
    console.log(this.state.user.id);
    console.log(this.state.product);
    console.log(this.state.user);
  };
  
    
    render()
    {  
      return( 
        <div className= "container-sm  mt-5 pd-4">  
        <div class="card">
            <div class="card-header">
               <h4>All Bookings</h4>
            </div>
            <div class="card-body"></div>
            <form className="row g-3" onSubmit={this.handleSubmit}  >
                  <label for="rproductQuantity" className="form-label">Product Quantity</label>
                   <input type="number" 
                   className="form-control" 
                   id="productQuantity" 
                   name="productQuantity"  
                   placeholder="Enter productQuantity" 
                   onChange={this.handleChange} 
                   required 
                   pattern="[0-9\s]+"/>
                   
                   <label for="address" className="form-label">Address</label>
                   <input type="text" 
                   className="form-control" 
                   id="address"
                   name="address" 
                   placeholder="Enter Your address" 
                   onChange={this.handleChange} 
                   required 
                   pattern="[A-Za-z\s]+" />   

                  <label for="city" className="form-label">City</label>
                   <input type="text" 
                   className="form-control" 
                   id="city"
                   name="city" 
                   placeholder="Enter Your city" 
                   onChange={this.handleChange} 
                   required 
                   pattern="[A-Za-z\s]+" /> 


                  <label for="state" className="form-label">State</label>
                   <input type="text" 
                   className="form-control" 
                   id="state"
                   name="state" 
                   placeholder="Enter state" 
                   onChange={this.handleChange} 
                   required 
                   pattern="[A-Za-z\s]+" /> 

                    <label for="zipcode" className="form-label">Zipcode</label>
                   <input type="text" 
                   className="form-control" 
                   id="zipcode"
                   name="zipcode" 
                   placeholder="Enter zipcode" 
                   onChange={this.handleChange} 
                   required 
                   pattern="[0-9\s]+"
                    /> 

              <div className ="col-2">
                   <button className="btn btn-primary"   type="submit">Place Order</button>
              </div>      
            </form>
         </div>
        </div> 
         
      
        )
        
    }
    
}

export default ProductBooking;


/*
//=======================================================================================================
import React from "react";
import axios  from "axios";
import 'bootstrap/dist/css/bootstrap.min.css';
import logindata from "./login";
import { Form, Button} from 'react-bootstrap';

class ProductBooking extends React.Component
{   
    state = {
      bookingId: "",
      productQuantity: "",
      address: "",
      city: "",
      state:"",
      zipcode:"",
      productId: "",
      product: [],
      userId:1,
      login:[]
      
  };

  componentDidMount() {
    axios.get(`http://localhost:2211/product/${this.props.match.params.id}`).then((responseData) => {
        console.log(responseData);
        this.setState({ product: responseData.data })

    }).catch((error) => {
        console.log("Some error in reading the data ");
        this.setState({ errMsg: "Error In Reading product Data" })
    })
    alert(`${this.state.product.productId}`)
    this.handleChange=this.handleChange.bind(this);
    this.handleSubmit=this.handleSubmit.bind(this);
}

handleChange(event)
{   
    this.setState({
        ...this.state,
        [event.target.name] : event.target.value,
        userId:1,
        
    })

    console.log(this.state.city);
}

  handleSubmit = async (event) => {
    event.preventDefault();
    let Allbooking = {
      bookingId: this.state.bookingId,
      productQuantity: this.state.productQuantity,
      address: this.state.address,
      city: this.state.city,
      state: this.state.state,
      zipcode: this.state.zipcode,
      product: {
        productId: this.state.product.productId,
      },
      login: {
        userId:1,
      },
    };

    await axios
      .post("http://localhost:2211/productbooking", Allbooking)
      .then((data) => {
      alert("BOOKING SUCCESSFULY!")
    })
      .catch((error) => {
        alert(error.response.data.message);
      });
    console.log(this.state.product.productId);
    console.log(this.state.login.userId);
    console.log(this.state.product);
    console.log(this.state.login);
  };
  
    
    render()
    {  
      return( 
        <div>   
         
  <Form  onSubmit={this.handleSubmit} >
  
        <Form.Group controlId="productQuantity">
        <Form.Label>Product Quantity</Form.Label>
        <Form.Control type="number" 
                      name="productQuantity" 
                      placeholder="Product Quantity" 
                      onChange={this.handleChange} 
                      required
                      pattern="[0-9\s]+" />
        <Form.Text className="text-muted">
        </Form.Text>
      </Form.Group>


      <Form.Group controlId="address">
        <Form.Label>Address</Form.Label>
        <Form.Control type="text" 
                      name="address" 
                      placeholder="Address" 
                      onChange={this.handleChange} 
                      required 
                      pattern="[A-Za-z0-9\s]+" />
      </Form.Group>

  
 
      <Form.Group controlId="city">
        <Form.Label>city</Form.Label>
        <Form.Control type="text" 
                      name="city"
                      placeholder="city" 
                      onChange={this.handleChange} 
                      required
                      pattern="[A-Za-z\s]+" />
      </Form.Group>

      <Form.Group controlId="state">
        <Form.Label>state</Form.Label>
        <Form.Control type="text" 
                      name="state"
                      placeholder="state" 
                      onChange={this.handleChange} 
                      required
                      pattern="[A-Za-z\s]+" />
      </Form.Group>

      <Form.Group controlId="zipcode">
        <Form.Label>zipcode</Form.Label>
        <Form.Control type="text" 
                      name="zipcode"
                      placeholder="zipcode" 
                      onChange={this.handleChange} 
                      required
                      pattern="[0-9\s]+"/>
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
*/
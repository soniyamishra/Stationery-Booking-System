import React from "react";
import axios  from "axios";
import 'bootstrap/dist/css/bootstrap.min.css';
import Modal from "react-bootstrap/Modal";
import { Form, Button ,Link} from 'react-bootstrap';

class AddReview extends React.Component
{   
  
  state = {
         reviewComment: "",
         ratingNumber: "",
         product: [],
         userId:1,
         productId:"",
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
            // alert(`${this.state.product.productId}`)
            this.handleChange=this.handleChange.bind(this);
            this.handleSubmit=this.handleSubmit.bind(this);
        }
  handleChange(event)
       {   
          this.setState({
          ...this.state,
          [event.target.name] : event.target.value
          })
          console.log(this.state.reviewComment);
       }

  handleSubmit = async (event) => 
     {
        event.preventDefault();
        let AllData= {
        reviewComment: this.state.reviewComment,
        ratingNumber: this.state.ratingNumber,
        product: {
        productId: this.state.product.productId,
        },
        login: {
        userId:1,

        },
       };

    //    =====================================U have not purchased this product==========

    await axios
      .post("http://localhost:2211/review", AllData)
      .then((data) => {})
      .catch((error) => {
      alert(error.response.data.error);
      });
      alert("Your Review has Added");
      this.props.history.push("/ShowAllProduct")
      console.log(this.state.product.productId);
      // console.log(this.state.product);
      // console.log(this.state.login);
      };


    render()
    {  
      
    return(     
    <div className= "container-sm  mt-5 pd-4">  
        <div class="card">
            <div class="card-header">
               <h4>Add Review </h4>
            </div>
            <div class="card-body"></div>
            <form className="row g-3" onSubmit={this.handleSubmit}  >
                   <label for="reviewComment" className="form-label">Enter your Comment</label>
                   <input type="text" className="form-control" id="reviewComment" name="reviewComment"  placeholder="Enter Your Comment" onChange={this.handleChange} required pattern="[A-Za-z0-9\s]+"/>
                   <label for="ratingNumber" className="form-label">Enter Rating Number</label>
                   <input type="number" className="form-control" id="ratingNumber"name="ratingNumber" placeholder="Enter Your Rating" onChange={this.handleChange} required min="1" max="5"/>      
              <div className ="col-2">
                   <button className="btn btn-primary"   type="submit">Add Review</button>
              </div>      
            </form>
         </div>
        </div>
  
        )
        
    }
    
}

export default AddReview;
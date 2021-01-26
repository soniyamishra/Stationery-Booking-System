import React from "react";
import axios  from "axios";
import 'bootstrap/dist/css/bootstrap.min.css';
import { Form, Button ,Link} from 'react-bootstrap';

class UpdateReview extends React.Component
{   
  constructor(props)
    {
        super(props); 
        this.state={AllReviewData:[],
          reviewComment:"",
          ratingNumber:0,

          productName:"",
          product:[],

        }
        this.changeComment = this.changeComment.bind(this);
        this.ratingNumber= this.ratingNumber.bind(this);
        this.handleSubmit=this.handleSubmit.bind(this) ;
    }

    componentDidMount()
   {
        console.log(this.props.match.params.id);
        axios.get(`http://localhost:2211/review/${this.props.match.params.id}`)
        .then((responseReviewData)=>{console.log(responseReviewData);
            this.setState({AllReviewData:responseReviewData.data

            
            })
          console.log(this.state.AllReviewData);
        })
        .catch((error)=>{console.log(error);
        this.setState({errMsg:"Error In Reading Review Data"})} );       
   }
   changeComment(event)
   {   
        this.setState({
        reviewComment:event.target.value
        } )
        console.log(this.state.AllReviewData.reviewComment); 
   }
   ratingNumber(event)
   {   
        this.setState({
        ratingNumber:event.target.value
        } )
        console.log(this.state.AllReviewData.ratingNumber);
   }

   handleSubmit = (event)=>
   {
    //event.preventDefault();
    
     console.log(`http://localhost:2211/review/update1/`);
     axios.put(`http://localhost:2211/review/update1/`, {
     reviewId:this.state.AllReviewData.reviewId,
     reviewComment:this.state.reviewComment,
     ratingNumber:this.state.ratingNumber
     
   })
      .then((response)=> {
        console.log(response);
        // this.props.history.push("/ShowAllProduct");
      })
      .catch((error) =>{
        // alert("You have not updated your review");
        this.props.history.push("/ShowAllProduct");
 
         alert(error.response.data.message);
         
        console.log(error);
        console.log("Some error in updating Review");
      })
      // alert("Your Review has Updated");
       this.props.history.push("/ShowAllProduct");
      //console.log(this.state.AllReviewData.product);
      //console.log(this.state.AllReviewData.product.productName);

}
  render()
    {  
      // var ProductList=this.state.AllReviewData
      // .map(
      //   (productList,index)=>{
      //     {productList.product.productName}

      //   })
      //   var ProdName = ProductList[0];

      return( 

         <div className= "container-sm  mt-5 pd-4">  
            <div class="card">
               <div class="card-header">
                 <h4>Update  Review</h4>
               </div>

            <div class="card-body">
                <form className="row g-3">
                <label for="reviewId" className="form-label">Review Id</label>
                <input type="text" className="form-control" id="reviewId" name="reviewId" value={this.state.AllReviewData.reviewId}   readOnly/>
                
                   <label for="reviewComment" className="form-label">Enter your Comment</label>
                   <input type="text" className="form-control" id="reviewComment" name="reviewComment"  placeholder={this.state.AllReviewData.reviewComment} onChange={this.changeComment} required pattern="[A-Za-z0-9\s]+"/>
                   <label for="ratingNumber" className="form-label">Enter Rating Number</label>
                   <input type="number" className="form-control" id="ratingNumber"name="ratingNumber" placeholder={this.state.AllReviewData.ratingNumber} onChange={this.ratingNumber} required min="1" max="5"/>      
                   <div className ="col-2">
                   <button className="btn btn-success" onClick={this.handleSubmit}  type="submit">Update</button>
                   </div>      
               </form>
          </div>
        
        </div>
     </div>

        )
    }
}
export default UpdateReview;
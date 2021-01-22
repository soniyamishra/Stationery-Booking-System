import React from "react";
import axios  from "axios";
import 'bootstrap/dist/css/bootstrap.min.css';
import { Form, Button ,Link} from 'react-bootstrap';

class UpdateReview extends React.Component
{   
  render()
    {  
      return( 
        <div  class="row justify-content-center" margin-top-500px >
         
        <Form margin-top-500px className = "form" onSubmit={this.handleSubmit} >
         <h1 margin-top-500px  >
          <span className="badge badge-dark">  &nbsp;&nbsp; &nbsp;Update  Review &nbsp;&nbsp;&nbsp;</span>
         </h1>
        <Form.Group controlId="empId">
        <Form.Label>Enter Comment </Form.Label>
        <Form.Control type="text" name="empId" placeholder="Enter Comment" onChange={this.handleChange} />
        <Form.Text className="text-muted">
        </Form.Text>
      </Form.Group>

      <Form.Group controlId="empName">
        <Form.Label>Enter Ratting Number Between 1 - 5</Form.Label>
        <Form.Control type="text" name="empName" placeholder="Enter Ratting Number Between 1 - 5" onChange={this.handleChange} />
      </Form.Group>
 
      <Button variant="primary" type="submit">Update Review</Button>
     </Form>
     </div>
        )
    }
}
export default UpdateReview;
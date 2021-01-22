import React from "react";
import axios  from "axios";
import 'bootstrap/dist/css/bootstrap.min.css';
import {NavLink} from 'react-router-dom';
import {Form, Button ,Link} from 'react-bootstrap';

class AddReview extends React.Component
{   
  
     constructor(props)
    {
        super(props);  
        this.state=
        {
            empId:"",
            empName:"",
            empObj:{"id":0,"empId": 0,"empName": ""}
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
       
     let   tempEmpObj={"empId":this.state.empId,
       "empName":this.state.empName
         }
        console.log("....."+tempEmpObj);
        this.setState({empObj:tempEmpObj},
            ()=>alert(" U entered...."+this.state.empObj.empName));
        axios.post("http://localhost:3000/emps", tempEmpObj)
            .then((empdataRes) => {console.log("data added"+empdataRes.data.message)})
            .catch((emperror) => {
              alert(emperror.response.data.message);             
            });    
    }
    
    render()
    {  
      
        return( 
            <div className= "container-sm mt-4 pd-4">
                <div> 
                   <h3> Product name : Pen</h3> 
                   <h3> Model name : Black</h3>
                   <h3> Brand name : Montex</h3>
                   <h3> Product price : 20 Rs</h3>
                </div>  
                <table class="table table-striped table-hover" >
                        <thead>
                            <tr>
                                <th scope="col" >UserId</th>
                                <th scope="col" >Comment</th>
                                <th scope="col" >Rating</th>
                                <th NavLink=""> Action </th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th scope="row">1</th>
                                <td>Quality of product is Good</td>
                                <td>3/5</td>
                                <td><NavLink exact to="/UpdateReview" type="button"> Update </NavLink></td>
                            </tr>
                            <tr>
                                <th scope="row">2</th>
                                <td>Quality of product is Bad</td>
                                <td>1/5</td>
                                <td><NavLink exact to="/UpdateReview" type="button"> Update </NavLink></td>
                            </tr>
                        </tbody>
                    </table>
            
         
        <Form margin-top-500px className = "form" onSubmit={this.handleSubmit} >
         
        <Form.Group controlId="empId">
        <Form.Label>Enter Your Comment </Form.Label>
        <Form.Control type="text" name="empId" placeholder="Enter Comment" onChange={this.handleChange} />
        <Form.Text className="text-muted">
        </Form.Text>
      </Form.Group>

      <Form.Group controlId="empName">
        <Form.Label>Enter Your Rating </Form.Label>
        <Form.Control type="text" name="empName" placeholder="Enter Rating" onChange={this.handleChange} />
      </Form.Group>
 
      <Button variant="primary" type="submit">
       Add Review
      </Button>
     </Form>
    </div>
        )
        
    }
}
export default AddReview;
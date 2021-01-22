import React from "react";
import axios  from "axios";

class DeleteBooking extends React.Component
{   
     constructor(props)
    {
        super(props);   
        this.state={}            
    }   
    componentDidMount() {
        axios
          .delete(`http://localhost:3000/proBookings/${this.props.match.params.id}` )
          .then(
            (result) => {
              alert("product is cancled..");
            },
            (error) => {
              alert("product is not cancled."+error);
            }
          );
        this.props.history.push("/ViewBooking");
      } 
    render()
    {  
        return( 
        <div>
          <p>  Product Successfully cancelled.{this.props.match.params.id}</p>
         </div>
        )
    }
}
export default DeleteBooking;
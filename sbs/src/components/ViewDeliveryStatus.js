import React,{Component} from 'react';
//import ReactDOM from 'react-dom';
import axios from 'axios';

export class ViewDeliveryStatus extends Component{

    constructor(props)
    {
        super(props)
        this.state={productData:[]}
    }
    componentDidMount()
    {
        axios.get('http://localhost:2211/deliverytracking/{bookingId}')
        .then(response =>
                {  
                    console.log(response.data);  
                    this.setState
                    ({  
                        ProductData: response.data  
                    });  
                }) ;
    }
    render()
    {
    return(
        
        
            <div className="mt-4 container-sm" >
            <table class="table table-striped ">
                <thead>
                    <tr>
                        <th scope="col" className="bg-dark text-white">Booking ID</th>
                        <th scope="col" className="bg-dark text-white">Delivery Status</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <th scope="row">1</th>
                        <td>Pending</td>
                    </tr>
                    <tr>
                        <th scope="row">2</th>
                        <td>Packed</td>
                    </tr>
                </tbody>
            </table>
        </div>
   )
    }
}
export default ViewDeliveryStatus;
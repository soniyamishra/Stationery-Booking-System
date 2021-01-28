import React, { Component } from 'react'; 
import Table from '@material-ui/core/Table';  
import TableBody from '@material-ui/core/TableBody';  
import TableCell from '@material-ui/core/TableCell';  
import TableContainer from '@material-ui/core/TableContainer';  
import TableHead from '@material-ui/core/TableHead';  
import TableRow from '@material-ui/core/TableRow'; 
//import Button from '@material-ui/core/Button';   
import axios from 'axios';      
import './App.css';  
import Pdf from "react-to-pdf";
import authHeader from "./services/auth-header";
 
const ref = React.createRef();

export class ExportPdf extends Component {  
  constructor(props) {  
    super(props)  
    this.state = {  
      ProductData: []  
    } 
    this.handleSubmit=this.handleSubmit.bind(this); 
  } 
/*   handleSubmit(){
    axios.get('http://localhost:8080/api/pdf/products',{ headers: authHeader() })
			.then(response => {
				response.blob().then(blob => {
					let url = window.URL.createObjectURL(blob);
					let a = document.createElement('a');
					a.href = url;
					a.download = 'product.pdf';
					a.click();
				});
		});
  } */
  componentDidMount() {  
    axios.get('http://localhost:8080/product',{ headers: authHeader() }).then(response => {  
      console.log(response.data);  
      this.setState({  
        ProductData: response.data  
      });  
    });  
  }  

  handleSubmit(){
    axios.get('http://localhost:8080/api/pdf/products',{ headers: authHeader() })
			.then(response => {
      console.log(response.data);  
    });  
   
  }  

  handleExport(divName){
    var printContents = document.getElementById(divName).innerHTML;
    var originalContents = document.body.innerHTML;

    document.body.innerHTML = printContents;

    window.print();

    document.body.innerHTML = originalContents;
  }
  render() {  
    console.log(this.state.ProductData);  
    return (  
      <div id="export-pdf" ref={ref}>  
        <h1 align="center"> <i className="fas fa-swatchbook"> Stationary Booking System </i></h1> <br/>
        <TableContainer>  
          <Table>  
            <TableHead>  
              <TableRow>  
                <TableCell><strong>product
                        Id</strong></TableCell>  
                <TableCell align="right"><strong>product
                        Name</strong></TableCell>  
                <TableCell align="right"><strong>product
                        Model</strong></TableCell>  
                <TableCell align="right"><strong>product
                        Brand</strong></TableCell>  
                <TableCell align="right"><strong>product
                        Price</strong></TableCell>  
                <TableCell align="right"><strong>product
                        Count</strong></TableCell>  
                <TableCell align="right"><strong>product
                        CreatedAt</strong></TableCell>
                <TableCell align="right"><strong>product
                        UpdatedAt</strong></TableCell>
                <TableCell align="right"><strong>product
                        DeletedAt</strong></TableCell>  
                <TableCell align="right" ><strong>product
                        DeletedFlag</strong></TableCell>  
              </TableRow>  
            </TableHead>  
            <TableBody>  
              {  
                this.state.ProductData.map((p, index) => {  
                  return <TableRow key={index}>  
                    <TableCell component="th" scope="row">{p.productId}</TableCell>  
                    <TableCell align="right">{p.productName}</TableCell>  
                    <TableCell align="right">{p.productModel}</TableCell>  
                    <TableCell align="right">{p.productBrand}</TableCell>  
                    <TableCell align="right">{p.productPrice}</TableCell>  
                    <TableCell align="right">{p.productCount}</TableCell>  
                    <TableCell align="right">{p.productCreatedAt}</TableCell>
                    <TableCell align="right">{p.productUpdatedAt}</TableCell> 
                    <TableCell align="right">{p.productDeletedAt}</TableCell>   
                    <TableCell align="right">{p.productDeleteFlag}</TableCell>  
                  </TableRow>  
                })  
              }  
            </TableBody>  
          </Table><br></br>
          </TableContainer>
          
         <div class="text-center"> 
          <a href="http://localhost:8080/api/pdf/products" className="fas fa-download btn btn-success" onClick={()=>{ this.handleExport("export-pdf") }}> Print </a> 
          </div>  
      </div>  
    );  
  }  
}  
export default ExportPdf;

     /*      <Pdf targetRef={ref} filename="product.pdf">
              {({ toPdf }) => 
              <button onClick={toPdf} className="btn btn-success">
                DOWNLOAD
              </button>}
            </Pdf>
            /<a href={"http://localhost:2211/api/pdf/products"} download={"product.pdf"}>{"Download Pdf"}</a>
            

            
              </a>  */
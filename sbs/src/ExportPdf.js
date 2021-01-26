import React, { Component } from 'react'  
import Table from '@material-ui/core/Table';  
import TableBody from '@material-ui/core/TableBody';  
import TableCell from '@material-ui/core/TableCell';  
import TableContainer from '@material-ui/core/TableContainer';  
import TableHead from '@material-ui/core/TableHead';  
import TableRow from '@material-ui/core/TableRow';  
import Paper from '@material-ui/core/Paper';  
import axios from 'axios';  
import jsPDF from 'jspdf';  
import Button from '@material-ui/core/Button';  
import './App.css';  
import html2canvas from 'html2canvas';
import ReactToPdf from 'react-to-pdf';  
  
export class ExportPdf extends Component {  
  constructor(props) {  
    super(props)  
    this.state = {  
      ProductData: []  
    }  
  } 

  
  componentDidMount()
  {  
    axios.get('http://localhost:2211/api/pdf/products').then(response =>
    {  
        console.log(response.data);  
       
    }).catch((error) => {
        console.log("Some error in reading the data ");
       
    })
      axios.get('http://localhost:2211/product/allproduct').then(response =>
      {  
          console.log(response.data);  
          this.setState
          ({  
              ProductData: response.data  
          });  
      }).catch((error) => {
          console.log("Some error in reading the data ");
          this.setState({ errMsg: "Error In Reading product Data" })
      })

     
  }  

  
  render() {  
    console.log(this.state.ProductData);  
    return (  
      <div>  
        <h1 align="center"> <i className="fas fa-swatchbook"> Stationary Booking System </i></h1> <br/>
        <TableContainer id="pdfdiv" className="txt" component={Paper}>  
          <Table stickyHeader aria-label="sticky table">  
            <TableHead>  
              <TableRow>  
                <TableCell><strong>productId</strong></TableCell>  
                <TableCell align="right"><strong>productName</strong></TableCell>  
                <TableCell align="right"><strong>productModel</strong></TableCell>  
                <TableCell align="right"><strong>productBrand</strong></TableCell>  
                <TableCell align="right"><strong>productPrice</strong></TableCell>  
                <TableCell align="right"><strong>productCount</strong></TableCell>  
                <TableCell align="right"><strong>productCreatedAt</strong></TableCell>
                <TableCell align="right"><strong>productUpdatedAt</strong></TableCell>
                <TableCell align="right"><strong>productDeletedAt</strong></TableCell>  
                <TableCell style={{ paddingRight: "60px" }} align="right" ><strong>productDeletedFlag</strong></TableCell>  
              </TableRow>  
            </TableHead>  
            <TableBody>  
              {  
                this.state.ProductData.map((p, index) => {  
                  return <TableRow key={index}>  
                    <TableCell component="th" scope="row">  
                        {p.productId}  
                    </TableCell>  
                    <TableCell align="right">{p.productName}</TableCell>  
                    <TableCell align="right">{p.productModel}</TableCell>  
                    <TableCell align="right">{p.productBrand}</TableCell>  
                    <TableCell align="right">{p.productPrice}</TableCell>  
                    <TableCell align="right">{p.productCount}</TableCell>  
                    <TableCell align="right">{p.productCreatedAt}</TableCell>
                    <TableCell align="right">{p.productUpdatedAt}</TableCell> 
                    <TableCell align="right">{p.productDeletedAt}</TableCell>   
                    <TableCell style={{ paddingRight: "114px" }} align="right">{p.productDeleteFlag}</TableCell>  
                  </TableRow>  
                })  
              }  
            </TableBody>  
          </Table><br></br> 
          <div class="text-center">  
            <ReactToPdf>
              {({toPdf, targetRef}) =>  (
                <Button onClick={toPdf} ref={targetRef} variant="contained" color="primary">
                    DOWNLOAD
                </Button>
              )}
            </ReactToPdf>
          </div>   
        </TableContainer>  
      </div>  
    );  
  }  
}  
export default ExportPdf;
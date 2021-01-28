import React, { useState, useEffect } from "react";

import UserService from "../services/user.service";
import BoardCustomer from "./BoardCustomer";
const CustomerHomePage = () => {
    const [content, setContent] = useState("");
  
    useEffect(() => {
      UserService.getCustomerBoard().then(
        (response) => {
            console.log(response.data)
            setContent(BoardCustomer);
        },
        (error) => {
          setContent("Access allowed only to customer");
          console.log((error.response &&
            error.response.data &&
            error.response.data.message) ||
          error.message ||
          error.toString())
        }
      );
    }, []);
  
    return (
      <div className="container">
        <header className="jumbotron">
          {content}
          </header>
      </div>
    );
  };
   export default CustomerHomePage;
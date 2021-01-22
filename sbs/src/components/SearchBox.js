import React from 'react';

const SearchBox = (props) =>{
    return(
<div class="form-outline">


  <input type='search'
         className = 'search'
         id="form1" class="form-control"
         placeholder ={props.placeholder}
         onChange ={props.handleChange}
         id="form1" class="form-control"
         
        />
</div>
        
    )

}

export default SearchBox;
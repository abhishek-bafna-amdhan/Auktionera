import React, { useState, useEffect, Component, useCallback } from "react";
import { Button } from "react-bootstrap";
import Axios from "axios";
import {useDropzone} from 'react-dropzone';

const UserProfile = () => {
  const [userProfile, setUserProfile] = useState([]);

  const fetchUserProfile = () => {
    Axios.get("http://localhost:8080/api/account").then(res => {
    console.log(res.data);  
    setUserProfile(res.data.user);
    })
  }

  useEffect(() => {
    fetchUserProfile();
  }, []);

  return (
    <div>
      <p>Hi dear {userProfile.userName}</p>
      <Dropzone></Dropzone>
    </div>
  )
};

function Dropzone() {
  const onDrop = useCallback(acceptedFiles => {
    const file = acceptedFiles[0];

    console.log(acceptedFiles[0]);
    const formData = new FormData();
    formData.append("file", file);

    Axios.post("http://localhost:8080/api/images/upload/1", formData, 
    {
      headers: {
        "Content-Type": "multipart/form-data"
      }
    }).then(() => {
      console.log("upload succeeded!");
    }).catch(err => {
      console.log(err);
    });
  }, [])

  const {getRootProps, getInputProps, isDragActive} = useDropzone({onDrop})

  return (
    <div {...getRootProps()}>
      <input {...getInputProps()} />
      {
        isDragActive ?
          <p>Drop the images here ...</p> :
          <p>Drag 'n' drop images here, or click to select images</p>
      }
    </div>
  )
}

class dashboard extends Component {
  handleLogout() {
    localStorage.clear();
    window.location.href = "/";
  }

  createAuction() {
    this.props.history.push("/");
  }

  render() {
    return (
      <div>
        <h1>WELCOME HOME</h1>
        <UserProfile></UserProfile>
        <Button>
          <span>Create auction</span>
        </Button><br/>
        <Button
          onClick={this.handleLogout}>
          <i></i>
          <span>Logout</span>
        </Button>
      </div>
    );
  }
}
export default dashboard;
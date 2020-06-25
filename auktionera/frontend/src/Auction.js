import React, { useState, useEffect } from "react";
import Axios from "axios";

const ActiveAuction = () => {
    const [auction, setAuction] = useState([]);
  
    const fetchActiveAuction = () => {
      Axios.get("http://localhost:8080/api/auctions/1").then(res => {
      console.log(res.data);  
      setAuction(res.data);
      })
    }

    useEffect(() => {
      fetchActiveAuction();
    }, []);
  

    return (
        <div>
            <p>Auction nr. {auction.auctionId} </p>
            <p>Description: {auction.description}</p>
            <p>Category: {auction.category}</p>
            <p>Starting price: {auction.startPrice}</p>
        </div>
    );
  };

  const AuctionImages = () => {
    const [images, setImages] = useState([]);

    const fetchAuctionImages = () => {
        Axios.get("http://localhost:8080/api/images/1").then(res => {
            console.log(res.data);
            setImages(res.data);
        })
    }
  
    useEffect(() => {
      fetchAuctionImages();
    }, []);

    return images.map((image, index) => {
        return (
            <div key={index}>
                    <img src={`data:image/jpeg;base64,${image.data}`} alt={image.title}/>
                    <p>{image.title}</p>
            </div>
        );
    });
  }

  function Auction() {
      return (
          <div>
              <ActiveAuction></ActiveAuction>
              <AuctionImages></AuctionImages>
          </div>
      )
  }
  export default Auction;
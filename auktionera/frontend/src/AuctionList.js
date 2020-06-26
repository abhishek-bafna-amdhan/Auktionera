import React, { useState, useEffect } from 'react';
import Axios from "axios";

const AuctionList = () => {
    const [auctions, setAuctions] = useState([]);
  
    const fetchAllAuctions = () => {
      Axios.get("http://localhost:8080/api/auctions/").then(res => {
      console.log(res.data);  
      setAuctions(res.data);
      })
    }

    useEffect(() => {
      fetchAllAuctions();
    }, []);

    return auctions.map((auction, index) => {
        return (
            <div key={index}>
                <p>{auction.description}</p>
                <p>{index}</p>

            </div>
        );
    });
}
export default AuctionList;
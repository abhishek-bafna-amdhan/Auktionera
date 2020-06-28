import React, { useState, useEffect } from "react";
import AuctionList from "./AuctionList";
import Axios from "axios";

const AuctionsPage = () => {
    const [auctions, setAuctions] = useState([]);

    const fetchAllAuctions = () => {
        Axios.get("http://localhost:8080/api/auctions/").then((res) => {
            console.log(res.data);
            setAuctions(res.data);
        });
    };

    useEffect(() => {
        fetchAllAuctions();
    }, []);

    return (
        <>
            <h2>Active Auctions</h2>
            <AuctionList auctions={auctions}></AuctionList>
        </>
    );
};
export default AuctionsPage;

import React, {useState} from 'react';
import AuctionForm from "./AuctionForm";
import Axios from "axios"

const CreateAuctionPage = (props) => {
    const [ auction, setAuction] = useState({
        id: null,
        description: "",
        category: "",
        tags: [],
        deliveryType: null,
        startPrice: 0,
        buyoutPrice: 0,
        minBidStep: 0
    })

    function handleChange(target) {
        const updatedAuction = {...auction, [target.name]: target.value}
        setAuction(updatedAuction)
    }

    function handleSubmit(e) {
        e.preventDefault();
        const body = JSON.stringify({
            ...auction
        })
        Axios.post("http://localhost:8080/api/auctions", body, {
            headers: {
                'Content-Type': 'application/json'
            }
        })
    }
    return (
        <>
            <h2>Create an auction</h2>
            <AuctionForm auction={auction} onChange={handleChange} onSubmit={handleSubmit}/>
        </>
    )
}
export default CreateAuctionPage;
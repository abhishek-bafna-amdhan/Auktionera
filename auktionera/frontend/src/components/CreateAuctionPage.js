import React, { useState } from "react";
import AuctionForm from "./AuctionForm";
import Axios from "axios";
import { toast } from "react-toastify";

const CreateAuctionPage = (props) => {
    const [auction, setAuction] = useState({
        description: "",
        category: "",
        deliveryType: null,
        startPrice: 0,
        buyoutPrice: 0,
        minBidStep: 0,
    });

    function handleChange({ target }) {
        setAuction({ ...auction, [target.name]: target.value });
    }

    function handleSubmit(e) {
        e.preventDefault();
        const body = JSON.stringify({
            ...auction,
        });
        console.log(body);

        Axios.post("http://localhost:8080/api/auctions", body, {
            headers: {
                "Content-Type": "application/json",
            },
        })
            .then(() => {
                props.history.push("/dashboard");
                toast.success("Auction created successfully!");
            })
            .catch((err) => {
                console.log(err);
            });
    }
    return (
        <>
            <h2>Create an auction</h2>
            <AuctionForm
                auction={auction}
                onChange={handleChange}
                onSubmit={handleSubmit}
            />
        </>
    );
};
export default CreateAuctionPage;

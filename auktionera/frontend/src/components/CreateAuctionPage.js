import React, { useState } from "react";
import AuctionForm from "./AuctionForm";
import Axios from "axios";
import { toast } from "react-toastify";

const CreateAuctionPage = (props) => {
    const [errors, setErrors] = useState({});
    const [auction, setAuction] = useState({
        description: "",
        category: "",
        deliveryType: null,
        startPrice: 0,
        buyoutPrice: 0,
        minBidStep: 10,
    });

    function handleChange({ target }) {
        setAuction({ ...auction, [target.name]: target.value });
    }

    function handleSubmit(e) {
        e.preventDefault();
        if (!formIsValid()) return;
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

    function formIsValid() {
        const _errors = {};

        if (!auction.description)
            _errors.description = "Description is required";
        if (!auction.category) _errors.category = "Category is required";
        if (!auction.startPrice)
            _errors.startPrice = "A starting price is required you twat";
        if (!auction.buyoutPrice)
            _errors.buyoutPrice = "Description is required";
        if (!auction.deliveryType)
            _errors.deliveryType = "You need to select a type of delivery";

        setErrors(_errors);
        return Object.keys(_errors).length === 0;
    }
    return (
        <>
            <h2>Create an auction</h2>
            <AuctionForm
                errors={errors}
                auction={auction}
                onChange={handleChange}
                onSubmit={handleSubmit}
            />
        </>
    );
};
export default CreateAuctionPage;

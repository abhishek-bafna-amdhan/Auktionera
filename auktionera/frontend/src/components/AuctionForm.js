import React from "react";
import TextInput from "./common/TextInput";
import NumberInput from "./common/NumberInput";

function AuctionForm(props) {
    return (
        <form onSubmit={props.onSubmit}>
            <TextInput
                id="description"
                label="Description"
                name="description"
                onChange={props.onChange}
                error={props.errors.description}
            />
            <TextInput
                id="category"
                label="Category"
                name="category"
                onChange={props.onChange}
                error={props.errors.category}
            />
            <NumberInput
                id="startPrice"
                label="Starting price"
                name="startPrice"
                onChange={props.onChange}
                value={props.auction.startPrice}
                error={props.errors.startPrice}
            />
            <NumberInput
                id="buyoutPrice"
                label="Buyout price"
                name="buyoutPrice"
                onChange={props.onChange}
                value={props.auction.buyoutPrice}
                error={props.errors.buyoutPrice}
            />
            <NumberInput
                id="minBidStep"
                label="Minimum bid"
                name="minBidStep"
                onChange={props.onChange}
                value={props.auction.minBidStep}
            />

            <div className="form-group">
                <label htmlFor="deliveryType">Type of delivery</label>
                <div className="field">
                    <select
                        id="deliveryType"
                        name="deliveryType"
                        onChange={props.onChange}
                        value={props.auction.deliveryType || ""}
                        className="form-controll"
                    >
                        <option value="" />
                        <option value="PICKUPATSELLERHOME">
                            Pick up at seller's home
                        </option>
                        <option value="PICKUPATADDRESS">
                            Pick up at a specified address
                        </option>
                        <option value="SELLERSENDSTOADDRESS">
                            Seller sends item by post
                        </option>
                    </select>
                </div>
                {props.errors.deliveryType && (
                    <div className="alert alert-danger">
                        {props.errors.deliveryType}
                    </div>
                )}
            </div>
            <input
                type="submit"
                value="Save"
                className="btn btn-primary"
            ></input>
        </form>
    );
}
export default AuctionForm;

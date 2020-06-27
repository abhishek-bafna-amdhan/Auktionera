import React from "react";
import TextInput from "./common/TextInput";

function AuctionForm(props) {
    return (
        <form onSubmit={props.onSubmit}>
            <TextInput
                id="description"
                label="Description"
                onChange={props.onChange}
                name="description"
            />
            <TextInput
                id="tags"
                label="Tags"
                onChange={props.onChange}
                name="tags"
            />
            <TextInput
                id="category"
                label="Category"
                onChange={props.onChange}
                name="category"
            />
            <div className="form-group">
                <label htmlFor="startPrice">Starting price</label>
                <input
                    type="number"
                    className="form-control"
                    name="startPrice"
                    value={props.auction.startPrice}
                    onChange={props.onChange}
                />
            </div>
            <div className="form-group">
                <label htmlFor="buyoutPrice">Buyout price</label>
                <input
                    type="number"
                    className="form-control"
                    name="buyoutPrice"
                    value={props.auction.buyoutPrice}
                    onChange={props.onChange}
                />
            </div>
            <div className="form-group">
                <label htmlFor="minBidStep">Minimum bidding</label>
                <input
                    type="number"
                    className="form-control"
                    name="minBidStep"
                    value={props.auction.minBidStep}
                    onChange={props.onChange}
                />
            </div>

            <div className="form-group">
                <label htmlFor="deliveryType">Type of delivery</label>
                <div className="field">
                    <select
                        id="deliveryType"
                        name="deliveryType"
                        onChange={props.onChange}
                        value={props.auction.deliveryType}
                        className="form-controll"
                    >
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

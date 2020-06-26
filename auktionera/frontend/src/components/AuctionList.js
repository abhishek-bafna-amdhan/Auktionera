import React from 'react';

const AuctionList = (props) => {

    return (
      <table className="table">
        <thead>
          <tr>
            <th>Title</th>
            <th>Category</th>
            <th>Starting price</th>
            <th>Ends at</th>
            <th>Current bid</th>
            <th>Minimal bid</th>
          </tr>
        </thead>
        <tbody>
          {props.auctions.map(auction => {
            return (
              <tr key={auction.id}>
                <td>{auction.description}</td>
                <td>{auction.category}</td>
                <td>{auction.startPrice}</td>
                <td>{auction.endsAt}</td>
                <td>{auction.currentBid}</td>
                <td>{auction.minBidStep}</td>
              </tr>
            )
          })}
        </tbody>
      </table>
    )
}
export default AuctionList;
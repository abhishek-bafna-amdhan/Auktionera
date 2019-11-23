# API IDEAS

| METHOD | PATH                      | QUERY/PARAM/BODY                            | DESC                         | RESPONSE  |
| ------ | ------------------------- | ------------------------------------------- | ---------------------------- | --------- |
| GET    | /api/signout              |                                             |                              |           |
| GET    | /api/account              |                                             | get logged in account        | Account   |
| PATCH  | /api/account              | BODY: Obj with props<br>userName<br>address | edit logged in account       | Account   |
| GET    | /api/account/auctions     | QUERIES: <br> filter[] <br> sort[]          | history of auctions          | Auction[] |
| GET    | /api/users                | QUERY: <br> filter[] <br> sort[]            | obfuscated result            | User[]    |
| GET    | /api/users/{id}           |                                             | obfuscated result            | User      |
| GET    | /api/users/{id}/auctions  | QUERY: <br> filter[] <br> sort[]            | obfuscated result            | Auction[] |
| GET    | /api/auctions             | QUERIES: <br> filter[] <br> sort[]          |                              | Auction[] |
| GET    | /api/auctions/{id}        |                                             |                              | Auction   |
| GET    | /api/auctions/{id}/images |                                             |                              | Image []  |
| DELETE | /api/auctions/{id}        |                                             | Seller can remove if no bids | Auction   |
| POST   | /api/auctions             | BODY: Auction                               |                              | Auction   |
| POST   | /api/auctions/{id}/bid    | BODY: Bid                                   |                              | Auction   |
| POST   | /api/auctions/{id}/review | BODY: Review                                | Add review.                  | Auction   |
| PUT    | /api/auctions/{id}/review | BODY: Review                                | Edit review.                 | Auction   |

```json

Account:
    {
        "user" : User,
        "email" : "string",
        "isAnonymousBuyer" : false,
        "address" : {
                        "streetName" : "string",
                        "postNr" : 12345,
                        "city" : "string"
                    }
    }

AccountRequest :
    {
        "userName" : "string",
        "streetName" : "string",
        "postNr" : 12345,
        "city" : "string",
        "isAnonymousBuyer" : false
    }

User:
    {
        "id" : 0,
        "userName" : "string",
        "createdAt" : Date,
    }

UserStats:
    {        
        "totalSales" : 0,
        "sellerRating" : 0,
        "buyerRating" : 0
    }

Review:
        {
            "auctionId" : 0,
            "seller" : User,
            "buyer" : User,
            "createdAt" : Date,
            "lastEditAt" : Date,
            "rating" : 0,
            "text" : "string"
        }

ReviewRequest:
    {
        "rating" : 0,
        "text" : "string"
    }

Auction:
        {
            "id" : 0,
            "tags" : ["string"],
            "description" : "string",
            "seller" : User,
            "buyer" : User,
            "sellerReview" : Review,
            "buyerReview" : Review,
            "state" : enum:AuctionState,
            "endsAt" : Date,
            "createdAt" : Date,
            "currentBidAt" : Date,
            "endedAt" : Date,
            "startPrice" : 0,
            "buyoutPrice" : 0,
            "minBidStep" : 0,
            "currentBid" : 0,
            "deliveryType" : enum:DeliveryType
        }

AuctionRequest:
    {
        "tags" : ["string"],
        "description" : "string",
        "endsAt" : Date,
        "startPrice" : 0,
        "buyoutPrice" : 0,
        "minBidStep" : 0,
        "deliveryType" : enum:DeliveryType,
        "images" : Image[]
    }

Image:  {
            "data" : byte [], eller base64 string,
            "description" : string
        }

enums:
    DeliveryType: [PickUpAtSellerHome, PickUpAtAddress, SellerSendsToAddress],
    AuctionState: [InProgress,EndedNotBought,EndedBought]

```

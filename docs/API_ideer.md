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
        "address" : {
                        "streetName" : "string",
                        "postNr" : 12345,
                        "city" : "string"
                    }
    }

User:
    {
        "id" : 0,
        "userName" : "string",
        "createdAt" : Date,
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

Auction:
        {
            "id" : 0,
            "tags" : ["string"],
            "seller" : User,
            "buyer" : User,
            
        }

```

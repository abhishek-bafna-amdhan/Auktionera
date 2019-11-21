# API IDEAS

| METHOD | PATH                      | QUERY/PARAM/BODY                            | DESC                                    |
| ------ | ------------------------- | ------------------------------------------- | --------------------------------------- |
| GET    | /api/signout              |                                             |
| GET    | /api/account              |                                             | get logged in account                   |
| PATCH  | /api/account              | BODY: Obj with props<br>userName<br>address | edit logged in account                  |
| GET    | /api/account/history      | QUERIES: <br> filter[] <br> sort[]          | Can only view it's own detailed history |
| GET    | /api/users/               | QUERY: <br> filter[] <br> sort[]            | obfuscated result                       |
| GET    | /api/users/{id}/history   | QUERY: <br> filter[] <br> sort[]            | obfuscated result                       |
| GET    | /api/auctions             | QUERIES: <br> filter[] <br> sort[]          |
| GET    | /api/auctions/{id}        |                                             |
| DELETE | /api/auctions/{id}        |                                             | Can remove if no bids                   |
| POST   | /api/auctions             | BODY: Auction                               |
| POST   | /api/auctions/{id}/bid    | BODY: Bid                                   |
| POST   | /api/auctions/{id}/review | BODY: Review                                | Add review.                             |

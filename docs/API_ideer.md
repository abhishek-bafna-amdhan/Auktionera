# API IDEAS

| METHOD | PATH                   | QUERY/PARAM/BODY                                      | DESC                                                                       |
| ------ | ---------------------- | ----------------------------------------------------- | -------------------------------------------------------------------------- |
| POST   | /api/users/signin       |                                                       | Create acc if not exists. Email unique. Link to social acc MS, Google etc. |
| GET    | /api/users/signout      |
| PATCH  | /api/users/         | BODY: Obj with props                                  |
| DELETE | /api/users/         |                                                       | Only logged in user can remove itself.                                     |
| GET    | /api/users/history | QUERIES: <br> amount <br> laterThan (Date)|Can only view it's own history
|GET|/api/users/stats| QUERY:<br>sort[]<br>amount
| GET    | /api/auctions          | QUERIES: <br> city <br>seller<br> tag[] <br> amount <br> sort[] |
| POST   | /api/auctions          | BODY: Auction                                         |
| POST   | /api/auctions/{id}/bid | BODY: Bid                                             |


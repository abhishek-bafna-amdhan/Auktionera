import React, { Component } from "react";
import axios from "axios";

class login extends Component {
    constructor() {
        super();

        this.state = {
            username: '',
            password: '',
            submitted: false
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);

    }

    handleSubmit(e) {
        e.preventDefault();
        const endpoint = "http://localhost:8080/api/authenticate";

        this.setState({ submitted: true });
        const { username, password } = this.state;

        const body = `{ "username": "` + username + `", "password": "`+ password + `" }`;
        console.log(body);
        
        axios.post(endpoint, body, {
            headers: {
                "Authorization": "Bearer ",
                'Content-Type': 'application/json'
            }
        }).then(res => {
            console.log(res.data.jwttoken);
            localStorage.setItem("Authorization", res.data.jwttoken);
            return this.handleDashboard();
        });
    }

    handleChange(e) {
        const { name, value } = e.target;
        this.setState({ [name]: value });
    }

    handleDashboard() {
        axios.get("http://localhost:8080/api/account").then(res => {
            console.log(res);
          if (res.status === 200) {
            this.props.history.push("/dashboard");
          } else {
            alert("Authentication failure");
          }
        });
      }

    render() {
        const { username, password, submitted } = this.state;
        return (
            <div className="col-md-6 col-md-offset-3">
                <h2>Login</h2>
                <form name="form" onSubmit={this.handleSubmit}>
                    <div className={'form-group' + (submitted && !username ? ' has-error' : '')}>
                        <label htmlFor="username">Username</label>
                        <input type="text" className="form-control" name="username" value={username} onChange={this.handleChange} />
                        {submitted && !username &&
                            <div className="help-block">Username is required</div>
                        }
                    </div>
                    <div className={'form-group' + (submitted && !password ? ' has-error' : '')}>
                        <label htmlFor="password">Password</label>
                        <input type="password" className="form-control" name="password" value={password} onChange={this.handleChange} />
                        {submitted && !password &&
                            <div className="help-block">Password is required</div>
                        }
                    </div>
                    <div className="form-group">
                        <button className="btn btn-primary">Login</button>
                    </div>
                </form>
            </div>
        );
    }
}
export default login;
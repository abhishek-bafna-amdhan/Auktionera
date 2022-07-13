var axios = require("axios");

//export const jwtToken = localStorage.getItem("Authorization");

axios.interceptors.request.use(
  function(config) {
	var jwtToken = localStorage.getItem("Authorization")
    if (jwtToken) {
      config.headers["authorization"] = "Bearer " + jwtToken;
    }
    return config;
  },
  function(err) {
    return Promise.reject(err);
  }
);

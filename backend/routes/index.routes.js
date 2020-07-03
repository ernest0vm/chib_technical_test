//express
const express = require("express");
const app = express();

app.use("/server/users", require("./users.routes"));
app.use("/server/login", require("./login.routes"));

module.exports = app
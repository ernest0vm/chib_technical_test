//express
const express = require("express");
const app = express();

//imports
const morgan = require("morgan");
var cors = require('cors')

//config
const { mongoose } = require("../config/config");


//middlewares
app.use(express.json());
app.use(morgan("dev"));
app.use(cors())

//routes
app.use(require("../routes/index.routes"));

//server
app.listen(3001, function() {
    console.log("server running on port 3001");
});

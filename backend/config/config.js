const mongoose = require('mongoose')

mongoose.connect('mongodb://127.0.0.1:27017/login', { useNewUrlParser: true, useCreateIndex: true, useUnifiedTopology: true }, (err, res) => {
    if (err) throw err;
    console.log('database online');
});

module.exports=mongoose
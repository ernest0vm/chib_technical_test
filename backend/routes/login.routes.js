const express = require("express");
const app = express();
const Usuarios = require("../models/user");
const bcrypt = require("bcryptjs");
const jwt = require('jsonwebtoken')

app.post('/', (req,res)=>{
    let body = req.body

    Usuarios.findOne({correo:body.correo},(err,usuario)=>{

        if(!usuario){
            return res.status(400).send({
                ok:false,
                err:{
                    message:'Usuario no válido'
                }
            })
        }

        if(!bcrypt.compareSync(body.clave,usuario.clave)){
            return res.status(400).send({
                ok:false,
                err:{
                    message:'Clave no válida'
                }
            })
        }

        let token = jwt.sign({
            usuariobd:usuario
        },'secret',{expiresIn:'24h'})

        res.json({
            ok:true,
            usuariobd:usuario,
            token
        })



    })
})


module.exports = app
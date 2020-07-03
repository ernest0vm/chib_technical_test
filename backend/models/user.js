const mongoose = require('mongoose')

let Schema = mongoose.Schema


let usuarioSchema = new Schema({
    nombre:{
        type:String,
        required: [true,'El nombre es necesario']
    },
    correo:{
        type:String,
        required: [true,'El correo es necesario']
    },
    clave:{
        type:String,
        required:[true,'La clave es necesaria']
    },
    telefono:{
        type:String
    },
    fechaNacimiento:{
        type:String
    },
    profesion:{
        type:String
    },
    descripcionProfesion:{
        type:String
    },
    precioPorHora:{
        type:String
    },
    avatar:{
        type:String
    }
})

usuarioSchema.methods.toJSON = function(){
    let user = this;
    let userObject = user.toObject();
    delete userObject.clave;

    return userObject
}

module.exports = mongoose.model('Users',usuarioSchema)
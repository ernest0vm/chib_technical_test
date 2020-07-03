const express = require("express");
const app = express();
const Usuarios = require("../models/user");
const bcrypt = require("bcryptjs");
const { verificarToken } = require("../middlewares/autenticacion");

app.get("/", async (req, res) => {
    await Usuarios.find((err, usuarios) => {
        res.json({
            usuarios
        });
    });
});

app.put("/", verificarToken, async (req, res) => {
    let body = req.body;

    await Usuarios.findOne({ correo: body.correo }, async (err, usuario) => {
        if (!usuario) {
            return res.status(400).send({
                ok: false,
                err: {
                    message: 'Usuario no válido'
                }
            })
        }

        let actualizaUsuario = new Usuarios({
            nombre: body.nombre,
            correo: body.correo,
            clave: bcrypt.hashSync(body.clave, 10),
            telefono: body.telefono,
            fechaNacimiento: body.fechaNacimiento,
            profesion: body.profesion,
            descripcionProfesion: body.descripcionProfesion,
            precioPorHora: body.precioPorHora,
            avatar : body.avatar != "" ? body.avatar : "https://icon-icons.com/icons2/1879/PNG/128/iconfinder-3-avatar-2754579_120516.png"
        });

        await actualizaUsuario.save((err, usuario) => {
            if (err) {
                return res.status(500).json({
                    err
                });
            }

            res.json({
                usuario
            });
        });
    });
});

app.post("/", async (req, res) => {
    let body = req.body;

    let nuevousuario = new Usuarios({
        nombre: body.nombre,
        correo: body.correo,
        clave: bcrypt.hashSync(body.clave, 10),
        telefono: body.telefono,
        fechaNacimiento: body.fechaNacimiento,
        profesion: body.profesion,
        descripcionProfesion: body.descripcionProfesion,
        precioPorHora: body.precioPorHora,
        avatar: body.avatar != "" ? body.avatar : "https://icon-icons.com/icons2/1879/PNG/128/iconfinder-3-avatar-2754579_120516.png"
    });

    await nuevousuario.save((err, usuario) => {
        if (err) {
            return res.status(500).json({
                err
            });
        }

        res.json({
            usuario
        });
    });
});

module.exports = app;

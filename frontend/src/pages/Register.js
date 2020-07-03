import React, { useEffect, useContext, useState } from "react";
import { Link } from "react-router-dom";
import { Center, Form, H1, WrappLogin, Input, Button, SecondaryButton } from "./styles";
import AuthGlobal from "../context/store/AuthGlobal";
import { registerUser } from "../context/actions/register.action";
import Error from "../components/Error";

export default function Register(props) {
    const context = useContext(AuthGlobal);
    const [correo, setcorreo] = useState("");
    const [clave, setclave] = useState("");
    const [nombre, setnombre] = useState("");
    const [telefono, settelefono] = useState("");
    const [fechaNacimiento, setfechanacimiento] = useState("");
    const [error, seterror] = useState("");
    const [showChild, setShowChild] = useState(false);

    useEffect(() => {
        if (context.stateUser.isAuthenticated === true) {
            props.history.push("/");
        }
        setShowChild(true);
    }, [context.stateUser.isAuthenticated, props.history]);

    const handleSubmit = e => {
        const user = {
            correo,
            clave,
            nombre,
            telefono,
            fechaNacimiento
        };
        if (correo === "" || clave === "" || nombre === "") {
            seterror("Ingrese datos correctamente");
        } else {
            registerUser(user, context.dispatch, seterror);
        }

        e.preventDefault();
    };

    if (!showChild) {
        return null;
    } else {
        return (
            <Center>
                <Form onSubmit={handleSubmit}>
                    <H1>Registro de usuario</H1>
                    <WrappLogin>
                        <Input
                            placeholder="Ingrese Correo"
                            onChange={e => setcorreo(e.target.value)}
                            id="correo"
                            name="correo"
                            value={correo}
                            autoComplete="off"
                        />
                        <Input
                            type="password"
                            placeholder="Ingrese Clave"
                            onChange={e => setclave(e.target.value)}
                            id="clave"
                            name="clave"
                            value={clave}
                        />
                        <Input
                            placeholder="Ingrese Nombre Completo"
                            onChange={e => setnombre(e.target.value)}
                            id="nombre"
                            name="nombre"
                            value={nombre}
                            autoComplete="off"
                        />
                        <Input
                            placeholder="Ingrese telefono"
                            onChange={e => settelefono(e.target.value)}
                            id="telefono"
                            name="telefono"
                            value={telefono}
                            autoComplete="off"
                        />
                        <Input
                            placeholder="Ingrese Fecha de Nacimiento"
                            onChange={e => setfechanacimiento(e.target.value)}
                            id="fechaNacimiento"
                            name="fechaNacimiento"
                            value={fechaNacimiento}
                            autoComplete="off"
                        />
                        <br />

                        <Button type="submit">Crear cuenta</Button>
                        <Link to="/login"><SecondaryButton type="button" style={{marginTop:10}}>Regresar</SecondaryButton></Link>
                    
                        {error ? <Error mensaje={error} /> : null}
                    </WrappLogin>
                </Form>
            </Center>
        );
    }
}

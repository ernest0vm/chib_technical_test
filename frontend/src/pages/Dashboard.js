import React, { useContext, useState, useEffect } from "react";
import { Center, Form, H1, WrappLogin, Input, Button } from "./styles";
import Header from "../components/Header";
import AuthGlobal from "../context/store/AuthGlobal";
import Error from "../components/Error";

export default function Dashboard(props) {
    const context = useContext(AuthGlobal);
    const [profesion, setProfesion] = useState("");
    const [descripcion, setDescripcion] = useState("");
    const [costoPorHora, setCostoPorHora] = useState("");
    const [error, seterror] = useState("");
    const [showChild, setShowChild] = useState(false);
    const [usuarios, setUsuarios] = useState([])

    useEffect(() => {
        if (
            context.stateUser.isAuthenticated === false ||
            context.stateUser.isAuthenticated === null
        ) {
            props.history.push("/login");
        }
        setShowChild(true);

        const jwt = localStorage.getItem("jwt");
        if (jwt) {
            fetch("http://localhost:3001/server/usuarios", {
                method: "PUT",
                headers: {
                    Accept: "application/json",
                    "Content-Type": "application/json",
                    Authorization: jwt
                }
            })
                .then(res => res.json())
                .then(data => {
                    setUsuarios(data.usuarios)
                })
                .catch(err => {
                    console.log(err);
                });
        }
    }, [context.stateUser.isAuthenticated, props.history]);

    const handleSubmit = e => {
        // const user = {
        //     profesion,
        //     descripcion,
        //     costoPorHora,
        // };

        //updateUser(user, context.dispatch, seterror);
        e.preventDefault();
    };

    if (!showChild) {
        return null;
    } else {
        return (
            <div>
                <Header />
                <Center>
                    <Form onSubmit={handleSubmit}>
                        <H1>Actualiza tu perfil</H1>
                        <WrappLogin>
                            <Input
                                placeholder="Ingrese su profesion"
                                onChange={e => setProfesion(e.target.value)}
                                id="profesion"
                                name="profesion"
                                value={profesion}
                                autoComplete="off"
                            />
                            <Input
                                placeholder="Ingrese la descripcion de tu profesion"
                                onChange={e => setDescripcion(e.target.value)}
                                id="descripcion"
                                name="descripcion"
                                value={descripcion}
                                autoComplete="off"
                            />
                            <Input
                                placeholder="¿cual es tu costo por hora de tu trabajo?"
                                onChange={e => setCostoPorHora(e.target.value)}
                                id="costoPorHora"
                                name="costoPorHora"
                                value={costoPorHora}
                                autoComplete="off"
                            />
                            <br />

                            <Button type="submit">Actualizar Perfil</Button>
                            {error ? <Error mensaje={error} /> : null}
                        </WrappLogin>
                    </Form>
                </Center>
            </div>
        );
    }
}

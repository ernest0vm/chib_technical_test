import React, { useContext } from "react";
import {
    Navbar,
    DivNavbar,
    Wrapper,
    NavLinks,
    Alinks,
    Button,
    EndWrapper,
    EndNav
} from "./Header-styles";
import AuthGlobal from "../context/store/AuthGlobal";
import { logoutUser } from "../context/actions/autenticacion.action";

export default function Header() {
    const context = useContext(AuthGlobal);
    const cerrarSesion = () => {
        logoutUser(context.dispatch);
    };

    return (
        <Navbar>
            <DivNavbar>
                <Wrapper>
                    <NavLinks>
                        {context.stateUser.isAuthenticated === true ? (
                            <>
                                <Alinks>
                                    Hola, {context.stateUser.user.usuariobd.nombre}
                                </Alinks>
                            </>
                        ) : null}
                    </NavLinks>
                </Wrapper>
                <EndWrapper>
                    <EndNav>
                        <Button onClick={cerrarSesion}>Cerrar Sesion</Button>
                    </EndNav>
                </EndWrapper>
            </DivNavbar>
        </Navbar>
    );
}

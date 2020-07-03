const jwt = require("jsonwebtoken");

let verificarToken = (req, res, next) => {
  let token = req.get("Authorization");//headers

  jwt.verify(token, "secret", (err, decoded) => {

    if (err) {
      return res.status(401).json({
        ok: false,
        err
      });
    }

    req.usuariobd = decoded.usuariobd;

    next();
  });

};

module.exports = {
  verificarToken
};

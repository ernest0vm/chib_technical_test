
export const registerUser = (user,dispatch,seterror) =>{ //register
  fetch("http://localhost:3001/server/users", {
    method: "POST",
    body: JSON.stringify(user),
    headers: {
      Accept: "application/json",
      "Content-Type": "application/json"
    }
  })
    .then(res => res.json())
    .then(data => {
      if (data.ok === true) {
        console.log(data)
      } else {
        seterror(data.err.message)
      }
    })
    .catch(err => {
      seterror(err.message);
    });

};
const baseUrl = "/auth";

const getValue = id => document.getElementById(id)?.value;

const cadastro = () => ({
    name: getValue("name"),
    cpf: getValue("cpf"),
    rg: getValue("rg"),
    birthDate: getValue("birthDate"),
    gender: getValue("gender"),
    role: getValue("role"),
    zipCode: getValue("zipCode"),
    street: getValue("street"),
    number: getValue("number"),
    complement: getValue("complement"),
    neighborhood: getValue("neighborhood"),
    city: getValue("city"),
    state: getValue("state"),
    phone: getValue("phone"),
    mobile: getValue("mobile"),
    email: getValue("email"),
    login: getValue("login"),
    password: getValue("password")
});

const handleResponse = (res, successMsg, errorMsg) => {
    if (res.ok) alert(successMsg);
    else alert(errorMsg);
};

document.querySelector("#cadastrar-usuario")?.addEventListener("click", () => {
console.log(cadastro())

    fetch(`${baseUrl}/register-user`, {
      method: "POST",
        headers: { "Content-Type": "application/json" },
       body: JSON.stringify(cadastro())
    }) .then(res => handleResponse(res, "Cadastro realizado com sucesso!", "Erro ao cadastrar"))
          .catch(err => alert("Erro na requisição: " + err));
      });
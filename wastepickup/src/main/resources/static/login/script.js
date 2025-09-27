const baseUrl = "/auth";

const getValue = id => document.getElementById(id)?.value;

const loginUsers = () => ({
    login: getValue("login"),
    password: getValue("password"),
});

const handleResponse = (res, successMsg, errorMsg) => {
    if (res.ok) alert(successMsg);
    else alert(errorMsg);
};

document.querySelector(".login")?.addEventListener("click", () => {
    fetch(`${baseUrl}/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(loginUsers())
    }).then(res => handleResponse(res, "Login efetuado com sucesso!", "Login não existe!"));
});


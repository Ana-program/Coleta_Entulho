
const getValue = id => document.getElementById(id)?.value;

const getRequestData = () => ({
    requesterName: getValue("requesterName"),
    debrisType: getValue("debrisType"),
    estimatedQuantity: getValue("estimatedQuantity"),
    requestDate: getValue("requestDate"),
    address: getValue("address"),
    userId: getValue("userId")
});

const handleResponse = (res, successMsg, errorMsg) => {
    if (res.ok) {
        alert(successMsg);
    } else {
        alert(errorMsg);
    }
};

document.querySelector("#solicitacao")?.addEventListener("click", () => {
    const data = getRequestData();

    const fieldNames = {
        requesterName: "Nome do solicitante",
        debrisType: "Tipo de entulho",
        estimatedQuantity: "Quantidade de entulho",
        requestDate: "Data da solicitação",
        address: "Endereço",
        userId: "ID do usuário"
    };

    for (const key in data) {
        if (!data[key]) {
            alert(`O campo "${fieldNames[key]}" é obrigatório!`);
            return;
        }
    }

    const token = localStorage.getItem("token");
    if (!token) {
        alert("Usuário não autenticado. Tente novamente.");
        window.location.href = "/solicitacao/solicitacao.html";
        return;
    }

    const userId = getValue("userId");

    fetch(`http://localhost:8080/api/requests/${userId}`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        },
        body: JSON.stringify(getRequestData())
    }).then(res =>
        handleResponse(res, "Solicitação efetuada com sucesso!", "Erro na solicitação")
    );
});



const baseUrl = "/api/requests";

const getValue = id => document.getElementById(id)?.value;

const getRequestData = () => ({
    requesterName: getValue("requesterName"),
    debrisType: getValue("debrisType"),
    estimatedQuantity: getValue("estimatedQuantity"),
    requestDate: getValue("requestDate"),
    address: getValue("address"),
    status: getValue("status")
});

const handleResponse = (res, successMsg, errorMsg) => {
    if (res.ok) alert(successMsg);
    else alert(errorMsg);
};

document.querySelector("#solicitacao")?.addEventListener("click", () => {
console.log(getRequestData())

 const token = localStorage.getItem("token");

  if (!token) {
         alert("Usuário não autenticado. Tente novamente.");
         window.location.href = "/solicitacao/solicitacao.html";
         return;
     }

    fetch(`${baseUrl}`, {
           method: "POST",
           headers: {
               "Content-Type": "application/json",
               "Authorization": `Bearer ${token}`
           },
           body: JSON.stringify(getRequestData())
       }).then(res => handleResponse(res, "Solicitação efetuada com sucesso!", "Erro na solicitação"));
   });




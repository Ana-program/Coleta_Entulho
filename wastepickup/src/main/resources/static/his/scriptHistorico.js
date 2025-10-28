document.getElementById("btnBuscar").addEventListener("click", async () => {
  const userId = document.getElementById("userId").value.trim();
  const tabela = document.getElementById("tabela");
  const corpo = document.getElementById("tabela-corpo");
  const alerta = document.getElementById("alerta");

  corpo.innerHTML = "";
  tabela.classList.add("d-none");
  alerta.classList.add("d-none");

  if (!userId) {
    mostrarAlerta("Por favor, insira um ID de usuário válido.", "warning");
    return;
  }

  const token = localStorage.getItem("token");

  if (!token) {
    mostrarAlerta("Token não encontrado. Faça login novamente.", "danger");
    return;
  }

  try {
    const response = await fetch(`http://localhost:8080/api/requests/${userId}`, {
      method: "GET",
      headers: {
        "Authorization": `Bearer ${token}`,
        "Content-Type": "application/json"
      }
    });

    if (!response.ok) {
      if (response.status === 403) {
        throw new Error("Acesso negado. Seu token pode ter expirado ou o usuário não possui permissão.");
      }
      throw new Error("Usuário não encontrado ou erro na API.");
    }

    const dados = await response.json();

    if (dados.length === 0) {
      mostrarAlerta("Nenhuma solicitação encontrada para este usuário.", "info");
      return;
    }

    dados.forEach(req => {
      const linha = `
        <tr>
          <td>${req.id}</td>
          <td>${req.requesterName}</td>
          <td>${req.address}</td>
          <td>${req.debrisType}</td>
          <td>${req.estimatedQuantity}</td>
          <td>${req.requestDate}</td>
          <td><span class="badge ${getBadgeClass(req.status)}">${req.status}</span></td>
        </tr>
      `;
      corpo.innerHTML += linha;
    });

    tabela.classList.remove("d-none");

  } catch (erro) {
    mostrarAlerta(erro.message, "danger");
    console.error(erro);
  }
});

function mostrarAlerta(mensagem, tipo) {
  const alerta = document.getElementById("alerta");
  alerta.textContent = mensagem;
  alerta.className = `alert alert-${tipo}`;
  alerta.classList.remove("d-none");
}

function getBadgeClass(status) {
  switch (status) {
    case "PENDING": return "bg-warning text-dark";
    case "IN_PROGRESS": return "bg-primary";
    case "COMPLETED": return "bg-success";
    default: return "bg-secondary";
  }

}

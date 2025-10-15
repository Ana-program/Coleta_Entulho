const API_URL = "/api/requests";

async function loadRequests() {
  try {
    const token = localStorage.getItem("token");

    if (!token) {
      alert("Usuário não autenticado. Faça login novamente.");
      window.location.href = "/historico/historico.html";
      return;
    }

    const response = await fetch(API_URL, {
      headers: {
        "Authorization": `Bearer ${token}`
      }
    });

    if (!response.ok) {
      throw new Error("Erro ao buscar solicitações.");
    }

    const requests = await response.json();
    const list = document.getElementById("requests-list");
    list.innerHTML = "";

    requests.forEach(req => {
      let statusClass = "";
      if (req.status === "COMPLETED") statusClass = "status-completed";
      else if (req.status === "PENDING") statusClass = "status-pending";
      else if (req.status === "IN_PROGRESS") statusClass = "status-in-progress";

      const item = document.createElement("li");
      item.classList.add("list-group-item", "d-flex", "justify-content-between", "align-items-center");

      item.innerHTML = `
        <span>Solicitação #${req.id}</span>
        <span class="status-badge ${statusClass}">
          ${formatStatus(req.status)}
        </span>
      `;

      list.appendChild(item);
    });

  } catch (error) {
    console.error(error);
  }
}

function formatStatus(status) {
  switch (status) {
    case "PENDING": return "Pendente";
    case "IN_PROGRESS": return "Em andamento";
    case "COMPLETED": return "Concluída";
    default: return status;
  }
}

loadRequests();


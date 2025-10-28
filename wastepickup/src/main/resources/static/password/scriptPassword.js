const baseUrl = "/password";

const getValue = id => document.getElementById(id)?.value.trim();

const passwordData = () => ({
  cpf: getValue("cpf"),
  newPassword: getValue("newPassword")
});

document.querySelector("#password")?.addEventListener("click", async () => {
  const data = passwordData();

  if (!data.cpf || !data.newPassword) {
    alert("Por favor, preencha todos os campos!");
    return;
  }

  try {
    const response = await fetch(`${baseUrl}/change-password`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data)
    });

    const messageDiv = document.getElementById("message");

    if (response.ok) {
      messageDiv.innerHTML = `<div class="alert alert-success mt-3">Senha alterada com sucesso!</div>`;
      document.getElementById("changePasswordForm").reset();
    } else {
      const errorText = await response.text();
      messageDiv.innerHTML = `<div class="alert alert-danger mt-3">Erro: ${errorText || "Não foi possível alterar a senha."}</div>`;
    }

  } catch (error) {
    console.error("Erro ao alterar senha:", error);
    document.getElementById("message").innerHTML =
      `<div class="alert alert-danger mt-3">Erro de conexão com o servidor.</div>`;
  }
});


const forms = document.getElementById('forms_cadastro');

function cadastrar() {
    const name_input = document.getElementById('name').value;
    const email_input = document.getElementById('email').value;
    const password_input = document.getElementById('password').value;
    console.log("Email enviado: ", email_input);

    fetch("http://localhost:8080/party-owners/register", {
        method: "POST",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            name: name_input,
            email: email_input,
            password: password_input
        })
    })
    .then(function(res) {
        if (res.ok) {
            alert("Cadastro realizado com sucesso!");
        } else {
            alert("Erro no cadastro. Tente novamente.");
        }
    })
    .catch(function(err) {
        console.error("Erro na requisição: ", err);
        alert("Erro ao tentar se conectar. Verifique sua conexão e tente novamente.");
    });
}

function limpar() {
    document.getElementById('forms_cadastro').reset();
}


forms.addEventListener('submit', function(event) {
    event.preventDefault();
    cadastrar();
    limpar();
});

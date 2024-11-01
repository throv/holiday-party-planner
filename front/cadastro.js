
const forms = document.getElementById('forms_cadastro')

function cadastrar() {
    const name_input = document.getElementById('name').value;
    const email_input = document.getElementById('email').value;
    const password_input = document.getElementById('password').value;
    fetch("http://localhost:8080/usuario", {
        method: "POST",
        headers: {
            "Aceept": "application/json",
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            name: name_input,
            email: email_input,
            password: password_input
        })
    })
    .then  (function (res) {console.log(res)})
    .catch (function (res) {console.log(res)})
}

forms.addEventListener('submit', function(event) {
    event.preventDefault();
    cadastrar();
})
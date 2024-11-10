// RANDOMIZA AS IMAGENS

const imageUrls = [
    '../../src/assets/img/login-cadastro/001-natal-pc.png', 
    '../../src/assets/img/login-cadastro/002-aniversario-pc.png', 
    '../../src/assets/img/login-cadastro/003-aniversario-pc.png'
];

function setRandomImage() {
    const randomIndex = Math.floor(Math.random() * imageUrls.length); 
    const selectedImage = imageUrls[randomIndex]; 
    console.log(selectedImage)

    const imageDiv = document.querySelector('.box_login .imagem');
    if (imageDiv) {
        imageDiv.style.backgroundImage = `url('${selectedImage}')`;
        console.log(imageUrls)
    } else {
        console.error("Div .box_login .imagem não encontrada");
    }
}

window.onload = setRandomImage;

const forms = document.getElementById('form_login');

function login() {
    const email_input = document.getElementById('email').value;
    const password_input = document.getElementById('password').value;
    
    fetch("http://localhost:8080/party-owners/login", {
        method: "POST",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            email: email_input,
            password: password_input
        })
    })
    .then(function(res) {
        if (res.ok) {
            alert("Login feito com sucesso");
        } else {
            alert("Login ou senha errado !");
        }
    })
    .catch(function(err) {
        console.error("Erro na requisição: ", err);
    });
}

forms.addEventListener('submit', function(event) {
    event.preventDefault();
    login();
});

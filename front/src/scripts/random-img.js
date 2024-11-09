function setRandomImage() {
    const randomIndex = Math.floor(Math.random() * imageUrls.length); // Seleciona um índice aleatório
    const selectedImage = imageUrls[randomIndex]; // Obtém o URL da imagem aleatória

    const imageDiv = document.querySelector('.box_login .imagem');
    imageDiv.style.backgroundImage = `url('${selectedImage}')`;
}

window.onload = setRandomImage;
let slideIndex = 0;
showSlides();

function showSlides() {
  let slides = document.querySelectorAll('.slide');
  slides.forEach((slide, index) => {
    slide.style.display = (index === slideIndex) ? 'block' : 'none';
  });

  slideIndex++;
  if (slideIndex >= slides.length) { slideIndex = 0; }
  setTimeout(showSlides, 10000); // Muda a imagem a cada 10 segundos
}

function plusSlides(n) {
  slideIndex += n;
  showSlides();
}

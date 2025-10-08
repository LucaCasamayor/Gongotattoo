document.addEventListener("DOMContentLoaded", () => {
    const toggle = document.querySelector(".nav-toggle");
    const nav = document.querySelector(".nav");

    if (!toggle || !nav) return;

    // 🔒 Asegura que el menú esté cerrado al cargar (mobile)
    nav.classList.remove("active");
    toggle.classList.remove("active");

    // Toggle del menú hamburguesa
    toggle.addEventListener("click", () => {
        toggle.classList.toggle("active");
        nav.classList.toggle("active");
    });

    // Cierra el menú al hacer click en un enlace
    document.querySelectorAll(".nav a").forEach(link => {
        link.addEventListener("click", () => {
            toggle.classList.remove("active");
            nav.classList.remove("active");
        });
    });
});

// --- Carrusel (deja igual si ya usás el gallery.js para la galería) ---
const carousel = document.querySelector(".carousel-multi");
const prevBtn = document.querySelector(".carousel-prev");
const nextBtn = document.querySelector(".carousel-next");

if (carousel && prevBtn && nextBtn) {
    const scrollStep = 300;

    nextBtn.addEventListener("click", () => {
        carousel.scrollBy({ left: scrollStep, behavior: "smooth" });
    });

    prevBtn.addEventListener("click", () => {
        carousel.scrollBy({ left: -scrollStep, behavior: "smooth" });
    });
}

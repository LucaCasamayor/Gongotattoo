document.addEventListener("DOMContentLoaded", () => {
    const mediaFiles = [
        "https://res.cloudinary.com/diyo4sghs/image/upload/v1759863721/tattooprueba4_edg0vv.png",
        "https://res.cloudinary.com/diyo4sghs/image/upload/v1759863716/tattooprueba2_f9xtw8.png",
        "https://res.cloudinary.com/diyo4sghs/image/upload/v1759863716/tattooprueba3_efcxi6.png",
        "https://res.cloudinary.com/diyo4sghs/image/upload/v1759863716/tattooprueba1_ojxzrg.png",
        "https://res.cloudinary.com/diyo4sghs/video/upload/v1759867907/AQPuzKulKn91pjVlyrGCoYs0gPcv5giyzXqEL9JH-zyHltVLC_z8y1GDENVU75ROYYnC6OkZrBc78K3Rse6tgJx3BXe3FWCZhjEmpeQ_uz8vn9.mp4"
    ];

    const track = document.getElementById("gallery-grid");
    const prevBtn = document.querySelector(".carousel-prev");
    const nextBtn = document.querySelector(".carousel-next");
    if (!track) return;

    // --- Crear ítems originales ---
    mediaFiles.forEach((url) => {
        const ext = url.split(".").pop().toLowerCase();
        const slide = document.createElement("div");
        slide.className = "carousel-item";

        if (["mp4", "webm", "ogg"].includes(ext)) {
            const video = document.createElement("video");
            video.src = url;
            video.muted = true;
            video.loop = true;
            video.playsInline = true;
            video.preload = "metadata";
            slide.addEventListener("mouseenter", () => video.play());
            slide.addEventListener("mouseleave", () => video.pause());
            slide.appendChild(video);
        } else {
            const img = document.createElement("img");
            img.src = url;
            img.alt = "Tattoo artwork";
            slide.appendChild(img);
        }

        track.appendChild(slide);
    });

    // --- Crear clones (x2 más) para un loop sin cortes ---
    const originalSlides = Array.from(track.children);
    for (let i = 0; i < 2; i++) {
        originalSlides.forEach((slide) => {
            const clone = slide.cloneNode(true);

            // volver a conectar los listeners de video si los hay
            const video = clone.querySelector("video");
            if (video) {
                clone.addEventListener("mouseenter", () => video.play());
                clone.addEventListener("mouseleave", () => video.pause());
            }

            track.appendChild(clone);
        });
    }

    // --- Movimiento infinito suave ---
    let pos = 0;
    let raf;
    const speed = 0.4;

    function animate() {
        pos -= speed;

        // ancho total del bloque original (solo la primera tanda)
        const totalWidth = Array.from(originalSlides).reduce(
            (acc, el) => acc + el.offsetWidth + parseFloat(getComputedStyle(el).marginRight),
            0
        );

        // 🔁 reinicio sin saltos
        if (-pos >= totalWidth) pos = 0;

        track.style.transform = `translateX(${pos}px)`;
        raf = requestAnimationFrame(animate);
    }

    function start() {
        cancelAnimationFrame(raf);
        raf = requestAnimationFrame(animate);
    }

    function stop() {
        cancelAnimationFrame(raf);
    }

    start();

    // --- Flechas ---
    const step = 300;
    const pauseAndResume = () => {
        stop();
        clearTimeout(track.resumeTimeout);
        track.resumeTimeout = setTimeout(start, 4000);
    };

    nextBtn.addEventListener("click", () => {
        pos -= step;
        track.style.transform = `translateX(${pos}px)`;
        pauseAndResume();
    });

    prevBtn.addEventListener("click", () => {
        pos += step;
        track.style.transform = `translateX(${pos}px)`;
        pauseAndResume();
    });

    // --- Hover pausa/reanuda ---
    track.closest(".carousel-container")?.addEventListener("mouseenter", stop);
    track.closest(".carousel-container")?.addEventListener("mouseleave", start);
});

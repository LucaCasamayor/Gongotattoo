document.addEventListener("DOMContentLoaded", () => {
    // 🌐 Archivos desde Cloudinary (imágenes + videos)
    const mediaFiles = [
        "https://res.cloudinary.com/diyo4sghs/image/upload/v1759863721/tattooprueba4_edg0vv.png",
        "https://res.cloudinary.com/diyo4sghs/image/upload/v1759863716/tattooprueba2_f9xtw8.png",
        "https://res.cloudinary.com/diyo4sghs/image/upload/v1759863716/tattooprueba3_efcxi6.png",
        "https://res.cloudinary.com/diyo4sghs/image/upload/v1759863716/tattooprueba1_ojxzrg.png",
        "https://res.cloudinary.com/diyo4sghs/video/upload/v1759867907/AQPuzKulKn91pjVlyrGCoYs0gPcv5giyzXqEL9JH-zyHltVLC_z8y1GDENVU75ROYYnC6OkZrBc78K3Rse6tgJx3BXe3FWCZhjEmpeQ_uz8vn9.mp4"
    ];

    const galleryContainer = document.getElementById("gallery-grid");

    if (!galleryContainer) {
        console.error("❌ No se encontró el contenedor de la galería (#gallery-grid).");
        return;
    }

    mediaFiles.forEach(url => {
        const fileType = url.split(".").pop().toLowerCase();
        let element;

        if (["mp4", "webm", "ogg"].includes(fileType)) {
            element = document.createElement("video");
            element.src = url;
            element.muted = true;
            element.loop = true;
            element.playsInline = true;
            element.preload = "metadata";
            element.classList.add("media-item");

            // 🎬 Solo reproducir en hover
            element.addEventListener("mouseenter", () => element.play());
            element.addEventListener("mouseleave", () => element.pause());
        }
        else if (["jpg", "jpeg", "png", "gif", "webp"].includes(fileType)) {
            element = document.createElement("img");
            element.src = url;
            element.alt = "Tattoo artwork";
            element.classList.add("media-item");
        }
        else {
            console.warn(`⚠️ Formato no soportado: ${url}`);
            return;
        }

        galleryContainer.appendChild(element);
    });
});

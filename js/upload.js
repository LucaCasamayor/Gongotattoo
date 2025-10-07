document.getElementById("uploadForm").addEventListener("submit", function (e) {
    e.preventDefault();
    const files = document.getElementById("fileInput").files;
    const gallery = document.getElementById("gallery-grid");

    if (!files.length) return alert("Please select at least one file.");

    [...files].forEach(file => {
        const reader = new FileReader();

        reader.onload = function (e) {
            const element = document.createElement(file.type.startsWith("image/") ? "img" : "video");
            element.src = e.target.result;
            element.classList.add("gallery-item");

            if (file.type.startsWith("video/")) {
                element.controls = true;
            }

            gallery.appendChild(element);
        };

        reader.readAsDataURL(file);
    });
});

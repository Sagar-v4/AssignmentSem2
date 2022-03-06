let circles = [];
let totalCircles = 10;
for (let i = 0; i < totalCircles; i++) {

    let node = document.createElement("div");
    node.className = "trail";
    node.style.display = "none";
    document.body.appendChild(node);
    circles.push(node);
}

let curr = 0;
window.addEventListener("mousemove", event => {

    let circle = circles[curr];
    circle.style.left = (event.pageX - 20) + "px";
    circle.style.top = (event.pageY - 20) + "px";
    circle.style.display = "block";
    curr = (curr + 1) % totalCircles;
});

var timeout;
document.onmousemove = function (event) {

    clearTimeout(timeout);
    timeout = setTimeout(() => {
        for (let i = totalCircles - 1; i >= 0; i--) {

            let circle = circles[i];
            circle.style.left = (event.pageX - 20) + "px";
            circle.style.top = (event.pageY - 20) + "px";
        }
    }, 20);
}
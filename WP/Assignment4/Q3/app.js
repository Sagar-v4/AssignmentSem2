let object = document.getElementById('object');
let reset = document.getElementById('reset');
let speeder = document.getElementById('speed');
let runner = document.getElementById('runner');
let submit = document.getElementById('submit');
let direction = document.getElementById('direction');

let speed = 50;
let rightD = 10, bottumD = 10;

speeder.addEventListener("change", event => {
    speed = (100 - event.target.value);
});

object.addEventListener("change", event => {
    runner.className = event.target.value;
    console.log(runner.className);
});

reset.addEventListener('click', () => {
    window.location.reload();
});

function moveRight(go) {
    runner.style.left = go + "px";
}

function moveBottom(go) {
    runner.style.top = go + "px";
}

var movement;
submit.addEventListener('click', () => {

    clearInterval(movement);
    var direct = direction.value;

    movement = setInterval(() => {

        if (rightD <= (screen.width - 130) && bottumD <= (screen.height - 270)) {
            if (direct === 'right') {
                moveRight(rightD);
                rightD += 10;
            }
            else if (direct === 'bottom') {
                moveBottom(bottumD);
                bottumD += 10;
            }
        }
        else {
            bottumD = 10;
            rightD = 10;
            moveRight(0);
            moveBottom(0);
            clearInterval(movement);
        }
    }, (speed * 10));
})


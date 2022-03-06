
function myFunction(event) {
    var x = event.key;
    var myImg = document.getElementById('bln');
    const pxs = ['100px', '150px', '200px', '250px', '300px', '350px', '400px', '450px', '500px', '550px', '600px'];

    var curr = myImg.style.height;
    for (var i = 0; i < 11; i++) {
        if (myImg.style.height == pxs[i]) {
            curr = i;
            break;
        }
    }

    if (x == "ArrowUp") {
        if (curr > 8) {
            myImg.src = "blast.jpg";
            myImg.style.height = pxs[10];
            myImg.style.width = pxs[10];
        } else {
            myImg.src = "normal.jpg";
            myImg.style.height = pxs[curr + 1];
            myImg.style.width = pxs[curr + 1];
        }

    } else if (x == "ArrowDown") {
        myImg.src = "normal.jpg";
        myImg.style.height = pxs[curr - 1];
        myImg.style.width = pxs[curr - 1];
    }
}
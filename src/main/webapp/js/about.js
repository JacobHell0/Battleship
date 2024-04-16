function closeNAV()
{
    let bar = document.getElementById("NAVbar");
    bar.classList.toggle("hide");
}

function toggleBox()
{
    try {
        let box = document.getElementById("myBox");

        if (box.style.display === "none") {
            box.style.display = "block";

            document.querySelector('.container').style.minHeight = "500px";
        } else {
            box.style.display = "none";

            document.querySelector('.container').style.minHeight = "200px";
        }
    } catch (e) {
        console.log("error opening box1");
    }
    background()
}

function toggleBox2()
{
    try {
        let box = document.getElementById("myBox2");
        if (box.style.display === "none") {
            box.style.display = "block";
            document.querySelector('.container').style.minHeight = "400px";
        } else {
            box.style.display = "none";
            document.querySelector('.container').style.minHeight = "200px";
        }
    } catch (e) {
        console.log("error opening box2");
    }
    background()

}

function toggleBox3()
{
    try {
        let box = document.getElementById("myBox3");
        if (box.style.display === "none") {
            box.style.display = "block";
            document.querySelector('.container').style.minHeight = "400px";
        } else {
            box.style.display = "none";
            document.querySelector('.container').style.minHeight = "200px";
        }
    } catch (e) {
        console.log("error opening box3")
    }
    background()
}

function onLoad()
{
    console.log("running background");
    toggleBox();
    toggleBox2();
    toggleBox3();
    background();

}

function background()
{
    VANTA.WAVES
    ({
        el: "#art",
        mouseControls: false,
        touchControls: false,
        gyroControls: false,
        minHeight: 200.00,
        minWidth: 200.00,
        scale: 1.00,
        scaleMobile: 1.00,
        color: 0x969696,
        shininess: 18.00,
        waveHeight: 40.00,
        waveSpeed: 0.30,
        zoom: 1
    })
}
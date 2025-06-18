let buttons = document.querySelectorAll('.box');
let res = document.querySelector('.winner');
let restart = document.querySelector(".hide");
let sub = document.querySelector(".sub");
let check = true;
let po = true;
let ini = true;
const per1 = 'o';
const per2 = 'x';
const wiining = [
    [0, 1, 2],
    [0, 4, 8],
    [0, 3, 6],
    [1, 4, 7],
    [2, 5, 8],
    [2, 4, 6],
    [3, 4, 5],
    [6, 7, 8]
];

sub.addEventListener('click',() => {
    po=true;
    sub.classList.add("hide")
    restart.classList.remove("hide")
    buttons.forEach((box) => {
        box.addEventListener('click', () => {
            if (po) {
                if (box.innerText === "") {
                    if (check) {
                        box.innerText = per1;
                        check = false;
                    }
                    else {
                        box.innerText = per2;
                        check = true;
                    }
                }
                win();
            }
        });
    });

})



function win() {
    for (let winner of wiining) {
        let pattern1 = buttons[winner[0]].innerText
        let pattern2 = buttons[winner[1]].innerText
        let pattern3 = buttons[winner[2]].innerText
        if (pattern1 != "") {
            if (pattern1 === pattern2 && pattern2 === pattern3) {
                if (check == false) {
                    res.innerText = `The winner is Person1 win ${per1}`;
                }
                else {
                    res.innerText = `The winner is person2 win ${per2}`;
                }
                po = false;
            }
        }
        
    }

}

restart.addEventListener('click',()=>{
        sub.classList.remove("hide");
    restart.classList.add("hide");
    check=true;
    res.innerText="";
    buttons.forEach((box) => {
        box.innerText="";
    });

    })
let display = document.getElementById("input")

function allclear(){
    display.value ="";
}
function add(e){
    display.value+=e
}
function total(){
    try {
    display.value = eval(display.value);
  } catch {
    display.value = "Error";
  }
}


function blackspace() {
  display.value = display.value.slice(0, -1);
}
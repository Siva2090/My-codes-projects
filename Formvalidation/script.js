const form = document.querySelector('form');
const user = document.getElementById('user');
const email = document.getElementById('email');
const pass = document.getElementById('pass');
const cpass= document.getElementById('cpass');



form.addEventListener('submit',e =>{
     if(!Validate()){
        e.preventDefault();
     }
})

function Validate(){
    let check = true;
    const username = user.value.trim();
    const email1= email.value.trim();
    const password = pass.value.trim();
    const cp = cpass.value.trim();

    if(username ===''){
        check=false;
        error(user,'Username is required');
    }
    else{
        sucess(user);
    }

    if( checkemail(email1)){
        sucess(email);
    }
    else{
        check=false;
        error(email ,'Please enter a valid email');
    }

    if(password ===''){
        check=false;
        error(pass ,'Password is required');
    }
    else if(password.length <6){
        check=false;
        error(pass ,'enter correct length' );
    }
    else{
        sucess(pass);
    }

    if(cp ===''){
        check=false;
        error(cpass ,'Password must be at least 6 character');
    }
    else if(cp === password){
        sucess(cpass);
    }
    else{
        check =false;
        error(cpass ,'Please confirm your password');
    }

    return check;
}

function error(element , msg){
    const parent = element.parentElement;
    const error = parent.querySelector('.error');
    error.innerText = msg;
    parent.classList.add('error');
    parent.classList.remove('sucess');
   
}

function sucess(element){
     const parent = element.parentElement;
     const error = parent.querySelector('.error');
     error.innerText="";
    parent.classList.add('sucess');
    parent.classList.remove('error');

}

function checkemail(email) {
      const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
      return regex.test(email);
    }
const GoJoin = document.querySelector(".LoginJoin button");
const EmailLogin = document.querySelector(".Email-Login");


GoJoin.onclick = () => {
    location.href = "/welcome/agree/join";
}

EmailLogin.onclick = () => {
    location.href = "/welcome/login";
}


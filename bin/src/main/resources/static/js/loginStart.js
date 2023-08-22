const GoJoin = document.querySelector(".LoginJoin button");
const EmailLogin = document.querySelector(".Email-Login");
// Kakao.init('0d7c41d8dd45e4cb0fbb1a54c85fd6a1');
// console.log(Kakao.islnitialized());

// ! 카카오 로그인 기능 추가 해야함

GoJoin.onclick = () => {
    location.href = "joinAgree.html";
}

EmailLogin.onclick = () => {
    location.href = "emailLogin.html"
}

// function KakaoLogin() {
//     Kakao.Auth.login({
//         success: function (response) {
//             Kakao.API.request({
//                 url: '',
//                 success function (response) {
//                     console.log(response)
//                 },
//                 fail: function (error) {
//                     console.log(error)
//                 },
//             })
//         },
//         fail : function (error) {
//             console.log(error)
//         },
//     })
// }
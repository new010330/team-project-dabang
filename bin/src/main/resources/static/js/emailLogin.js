const inputs = document.querySelectorAll("input");
const longinBtn = document.querySelector(".SolidBtn");

let checkUseremailFlag = false;

inputs[0].onblur = () => {
    //아이디 중복확인
    $.ajax({
        async: false,
        type: "get",
        url: "/api/v1/welcome/login/validation/useremail",
        data: {username: inputs[0].value},
        dataType: "json",
        success: (response) => {
            checkUseremailFlag = response.data;
            if(checkUseremailFlag){
                alert("사용 가능한 아이디입니다.")
            }else{
                alert("이미 사용중인 아이디 입니다.")
            }
        },
        error: (error) => {
            if(error.statis == 400){
                alert(JSON.stringify(error.responseJSON.data));
            }else{
                console.log("요청실패")
                console.log(error)
            }
        }
    })
}
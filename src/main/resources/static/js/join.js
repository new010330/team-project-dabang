const inputs = document.querySelectorAll("input");
const longinBtn = document.querySelector(".SolidButton");
const email = document.querySelector(".input-Email");
const password = document.querySelector(".Password-Input");
const LookBtn = document.querySelector(".seeBtn");
const LookBtn2 = document.querySelector(".seeBtn2");
const PError = document.querySelector(".Perror");
const PError2 = document.querySelector(".Perror2");


const inputEmail = document.getElementById("email")
const emailformat = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,}$/;

const inputPassword = document.getElementById("password");
const passwordformat = /^(?=.*[a-zA-Z])(?=.*\d)(?=.*[-~!@#$%^&*_+=])[a-zA-Z\d-~!@#$%^&*_+=]{8,20}$/;

const passwordCheck = document.getElementById("passwordCheck");
const PError3 = document.querySelector(".Perror3");

let checkUseremailFlag = false;
let checkUserpasswordFlag = false;

function check_password(){
	
	if(passwordformat.test(inputPassword)){
		console.log("비밀번호 형식 ok")
	}else{
		console.log("비밀번호 형식 no")
	}
}


function check_email() {
	if(emailformat.test(inputEmail)) {
		console.log("이메일 형식 ok")
	}else{
		console.log("잘못된 형식")
		
	}
}




inputPassword.addEventListener("input", () => {
  if (passwordformat.test(inputPassword.value)) {
    PError2.style.display = "none";
  } else {
    PError2.style.display = "block";
  }
});

inputEmail.addEventListener("input", () => {
  if (emailformat.test(inputEmail.value)) {
    PError.style.display = "none";
  } else {
    PError.style.display = "block";
  }
});

passwordCheck.addEventListener("input", () => {
  if (passwordCheck.value !== inputPassword.value) {
    PError3.style.display = "block";
  } else {
    PError3.style.display = "none";
  }
});


function togglePasswordVisibility() {
    if (password.type === 'password') {
        password.type = 'text';
       /* passwordCheck.type = 'text';*/
    } else {
        password.type = 'password';

    }
}

function togglePasswordVisibility2() {
	
    if (passwordCheck.type === 'password') {
        passwordCheck.type = 'text';
    } else {
        passwordCheck.type = 'password';
    }
}

LookBtn.onclick = togglePasswordVisibility;
LookBtn2.onclick = togglePasswordVisibility2;


inputs[0].onblur = () => {
	/*check_email()*/
    //아이디 중복확인
    console.log(inputs[0].value)
    $.ajax({
        async: false,
        type: "get",
        url: "/api/v1/auth/join/email/validation",
        data: {email: inputs[0].value},
        dataType: "json",
        success: (response) => {
            checkUseremailFlag = response.data;
            if(checkUseremailFlag){
                alert("사용 가능한 이메일입니다.")
            }else{
                alert("이미 사용중인 이메일입니다.")
            }
        },
        error: (error) => {
            if(error.status == 400){
                alert(JSON.stringify(error.responseJSON.data));
            }else{
                console.log("요청실패")
                console.log(error)
            }
        }
    })
  }
  
inputs[1].onblur = () => {
	check_password();
	
	 console.log(inputs[1].value)
	  $.ajax({
        async: false,
        type: "get",
        url: "/api/v1/auth/join/password/validation",
        data: {password: inputs[1].value},
        dataType: "json",
        success: (response) => {
            checkUserpasswordFlag = response.data;
            if(checkUserpasswordFlag){
                console.log("비밀번호 검사 ok.")
            }else{
                console.log("비밀번호 검사 no")
            }
        },
        error: (error) => {
            if(error.status == 400){
                alert(JSON.stringify(error.responseJSON.data));
            }else{
                console.log("요청실패")
                console.log(error)
            }
        }
    })
}

function validatePassword() {
	
}

    
longinBtn.onclick = () => {
	let joinData = {
		email: inputs[0].value,
		password: inputs[1].value,
		checkpassword: inputs[2].value,
		"checkUseremailFlag": checkUseremailFlag
	}
	
	$.ajax({
		async: false,
		type: "post",
		url: "/api/v1/auth/welcome/join",
		contentType: "application/json",
		data: JSON.stringify(joinData),
		dataType: "json",
		success: (response) => {
			if(response.data){
				alert("회원가입완료")
				location.replace("/index")
			}
		},
		error: (error) => {
			if(error.status == 400){
				alert(JSON.stringify(error.responseJSON.data));
			}else{
				console.log(joinData[0].value);
				console.log("요청실패")
            	console.log(error)
			}
		}
	})
}



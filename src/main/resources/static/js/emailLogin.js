const email = document.querySelector(".Email-input");
const emailtext = document.getElementById("Email-input");
const emailNoError = document.querySelector(".emailerror");
const password = document.querySelector(".password-input");
const buttons = document.querySelectorAll(".Btn-wrap button");
const loginBtn = document.querySelector(".SolidBtn");
const LookBtn = document.querySelector(".seeBtn");


const emailLoginBtn = buttons[1];
const changepassword = buttons[0];

const Error = document.querySelector(".error-logo")

function passwordempty() {
  if (email.value.trim() !== '' && password.value.trim() === '') {
    emailNoError.innerHTML = "비밀번호를 입력해주세요.";
    emailNoError.style.display = 'block';
    Error.style.display = 'block';
    setTimeout(() => {
      emailNoError.style.display = 'none';
      Error.style.display = 'none';
    }, 4000);
  } else {
    emailNoError.style.display = 'none';
    Error.style.display = 'none';
  }
}


function emailError() {
  if (email.value.trim() === '' || password.value.trim() === '') {
    emailNoError.innerHTML = "이메일과 비밀번호를 입력해주세요.";
    emailNoError.style.display = 'block';
    Error.style.display = 'block';
    setTimeout(() => {
      emailNoError.style.display = 'none';
      Error.style.display = 'none';
    }, 4000);
  } else {
    emailNoError.style.display = 'none';
    Error.style.display = 'none';
  }
}

function emailError2() {
  const emailFormat = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
  if (email.value.trim() !== '' && !emailFormat.test(email.value.trim())) {
    emailNoError.innerHTML = "이메일 형식을 확인해 주세요.";
    emailNoError.style.display = 'block';
    Error.style.display = 'block';
    setTimeout(() => {
      emailNoError.style.display = 'none';
      Error.style.display = 'none';
    }, 4000);
  } else {
    emailNoError.style.display = 'none';
    Error.style.display = 'none';
  }
}


function noFindUser() {
  emailNoError.innerHTML = "이메일 주소 또는 비밀번호가 틀렸습니다.";
  emailNoError.style.display = 'block';
  Error.style.display = 'block';
  setTimeout(() => {
    emailNoError.style.display = 'none';
    Error.style.display = 'none';
  }, 4000);
}

LookBtn.onclick = () => {
	 password.type = password.type === 'password' ? 'text' : 'password';
}

loginBtn.onclick = () => {
  event.preventDefault();
  
  emailError2();
  emailError();
  /*passwordempty();*/
  
  const loginData = {
    email: email.value,
    password: password.value
  };

  $.ajax({
    type: "POST",
    url: "/api/v1/auth/login",
    contentType: "application/json",
    data: JSON.stringify(loginData),
    dataType: "json",
    success: (response) => {
      if (response.data) {
        location.replace("/index");
      } else {
        noFindUser();
      }
    },
    error: (error) => {
      console.log("요청 실패");
      console.log(error);
    }
  });
};

emailLoginBtn.onclick = () => {
	location.href = "/welcome/agree/join";
}

function getPrincipal(){
	let user = null;
	$.ajax({
		async:false,
		type: "get",
		url: "/api/v1/auth/principal",
		dataType: "json",
		success: (response) => {
			user = response.data;
		},
		error: (error) => {
			console.log(error);
		}
	});
	return user;
}



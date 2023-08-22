function load(usercode) {
    $.ajax({
      type: "get",
      url: `/api/v1/account/get/${usercode}`,
      dataType: "json",
      success: (response) => {
        console.log(response.data);
        console.log(usercode);
        getData(usercode); // 수정된 부분: usercode를 인자로 getData() 함수 호출
      },
      error: function (xhr, status, error) {
        console.log(error);
      }
    });
}

function getData() {
    const nickNameAnswer = document.querySelector(".nickNameAnswer");
    if (getUser() != null) {
        console.log("nname 불러오기");
        nickNameAnswer.innerHTML = `<p>${user.nname}</p>`;
    }
}

load(getUser().user_code);


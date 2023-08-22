const inqueryButton = document.querySelector(".Inquery");
const boardData = document.querySelectorAll(".board-data");


function getData() {
    const nickNameAnswer = document.querySelector(".nickNameAnswer");
    if (getUser() != null) {
        console.log("nname 불러오기");
        nickNameAnswer.innerHTML = `<p>${user.nname}</p>`;
    }
}

inqueryButton.onclick = () => {
	let formData = new FormData(document.querySelector("form"));
	formData.append("userCode", getUser().user_code);
	formData.append("noticeNname", getUser().nname);
	
	formData.forEach((v, k) => {
		console.log("key: " + k);
		console.log("value: " + v);
	})
	
	// let getData = {
	// 	noticeTitle: boardData[0].value,
	// 	noticeContent: boardData[1].value,
	// 	noticeNname: getUser().nname
	// }
	
	
	
	$.ajax({
		async: false,
		type: "post",
		url: "/api/v1/board/post",
		enctype: "multipart/form-data",
		contentType: false,
		processData: false,
		data: formData,
		dataType: "json",
		success: (response) => {
			console.log(response.data);
			alert("등록완료");
		},
		error: (error) => {
			console.log(error);
		}
	})
	
	location.href = "/result_board";
}


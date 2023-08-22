
const inqueryButton = document.querySelector(".Inquery");
const boardData = document.querySelectorAll(".board-data");

inqueryButton.onclick = () => {
	
	let getData = {
		boardTitle: boardData[0].value,
		boardContent: boardData[1].value,
		boardNumber: 1
	}
	
	$.ajax({
		async: false,
		type: "post",
		url: "/api/v1/board/post",
		contentType: "application/json",
		data: JSON.stringify(getData),
		dataType: "json",
		success: (response) => {
			console.log(response.data);
			alert("등록완료");
		},
		error: (error) => {
			console.log(error);
		}
	})
}
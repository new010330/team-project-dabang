const boardContentsPlus = document.querySelector(".board-contents-plus");
const nname = getUser().nname;


document.addEventListener("DOMContentLoaded", function() {
  var contentsPlus = document.querySelector(".contents-plus");
  var editedContent = document.querySelector(".edited-content");

  // contents-plus 요소를 클릭할 때의 동작을 정의합니다.
  contentsPlus.addEventListener("click", function() {
    // contents-plus 요소를 숨깁니다.
    contentsPlus.style.display = "none";
    // edited-content visible 요소를 표시합니다.
    editedContent.style.display = "block";
    editedContent.focus();
  });

  // edited-content에서 포커스가 벗어났을 때의 동작을 정의합니다.
  editedContent.addEventListener("blur", function() {
    if (editedContent.value.trim() === "") {
      // 만약 수정한 내용이 비어있다면, contents-plus 요소를 표시하고 edited-content 요소를 숨깁니다.
      contentsPlus.style.display = "block";
      editedContent.style.display = "none";
    }
  });

  // edited-content에서 키보드 키가 눌렸을 때의 동작을 정의합니다.
  editedContent.addEventListener("keyup", function(event) {
    if (event.key === "Enter" || contentsPlus.onblur()) {
      // Enter 키가 눌렸을 때, edited-content 요소를 숨기고 contents-plus 요소를 표시합니다.
      contentsPlus.style.display = "block";
      editedContent.style.display = "none";
    }
  });
});






const inqueryButton = document.querySelector(".Inquery");

inqueryButton.onclick = () => {
    location.href = "/board";
}



function load(usercode) {
	$.ajax({
        type: "get",
        url: `/api/v1/board/get/${usercode}`,
        dataType: "json",
        success: (response) => {
			console.log(response.data);
			loadContent(response.data);
			ContentArray(response.data);
		},
		error: (error) => {
			console.log(error);
		}
	});
}

load(getUser().user_code);


function getData() {
    const nickNameAnswer = document.querySelector(".nickNameAnswer");
    if (getUser() != null) {
        console.log("nname 불러오기");
        nickNameAnswer.innerHTML = `<p>${user.nname}</p>`;
    }
}



function loadContent(data) {
	
	const contentPlus = document.querySelector(".contents-plus");
	const editedContent = document.querySelector(".edited-content");
	contentPlus.innerHTML = ""; // 내용 초기화

	data.forEach(content => {
		contentPlus.textContent += `${content.notice_content}`; // 누적하여 추가
		/*editedContent.value = `&{content.notice_content}`;*/
	});
}

function ContentArray(data) {
	const boardTbody = document.querySelector(".board tbody");
    boardTbody.innerHTML = ""; // Clear previous content
    
    for (const item of data) {
        const tr = document.createElement("tr"); // Create a new tr element
        tr.className = "board-contents";
        
        tr.innerHTML = `
            <td class="board-type board-content">${item.notice_type}</td>
            <td class="board-title board-content">${item.notice_title}</td>
            <td class="board-enrollment board-content">${item.create_date}</td>
            <td class="board-answer board-content">${item.total_board_count > 0 ? "답변 완료" : "미답변"}</td>
        `;
        
        boardTbody.appendChild(tr); // Append the tr element to the tbody

    }

    const boardContents = document.querySelectorAll(".board-contents");
    boardContents.forEach((contentArray) => {
        contentArray.onclick = () => {
            const boardDtl = contentArray.querySelector(".board-contents-plus");
            boardDtl.classList.toggle("hidden");
        }
    });
    addEvent();
}

const boardTbody = document.querySelector(".board tbody"); 

boardTbody.onclick = () => {
    const boardDtl = document.querySelector(".board-contents-plus");
    console.log("hidden 클릭")
    boardDtl.classList.toggle("hidden");
}


const editedContent = document.querySelector(".edited-content");
const revise = document.querySelector(".revise");

revise.onclick = () => {
	editedContent.focus();
}

function addEvent() {
    const contentPlus = document.querySelector(".contents-plus");
    const editedContent = document.querySelector(".edited-content");
    const reviseButton = document.querySelector(".revise");
    let usercode = getUser().user_code;
    let temp = "";

    contentPlus.onclick = () => {
        // 클릭했을 때 input 창이 초기화 되지않고 원래 text 값을 넣어준다.
        editedContent.value = contentPlus.textContent;
        temp = editedContent.value
        contentPlus.classList.toggle("visible");
        editedContent.classList.toggle("visible");
        
        editedContent.focus();

        editedContent.onblur = () => {
            if(editedContent.value != temp) {
                updateBoardContent();
                editedContent.blur();
                editedContent.classList.toggle("visible");
                contentPlus.textContent = editedContent.value;
            }
        }
    }
    
    let updateBoardContent = () => {
        $.ajax({
            type: "put",
            url: `/api/v1/board/put/${usercode}`,
            contentType: "application/json",
            data: JSON.stringify({
                noticeContent: editedContent.value
            }),
            async: false,
            dataType: "json",
            success: (response) => {
                console.log(response.data);
                contentPlus.classList.toggle("visible");
            }
        })
    }

    editedContent.onblur = () => {
        updateBoardContent();
    }

    editedContent.onkeyup = () => {
        updateBoardContent();
    }
}

var deleteButton = document.querySelector(".delete");

deleteButton.onclick = function () {
    var boardContents = document.querySelector(".board-contents");
    var boardContentsPlus = document.querySelector(".board-plus");
    var usercode = getUser().user_code; // Change this to the actual usercode
    
    deleteBoard(boardContents,boardContentsPlus, usercode);
};

function deleteBoard(boardContents,boardContentsPlus, usercode) {
    $.ajax({
        type: 'delete',
        url: `/api/v1/board/delete/${usercode}`,
        dataType: 'json',
        success: function (response) {
            if (response.data) {
                boardContents.remove();
                boardContentsPlus.remove();
            }
        },
        error: function (error) {
            console.error('Error deleting board contents:', error);
        }
    });
}

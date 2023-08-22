const optionBoxes = document.querySelectorAll(".option-box");
const buttons = document.querySelectorAll(".search-type button");
const optionDetailLabel = document.querySelectorAll(".option-detail-label");

buttons[0].onclick = () => {
    if(buttons[1].classList.contains("button-color")) {
        buttons[1].classList.remove("button-color");
        buttons[0].classList.add("button-color");

    }
}

buttons[1].onclick = () => {
    if(buttons[0].classList.contains("button-color")) {
        buttons[0].classList.remove("button-color");
        buttons[1].classList.add("button-color");
    }
}

/*optionBoxes.forEach((optionBox) => {
    const optionButton = optionBox.querySelector(".option-button");
    const textColor = optionButton.querySelector("p");
    const optionContainer = optionBox.querySelector(".option-detail-container");
  
    optionButton.onclick = () => {
      optionButton.classList.toggle("black-button");
      optionButton.classList.toggle("blue-button");
      textColor.classList.toggle("black-font");
      textColor.classList.toggle("blue-font");
      optionContainer.classList.toggle("hidden");
    }

    optionButton.onblur = () => {
      if(!optionContainer.classList.contains("hidden")) {
        optionButton.classList.toggle("black-button");
        optionButton.classList.toggle("blue-button");
        textColor.classList.toggle("black-font");
        textColor.classList.toggle("blue-font");
        optionContainer.classList.toggle("hidden");
      }
    }
});*/

let prevOptionBox = null;

optionBoxes.forEach((optionBox) => {
    const optionButton = optionBox.querySelector(".option-button");
    const textColor = optionButton.querySelector("p");
    const optionContainer = optionBox.querySelector(".option-detail-container");

    optionButton.onclick = () => {
        // 이전에 클릭된 optionBox가 있고, 그것이 현재 클릭된 optionBox와 다른 경우에만,
        // 이전 optionBox의 option-detail-container의 표시 상태를 토글합니다.
        if (prevOptionBox && prevOptionBox !== optionBox) {
            const prevOptionButton = prevOptionBox.querySelector(".option-button");
            const prevTextColor = prevOptionButton.querySelector("p");
            const prevOptionContainer = prevOptionBox.querySelector(".option-detail-container");

            prevOptionButton.classList.toggle("black-button");
            prevOptionButton.classList.toggle("blue-button");
            prevTextColor.classList.toggle("black-font");
            prevTextColor.classList.toggle("blue-font");
            prevOptionContainer.classList.toggle("hidden");
        }

        // 클릭된 버튼에 대한 색상과 텍스트 색상을 변경하고,
        // 클릭된 버튼에 해당하는 option-detail-container의 표시 상태를 토글합니다.
        optionButton.classList.toggle("black-button");
        optionButton.classList.toggle("blue-button");
        textColor.classList.toggle("black-font");
        textColor.classList.toggle("blue-font");
        optionContainer.classList.toggle("hidden");

        
        if(prevOptionBox === optionBox) {
          prevOptionBox = null;
          console.log("이전 optionBox: " + prevOptionBox);
          console.log("클릭한 optionBox: " + optionBox);
        }else {
          // 클릭된 optionBox를 이전 optionBox로 저장합니다.
          prevOptionBox = optionBox;
          console.log("이전 optionBox: " + prevOptionBox);
          console.log("클릭한 optionBox: " + optionBox);
        }
    };
});


// let address = "전포동";
// getRoomList(address);

// function getRoomList(address) {
//   console.log("test");
//   $.ajax({
//     async: false,
//     type: "get",
//     url: `/api/v1/map/room/${address}`,
//     data: {
//       address: `${address}`
//     },
//     dataType: "json",
//     success: (response) => {
//       console.log(response.data);
//       roomListData(response.data);
//       console.log("successTest");
//     },
//     error: (error) => {
//       console.log(error);
//     }
//   })
// }

// function roomListData(data) {
//   const roomSearchList = document.querySelector(".room-search-list");
//   roomSearchList.innerHTML = ""
//   data.forEach(list => {
//     let freeFee = null;

//     if(`${list.management_fee}` == 0) {
//       freeFee = "관리비 없음";
//     }else {
//       freeFee = `관리비 ${list.management_fee}만`;
//     }

//     let title = null;
//     if(`${list.desc_title}` == null || `${list.desc_title}` == "null") {
//       title = "";
//     } else {
//       title = `${list.desc_title}`;
//     }
//     roomSearchList.innerHTML += `
//         <div class="room-data">
//             <div class="room-img">
//                 <img src="/static/images/room_img.PNG">
//             </div>
//             <div class="room-text-data">
//                 <h1>월세 ${list.monthly_price}/${list.monthly_price_deposit}</h1>    
//                 <p class="room-text">오피스텔 · 판도라</p>
//                 <p>${list.num_floor}층, ${list.size_exclusive_m}m², ${freeFee}</p>
//                 <p>${title}</p>
//             </div>
//         </div>
//     `
//   })
// }

/**
 * 해야 할 것
 * 
 * 지도 마커 표시하기
 * 범위 찾기
 * 방 가져오기
 * 
 */
<<<<<<< HEAD
const optionButtons = document.querySelectorAll(".option-button");

=======
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

optionBoxes.forEach((optionBox) => {
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
});

/**
 * 해야 할 것
 * 
 * 지도 마커 표시하기
 * 범위 찾기
 * 방 가져오기
 * 
 */
>>>>>>> 6a628feb265ad13ff7da7284ac78200a7df996e0

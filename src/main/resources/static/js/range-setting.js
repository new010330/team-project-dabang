// const inputLeft = document.getElementById("input-left");
// const inputRight = document.getElementById("input-right");

// const thumbLeft = document.querySelector(".slider > .thumb.left");
// const thumbRight = document.querySelector(".slider > .thumb.right");
// const range = document.querySelector(".slider > .range");

// const setLeftValue = () => {
//   const _this = inputLeft;
//   const [min, max] = [parseInt(_this.min), parseInt(_this.max)];
  
//   // 교차되지 않게, 1을 빼준 건 완전히 겹치기보다는 어느 정도 간격을 남겨두기 위해.
//   _this.value = Math.min(parseInt(_this.value), parseInt(inputRight.value) - 1);
  
//   // input, thumb 같이 움직이도록
//   const percent = ((_this.value - min) / (max - min)) * 100;
//   thumbLeft.style.left = percent + "%";
//   range.style.left = percent + "%";
// };

// const setRightValue = () => {
//   const _this = inputRight;
//   const [min, max] = [parseInt(_this.min), parseInt(_this.max)];
  
//   // 교차되지 않게, 1을 더해준 건 완전히 겹치기보다는 어느 정도 간격을 남겨두기 위해.
//   _this.value = Math.max(parseInt(_this.value), parseInt(inputLeft.value) + 1);
  
//   // input, thumb 같이 움직이도록
//   const percent = ((_this.value - min) / (max - min)) * 100;
//   thumbRight.style.right = 100 - percent + "%";
//   range.style.right = 100 - percent + "%";
// };

// inputLeft.addEventListener("input", setLeftValue);
// inputRight.addEventListener("input", setRightValue);

// class MultiRangeSlider {
//   constructor(container) {
//     this.container = container;
//     this.inputLeft = container.querySelector(".input-left");
//     this.inputRight = container.querySelector(".input-right");
//     this.thumbLeft = container.querySelector(".slider > .thumb.left");
//     this.thumbRight = container.querySelector(".slider > .thumb.right");
//     this.range = container.querySelector(".slider > .range");
    
//     this.init();
//   }
  
//   init() {
//     this.inputLeft.addEventListener("input", () => this.setLeftValue());
//     this.inputRight.addEventListener("input", () => this.setRightValue());
    
//     this.setLeftValue();
//     this.setRightValue();
//   }
  
//   setLeftValue() {
//     const [min, max] = [parseInt(this.inputLeft.min), parseInt(this.inputLeft.max)];
//     this.inputLeft.value = Math.min(parseInt(this.inputLeft.value), parseInt(this.inputRight.value) - 1);
    
//     const percent = ((this.inputLeft.value - min) / (max - min)) * 100;
//     this.thumbLeft.style.left = percent + "%";
//     this.range.style.left = percent + "%";
//   }
  
//   setRightValue() {
//     const [min, max] = [parseInt(this.inputRight.min), parseInt(this.inputRight.max)];
//     this.inputRight.value = Math.max(parseInt(this.inputRight.value), parseInt(this.inputLeft.value) + 1);
    
//     const percent = ((this.inputRight.value - min) / (max - min)) * 100;
//     this.thumbRight.style.right = 100 - percent + "%";
//     this.range.style.right = 100 - percent + "%";
//   }
// }

// const sliders = document.querySelectorAll(".multi-range-slider");
// sliders.forEach((slider) => new MultiRangeSlider(slider));

const sliders = document.querySelectorAll(".multi-range-slider");

sliders.forEach((slider) => {
  const inputLeft = slider.querySelector(".input-left");
  const inputRight = slider.querySelector(".input-right");
  const thumbLeft = slider.querySelector(".slider .thumb.left");
  const thumbRight = slider.querySelector(".slider .thumb.right");
  const range = slider.querySelector(".slider .range");

  const setLeftValue = () => {
    const [min, max] = [parseInt(inputLeft.min), parseInt(inputLeft.max)];

    inputLeft.value = Math.min(parseInt(inputLeft.value), parseInt(inputRight.value) - 1);

    const percent = ((inputLeft.value - min) / (max - min)) * 100;
    thumbLeft.style.left = percent + "%";
    range.style.left = percent + "%";
  };

  const setRightValue = () => {
    const [min, max] = [parseInt(inputRight.min), parseInt(inputRight.max)];

    inputRight.value = Math.max(parseInt(inputRight.value), parseInt(inputLeft.value) + 1);

    const percent = ((inputRight.value - min) / (max - min)) * 100;
    thumbRight.style.right = 100 - percent + "%";
    range.style.right = 100 - percent + "%";
  };

  inputLeft.addEventListener("input", setLeftValue);
  inputRight.addEventListener("input", setRightValue);
});
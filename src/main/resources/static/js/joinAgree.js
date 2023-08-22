
const loginBtn = document.querySelector(".login-page-button")
const form = document.querySelector("#JoinAgree-form");
const checkAll = document.querySelector(".Check-Container input");
const checkBoxs = document.querySelectorAll(".Check-boxs input");
const submitButton = document.querySelector(".JoinBtn");

/*loginBtn.onclick = () => {
    console.log("login");
    location.href = "/welcome/login";

}
*/
submitButton.onclick = () => {
    console.log("Join");
    location.href = "/welcome/join";

}



//버튼 정보함
const agreements = {
    agecheck:false,         //첫번째 필수동의 체크박스
    AcceptanceCheck: false,  //두번째 필수동의 체크박스
    personalInfo: false,    //세번째 필수동의 체크박스
    marketintInfo:false     //네번째 필수동의 체크박스
}

form.addEventListener('submit', (e) => e.preventDefault())

checkBoxs.forEach((item) => item.addEventListener('input', toggleCheckbox));


function toggleCheckbox(e) {
    const { checked, id} = e.target;
    agreements[id] = checked;
    this.parentNode.classList.toggle('active');
    checkAllStatus();
    toggleSubmitButton();
}

/*
총 4개의 체크박스의 체크를 true로 만들어주는 함수
*/
function checkAllStatus() {
    const {agecheck, AcceptanceCheck,personalInfo ,marketintInfo} = agreements;
    if(agecheck & AcceptanceCheck &personalInfo & marketintInfo) {
        checkAll.checked = true;
    }else{
        checkAll.checked =false;
    }
}
/*
	필수 버튼 3개 눌러야 버튼 활성화 나머지 1개는 선택이므로 선택하던 말던 상관없음
*/
function toggleSubmitButton() {
    const { agecheck, AcceptanceCheck, personalInfo } = agreements;
    if(agecheck && AcceptanceCheck && personalInfo) {
        submitButton.disabled = false;
    }else {
        submitButton.disabled =true;
    }
}

//checkAll 버튼을 누르면 addEventListener로 인해 누르면 모드 체크 누르지 않으면 모두 체크 X or 눌렀다가 다시 누르면 체크 활성화 X

checkAll.addEventListener('click', (e) => {
    const {checked} = e.target;
    if(checked){
        checkBoxs.forEach((item) => {
            item.checked = true;
            agreements[item.id] =true;
            item.parentNode.classList.add('active');
        });
    }else {
        checkBoxs.forEach((item) => {
            item.checked = false;
            agreements[item.id] =false;
            item.parentNode.classList.remove('active');
        });
    }
    toggleSubmitButton();
})
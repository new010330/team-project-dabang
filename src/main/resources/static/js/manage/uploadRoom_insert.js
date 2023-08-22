

const inqueryButton = document.querySelector(".submitBtn");
const buildingTypeSelected = document.getElementsByName("buildingType");

// ===================== 동 정보유무 체크박스 ===============================
document.querySelector('.address-checkbox-input').addEventListener('change', function() {
    const textInputs = document.querySelectorAll('.dongInput');
    
    textInputs.forEach(input => {
        if (this.checked) {
            input.disabled = true;
            input.value = '';  // 입력 값 초기화
        } else {
            input.disabled = false;
        }
    });
});

// ================= 파일 업로드 시 파일 목록 추가 =================================
document.getElementById('file').addEventListener('change', function(e) {
	const files = e.target.files;
	const fileList = document.getElementById('fileList');
	
	// 목록 초기화
	fileList.innerHTML = '';

	for(let i = 0; i < files.length; i++) {
		const listItem = document.createElement('li');
		listItem.textContent = files[i].name;
		fileList.appendChild(listItem);
	}
});


//=================== checkbox에서 전체 선택시 하위 선택 전체선택/해제 하기 ===================================

// 생활시설 전체선택 체크/해제 설정
// '전체 선택' 체크박스의 참조를 얻습니다.
const facCommSelectAllCheckbox = document.querySelector('input[name="facCommAllSelected"]');

// '전체 선택' 체크박스의 이벤트 리스너를 등록합니다.
facCommSelectAllCheckbox.addEventListener('change', (e) => {
    // 모든 'facComm' 체크박스를 선택합니다.
    const facCommCheckboxes = document.querySelectorAll('input[name="facComm"]');

    facCommCheckboxes.forEach((checkbox) => {
        checkbox.checked = e.target.checked;
    });
});

// 각각의 'facComm' 체크박스에 이벤트 리스너를 등록합니다.
const facCommCheckboxes = document.querySelectorAll('input[name="facComm"]');
facCommCheckboxes.forEach((checkbox) => {
    checkbox.addEventListener('change', () => {
        // 모든 'facComm' 체크박스가 체크되어 있는지 확인합니다.
        const facCommAllChecked = [...facCommCheckboxes].every((checkbox) => checkbox.checked);

        // '전체 선택' 체크박스의 상태를 업데이트합니다.
        facCommSelectAllCheckbox.checked = facCommAllChecked;
    });
});

// 보안시설 전체선택 체크/해제 설정
const facSecSelectAllCheckbox = document.querySelector('input[name="facSecAllSelected"]');

facSecSelectAllCheckbox.addEventListener('change', (e) => {
	const facSecCheckboxes = document.querySelectorAll('input[name="facSec"]');
	
	facSecCheckboxes.forEach((checkbox) => {
		checkbox.checked = e.target.checked;	
	});
});

const facSecCheckboxes = document.querySelectorAll('input[name="facSec"]');
facSecCheckboxes.forEach((checkbox) => {
	checkbox.addEventListener('change', () => {
		const facSecAllChecked = [...facSecCheckboxes].every((checkbox) => checkbox.checked);
		
		facSecSelectAllCheckbox.checked = facSecAllChecked;
	})
})
// =========================== 전용면적/공급면적 평과 평방미터 변환하기 ===================================
const exclusivePyeongInput = document.querySelector('.exclusiveP');
const exclusiveMeterInput = document.querySelector('.exclusiveM');
const supplyPyeongInput = document.querySelector('.supplyP');
const supplyMeterInput = document.querySelector('.supplyM');
const conversionRate = 3.305785;

exclusivePyeongInput.addEventListener('input', (e) => {
	exclusiveMeterInput.value =(e.target.value * conversionRate).toFixed(2);
});
exclusiveMeterInput.addEventListener('input', (e) => {
	exclusivePyeongInput.value = (e.target.value / conversionRate).toFixed(0);
});

supplyPyeongInput.addEventListener('input', (e) => {
	supplyMeterInput.value =(e.target.value * conversionRate).toFixed(2);
});
supplyMeterInput.addEventListener('input', (e) => {
	supplyPyeongInput.value = (e.target.value / conversionRate).toFixed(0);
});

// ========================== 방 수 입력시 방거실형태/방 특징 활성화 =============================
const roomCountInput = document.querySelector('.roomCount');
const livingRoomTypeInputs = document.querySelectorAll('.livingRoomTypeText');
const roomCharInputs = document.querySelectorAll('.roomCharText');

roomCountInput.addEventListener('input', (e) => {
	const isEnabled = e.target.value !=='' && e.target.value !== '0';
	
	livingRoomTypeInputs.forEach((input) => {
    input.disabled = !isEnabled;
    if (!isEnabled) {
      input.checked = false;
    }
  });
  
  roomCharInputs.forEach((input) => {
    input.disabled = !isEnabled;
    if (!isEnabled) {
      input.checked = false;
    }
  });
})
// =============================== 전세/월세 변경 ====================================
const tradeTypeInputs = document.querySelectorAll('.tradeTypeText');
const depositPriceInput = document.querySelector('.depositDiv');
const rentPriceInputs = document.querySelectorAll('.rentPriceDiv');

rentPriceInputs.forEach((input) => {
  input.style.display = 'none';
});

tradeTypeInputs.forEach((input) => {
  input.addEventListener('change', () => {
    if (input.value === 'tradeType1') { // 전세 선택 시
      depositPriceInput.style.display = 'block';
      rentPriceInputs.forEach((input) => {
        input.style.display = 'none';
      });
    } else if (input.value === 'tradeType2') { // 월세 선택 시
      depositPriceInput.style.display = 'none';
      rentPriceInputs.forEach((input) => {
        input.style.display = 'block';
      });
    }
  });
});
// ========================= 관리비 여부 ==========================
const managementFeeCheckboxes = document.querySelectorAll('.managementFeeCheckbox');
const managementFeeAmountInput = document.querySelector('.managementFeeText');

managementFeeCheckboxes.forEach((input) => {
	input.addEventListener('change', () => {
		if(input.value === 'managementFee1') {
			managementFeeAmountInput.disabled = true;
			managementFeeAmountInput.value = '';
		}
		else if (input.value === 'managementFee2') {
			managementFeeAmountInput.disabled = false;
		}
	});
});
// ===================== 전체층수 / 해당 층수 선택 ===================

document.querySelector('.totalFloors').addEventListener('change', function() {
    const totalFloors = parseInt(this.value, 10);
    const currentFloorSelect = document.querySelector('.currentFloor');
    
    // 옵션 초기화
    currentFloorSelect.innerHTML = '<option value>선택</option>';
    currentFloorSelect.options.add(new Option('반지층', '-1'));
    currentFloorSelect.options.add(new Option('옥탑', '0'));

    for (let i = 1; i <= totalFloors; i++) {
        const option = new Option(i + '층', i);
        currentFloorSelect.options.add(option);
    }

    // 해당 층 수 칸 활성화
    currentFloorSelect.disabled = false;
});

// 페이지 로드 시 해당 층 수 칸 초기 상태 설정
window.addEventListener('DOMContentLoaded', function() {
    const currentFloorSelect = document.querySelector('.currentFloor');
    currentFloorSelect.disabled = true;
});


// =====================주차 가능여부 체크박스 ====================
const parkingCheckboxes = document.querySelectorAll(".parkingAvailabilityCheckbox");
const parkingTextInput = document.querySelector(".parkingText");

parkingCheckboxes.forEach((input) => {
	input.addEventListener('change', () => {
		if(input.value === 'parkingAvailability1') {
			parkingTextInput.disabled = true;
			parkingTextInput.value = '';
		}
		else if (input.value === 'parkingAvailability2') {
			parkingTextInput.disabled = false;
		}
		
	});
});

// ====================================================================================
const detailTitleInput = document.querySelector('.detailTitleText');
const titleCharCountElement = document.querySelector('.detailInfo-charCount p');
const titleMaxLength = 40;

detailTitleInput.addEventListener('input', function() {
    let currentLength = detailTitleInput.value.length;
    if (currentLength > titleMaxLength) {
        detailTitleInput.value = detailTitleInput.value.substring(0, titleMaxLength);
        currentLength = titleMaxLength;
    }
    titleCharCountElement.textContent = `${currentLength} / ${titleMaxLength}`;
});

// 페이지가 로드되면 문자 카운터 상태를 초기화합니다.
window.addEventListener('DOMContentLoaded', function() {
    titleCharCountElement.textContent = `0 / ${titleMaxLength}`;
});

const detailContentTextarea = document.querySelector('.detailContentText');
const contentCharCountElement = document.querySelector('.detailInfo-textCount p');
const contentMaxLength = 1000;

detailContentTextarea.addEventListener('input', function() {
    let currentLength = detailContentTextarea.value.length;
    if (currentLength > contentMaxLength) {
        detailContentTextarea.value = detailContentTextarea.value.substring(0, contentMaxLength);
        currentLength = contentMaxLength;
    }
    contentCharCountElement.textContent = `${currentLength} / ${contentMaxLength}`;
});

// 페이지가 로드되면 문자 카운터 상태를 초기화합니다.
window.addEventListener('DOMContentLoaded', function() {
    contentCharCountElement.textContent = `0 / ${contentMaxLength}`;
});
// ====================================================================================

// getData을 위한 변수 선언

const registeredCheckbox = document.querySelector(".registeredCheckbox");
const roomCount = document.querySelector(".roomCount"); // 방 수
const inputAddress = document.querySelectorAll(".inputAddress") // 주소
const roadAddress = document.querySelector(".road-address"); // 도로명주소
const jibunAddress = document.querySelector(".jibun-address"); // 지번 주소
const inputExclusiveSizes = document.querySelectorAll(".exclusiveAreaText"); // 전용면적
const inputSupplySizesSizes = document.querySelectorAll(".supplyAreaText"); // 공급면적
const selectedBuildingUse = document.querySelector(".buildingUse");// 건축물용도
const selectedBuildingApprov = document.querySelector(".buildingApprove") // 건축물승인
const inputBuildingApproveDate = document.querySelector(".buildingApproveDate") // 건축물승인 날짜
const inputDepositText = document.querySelector(".depositText"); // 전세보증금
const inputMonthlyDeposit = document.querySelector(".rentPriceDepositText"); // 월세보증금
const inputMonthlyPrice = document.querySelector(".rentPriceMonthly"); // 월세
const inputManagementFee = document.querySelector(".managementFeeText"); // 관리비
const inputMovedDate = document.querySelector(".movedDate"); // 입주 가능 일자
const selectedTotalFloors = document.querySelector(".totalFloors"); // 전체 층 수
const selectedCurrentFloor = document.querySelector(".currentFloor"); // 현재 층 수
const inputCountBathroom = document.querySelector(".countBathroomText"); // 욕실 수
const inputCountParking = document.querySelector(".parkingText"); // 주차 가능 수
const inputDetailTitle = document.querySelector(".detailTitleText"); // 상세설명 제목
const inputDetailContext = document.querySelector(".detailContentText"); // 상세설명 내용

// =====================================================================================

// 매물등록 버튼 클릭
inqueryButton.onclick = () => {
	console.log("버튼누름");

	
	

	// 입력받은 데이터 저장

	let getDataInputAddress = { // 주소
		mainRoadAddress: inputAddress[0].textContent,
		mainJibunAddress: inputAddress[1].textContent,
		dong: inputAddress[2].value,
		ho: inputAddress[3].value
	}
	
	let getDataExclusivesSize = { // 전용면적
		exclusiveP: inputExclusiveSizes[0].value, // 평
		exclusiveM: inputExclusiveSizes[1].value // 미
	}
	let getDataSupplySize = { // 공급면적
		supplyP: inputSupplySizesSizes[0].value, // 평
		supplyM: inputSupplySizesSizes[1].value // 미
	}

	let getDataRoomCount = roomCount.value; // 방수

	let selectedOptionBuildingUse = selectedBuildingUse.options[selectedBuildingUse.selectedIndex];
	let getDataSelectedBuildingUse = selectedOptionBuildingUse.text; //건축물용도
	
	let selectedOptionBuildingApprov = 	selectedBuildingApprov.options[selectedBuildingApprov.selectedIndex];// 건축물 승인
	let getDataSelectedBuildingApprov = selectedOptionBuildingApprov.text; 
	
	let getDataInputBuildingApproveDate = inputBuildingApproveDate.value; // 건축물승인 날짜
	
	let getDataPriceInfo = { // 매물가격 정보
		depositPrice: inputDepositText.value, // 전세가
		monthlyPriceDeposit: inputMonthlyDeposit.value, // 월세 - 보증금
		monthlyPrice: inputMonthlyPrice.value // 월세 
	};
	console.log(getDataPriceInfo);
	
	let getDataManagementFee = managementFeeAmountInput.value; // 관리비
	console.log(getDataManagementFee);
	
	let getDataInputMovedDate = inputMovedDate.value; // 입주가능일 날짜
	
	let selectedOptionTotalFloors = selectedTotalFloors.options[selectedTotalFloors.selectedIndex];
	let getDataSelectedTotalFloors = selectedOptionTotalFloors.text; // 전체 층수
	
	let selectedOptionCurrentFloor = selectedCurrentFloor.options[selectedCurrentFloor.selectedIndex];
	let getDataSelectedCurrentFloor = selectedOptionCurrentFloor.text;// 현재 층
	
	let getDataCountBathroom = inputCountBathroom.value; // 욕실 수
	console.log(getDataCountBathroom);
	
	let getDataCountParking = inputCountParking.value; // 주차 가능 수
	console.log(getDataCountParking);
	
	let getDataDetailTitle = inputDetailTitle.value;
	console.log(getDataDetailTitle);
	let getDataDetailContext = inputDetailContext.value;
	console.log(getDataDetailContext);
	
	
	// radio 선택시, 
	// 매물유형 선택(주택/빌라)
	function getBuildingType() {
		const buildingTypes = document.getElementsByName('buildingType');
		let selectedType;
		let selectedTypeText;
		
		buildingTypes.forEach((type) => {
			if (type.checked) {
				selectedType = type.value;
				selectedTypeText = type.nextElementSibling.innerText;
			}
			
		});
		return selectedTypeText;
		
	}
	
	// 방 거실 형태 선택(오픈형/분리형)
	function getLinvigRoomType() {
		const livingRoomTypes = document.getElementsByName('livingRoomType');
		let selectedLivingRoomType;
		let selectedLivingRoomTypeText;
		
		livingRoomTypes.forEach((type) => {
			if(type.checked) {
				selectedLivingRoomType = type.value;
				selectedLivingRoomTypeText = type.nextElementSibling.innerText;
			}
		});
		return selectedLivingRoomTypeText;
	}
	
	// 거래 종류 선택(전/월세)
	function getTradeType() {
		const tradeTypes = document.getElementsByName('tradeType');
		let selectedTradeType;
		let selectedTradeTypeText;
		
		tradeTypes.forEach((type) => {
			if(type.checked) {
				selectedTradeType = type.value;
				selectedTradeTypeText = type.nextElementSibling.innerText;
			}
		});
		return selectedTradeTypeText;
	}
	
	// 공용관리비(없음/있음)
	function getManagementFee() {
		const managementFees = document.getElementsByName('managementFee');
		let selectedManagementFee;
		let selectedManagementFeeText;
		
		managementFees.forEach((type) => {
			if(type.checked) {
				selectedManagementFee = type.value;
				selectedManagementFeeText = type.nextElementSibling.innerText;
			}
		});
		return selectedManagementFeeText
	}
	
	// 입주가능여부(즉시입주/일자선택)
	function getpossibleMoveDate() {
		const possibleMoveDates = document.getElementsByName('possibleMoveDate');
		let selectedPossibleMoveDate;
		let selectePpossibleMoveDateText;
		
		possibleMoveDates.forEach((type) => {
			if(type.checked) {
				selectedPossibleMoveDate = type.value;
				selectePpossibleMoveDateText = type.nextElementSibling.innerText;
			}
		});
		return selectePpossibleMoveDateText
	}
	
	// 엘리베이터 유/무
	function getElevator() {
		const elevatorType = document.getElementsByName('elevator');
		let selectedElevatorType;
		let selectedElevatorText;
		
		elevatorType.forEach((type) => {
			if(type.checked) {
				selectedElevatorType = type.value;
				selectedElevatorText = type.nextElementSibling.innerText;
			}
		});
		return selectedElevatorText
	}
	
	// 주차가능여부
	function getParkingAvailability() {
		const parkingAvailabilityType = document.getElementsByName('parkingAvailability');
		let selectedParkingAvailabilityType;
		let selectedParkingAvailabilityText;
		
		parkingAvailabilityType.forEach((type) => {
			if(type.checked) {
				selectedParkingAvailabilityType = type.value;
				selectedParkingAvailabilityText = type.nextElementSibling.innerText;
			}
		});
		return selectedParkingAvailabilityText
	}
	
	// 난방시설
	function getHeatingType() {
		const heatingTypes = document.getElementsByName('heatingType');
		let selectedHeatingType;
		let selctedHeatingTypeText;
		
		heatingTypes.forEach((type) => {
			if(type.checked) {
				selectedHeatingType = type.value;
				selctedHeatingTypeText = type.nextElementSibling.innerText;
			}
		});
		return selctedHeatingTypeText
	}
	
	// checkbox 선택시,
	
	// 방정보 - 방특징선택(신축/큰길가/반려동물)
	function getRoomChar() {
		const roomChars = document.getElementsByName('roomChar');
		let selectedRoomChars = [];
		
		roomChars.forEach((type, index) => {
			if(type.checked) {
				selectedRoomChars.push(index);
		}
		});
		return selectedRoomChars.join(',');
	}
	
	// 냉방시설
	function getAirCndType() {
		const airCndTypes = document.getElementsByName('airCndType');
		let selectedAirCndTypes = [];
		
		airCndTypes.forEach((type, index) => {
			if(type.checked) {
				selectedAirCndTypes.push(index);
			}
		});
		return selectedAirCndTypes.join(',');
	}
	
	// 생활시설
	function getFacComm() {
		const facComms = document.getElementsByName('facComm');
		let selectedFacComms = [];
		
		facComms.forEach((type, index) => {
			if(type.checked) {
				selectedFacComms.push(index);
			}
		});
		return selectedFacComms.join(',');
		
	}
	
	// 보안시설
	function getFacSec() {
		const facSecs = document.getElementsByName('facSec');
		let selectedFacSecs = [];
		
		facSecs.forEach((type, index) => {
			if(type.checked) {
				selectedFacSecs.push(index);
			}
		});
		return selectedFacSecs.join(',');
	}
	
	// 기타시설
	function getFacOther() {
		const facOthers = document.getElementsByName('facOther');
		let selectedFacOthers = [];
		
		facOthers.forEach((type, index) => {
			if(type.checked) {
				selectedFacOthers.push(index);
			}
		});
		return selectedFacOthers.join(',');
	}
	
	// checkbox 선택시 콘솔확인
	const selectedRoomChar = getRoomChar();
	console.log(selectedRoomChar);
	const selectedAirCndType = getAirCndType();
	console.log(selectedAirCndType);
	const selectedFacComm = getFacComm();
	console.log(selectedFacComm);
	const selectedFacSec = getFacSec();
	console.log(selectedFacSec);
	const selectedFacOtherType = getFacOther();
	console.log(selectedFacOtherType); 
	
	// radio 선택시 콘솔확인
	const selectedBuildingType = getBuildingType();
	console.log(selectedBuildingType); // 선택된 건물 유형을 콘솔에 출력합니다.
	const selectedLivingRoomType = getLinvigRoomType();
	console.log(selectedLivingRoomType)
	const selectedTradeType = getTradeType();
	console.log(selectedTradeType);
	const selectedManagementFee = getManagementFee();
	console.log(selectedManagementFee);
	const selectedPossibleMoveDate = getpossibleMoveDate();
	console.log(selectedPossibleMoveDate);
	const selectedElevator = getElevator();
	console.log(selectedElevator);
	const selectedParkingAvailability = getParkingAvailability();
	console.log(selectedParkingAvailability);
	const selectedHeatingType = getHeatingType();
	console.log(selectedHeatingType);
	// ================formData에 담기 =================================

	// 파일 업로드
	let formData = new FormData(document.querySelector("#formFileUpload"));

	// 방타입
	formData.append("salesType", selectedBuildingType);
	// 미등기건물추가해야함
	if (registeredCheckbox.checked) {
        formData.append('unregisteredCheck', '1');
    } else {
        formData.append('unregisteredCheck', '0');
    }
	//formData.append("unregisteredCheck", registeredCheckbox);
	// 매물 주소
	formData.append("salesAddressMainRoad", getDataInputAddress.mainRoadAddress);
	formData.append("salesAddressMainJibeon", getDataInputAddress.mainJibunAddress);
	formData.append("salesAddressDong", getDataInputAddress.dong);
	formData.append("salesAddressHo", getDataInputAddress.ho);

	// 매물크기
	formData.append("sizeExclusiveP", getDataExclusivesSize.exclusiveP);
	formData.append("sizeExclusiveM", getDataExclusivesSize.exclusiveM);
	formData.append("sizeSupplyP", getDataSupplySize.supplyP);
	formData.append("sizeSupplyM", getDataSupplySize.supplyM);

	// 방 정보
	formData.append("roomInfoCount", getDataRoomCount);
	formData.append("roomInfoLivingroom", selectedLivingRoomType);
	formData.append("roomInfoChar", selectedRoomChar);

	// 건축물 용도
	formData.append("buildingUse", getDataSelectedBuildingUse);
	
	// 건축물 승인
	formData.append("buildingApproval", getDataSelectedBuildingApprov);
	formData.append("buildingApprovalDate", getDataInputBuildingApproveDate);

	// 거래 종류
	formData.append("trnscType", selectedTradeType);
	formData.append("userCode", getUser().user_code);

	// 가격 정보

	if (getDataPriceInfo.depositPrice && getDataPriceInfo.depositPrice !== "") {
		formData.append("depositPrice", getDataPriceInfo.depositPrice);
	} else {
		if (getDataPriceInfo.monthlyPriceDeposit && getDataPriceInfo.monthlyPriceDeposit !== "") {
			formData.append("monthlyPriceDeposit", getDataPriceInfo.monthlyPriceDeposit);
		}
		if (getDataPriceInfo.monthlyPrice && getDataPriceInfo.monthlyPrice !== "") {
			formData.append("monthlyPrice", getDataPriceInfo.monthlyPrice);
		}
	}
	// formData.append("depositPrice", getDataPriceInfo.depositPrice);
	// formData.append("monthlyPriceDeposit", getDataPriceInfo.monthlyPriceDeposit);
	// formData.append("monthlyPrice", getDataPriceInfo.monthlyPrice);

	// 관리비 여부
	formData.append("publicManagement", selectedManagementFee);
	if (selectedManagementFee === "없음") {
		formData.append("managementFee", 0);
	} else {
		formData.append("managementFee", getDataManagementFee);
	}
	// formData.append("managementFee", getDataManagementFee);

	// 입주 가능 일자
	formData.append("possibleMoved", selectedPossibleMoveDate);
	formData.append("possibleMovedDate", getDataInputMovedDate);
	// 협의가능할 경우 체크 추가해야함

	// 층 수
	formData.append("totalFloors", getDataSelectedTotalFloors);
	formData.append("numFloor", getDataSelectedCurrentFloor);

	// 욕실 수
	formData.append("numBathrooms", getDataCountBathroom);
	formData.append("elevator", selectedElevator);

	// 주차가능 여부
	formData.append("parkingAvailability", selectedParkingAvailability);
	if (selectedParkingAvailability === "불가능") {
		formData.append("totalParking", 0);
	} else {
		formData.append("totalParking", getDataCountParking);
	}
	// formData.append("totalParking", getDataCountParking);

	// 시설 정보
	formData.append("facHeating", selectedHeatingType);
	formData.append("facAircnd", selectedAirCndType);
	formData.append("facComm", selectedFacComm);
	formData.append("facSecurity", selectedFacSec);
	formData.append("facOther", selectedFacOtherType);

	// 상세설명
	formData.append("descTitle", getDataDetailTitle);
	formData.append("descDetail", getDataDetailContext);
	
	//formData.append("userCode", ${user.user_code});

	// 매물관리규정 확인 체크해야함

	
	//formData.append("descTitle", inputDetailTitle);

	formData.forEach((v, k) => {
		console.log("key: " + k);
	if (v instanceof File) {
			console.log("value: " + v.name); // 파일 객체의 경우
		} else {
			console.log("value: " + v); // 그 외의 경우
		}
		//console.log("value: " + v.name);
	});

	
	$.ajax({
		async: false,
		type: "post",
		url: "/api/v1/manage/content",
		enctype: "multipart/form-data",
		contentType: false,
		processData: false,
		data: formData,
		dataType: "json",
		success: (response) => {
			console.log(response.data);
			alert("등록 완료");
			getRoomPage(response.data);
		},
		error: (error) => {
			console.log(error);
		}
	})
	
}; // 매물등록버튼 inqueryButton.onclick() 여기까지.



function getRoomPage(data) {
	location.href = `/manage_result/`;
}










//아래는 DB에서 불러올 때,

// 체크박스의 텍스트를 배열에 저장합니다.
const roomCharTextArray = []; // 방특징선택
	document.getElementsByName('roomChar').forEach((type) => {
		roomCharTextArray.push(type.nextElementSibling.innerText);
	});
	
const airCndTypeTextArray = []; // 냉방시설
	document.getElementsByName('airCndType').forEach((type) => {
		airCndTypeTextArray.push(type.nextElementSibling.innerText);
	});

const facCommTextArray = []; // 생화시설
	document.getElementsByName('facComm').forEach((type) => {
		facCommTextArray.push(type.nextElementSibling.innerText);
	});
	
const facSecTextArray = []; // 보안시설
	document.getElementsByName('facSec').forEach((type) => {
		facSecTextArray.push(type.nextElementSibling.innerText);
	});
	
const facOtherTextArray = []; // 기타시설
	document.getElementsByName('facOther').forEach((type) => {
		facOtherTextArray.push(type.nextElementSibling.innerText);
	});
	
// DB에서 인덱스를 가져온 뒤 해당 인덱스의 텍스트를 뿌립니다.	

function getRoomCharTextFromDB(indexesFromDB) { // 방특징 선택
	return indexesFromDB.map(index => roomCharTextArray[index]);
}

function getAirCndTextFromDB(indexesFromDB) { // 냉방시설
	return indexesFromDB.map(index => airCndTypeTextArray[index]);
}

function getFacCommTextFromDB(indexesFromDB) { // 생활시설
	return indexesFromDB.map(index => facCommTextArray[index]);
}
function getFacSecTextFromDB(indexesFromDB) { // 보안시설
	return indexesFromDB.map(index => facSecTextArray[index]);
}
function getFacOtherTextFromDB(indexesFromDB) { // 기타시설
	return indexesFromDB.map(index => facOtherTextArray[index]);
}

// 콘솔 확인 테스트를 위해 임의의 인덱스 배열을 사용.
console.log(getRoomCharTextFromDB([1,2]));
console.log(getAirCndTextFromDB([1, 2]));
console.log(getFacCommTextFromDB([18,19]));
console.log(getFacSecTextFromDB([6,7]));
console.log(getFacOtherTextFromDB([3,4]));

// ===================================================================================
// ====================================================================================
	/*
	// ajax
	let getData = {
		salesType: selectedBuildingType,
		unregisteredCheck,
		salesAddressMainRoad,
		salesAddressMainJibeon,
		salesAddressDong,
		salesAddressHo,
		
		salesAddress: getDataInputAddress.mainAddress,
		salesExclusiveP: getDataExclusivesSize.exclusiveP,
		salesExclusiveM: getDataExclusivesSize.exclusiveM,
		salesSupplyP: getDataSupplySize.supplyP,
		salesSupplyM: getDataSupplySize.supplyM,
		roomInfoCount,
		roomInfoLivingroom,
		roomInfoChar,
		buildingUse,
		buildingApproval,
		buildingApprovalDate,
		
		trnscType,
		depositPrice: getDataPriceInfo.depositPrice,
		monthlyPriceDeposit: getDataPriceInfo.monthlyPriceDeposit,
		monthlyPrice: getDataPriceInfo.monthlyPrice,
		publicManagement,
		managementFee: getDataManagementFee,
		possibleMoved,
		possibleMovedDate,
		possibleMovedCheck,
		
		totalFloors,
		numFloor,
		numBathrooms: getdataCgetDataCountBathroom,
		elevator,
		parkingAvailability,
		totalParking,
		
		facHeating,
		facAircnd: selectedAirCndType,
		facComm: selectedFacComm,
		facSecurity: selectedFacSec,
		facOther: selectedFacOtherType,
		
		photoGeneral,
		photoFilename,
		photoFilecode,
		
		descTitle: getDataDetailTitle,
		descDetail: getDataDetailContext,
		descCode,
		
		//parkingAvailability: getParkingAvailability(),
		
	}*/
	//console.log(getData);
	/*
	$.ajax({
		async: false,
		type: "post",
		url: "/api/v1/manage/content",
		contentType: "application/json",
		data: JSON.stringify(getData),
		dataType: "json",
		success: (response) => {
			console.log(response.data);
			alert("등록 완료");
		},
		error: (error) => {
			console.log(error);
		}
	})
	*/
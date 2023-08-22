let container = document.getElementById('map');

let options = {
	center: new kakao.maps.LatLng(35.157323, 129.059377),
	level: 3,
	maxLevel: 11,
	minLevel: 1
};

let map = new kakao.maps.Map(container, options);

// 지도 확대, 축소 컨트롤에서 확대 버튼을 누르면 호출되어 지도를 확대하는 함수입니다
function zoomIn() {
	map.setLevel(map.getLevel() - 1);
}

// 지도 확대, 축소 컨트롤에서 축소 버튼을 누르면 호출되어 지도를 확대하는 함수입니다
function zoomOut() {
	map.setLevel(map.getLevel() + 1);
}

let city1 = [];
let city2 = [];
let dong = [];

const citySet = new Set();

$.get("/static/js/dong-data.json", function (data) {
	for (const location in data) {
		const lat = data[location].lat;
		const lng = data[location].lng;
		const coords = new kakao.maps.LatLng(lat, lng);

		const dongName = location;

		let customOverlay = new kakao.maps.CustomOverlay({
			map: map,
			clickable: true,
			content: '<div class="marker-content"><h1>0</h1><p>' + dongName + '</p></div>',
			position: coords,
			xAnchor: 0.5,
			yAnchor: 1,
			zIndex: 10, // Set a higher z-index value to ensure it's displayed above cluster markers
		});


		dong.push(customOverlay);
		customOverlay.setMap(null);
	}
});

$.get("/static/js/city-data.json", function (data) {
	// Iterate through the data and create custom overlays for each location

	for (const location in data) {
		const cityName = location.split('/')[0];
		citySet.add(cityName);
		const lat = data[location].lat;
		const lng = data[location].long;
		const coords = new kakao.maps.LatLng(lat, lng);

		// Extract the text after the forward slash ("/")
		const gungu = location.split('/')[1];

		// Create a custom overlay for each address
		let customOverlay = new kakao.maps.CustomOverlay({
			map: map,
			clickable: true,
			content: '<div class="marker-content"><h1>0</h1><p>' + gungu + '</p></div>',
			position: coords,
			xAnchor: 0.5,
			yAnchor: 1,
			zIndex: 10, // Set a higher z-index value to ensure it's displayed above cluster markers
		});

		// Add the custom overlay to the map
		//customOverlay.setMap(map);

		city2.push(customOverlay);
		customOverlay.setMap(null);
	}
	const cities = Array.from(citySet);

	getAddress(cities);
	console.log(cities);
});


// Function to hide all custom overlays in the 'city' array
function hideCityOverlays1() {
	city1.forEach(function (overlay) {
		overlay.setMap(null);
	});
}

// Function to show all custom overlays in the 'city' array
function showCityOverlays1() {
	city1.forEach(function (overlay) {
		overlay.setMap(map);
	});
}

// Function to hide all custom overlays in the 'city' array
function hideCityOverlays2() {
	city2.forEach(function (overlay) {
		overlay.setMap(null);
	});
}

// Function to show all custom overlays in the 'city' array
function showCityOverlays2() {
	city2.forEach(function (overlay) {
		overlay.setMap(map);
	});
}

// Function to hide all custom overlays in the 'city' array
function hideDongOverlays() {
	dong.forEach(function (overlay) {
		overlay.setMap(null);
	});
}

// Function to show all custom overlays in the 'city' array
function showDongOverlays() {
	dong.forEach(function (overlay) {
		overlay.setMap(map);
	});
}

function toggleCityOverlays() {
	const currentLevel = getMapLevel();
	if (currentLevel >= 10) {
		showCityOverlays1();
	} else {
		hideCityOverlays1();
	}
	if (currentLevel <= 9 && currentLevel >= 8) {
		showCityOverlays2();
	} else {
		hideCityOverlays2();
	}
	if (currentLevel <= 7 && currentLevel >= 4) {
		showDongOverlays();
	} else {
		hideDongOverlays();
	}

}

// Add event listener for map zoom change
kakao.maps.event.addListener(map, 'zoom_changed', toggleCityOverlays);

// Call the function to show/hide city overlays initially
toggleCityOverlays();

let geocoder = new kakao.maps.services.Geocoder();

function load() {
	$.ajax({
		async: false,
		type: "get",
		url: "/api/v1/map/address",
		dataType: "json",
		success: (response) => {
			console.log(response.data);
		},
		error: (error) => {
			console.log(error);
		}
	});
}

function getAddress(data) {
	for (let i = 0; i < data.length; i++) {
		const address = data[i];
		// Use the geocoder to get coordinates for each address
		geocoder.addressSearch(address, function (result, status) {
			if (status === kakao.maps.services.Status.OK) {
				let coords = new kakao.maps.LatLng(result[0].y, result[0].x);

				// Create a custom overlay for each address
				let customOverlay = new kakao.maps.CustomOverlay({
					map: map,
					clickable: true,
					content: '<div class="marker-content"><h1>0</h1><p>' + address + '</p></div>',
					position: coords,
					xAnchor: 0.5,
					yAnchor: 1,
					zIndex: 10, // Set a higher z-index value to ensure it's displayed above cluster markers
				});

				city1.push(customOverlay);
			}
		});
	}
}

function searchEnter() {
	const searchBox = document.querySelector(".search-box");
	console.log("onkeyup True");
	if (window.event.keyCode == 13) { // 올바른 속성명은 keyCode입니다.
		let searchText = searchBox.value; // 올바른 속성명은 value입니다.
		console.log("enter key");
		if (searchText != null) {
			console.log("searchText True");
			roomSearch(searchText);
		}
	}
}

function roomSearch(data) {
	geocoder.addressSearch(data, function(result, status) {
		// 정상적으로 검색이 완료됐으면 
		if (status === kakao.maps.services.Status.OK) {
			let infowindow = null;
			let marker = null;
			let searchPs = new kakao.maps.LatLng(result[0].y, result[0].x);
			console.log(searchPs);
			map.setCenter(searchPs);

			// 결과값으로 받은 위치를 마커로 표시합니다
			marker = new kakao.maps.Marker({
				map: map,
				position: searchPs
			});

			// 인포윈도우로 장소에 대한 설명을 표시합니다
			infowindow = new kakao.maps.InfoWindow({
				content: `<div style="width:150px;text-align:center;padding:6px 0;">${data}</div>`
			});
			infowindow.open(map, marker);
		}
	});
	getRoomList(data);
}

function getRoomList(address) {
  console.log("test");
  $.ajax({
    async: false,
    type: "get",
    url: `/api/v1/map/room/${address}`,
    dataType: "json",
    success: (response) => {
      console.log(response.data);
      roomListData(response.data);
      console.log("successTest");
    },
    error: (error) => {
      console.log(error);
    }
  })
}

function roomListData(data) {
	const roomSearchList = document.querySelector(".room-search-list");
	roomSearchList.innerHTML = ""
	for (const list of data) {
		let freeFee = null;

		if (`${list.management_fee}` == 0) {
			freeFee = "관리비 없음";
		} else {
			freeFee = `관리비 ${list.management_fee}만`;
		}

		let title = null;
		if (`${list.desc_title}` == null || `${list.desc_title}` == "null") {
			title = "";
		} else {
			title = `${list.desc_title}`;
		}
		roomSearchList.innerHTML += `
        <div class="room-data">
            <div class="room-img">
                <img src="/static/images/none_img.jpg">
            </div>
            <div class="room-text-data">
                <h1>월세 ${list.monthly_price}/${list.monthly_price_deposit}</h1>    
                <p class="room-text">오피스텔 · 판도라</p>
                <p>${list.num_floor}층, ${list.size_exclusive_m}m², ${freeFee}</p>
                <p>${title}</p>
            </div>
        </div>
    `
	}

}

// function listclick(list) {
// }

function getMapLevel() {
	let level = map.getLevel();
	console.log(level);
	return level;
}

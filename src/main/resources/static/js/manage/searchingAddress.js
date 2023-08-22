let mapContainer = document.getElementById('map'), // 지도를 표시할 div
    mapOption = {
        center: new daum.maps.LatLng(37.537187, 127.005476), // 지도의 중심좌표
        level: 5 // 지도의 확대 레벨
    };

//지도를 미리 생성
let map = new daum.maps.Map(mapContainer, mapOption);
//주소-좌표 변환 객체를 생성
let geocoder = new daum.maps.services.Geocoder();
//마커를 미리 생성
let marker = new daum.maps.Marker({
    position: new daum.maps.LatLng(37.537187, 127.005476),
    map: map
});

//본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
function sample4_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function (data) {
            // Get the road address and jibun address
            let roadAddr = data.roadAddress;
            let jibunAddr = data.jibunAddress;

            // Display the addresses in the HTML elements
            const roadAddress = document.querySelector(".road-address");
            const jibunAddress = document.querySelector(".jibun-address");
            roadAddress.innerHTML = roadAddr;
            jibunAddress.innerHTML = jibunAddr;

            const addressNameBox = document.querySelector(".address-name-box");
            const addressDonghoArea = document.querySelector(".address-dongho-area");
            const addressMapBlock = document.querySelector(".address-map-block");
            const test = document.querySelectorAll(".remove-box");
            // Check if both road address and jibun address are not empty
            if (roadAddr !== "" && jibunAddr !== "") {
                test[0].remove();
                test[1].remove();
                addressNameBox.classList.remove("hidden");
                // Perform geocoding to get the coordinates from the road address
                geocoder.addressSearch(roadAddr, function (results, status) {
                    if (status === daum.maps.services.Status.OK) {
                        var result = results[0]; // Take the first result
                        var coords = new daum.maps.LatLng(result.y, result.x); // Extract coordinates

                        // Display the map and marker
                        mapContainer.style.display = "block";
                        map.relayout();
                        map.setCenter(coords); // Set the map center
                        marker.setPosition(coords); // Set the marker position
                        marker.setMap(map); // Add the marker to the map

                    }
                });
            } else {
                // If one of the addresses is empty, show an error message or take appropriate action
                alert("Please enter both road address and jibun address.");
            }
        }
    }).open();
}
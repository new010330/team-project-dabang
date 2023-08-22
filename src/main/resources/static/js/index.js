// let address = "전포동";
// getRoomList(address);

// function getRoomList(address) {
//     console.log("test");
//     $.ajax({
//       async: false,
//       type: "get",
//       url: `/api/v1/map/room/${address}`,
//       dataType: "json",
//       success: (response) => {
//         console.log(response.data);
//         roomListData(response.data);
//         console.log("successTest");
//       },
//       error: (error) => {
//         console.log(error);
//       }
//     })
//   }
  
//   function roomListData(data) {
//       const roomDataList = document.querySelector(".room-data-list");
//       roomDataList.innerHTML = ""
//       for (const list of data) {
//           let freeFee = null;
  
//           if (`${list.management_fee}` == 0) {
//               freeFee = "관리비 없음";
//           } else {
//               freeFee = `관리비 ${list.management_fee}만`;
//           }
  
//           let title = null;
//           if (`${list.desc_title}` == null || `${list.desc_title}` == "null") {
//               title = "";
//           } else {
//               title = `${list.desc_title}`;
//           }

            
//           roomDataList.innerHTML += `
//             <div class="room-data">
//                 <div class="room-img">
//                     <img src="/static/images/none_img.jpg">
//                 </div>
//                 <div class="recommend-room-box">
//                     <p class="room-text">${list.sales_type}</p>
//                     <h1>월세 ${list.monthly_price}/${list.monthly_price_deposit}</h1>    
//                     <p>${list.num_floor}층, ${list.size_exclusive_m}m², ${freeFee}</p>
//                     <p>${title}</p>
//                 </div>
//             </div>
//       `
//       }
  
//   }
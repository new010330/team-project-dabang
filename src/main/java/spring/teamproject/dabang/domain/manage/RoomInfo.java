package spring.teamproject.dabang.domain.manage;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.teamproject.dabang.web.dto.manage.CreateRoomInfoRespDto;
import spring.teamproject.dabang.web.dto.manage.ReadRoomInfoRespDto;
import spring.teamproject.dabang.web.dto.map.ReadSimpleDataRespDto;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomInfo {
		// 매물정보
		private int room_code;
		private String sales_type;
		private int unregistered_check;
		private String sales_address_main_road;
		private String sales_address_main_jibeon;
		private String sales_address_dong;
		private String sales_address_ho;
		private int size_exclusive_p;
		private double size_exclusive_m;
		private int size_supply_p;
		private double size_supply_m;
		private int room_info_count;
		private String room_info_livingroom;
		private String room_info_char;
		private String building_use;
		private String building_approval;
		private Date building_approval_date;
		
		// 거래정보
		private String trnsc_type;
		private int deposit_price;
		private int monthly_price_deposit;
		private int monthly_price;
		private String public_management;
		private int management_fee;
		private String possible_moved;
		private Date possible_moved_date;
		private int possible_moved_check;
		
		// 추가정보
		private String total_floors;
		private String num_floor;
		private int num_bathrooms;
		private String elevator;
		private String parking_availability;
		private int total_parking;
		
		// 시설정보
		private String fac_heating;
		private String fac_aircnd;
		private String fac_comm;
		private String fac_other;
		private String fac_security;
		
		// 사진등록
		private String photo_filename;
		private int photo_filecode;
		
		// 상세설명
		private String desc_title;
		private String desc_detail;
		private int desc_code;
		
		// 유저정보 및 업로드
		private int user_code;
		private LocalDateTime create_date;
		private LocalDateTime update_date;
		
		public ReadRoomInfoRespDto toReadRoomInfoDto() {
			return ReadRoomInfoRespDto.builder()
					.roomCode(room_code)
					.salesType(sales_type)
					.unregisteredCheck(unregistered_check)
					.salesAddressMainRoad(sales_address_main_road)
					.salesAddressMainJibeon(sales_address_main_jibeon)
					.salesAddressDong(sales_address_dong)
					.salesAddressHo(sales_address_ho)
					.sizeExclusiveP(size_exclusive_p)
					.sizeExclusiveM(size_exclusive_m)
					.sizeSupplyP(size_supply_p)
					.sizeSupplyM(size_supply_m)
					.roomInfoCount(room_info_count)
					.roomInfoLivingroom(room_info_livingroom)
					.roomInfoChar(room_info_char)
					.buildingUse(building_use)
					.buildingApproval(building_approval)
					.buildingApprovalDate(building_approval_date)
					
					.trnscType(trnsc_type)
					.depositPrice(deposit_price)
					.monthlyPriceDeposit(monthly_price_deposit)
					.monthlyPrice(monthly_price)
					.publicManagement(public_management)
					.managementFee(management_fee)
					.possibleMoved(possible_moved)
					.possibleMovedDate(building_approval_date)
					.possibleMovedCheck(possible_moved_check)
					
					.totalFloors(total_floors)
					.numFloor(num_floor)
					.numBathrooms(num_bathrooms)
					.elevator(elevator)
					.parkingAvailability(parking_availability)
					.totalParking(total_parking)
					
					.facHeating(fac_heating)
					.facAircnd(fac_aircnd)
					.facComm(fac_comm)
					.facOther(fac_other)
					.facSecurity(fac_security)
					
					.photoFilecode(photo_filecode)
					.photoFilename(photo_filename)
					
					.descCode(desc_code)
					.descDetail(desc_detail)
					.descTitle(desc_title)
					
					.userCode(user_code)
					.createDate(create_date)
					.updateDate(update_date)
					.build();
					
		}
		
		public ReadRoomInfoRespDto toGetRoomInfoDto() {
			return ReadRoomInfoRespDto.builder()
					.createDate(create_date)
					.roomCode(room_code)
					.trnscType(trnsc_type)
					.salesType(sales_type)
					.roomInfoCount(room_info_count)
					.salesAddressMainRoad(sales_address_main_road)
					.salesAddressDong(sales_address_dong)
					.salesAddressHo(sales_address_ho)
					.descTitle(desc_title)
					.build();
		}
		
		public CreateRoomInfoRespDto toCreateRoomInfoDto(Map<String, Boolean> respInsertStatus) {
			return CreateRoomInfoRespDto.builder()
					.roomCode(room_code)
					.salesType(sales_type)
					.unregisteredCheck(unregistered_check)
					.salesAddressMainRoad(sales_address_main_road)
					.salesAddressMainJibeon(sales_address_main_jibeon)
					.salesAddressDong(sales_address_dong)
					.salesAddressHo(sales_address_ho)
					.sizeExclusiveP(size_exclusive_p)
					.sizeExclusiveM(size_exclusive_m)
					.sizeSupplyP(size_supply_p)
					.sizeSupplyM(size_supply_m)
					.roomInfoCount(room_info_count)
					.roomInfoLivingroom(room_info_livingroom)
					.roomInfoChar(room_info_char)
					.buildingUse(building_use)
					.buildingApproval(building_approval)
					.buildingApprovalDate(building_approval_date)
					
					.trnscType(trnsc_type)
					.depositPrice(deposit_price)
					.monthlyPriceDeposit(monthly_price_deposit)
					.monthlyPrice(monthly_price)
					.publicManagement(public_management)
					.managementFee(management_fee)
					.possibleMoved(possible_moved)
					.possibleMovedDate(building_approval_date)
					.possibleMovedCheck(possible_moved_check)
					
					.totalFloors(total_floors)
					.numFloor(num_floor)
					.numBathrooms(num_bathrooms)
					.elevator(elevator)
					.parkingAvailability(parking_availability)
					.totalParking(total_parking)
					
					.facHeating(fac_heating)
					.facAircnd(fac_aircnd)
					.facComm(fac_comm)
					.facOther(fac_other)
					.facSecurity(fac_security)
	
					.photoFilecode(photo_filecode)
					.photoFilename(photo_filename)
					
					.descCode(desc_code)
					.descDetail(desc_detail)
					.descTitle(desc_title)
					
					.userCode(user_code)
					.createDate(create_date)
					.updateDate(update_date)
					.build();
		}
		
}
package spring.teamproject.dabang.domain.manage;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.teamproject.dabang.web.dto.map.ReadSimpleDataRespDto;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomData {
	// 매물정보
	private int room_code;
	private String sales_type;
	private String sales_address_main_road;
	private String sales_address_main_jibeon;
	private int size_exclusive_p;
	private double size_exclusive_m;
	private int size_supply_p;
	private double size_supply_m;
	private int room_count;
	private String room_info_char;
	private String building_use;
	private LocalDate building_approval_date;
	
	// 거래정보
	private String trnsc_type;
	private int deposit_price;
	private int monthly_price_deposit;
	private int monthly_price;
	private int management_fee;
	private String possible_moved;
	private LocalDate possible_move_date;
	
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
	private String photo_general;
	private String photo_filename;
	private String photo_filecode;
	
	// 상세설명
	private String desc_title;
	private String desc_detail;
	private String desc_code;
	
	// 유저정보 및 업로드
	private int user_code;
	private LocalDateTime create_date;
	private LocalDateTime update_date;
	
//	public ReadSimpleDataRespDto SimpleDataToDto() {
//		return ReadSimpleDataRespDto.builder()
//				.salesType(sales_type)
//				.addressRoad(sales_address_main_road)
//				.addressJibun(sales_address_main_jibeon)
//				.sizeExclusiveP(size_exclusive_p)
//				.sizeExclusiveM(size_exclusive_m)
//				.sizeSupplyP(size_supply_p)
//				.sizeSupplyM(size_supply_m)
//				.roomCount(room_count)
//				.roomInfoChar(room_info_char)
//				.buildingUse(building_use)
//				.BuildingApprovalDate(building_approval_date)
//				.trnseType(trnsc_type)
//				.depositPrice(deposit_price)
//				.monthlyPriceDeposit(monthly_price_deposit)
//				.monthlyPrice(monthly_price)
//				.managementFee(management_fee)
//				.possibleMoved(possible_moved)
//				.possibleMoveDate(possible_move_date)
//				.totalFloors(total_floors)
//				.numFloor(num_floor)
//				.numBathrooms(num_bathrooms)
//				.elevator(elevator)
//				.parkingAvailability(parking_availability)
//				.totalParking(total_parking)
//				.facHeating(fac_heating)
//				.facAircnd(fac_aircnd)
//				.facComm(fac_comm)
//				.facSecurity(fac_security)
//				.facOther(fac_other)
//				.descTitle(desc_title)
//				.descDetail(desc_detail)
//				.build();
//	}
}

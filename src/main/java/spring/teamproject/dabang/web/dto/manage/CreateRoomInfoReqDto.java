package spring.teamproject.dabang.web.dto.manage;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import spring.teamproject.dabang.domain.manage.RoomInfo;
import spring.teamproject.dabang.domain.manage.RoomInfoFile;

@Data
public class CreateRoomInfoReqDto {
		
		private List<MultipartFile> file;
	
		// 매물정보
		private int roomCode;
		private String salesType;
		private int unregisteredCheck;
		private String salesAddressMainRoad;
		private String salesAddressMainJibeon;
		private String salesAddressDong;
		private String salesAddressHo;		
		private int sizeExclusiveP;
		private double sizeExclusiveM;
		private int sizeSupplyP;
		private double sizeSupplyM;
		private int roomInfoCount;
		private String roomInfoLivingroom;
		private String roomInfoChar;
		private String buildingUse;
		private String buildingApproval;
		private Date buildingApprovalDate;
		
		// 거래정보
		private String trnscType;
		private int depositPrice;
		private int monthlyPriceDeposit;
		private int monthlyPrice;
		private String publicManagement;
		private int managementFee;
		private String possibleMoved;
		private Date possibleMovedDate;
		private int possibleMovedCheck;
		
		// 추가정보
		private String totalFloors;
		private String numFloor;
		private int numBathrooms;
		private String elevator;
		private String parkingAvailability;
		private int totalParking;
		
		// 시설정보
		private String facHeating;
		private String facAircnd;
		private List<Integer> facAircndList;
		private String facComm;
		private List<Integer> facCommList;
		private String facOther;
		private List<Integer> facOtherList;
		private String facSecurity;
		private List<Integer> facSecList;
		
		// 사진등록
		private String photoFilename;
		private int photoFilecode;
		
		// 상세설명
		private String descTitle;
		private String descDetail;
		private int descCode;
		
		
		// 유저정보 및 업로드
		private int userCode;
		private LocalDateTime createDate;
		private LocalDateTime updateDate;
	
	public RoomInfo toEntity() {
		return RoomInfo.builder()
				
				.room_code(roomCode)
				.sales_type(salesType)
				.unregistered_check(unregisteredCheck)
				.sales_address_main_road(salesAddressMainRoad)
				.sales_address_main_jibeon(salesAddressMainJibeon)
				.sales_address_dong(salesAddressDong)
				.sales_address_ho(salesAddressHo)
				.size_exclusive_p(sizeExclusiveP)
				.size_exclusive_m(sizeExclusiveM)
				.size_supply_p(sizeSupplyP)
				.size_supply_m(sizeSupplyM)
				.room_info_count(roomInfoCount)
				.room_info_livingroom(roomInfoLivingroom)
				.room_info_char(roomInfoChar)
				.building_use(buildingUse)
				.building_approval(buildingApproval)
				.building_approval_date(buildingApprovalDate)
				
				.trnsc_type(trnscType)
				.deposit_price(depositPrice)
				.monthly_price_deposit(monthlyPriceDeposit)
				.monthly_price(monthlyPrice)
				.public_management(publicManagement)
				.management_fee(managementFee)
				.possible_moved(possibleMoved)
				.possible_moved_date(buildingApprovalDate)
				.possible_moved_check(possibleMovedCheck)
				
				.total_floors(totalFloors)
				.num_floor(numFloor)
				.num_bathrooms(numBathrooms)
				.elevator(elevator)
				.parking_availability(parkingAvailability)
				.total_parking(totalParking)
				
				.fac_heating(facHeating)
				.fac_aircnd(String.join(",", facAircndList.stream().map(Object::toString).collect(Collectors.toList())))
				.fac_comm(String.join(",", facCommList.stream().map(Object::toString).collect(Collectors.toList())))
				.fac_security(String.join(",", facSecList.stream().map(Object::toString).collect(Collectors.toList())))
				.fac_other(String.join(",", facOtherList.stream().map(Object::toString).collect(Collectors.toList())))
				
				.desc_title(descTitle)
				.desc_detail(descDetail)
				.desc_code(descCode)
				
				.user_code(userCode)
				.create_date(createDate)
				.update_date(updateDate)
				.build();
	}	
	
}

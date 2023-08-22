package spring.teamproject.dabang.web.dto.manage;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReadRoomInfoRespDto {
	
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
		private String facComm;
		private String facOther;
		private String facSecurity;
		
		// 사진등록
		private String photoGeneral;
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

}

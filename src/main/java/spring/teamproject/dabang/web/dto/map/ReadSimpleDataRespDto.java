package spring.teamproject.dabang.web.dto.map;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReadSimpleDataRespDto {
	private int roomCode;
	private String salesType;				// 원룸, 오피스텔, 아파트
	private String addressRoad; 			// 도로명주소
	private String addressJibun;			// 지번주소
	private int sizeExclusiveP;				// 전용면적 평수
	private double sizeExclusiveM;			// 전용면적 제곱미터
	private int sizeSupplyP;				// 공급면적 평수
	private double sizeSupplyM;				// 공급면적 제곱미터
	private int roomCount;					// 방수
	private String roomInfoChar;			// 방특징
	private String buildingUse;				// 건축물 용도
	private LocalDate BuildingApprovalDate;	// 승인 날짜
	private String trnseType;				// 전월세 중
	private int depositPrice;				// 전세
	private int monthlyPriceDeposit;		// 보증금
	private int monthlyPrice;				// 월세
	private int managementFee;				// 관리비
	private String possibleMoved;			// 입주가능여부
	private LocalDate possibleMoveDate;		// 입주가능날짜
	private String totalFloors;				// 전체 층 수
	private String numFloor;				// 해당 층 수
	private int numBathrooms;				// 욕실 수
	private String elevator;				// 엘리베이터
	private String parkingAvailability;		// 주차가능여부
	private int totalParking;				// 주차가능 수
	private String facHeating;				// 난방시설
	private String facAircnd;				// 냉방시설
	private String facComm;					// 생활시설
	private String facSecurity;				// 보안시설
	private String facOther;				// 기타시설
	private String descTitle;				// 추가설명 제목
	private String descDetail;				// 추가설명 내용
}

package com.yedam.app.market.vo;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yedam.app.common.vo.PaymentDetailVO;

import lombok.Data;

@Data
public class CropsSaleVO {
	// crops_sale
	private String boardNo;
	private String memNo;
	private String title;
	private String crpCtg;
	private int price;
	private int qty;
	private String rep;
	private String detaDesct;
	@DateTimeFormat(pattern="yyyy/MM/dd")
	@JsonFormat(pattern="yyyy/MM/dd")
	private Date regDate;
	private String regStts;
	private double rateAvg; // 별점 평균(xml에서 alias한 이름)
	private int rateCnt; // 리뷰 갯수
	
	// review
	private int rate;
	
	// member
	private String nick;
	
	// member_detail
	private String memGrd;
	
	// paymentDetail (구매내역)
	private List<PaymentDetailVO> payList;
}

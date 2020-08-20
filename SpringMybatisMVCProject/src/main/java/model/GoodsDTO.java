package model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsDTO {
	String goodsNum;
	String userId;
	String goodsName;
	Long goodsPrice;
	String goodsContent;
	String goodsImage;
	Timestamp goodsRegister;
	String ipAddr;
	Long readCount;
	
	StartEndPageDTO startEndPageDTO;
	
}

package service.goods;

import java.io.File;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.AuthInfo;
import model.GoodsDTO;
import repository.GoodsRepository;

@Service
public class GoodsDeleteService {
	@Autowired
	GoodsRepository goodsRepository;
	public void goodsDelete(String goodsNum, HttpSession session) {
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo"); 
		GoodsDTO dto = new GoodsDTO();
		dto.setGoodsNum(goodsNum);
		dto.setUserId(authInfo.getUserId());
		dto = goodsRepository.goodsDetail(dto);
		Integer i = goodsRepository.goodsDelete(dto);
		if (i >= 1) {
			String path = "WEB-INF/view/goodsView/upload/";
			String filePath = session.getServletContext().getRealPath(path);
			String [] store = dto.getGoodsImage().split("`");
			for (String s : store) {
				File file = new File(filePath + s);
				if (file.exists()) {
					file.delete();
				}
			}
		}
		
	}
	
}

package service.goods;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import command.GoodsCommand;
import model.AuthInfo;
import model.GoodsDTO;
import repository.GoodsRepository;

@Service
public class GoodsWriteService {
	@Autowired
	GoodsRepository goodsRepository;
	
	public void goodsWrite(GoodsCommand goodsCommand, HttpServletRequest request) {
		HttpSession session = request.getSession();
		GoodsDTO dto = new GoodsDTO();
		dto.setGoodsContent(goodsCommand.getGoodsContent());
		dto.setGoodsName(goodsCommand.getGoodsName());
		dto.setGoodsNum(goodsCommand.getGoodsNum());
		dto.setGoodsPrice(goodsCommand.getGoodsPrice());
		dto.setIpAddr(request.getRemoteAddr());
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		dto.setUserId(authInfo.getUserId());
		
		String path = "WEB-INF/view/goodsView/upload/";
		String filePath = session.getServletContext().getRealPath(path);
		String goodsImage = "";
		for (MultipartFile mf : goodsCommand.getGoodsImage()) {
			String original = mf.getOriginalFilename();
			String originalFileExtension = original.substring(original.lastIndexOf("."));
			String store = UUID.randomUUID().toString().replace("-", "") + originalFileExtension;
			goodsImage += store + "`";
			File file = new File(filePath + store);
			try {
				mf.transferTo(file);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		dto.setGoodsImage(goodsImage);
		
		goodsRepository.goodsInsert(dto);
		
	}
	
}

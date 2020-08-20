package service.goods;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import model.AuthInfo;
import model.GoodsDTO;
import model.WishDTO;
import repository.GoodsRepository;

@Service
public class GoodsWishService {
	@Autowired
	GoodsRepository goodsRepository;
	public void goodsWishAdd(String goodsNum, HttpSession session, Model model) {
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		WishDTO dto = new WishDTO();
		dto.setGoodsNum(goodsNum);
		dto.setUserId(authInfo.getUserId());
		
		Integer i = goodsRepository.wishAdd(dto);
		model.addAttribute("num", i);
	}
	public void goodsWishList(Model model, HttpSession session) {
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		List<GoodsDTO> list = goodsRepository.goodsWishList(authInfo.getUserId());
		model.addAttribute("wishList", list);
		
	}
	
	
}

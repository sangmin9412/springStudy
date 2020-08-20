package service.goods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import model.AuthInfo;
import model.CartDTO;
import model.GoodsDTO;
import repository.GoodsRepository;

public class GoodsCartService {
	@Autowired
	GoodsRepository goodsRepository;
	
	
	public void goodsCartAdd(String goodsNum, Model model, HttpSession session) {
		// 사용자 정보
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		
		// 장바구니에 상품을 담기 위해서 먼저 상품정보를 가져와야 한다.
		GoodsDTO dto = new GoodsDTO();
		dto.setGoodsNum(goodsNum);
		dto = goodsRepository.goodsDetail(dto);
		CartDTO cart = new CartDTO();
		String [] imgs = dto.getGoodsImage().split("`");
		cart.setUserId(authInfo.getUserId());
		cart.setGoodsNum(dto.getGoodsNum());
		cart.setGoodsName(dto.getGoodsName());
		cart.setGoodsPrice(dto.getGoodsPrice());
		cart.setGoodsImage(imgs[0]);
		Integer i = goodsRepository.goodsCartAdd(cart);
		model.addAttribute("num", i);
	}


	public void goodsCartList(Model model, HttpSession session) {
		AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
		List<CartDTO> list = goodsRepository.getCartList(authInfo.getUserId());
		model.addAttribute("cartList", list);
	}


	public void goodsCartRemove(Long[] cartNums) {
		List<Long> cs = new ArrayList<Long>();
		for (Long cartNum : cartNums) {
			cs.add(cartNum);
		}
		
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("cartNums", cs);
		goodsRepository.goodsCartRemove(condition);
	}


	public void goodsCartQty(Long cartNum, String condition, Model model) {
		CartDTO dto = goodsRepository.goodsCartQty(cartNum, condition);
		model.addAttribute("cart", dto);
	}

}

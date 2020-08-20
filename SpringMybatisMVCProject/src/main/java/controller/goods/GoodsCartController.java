package controller.goods;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import service.goods.GoodsCartService;
import service.goods.GoodsDeleteService;
import service.goods.GoodsWishService;

@Controller
@RequestMapping("cart")
public class GoodsCartController {
	@Autowired
	GoodsCartService goodsCartService;
	@Autowired
	GoodsWishService goodsWishService;
	@Autowired
	GoodsDeleteService goodsDeleteService;
	@RequestMapping(value = "goodsCartAdd")
	public String goodsCartAdd(
				@RequestParam(value = "goodsNum") String goodsNum,
				Model model,
				HttpSession session
			) {
		goodsCartService.goodsCartAdd(goodsNum, model, session);
		return "ajax/returnNum";
	}
	@RequestMapping("goodsCartList")
	public String goodsCartList(
				Model model,
				HttpSession session
			) {
		goodsCartService.goodsCartList(model, session);
		return "goodsView/goodsCartList";
	}
	@RequestMapping("goodsCartRemove")
	public String goodsCartRemove(
				@RequestParam(value = "delete") Long [] cartNums
			) {
		goodsCartService.goodsCartRemove(cartNums);
		return "redirect:/cart/goodsCartList";
	}
	@RequestMapping("goodsCartQty")
	public String goodsCartQtyDown(
				@RequestParam(value = "cartNum") Long cartNum,
				@RequestParam(value = "condition") String condition,
				Model model
			) {
		goodsCartService.goodsCartQty(cartNum, condition, model);
		return "ajax/cartAjax";
	}
	@RequestMapping("goodsWishAdd")
	public String goodsWishAdd(
				@RequestParam(value = "goodsNum") String goodsNum,
				HttpSession session,
				Model model
			) {
		goodsWishService.goodsWishAdd(goodsNum, session, model);
		return "ajax/returnNum";
	}
	@RequestMapping("goodsWishList")
	public String goodsWishList(
				Model model,
				HttpSession session
			) {
		goodsWishService.goodsWishList(model, session);
		return "goodsView/goodsWishList";
	}
	@RequestMapping("goodsDelete")
	public String goodsDelete(
				@RequestParam(value = "goodsNum") String goodsNum,
				HttpSession session
			) {
		goodsDeleteService.goodsDelete(goodsNum, session);
		return "redirect:/gd/goodsList";
	}
}

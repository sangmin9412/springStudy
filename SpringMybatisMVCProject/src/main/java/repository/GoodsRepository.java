package repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.CartDTO;
import model.GoodsDTO;
import model.WishDTO;

@Repository
public class GoodsRepository {
	@Autowired
	private SqlSession sqlSession;
	private final String namespace = "goodsMapper";
	
	public void goodsInsert(GoodsDTO dto) {
		String statement = namespace + ".goodsInsert";
		sqlSession.insert(statement, dto);
	}

	public List<GoodsDTO> getGoodsList(GoodsDTO dto) {
		String statement = namespace + ".getGoodsList";
		return sqlSession.selectList(statement, dto);
	}

	public int getGoodsCount() {
		String statement = namespace + ".getGoodsCount";
		return sqlSession.selectOne(statement);
	}

	public GoodsDTO goodsDetail(GoodsDTO dto) {
		String statement = namespace + ".getGoodsList";
		return sqlSession.selectOne(statement, dto);
	}

	public Integer goodsCartAdd(CartDTO cart) {
		String statement = namespace + ".goodsCartAdd";
		return sqlSession.update(statement, cart);
	}

	public List<CartDTO> getCartList(String userId) {
		String statement = namespace + ".getCartList";
		return sqlSession.selectList(statement, userId);
	}

	public void goodsCartRemove(Map<String, Object> condition) {
		String statement = namespace + ".goodsCartRemove";
		sqlSession.delete(statement, condition);
	}

	public CartDTO goodsCartQty(Long cartNum, String condition) {
		String id = ".goodsCartQty" + condition;
		String statement = namespace + id;
		sqlSession.selectOne(statement, cartNum);
		statement = namespace + ".getCartOne";
		return sqlSession.selectOne(statement, cartNum);
	}

	public Integer wishAdd(WishDTO dto) {
		String statement = namespace + ".wishAdd";
		sqlSession.update(statement, dto);
		statement = namespace + ".wishCount";
		return sqlSession.selectOne(statement, dto);
	}

	public List<GoodsDTO> goodsWishList(String userId) {
		String statement = namespace + ".goodsWishList";
		return sqlSession.selectList(statement, userId);
	}

	public Integer goodsDelete(GoodsDTO dto) {
		String statement = namespace + ".goodsDelete";
		return sqlSession.delete(statement, dto);
	}	
	
}
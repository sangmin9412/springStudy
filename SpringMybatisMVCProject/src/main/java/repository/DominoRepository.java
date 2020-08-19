package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.ADTO;
import model.BDTO;
import model.CDTO;

@Repository
public class DominoRepository {
	@Autowired
	private SqlSession sqlSession;
	private final String namespace = "DominoMapper";
	
	public List<ADTO> selectA() {
		String statement = namespace + ".selectA";
		return sqlSession.selectList(statement);
	}

	public List<BDTO> selectB(Integer num) {
		String statement = namespace + ".selectB";
		return sqlSession.selectList(statement, num);
	}

	public List<CDTO> selectC(BDTO dto) {
		String statement = namespace + ".selectC";
		return sqlSession.selectList(statement, dto);
	}
	
	
}

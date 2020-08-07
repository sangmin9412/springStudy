package repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.MemberDTO;

@Repository
public class MemberRepository {
	@Autowired
	private SqlSession sqlSession;
	private final String namespace = "mappers.member.memberMapper";
	private String statement;
	
	public Integer joinOkUpdate(MemberDTO memberDTO) {
		statement = namespace + ".joinOkUpdate";
		return sqlSession.update(statement, memberDTO);
	}
	
	public Integer insertMember(MemberDTO memberDTO) {
		try {
			statement = namespace + ".insertMember";
			return sqlSession.insert(statement, memberDTO);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public MemberDTO selectByMember(MemberDTO memberDTO) {
		statement = namespace + ".selectMember";
		return sqlSession.selectOne(statement, memberDTO);
	}
	
	
}

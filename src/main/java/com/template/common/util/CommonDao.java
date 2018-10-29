package com.template.common.util;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommonDao {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    protected void printQueryId(String queryId) {
        if(logger.isDebugEnabled()){
            logger.debug("\t QueryId  \t:  " + queryId);
        }
    }

    public int insert(SqlSessionTemplate  sqlSession, String queryId, Object params) throws DataAccessException{
        printQueryId(queryId);
        return sqlSession.insert(queryId, params);
    }

    public int update(SqlSessionTemplate  sqlSession, String queryId, Object params) throws DataAccessException{
        printQueryId(queryId);
        return sqlSession.update(queryId, params);

    }

    public Object delete(SqlSessionTemplate  sqlSession, String queryId, Object params) throws DataAccessException{
        printQueryId(queryId);
        return sqlSession.delete(queryId, params);

    }

    public Object selectOne(SqlSessionTemplate  sqlSession, String queryId) throws DataAccessException{
        printQueryId(queryId);
        return sqlSession.selectOne(queryId);

    }

    public Object selectOne(SqlSessionTemplate  sqlSession, String queryId, Object params) throws DataAccessException{
        printQueryId(queryId);
        return sqlSession.selectOne(queryId, params);

    }

    @SuppressWarnings("rawtypes")
    public List selectList(SqlSessionTemplate  sqlSession, String queryId) throws DataAccessException{
        printQueryId(queryId);
        return sqlSession.selectList(queryId);

    }

    @SuppressWarnings("rawtypes")
    public List selectList(SqlSessionTemplate  sqlSession, String queryId, Object params) throws DataAccessException{
        printQueryId(queryId);
        return sqlSession.selectList(queryId,params);
    }
}

package com.template.sample.service;

import com.template.common.util.CommonDao;
import com.template.common.util.ResultData;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class SimpleServiceImpl implements SimpleService {

    @Autowired
    @Qualifier("db1SqlSessionTemplate")
    private SqlSessionTemplate db1;

    @Autowired
    @Qualifier("db2SqlSessionTemplate")
    private SqlSessionTemplate db2;

    @Autowired
    @Qualifier("db3SqlSessionTemplate")
    private SqlSessionTemplate db3;

    @Autowired
    private CommonDao commonDao;

    @Override
    public ResponseEntity getList() {
        ResultData rd = new ResultData();

        Map map = new HashMap<String,Object>();

        //map.put("db1",db1SqlTemplate.selectList("SampleMapper.getList"));
        //map.put("db2",db2SqlTemplate.selectList("SampleMapper.getList"));

        map.put("db1",commonDao.selectList(db1,"SampleMapper.getList"));
        map.put("db2",commonDao.selectList(db2,"SampleMapper.getList"));

        rd.setResult(map);

        return new ResponseEntity<>(rd, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getList2() {
        ResultData rd = new ResultData();

        //rs.setResult(db2SqlTemplate.selectList("SampleMapper.getList2"));

        return new ResponseEntity<>(rd, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity setData() {
        ResultData rd = new ResultData();

        db1.insert("SampleMapper.setData");
        db1.insert("SampleMapper.setData2");

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

package com.artwell.sampleordersheet.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.NoResultException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.artwell.sampleordersheet.entity.MtYarn;
import com.artwell.sampleordersheet.entity.SeWei;
import com.artwell.sampleordersheet.entity.Style;
import com.artwell.sampleordersheet.entity.YongLiang;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.global.AppBeans;

@Service(BomService.NAME)
public class BomServiceBean implements BomService {
	@Inject
	private Persistence persistence;
	@Override
	public List<SeWei> getSeWeiListById(Integer styleId) {
		List<SeWei> seWeiList=new ArrayList<SeWei>();
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("styleId", styleId);
		try (Transaction tx = persistence.createTransaction("ERPDB")) {
			SqlSession sqlSession = AppBeans.get("sqlSession_ERPDB");

			seWeiList = sqlSession.selectList("ErpMapper.getSeWei", parameter);
			tx.commit();
		} catch (NoResultException e) {
			return null;
		}
		for (SeWei seWei : seWeiList) {
			MtYarn yarn=new MtYarn();
			yarn.setId(1234);
			yarn.setXName("wwwwww");
			yarn.setXCode("l1233");
			seWei.setYarn(yarn);
		}
		return seWeiList;
	}
	@Override
	public List<YongLiang> getYongLiangListById(Integer styleId) {
		List<YongLiang> yongLiangiList=new ArrayList<YongLiang>();
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("styleId", styleId);
		/*try (Transaction tx = persistence.createTransaction("ERPDB")) {
			SqlSession sqlSession = AppBeans.get("sqlSession_ERPDB");

			yongLiangiList = sqlSession.selectList("ErpMapper.getYongLiang", parameter);
			tx.commit();
		} catch (NoResultException e) {
			return null;
		}*/
		
		return yongLiangiList;
	}

}
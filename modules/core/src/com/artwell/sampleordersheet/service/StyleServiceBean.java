package com.artwell.sampleordersheet.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.NoResultException;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.artwell.sampleordersheet.entity.EtStyleSize;
import com.artwell.sampleordersheet.entity.SeWei;
import com.artwell.sampleordersheet.entity.SeZu;
import com.artwell.sampleordersheet.entity.Style;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.global.AppBeans;

@Service(StyleService.NAME)
public class StyleServiceBean implements StyleService {
	@Inject
	private Persistence persistence;
	@Override
	public List<Style> getStyleList() {
		List<Style> styleList=new ArrayList<Style>();
		try (Transaction tx = persistence.createTransaction("ERPDB")) {
			SqlSession sqlSession = AppBeans.get("sqlSession_ERPDB");

			styleList = sqlSession.selectList("ErpMapper.getStyleById", null);
			tx.commit();
		} catch (NoResultException e) {

			return null;
		}
		return styleList;
	}
	@Override
	public Style getStyleById(Integer styleId) {
		Style style = new Style();
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("styleId", styleId);
		try (Transaction tx = persistence.createTransaction("ERPDB")) {
			SqlSession sqlSession = AppBeans.get("sqlSession_ERPDB");
			style = sqlSession.selectOne("ErpMapper.getStyleById", parameter);

			tx.commit();
			SimpleDateFormat format0 = new SimpleDateFormat("yyyy/MM/dd");
			style.setXDate1(format0.parse(format0.format(style.getXDate1())));
			style.setXDate2(format0.parse(format0.format(style.getXDate2())));
		} catch (NoResultException e) {

			return null;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return style;
	}

	@Override
	public List<SeZu> getSeZuListById(Integer styleId) {
		List<SeZu> seZuList = new ArrayList<>();
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("styleId", styleId);
		try (Transaction tx = persistence.createTransaction("ERPDB")) {
			SqlSession sqlSession = AppBeans.get("sqlSession_ERPDB");
			seZuList = sqlSession.selectList("ErpMapper.getSeZuListById", parameter);
			tx.commit();
			for (SeZu seZu : seZuList) {
				String seZuName="";
				if(null!=seZu.getXEColor()&&!"".equals(seZu.getXEColor())) {
					seZuName=seZuName+seZu.getXEColor();
				}
				if(null!=seZu.getXMColor()&&!"".equals(seZu.getXMColor())) {
					seZuName=seZuName+seZu.getXMColor();
				}
				if(null!=seZu.getXMColorNo()&&!"".equals(seZu.getXMColorNo())) {
					seZuName=seZuName+"/"+seZu.getXMColorNo();
				}
				seZu.setSeZuName(seZuName);
			}
		} catch (NoResultException e) {

			return null;
		}
		return seZuList;
	}

	

	@Override
	public List<Map<String, Object>> getPaiSeYongLiaoById(Integer styleId) {
		List<Map<String, Object>> paiSeList = new ArrayList<>();
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("styleId", styleId);
		try (Transaction tx = persistence.createTransaction("ERPDB")) {
			SqlSession sqlSession = AppBeans.get("sqlSession_ERPDB");
			paiSeList = sqlSession.selectList("ErpMapper.getPaiSeYongLiaoById", parameter);

			tx.commit();
		} catch (NoResultException e) {

			return null;
		}
		for (Map<String, Object> map : paiSeList) {
			String seWei="";
			if(null!=map.get("xCBit")&&!"".equals(map.get("xCBit").toString())) {
				seWei=seWei+map.get("xCBit").toString();
			}
			if(null!=map.get("xPart")&&!"".equals(map.get("xPart").toString())) {
				seWei=seWei+map.get("xPart").toString();
			}
			map.put("seWei", seWei);
			parameter.put("xCBit", map.get("xCBit"));
			map.remove("xCBit");
			
			List<Map<String, Object>> sezuList = new ArrayList<>();
			SqlSession sqlSession = AppBeans.get("sqlSession_ERPDB");
			sezuList = sqlSession.selectList("ErpMapper.getPaiSeYongLiaoById2", parameter);

			for (int i = 0; i < sezuList.size(); i++) {
				String yongLiao=sezuList.get(i).get("xColorNo").toString()+" "+ sezuList.get(i).get("xColor").toString();
				if(null!=sezuList.get(i).get("xVatNo")&&!"".equals(sezuList.get(i).get("xVatNo").toString())) {
					yongLiao=yongLiao+" 缸号"+sezuList.get(i).get("xVatNo").toString();
				}
				
				map.put("sezu"+i, yongLiao);

			}
		}
		return paiSeList;
	}

	@Override
	public List<EtStyleSize> getEtStyleSizeById(Integer styleId) {
		List<EtStyleSize> etStyleSizeList = new ArrayList<>();
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("styleId", styleId);
		try (Transaction tx = persistence.createTransaction("ERPDB")) {
			SqlSession sqlSession = AppBeans.get("sqlSession_ERPDB");
			etStyleSizeList = sqlSession.selectList("ErpMapper.getEtStyleSizeById", parameter);
			tx.commit();
		} catch (NoResultException e) {
			return null;
		}
		return etStyleSizeList;
	}
	@Override
	public void upStyle(Style style) {
		try (Transaction tx = persistence.createTransaction("ERPDB")) {
			SqlSession sqlSession = AppBeans.get("sqlSession_ERPDB");
			int success=sqlSession.update("ErpMapper.updateStyle", style);
			int success2=sqlSession.update("ErpMapper.updateStyleSize", style);
			tx.commit();
		} catch (NoResultException e) {
			 
		}
		
	}

}
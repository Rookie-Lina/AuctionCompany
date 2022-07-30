package com.sg.service.impl;

import com.sg.dao.GoodsTypeDao;
import com.sg.entity.GoodsType;
import com.sg.service.GoodsTypeService;
import com.sg.vo.GoodsTypeVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {

    @Resource
    private GoodsTypeDao goodsTypeDao;

    @Override
    public List<GoodsTypeVo> selectAll() {
        List<GoodsType> goodsTypes = goodsTypeDao.selectList(null);
        List<GoodsTypeVo> list = new ArrayList<>();
        GoodsTypeVo goodsTypeVo1 = new GoodsTypeVo();
        for (GoodsType g1 : goodsTypes) {
            if (g1.getGrade() == 1) {
                goodsTypeVo1.setLabel(g1.getTypeName());
                goodsTypeVo1.setValue(g1.getGrade() + "-" + g1.getId());
                List<GoodsTypeVo> list2 = new ArrayList<>();
                for (GoodsType g2 : goodsTypes) {
                    if (g2.getFirstId() == g1.getId() && g2.getGrade() == 2) {
                        GoodsTypeVo goodsTypeVo2 = new GoodsTypeVo();
                        goodsTypeVo2.setValue(g2.getGrade() + "-" + g2.getId());
                        goodsTypeVo2.setLabel(g2.getTypeName());
                        List<GoodsTypeVo> list3 = new ArrayList<>();
                        for (GoodsType g3 : goodsTypes) {
                            if (g3.getSecondId() == g2.getId() && g3.getGrade() == 3) {
                                GoodsTypeVo goodsTypeVo3 = new GoodsTypeVo();
                                goodsTypeVo3.setValue(g3.getGrade() + "-" + g3.getId());
                                goodsTypeVo3.setLabel(g3.getTypeName());
                                list3.add(goodsTypeVo3);
                            }
                        }
                        goodsTypeVo2.setChildren(list3);
                        list2.add(goodsTypeVo2);
                    }
                }
                goodsTypeVo1.setChildren(list2);
                list.add(goodsTypeVo1);
            }
        }
        return list;
    }
}

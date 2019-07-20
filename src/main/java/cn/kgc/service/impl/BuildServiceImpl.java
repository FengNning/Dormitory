package cn.kgc.service.impl;

import cn.kgc.dao.BuildDao;
import cn.kgc.dao.impl.BuildDaoImpl;
import cn.kgc.entity.Dormbuild;
import cn.kgc.service.BuildService;
import cn.kgc.util.PageBean;
import java.util.List;

public class BuildServiceImpl implements BuildService {
    BuildDao buildDao=new BuildDaoImpl();
    @Override
    public List<Dormbuild> listDormbuild(PageBean pageBean, Dormbuild dormbuild) {
        return buildDao.listDormbuild(pageBean,dormbuild);
    }

    @Override
    public int countbuild(Dormbuild dormbuild) {
        return buildDao.countbuild(dormbuild);
    }

    @Override
    public int savebuild(Dormbuild dormbuild) {
        return buildDao.savebuild(dormbuild);
    }

    @Override
    public int updatebuild(Dormbuild dormbuild) {
        return buildDao.updatebuild(dormbuild);
    }

    @Override
    public Dormbuild getbuildById(int id) {
        return buildDao.getbuildById(id);
    }

    @Override
    public boolean delebuild(int id) {
        return buildDao.delebuild(id);
    }
}

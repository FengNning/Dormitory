package cn.kgc.service.impl;

import cn.kgc.dao.DormManagerDao;
import cn.kgc.dao.impl.DormManagerDaoImpl;
import cn.kgc.entity.DormManager;
import cn.kgc.service.DormManagerService;
import cn.kgc.util.PageBean;

import java.sql.SQLException;
import java.util.List;

public class DormManagerServiceImpl implements DormManagerService {

    DormManagerDao dormManagerDao = new DormManagerDaoImpl();

    @Override
    public List<DormManager> listDormManager(PageBean pageBean, DormManager dormManager) throws SQLException {
        return dormManagerDao.listDormManager(pageBean,dormManager);
    }

    @Override
    public int countDormManager(DormManager dormManager) throws Exception {
        return dormManagerDao.countDormManager(dormManager);
    }

    @Override
    public int updateDormManager(DormManager dormManager) {
        return dormManagerDao.updateDormManager(dormManager);
    }

    @Override
    public int saveDormManager(DormManager dormManager) {
        return dormManagerDao.saveDormManager(dormManager);
    }

    @Override
    public DormManager getDormManagerById(Integer dormManId) {
        return dormManagerDao.getDormManagerById(dormManId);
    }

    @Override
    public int deleteManagerById(Integer dormManId) {
        return dormManagerDao.deleteManagerById(dormManId);
    }

    public static void main(String[] args) {
        DormManagerService  service = new DormManagerServiceImpl();
        DormManager dormManager = new DormManager("张三","123",1,"男","12345678900");
//        System.out.println(service.saveDormManager(dormManager));
        System.out.println(service.getDormManagerById(15).getManagerName());

    }
}

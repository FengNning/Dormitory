package cn.kgc.service;

import cn.kgc.entity.DormManager;
import cn.kgc.util.PageBean;

import java.sql.SQLException;
import java.util.List;

public interface DormManagerService {

    public List<DormManager> listDormManager(PageBean pageBean, DormManager dormManager) throws SQLException;
    public int countDormManager(DormManager dormManager) throws Exception;
    public int updateDormManager(DormManager dormManager);

    public int saveDormManager(DormManager dormManager);

    public DormManager getDormManagerById(Integer dormManId);
    public int deleteManagerById(Integer dormManId);
}

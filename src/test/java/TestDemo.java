import cn.kgc.entity.DormManager;
import cn.kgc.service.DormManagerService;
import cn.kgc.service.impl.DormManagerServiceImpl;
import cn.kgc.util.PageBean;
import java.util.List;

/**
 * date 2019-01-12
 * author admin
 * description XXXXXXX
 */
public class TestDemo {

    public static void main(String[] args) throws Exception {
        DormManagerService service = new DormManagerServiceImpl();
        PageBean pageBean = new PageBean();
        pageBean.setCurrentPageNo(1);
        pageBean.setPageSize(5);
        DormManager dormManager = new DormManager();
        dormManager.setManagerName("管");
//        dormManager.setDormBuildId(2);
//        List<DormManager> dormManagers = service.listDormManager(pageBean,dormManager);
//        for (DormManager manager : dormManagers) {
//            System.out.println(manager.getManagerName());
//            System.out.println(manager.getDormBuildName());
//        }

        System.out.println(service.countDormManager(dormManager));


    }
}

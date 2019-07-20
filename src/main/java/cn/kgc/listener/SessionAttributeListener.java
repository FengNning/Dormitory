package cn.kgc.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import org.apache.log4j.Logger;

public class SessionAttributeListener implements HttpSessionAttributeListener {
    private final Logger log = Logger
            .getLogger(SessionAttributeListener.class);

    @Override
    public void attributeAdded(HttpSessionBindingEvent arg0) {
        log.info("变量 " + arg0.getName() + " 被添加到session中，其类型为 "
                + arg0.getValue().getClass().getName()+",值为:"+arg0.getValue());
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent arg0) {
        log.info("变量 " + arg0.getName() + " 被从session中移除 ");
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent arg0) {
        log.info("session中的变量 " + arg0.getName() + " 被替换，其旧值为 "
                + arg0.getValue());
    }
}

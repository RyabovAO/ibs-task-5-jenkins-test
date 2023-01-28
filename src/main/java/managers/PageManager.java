package managers;

import pages.*;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class PageManager {

    private static PageManager pageManager = null;
    private Map<String, Object> mapPage = new HashMap<>();

    private PageManager() {
    }

    public static PageManager getInstance() {
        if (pageManager == null) {
            pageManager = new PageManager();
        }
        return pageManager;
    }

    public <T extends BasePage> T getPage(Class<T> page) {
       if(mapPage.isEmpty() || mapPage.get(page.getName()) == null) {

           try {
               mapPage.put(page.getName(), page.getConstructor().newInstance());
           } catch (InstantiationException e) {
               e.printStackTrace();
           } catch (IllegalAccessException e) {
               e.printStackTrace();
           } catch (InvocationTargetException e) {
               e.printStackTrace();
           } catch (NoSuchMethodException e) {
               e.printStackTrace();
           }
       }
       return (T)  mapPage.get(page.getName());
    }

    public void clearMap() {
        mapPage.clear();
    }

}

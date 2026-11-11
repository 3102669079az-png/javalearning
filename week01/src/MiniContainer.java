import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class MiniContainer {
    private final Map<Class<?>,Object> beans=new HashMap<>();

    public <T> T get(Class<T>clazz) throws Exception{
        Object bean=beans.get(clazz);
        if(bean==null){
            bean=clazz.getDeclaredConstructor().newInstance();
            inject(bean);
            beans.put(clazz,bean);
        }
        return clazz.cast(bean);
    }

    private void inject(Object bean) throws Exception{
        for(Field f:bean.getClass().getDeclaredFields()){
            if(f.isAnnotationPresent(AutoInject.class)){
                Object dep=get(f.getType());
                f.setAccessible(true);
                f.set(bean,dep);
                System.out.println("  注入: " + bean.getClass().getSimpleName()
                        + "." + f.getName() + " <- " + dep.getClass().getSimpleName());
            }
        }
    }
}

public class AnnoDemo {

    public static class DemoDao{
        public String findById(long id){
            return "围城(假装问数据库要的)";
        }
    }

    public static class DemoService{
        @AutoInject
        private  DemoDao demoDao;

        public void show(long id){
            System.out.println(" demoDao =" + (demoDao == null ? "mull(没注入)" : "已注入"));
            if (demoDao != null){
                System.out.println(" 调用结果 =" + demoDao.findById(id));
            }
        }
    }
    public static void main(String[] args) throws Exception{
        System.out.println("=== 1. 直接 new，没有容器 ===");
        new DemoService().show(5);

        System.out.println();
        System.out.println("=== 2. 交给 MiniContainer ===");
        MiniContainer c=new MiniContainer();
        DemoService svc=c.get(DemoService.class);
        svc.show(5);

        System.out.println();
        System.out.println("=== 3. 同一容器再要一次 ===");
        DemoService svc2=c.get(DemoService.class);
        System.out.println("  svc == svc2 ? " + (svc == svc2));
    }
}

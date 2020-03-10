package gc;

public class Allocation {
    private static final int _1MB = 1024 * 1024;

    /**对象优先在Eden分配
     * VM参数：-verbose:gc-Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     */
    public static void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;

        allocation1 = new byte[2 * _1MB];
        System.out.println("allocation1");

        allocation2 = new byte[2 * _1MB];
        System.out.println("allocation2");

        allocation3 = new byte[2 * _1MB];
        System.out.println("allocation3");

        allocation4 = new byte[4 * _1MB];//出现一次Minor GC
        System.out.println("allocation4");

    }

    /**大对象直接进入老年代
     * VM参数：
     * -verbose:gc
     * -XX:+UseSerialGC
     * -Xms20M
     * -Xmx20M
     * -Xmn10M
     * -XX:+PrintGCDetails
     * -XX:SurvivorRatio=8
     * -XX:PretenureSizeThreshold=3145728
     */

    public static void testPretenureSizeThreshold() {
        byte[] allocation;
        allocation = new byte[4 * _1MB];//直接分配在老年代中
    }

    /**长期存活的对象将进入老年代
     * VM参数：-verbose:gc -XX:+UseSerialGC -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution
     */
    public static void testTenuringThreshold() {

        byte[] allocation1, allocation2, allocation3;

        allocation1 = new byte[_1MB / 4];

        //什么时候进入老年代取决于XX:MaxTenuringThreshold设置

        allocation2 = new byte[4 * _1MB];

        allocation3 = new byte[4 * _1MB];

        allocation3 = null;

        allocation3 = new byte[4 * _1MB];

    }

    /**动态对象年龄判定
     *VM参数：-verbose:gc -XX:+UseSerialGC -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:+PrintTenuringDistribution
     */
    @SuppressWarnings("unused")
    public static void testTenuringThreshold2(){

        byte[]allocation1,allocation2,allocation3,allocation4;

        allocation1=new byte[_1MB/4];

        //allocation1+allocation2大于survivo空间一半

        allocation2=new byte[_1MB/4];

        allocation3=new byte[4*_1MB];

        allocation4=new byte[4*_1MB];

        allocation4=null;

        allocation4=new byte[4*_1MB];

    }
    
   /**空间分配担保
    *VM参数：-Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:-HandlePromotionFailure
   */

    @SuppressWarnings("unused")

    public static void testHandlePromotion(){

        byte[]allocation1,allocation2,allocation3,allocation4,allocation5,allocation6,allocation7;

        allocation1=new byte[2*_1MB];

        allocation2=new byte[2*_1MB];

        allocation3=new byte[2*_1MB];

        allocation1=null;

        allocation4=new byte[2*_1MB];

        allocation5=new byte[2*_1MB];

        allocation6=new byte[2*_1MB];

        allocation4=null;

        allocation5=null;

        allocation6=null;

        allocation7=new byte[2*_1MB];

    }

    public static void main(String[] args) {
        //testAllocation();
        //testPretenureSizeThreshold();
        testTenuringThreshold2();

    }
}

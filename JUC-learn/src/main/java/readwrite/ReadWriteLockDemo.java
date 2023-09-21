package readwrite;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 资源类
 */
class MyCache {

    //创建map
    private volatile Map<String, Object> map = new HashMap<String, Object>();

    //创建读写锁
    private ReadWriteLock rwLock = new ReentrantReadWriteLock();

    //放数据
    public void put(String key, Object value) {
        //添加写锁
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " 正在写操作" + key);
            //暂停一会
            TimeUnit.MICROSECONDS.sleep(300);
            //放数据
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + " 写完了" + key);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            //释放写锁
            rwLock.writeLock().unlock();
        }

    }

    //取数据
    public Object get(String key) {
        //添加读锁
        rwLock.readLock().lock();
        Object result = null;
        try {
            //暂停一会
            System.out.println(Thread.currentThread().getName() + "  正在读取操作" + key);
            TimeUnit.MICROSECONDS.sleep(300);
            //放数据
            result = map.get(key);
            System.out.println(Thread.currentThread().getName() + " 已经取完了" + result);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            //释放读锁
            rwLock.readLock().unlock();
        }
        return result;
    }

}


/**
 * 读写锁测试
 * 一个资源可以被多个读线程或一个写线程访问，但是读写不能同时进行，读写互斥，读读是共享的
 */
public class ReadWriteLockDemo {

    public static void main(String[] args) throws InterruptedException {
        MyCache myCache = new MyCache();

        //一遍放
        for (int i = 1; i <= 5 ; i++) {
            final int num = i;
            new Thread(()->{
                myCache.put(num + "",num + "");
            },String.valueOf(i)).start();
        }
        TimeUnit.MICROSECONDS.sleep(300);
        //一遍取
        for (int i = 1; i <= 5 ; i++) {
            final int num = i;
            new Thread(()->{
                myCache.get(num + "");
            },String.valueOf(i)).start();
        }

    }
}

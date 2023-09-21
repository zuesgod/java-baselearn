package readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 演示读写锁降级
 */
public class Demo1 {

    public static void main(String[] args) {
        //可重入读写锁对象
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = rwLock.readLock();//读锁
        ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();//写锁


        //获取读锁
        readLock.lock();
        System.out.println("zues read");

        //锁降级
        //1. 获取写锁
        writeLock.lock();
        System.out.println("zues write");



        //释放读写锁
        writeLock.unlock();
        readLock.unlock();


    }
}

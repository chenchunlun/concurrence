package charpter01;

/**
 * 非线程安全的数值序列生成器
 */
public class UnsafeSequence {
    public static void test(){
        final UnsafeSequence unsafeSequence = new UnsafeSequence();
        new Thread(new Runnable() {
            @Override
            public void run() {
                unsafeSequence.doSomething(unsafeSequence);
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                unsafeSequence.doSomething(unsafeSequence);
            }
        }).start();
    }

    private int value ;

    private void doSomething(UnsafeSequence unsafeSequence){
        for(int i = 0;i<10;i++){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"---->"+unsafeSequence.getNext());
        }
    }

    public synchronized int getNext(){
        return value++;
    }
}

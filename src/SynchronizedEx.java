public class SynchronizedEx {
    public static void main(String[] args) {
        ShareObj shareObj = new ShareObj();
        AddThread thread = new AddThread("HongGilDong", shareObj);
        thread.start();

        AddThread thread2 = new AddThread("Dooli", shareObj);
        thread2.start();
    }
}

class ShareObj {
    int sum = 0;

    synchronized public void add() {
        int n = sum;
        n++;
        sum = n;
        System.out.println(Thread.currentThread().getName() + " - " + sum);
    }

    public int getSum() {
        return sum;
    }
}

class AddThread extends Thread {
    ShareObj shareObj;

    public AddThread(String name, ShareObj shareObj) {
        super(name);
        this.shareObj = shareObj;
    }

    public void run() {
        int i = 0;
        while (i < 10) {
            shareObj.add();
            i++;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

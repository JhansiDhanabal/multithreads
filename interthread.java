package multithreading;
public class interthread {
    public static void main(String[] args) {
        try
        {
            newthread obj=new newthread("one");
            Thread.sleep(1000);
            obj.my_suspend();
            System.out.println("one is suspended");
            Thread.sleep(1000);
            obj.myresume();
            System.out.println("one is resumed");
            Thread.sleep(1000);
            obj.my_suspend();
            System.out.println("one is suspended");
            Thread.sleep(1000);
            obj.myresume();
            System.out.println("one is resumed");
            Thread.sleep(1000);
            obj.my_stop();
            System.out.println("one is stoped");
        }
        catch(InterruptedException e)
        {
            System.out.println(e);
        }
    }
    
}
class newthread implements Runnable
{
    String name;
    Thread t;
    boolean susp=false,stop=false;
    newthread(String n)
    {
        name=n;
        new Thread(this,name).start();
    }
    @Override
    public void run()
    {
        try
        {
            int j=1;
            while(j++<10)
            {
                synchronized(this)
                {
                    while(susp)
                        wait();
                    if(stop)
                        break;
                }
            }
        }
        catch(InterruptedException e)
        {
            System.out.println(e);
        }
    }
    synchronized void my_suspend()
    {
                susp=true;    
    }
    synchronized void myresume()
    {
                susp=false;
                notify();
    }
    synchronized void my_stop()
    {
                susp=false;
                stop=true;
                notify();
    }
}

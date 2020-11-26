// her skal jeg lave en ny tråd som skal printe en anden besked og holde styr på hvor
// mange beskeder den har sendt. de 2 seperate tråde skal så sammenligned med hinanden
// og kun sende tråde hvis den anden tråd har samme count.

class MessageLoop2 implements Runnable{

    // Display a message, preceded thread
    static void threadMessage(String message){
        String threadName = Thread.currentThread().getName();

        // formatere out print så den printer currently active thread + string teksten ud
        System.out.format("%s: %s%n",
                threadName,
                message);
    }


    public int threadTwoMessageCount=0;

    protected int getThreadTwoCount() {
        return threadTwoMessageCount;
    }

    public void run(){
        String superPrint[]= {
                "Super",
                "Speed",
                "Thread",
                "Output",
        };






        // lav en forloop med SuperPrint length
        // som så printer ud hele tiden og tæller.
        // sammenligningen med den andn tråd skal ske efter noget tid.
        // og den tråd der så har printet mest skal interuptes.
        try{

            for(int j=0;j<= 10; j++){

                Thread.sleep((long)(Math.random()*2000)+1);

                if(j<4){
                    threadMessage(superPrint[j]);
                    threadTwoMessageCount++;
                    threadMessage("Thread two's message count is "+threadTwoMessageCount);
                    Thread.sleep(5000);
                }

                else if(j>= superPrint.length){
                    threadMessage(superPrint[0]+" "+superPrint[1]);
                    threadTwoMessageCount++;
                    threadMessage("Thread two's message count is "+threadTwoMessageCount);
                    Thread.sleep(5000);
                }
                else{
                    threadMessage("something went wrong in the else if statements in thread 2" +
                            "and it got into the else statement wich was not the plan");
                    Thread.sleep(5000);
                }
            }

        } catch (InterruptedException e) {
            threadMessage("Something Interrupted thread 2");
            e.printStackTrace();
        }

    }
}

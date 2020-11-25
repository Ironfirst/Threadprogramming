
public class SimpleThread
{

    // Display a message, preceded thread
    static void threadMessage(String message){
        String threadName = Thread.currentThread().getName();

        // formatere out print så den printer currently active thread + string teksten ud
        System.out.format("%s: %s%n",
                threadName,
                message);
    }

    // laver en classe der har et Array med 4 forskellige index 0 til 3
    // som indeholder en udprints tekst.

    //classen implementere runneable interface som siger at classen
    // skal indeholde en metode uden input parametre
    // denne run metode gør det muligt for threads at udføre handlinger
    // mens threaden er aktiv

    // Classen indeholder også en metode der hedder run
    // som iterere igennem Array length med en for loop.
    // den laver en thread.sleep på 4000 som er 4 sec.
    // dette gør at processen venter 4 sec med at køre mellem hver iteration.

    // efter der er gået 4 sec bruger den threadMessage metoden med
    // array udprint af iterator variable i.
    private static class MessageLoop implements Runnable {

       public int threadMessageCount=0;


        public void run() {
            String importantInfo[] = {
                    "Mares eat oats",
                    "Does eat oats",
                    "Little lambs eat ivy",
                    "A kid will eat ivy too"
            };



            //

            try {

                for ( int i = 0; i< importantInfo.length; i++){
                    //int randomInterval =
                    // pause for random number between 0 and 5000
                    // 0 is 0 sec and 5000 is 5 sec
                    long randomInterval = (long)(Math.random()*5000)+1;
                    Thread.sleep(randomInterval);
                    System.out.println("The wait interval was " + randomInterval+ " Miliseconds");
                    //threadMessage = static void threadMessage(String message)
                    // Print a message

                    threadMessage(importantInfo[i]);
                    threadMessageCount++;


                    System.out.println("Thread 1 message count is "+threadMessageCount);
                }
            }
            // hvis Thread bliver interrupted bliver denne String
            // skrevet alså "i wasn't done"
            catch(InterruptedException e) {
                threadMessage("I wasn't done");

            }
        }

        public int getThreadOneMessageCount(){
            return threadMessageCount;
        }
    }

    // her skal jeg lave en ny tråd som skal printe en anden besked og holde styr på hvor
    // mange beskeder den har sendt. de 2 seperate tråde skal så sammenligned med hinanden
    // og kun sende tråde hvis den anden tråd har samme count.

    private static class MessageLoop2 implements Runnable{

        public int threadTwoMessageCount=0;

        public int getThreadTwoCount() {
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
            System.out.println("Thread two's message count is "+threadTwoMessageCount);
        }

        else if(j>= superPrint.length){
            threadMessage(superPrint[0]+" "+superPrint[1]);
            threadTwoMessageCount++;
            System.out.println("Thread two's message count is "+threadTwoMessageCount);
        }
        else{
            System.out.println("something went wrong in the else if statements in thread 2" +
                    "and it got into the else statement wich was not the plan");
        }
    }

} catch (InterruptedException e) {
    System.out.println("Something Interrupted thread 2");
    e.printStackTrace();
}

        }
    }

    public static void main(String[] args)

            throws InterruptedException {
        //Delay, in miliseconds before
        // we interrupts MessageLoop
        // thread ( default one hour).
        long patience = 1000 * 60 * 60;

        // If command line argument
        // present, gives patience
        // in seconds.

        // args. length forstår jeg ikke rigtig.
        // den tekststreng man hæler ind ved starter af programmet.
        // hvis man gemmer en tekst  bliver det gemt i args.
       /* if (args.length > 0) {
            try {
                patience = Long.parseLong(args[0]) * 1000;
            } catch (NumberFormatException e) {
                System.err.println("Argument must be an integer.");
                System.exit(1);
            }
        }
        */



        threadMessage("Starting MessageLoop threads");
        long startTime = System.currentTimeMillis();
        Thread t = new Thread(new MessageLoop());
        t.start();



        // loop until MessageLoop thread exits
        threadMessage("Waiting for MessageLoop thread to finish");

        Thread u = new Thread(new MessageLoop2());
        u.start();
        while(u.isAlive()) {
            if(System.currentTimeMillis()<4000){
                u.getThreadOneMessageCount();
            }
        }

        // isAlive metoden checker om Thread T er aktiv
        // ( har været startet og ikke er afsluttet endnu)

        // den system out print så i konsollen
        // at den køre og venter på de 4 sec
        while (t.isAlive()) {
            threadMessage("Still waiting...");
            // Wait maximum of 1 second
            // for MessageLoop thread
            // to finish.

            t.join(1000);


            // if statement der printer en besked hvis
            // currentime - starttime er længere end 1 time.
            if (((System.currentTimeMillis() - startTime) > patience)
                    && t.isAlive()) {
                threadMessage("Tired of waiting!");
                t.interrupt();
                // Shouldn't be long now
                // -- wait indefinitely
                t.join();
            }
        }
        threadMessage("Finally!");
    }
}

public class SimpleThread
{

    static MessageLoop messageLoop = new MessageLoop();
    static MessageLoop2 messageLoop2 = new MessageLoop2();

    // Display a message, preceded thread
    static void threadMessage(String message){
        String threadName = Thread.currentThread().getName();

        // formatere out print så den printer currently active thread + string teksten ud
        System.out.format("%s: %s%n",
                threadName,
                message);
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
        if(messageLoop2.getThreadTwoCount()>5){



                if(messageLoop.getThreadOneCount() > messageLoop2.getThreadTwoCount()){
                    threadMessage("the first thread was too active and thread 2 made 1 stop");
                    t.interrupt();
                    //u.join(10000);
                }
                else if(messageLoop2.getThreadTwoCount() > messageLoop.getThreadOneCount()) {
                    threadMessage("the second thread was too active and thread 1 made 2 stop");
                    u.interrupt();
                    //t.join(10000);

                }
                else{System.out.println("System.currentime - start time reached but no if statement executed?!");}

//            else if(System.currentTimeMillis()>4000){
//
//                threadMessage("nothing to report");
//            }
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
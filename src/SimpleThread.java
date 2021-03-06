
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

        MessageLoop messageLoop = new MessageLoop();
        Thread t = new Thread(messageLoop);
        t.start();



        // loop until MessageLoop thread exits
        threadMessage("Waiting for MessageLoop thread to finish");

        MessageLoop2 messageLoop2 = new MessageLoop2();
        Thread u = new Thread(messageLoop2);
        u.start();



            while (u.isAlive()) {

                if (messageLoop.getThreadOneCount() > messageLoop2.getThreadTwoCount()) {
                    threadMessage("the first thread was too active and thread 2 made 1 stop");
//                    t.interrupt();
                    u.join(7000);
//
                } else if (messageLoop2.getThreadTwoCount() > messageLoop.getThreadOneCount()) {
                    threadMessage("the second thread was too active and thread 1 made 2 stop first loop");
//                    u.interrupt();
                    t.join(7000);

                }
            }

            while (t.isAlive()) {

                if (messageLoop.getThreadOneCount() > messageLoop2.getThreadTwoCount()) {
                    threadMessage("the first thread was too active and thread 2 made 1 stop");
//                    t.interrupt();
                    u.join(7000);
//
                } else if (messageLoop2.getThreadTwoCount() > messageLoop.getThreadOneCount()) {
                    threadMessage("the second thread was too active and thread 1 made 2 stop second loop");
//                    u.interrupt();
                    t.join(7000);

                }
            }
//            else if(System.currentTimeMillis()>4000){
//
//                threadMessage("nothing to report");
//            }


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
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
class MessageLoop implements Runnable {


    // Display a message, preceded thread
    static void threadMessage(String message){
        String threadName = Thread.currentThread().getName();



        // formatere out print så den printer currently active thread + string teksten ud
        System.out.format("%s: %s%n",
                threadName,
                message);
    }

    public int threadMessageCount=0;


    protected int getThreadOneCount() {
        return threadMessageCount;
    }

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
                threadMessage("The wait interval was " + randomInterval+ " Miliseconds");
                //threadMessage = static void threadMessage(String message)
                // Print a message

                threadMessage(importantInfo[i]);
                threadMessageCount++;


                threadMessage("Thread 1 message count is "+threadMessageCount);
            }
        }
        // hvis Thread bliver interrupted bliver denne String
        // skrevet alså "i wasn't done"
        catch(InterruptedException e) {
            threadMessage("I wasn't done");

        }
    }
 }

java -cp target/classes net.optionfactory.javaexamples.MainThreadSleeping
jps [-vVl]
top
 * H for thread mode
 * V for tree
 * f to enable PPID
 * o to filter by COMMAND=java
 * c for commandline

top -H -p PID
 * for sanity
 * CPU, memory, number of threads

jstack PID
 * threads, stacks, locks, sleeps

kill -QUIT PID



java -cp target/classes net.optionfactory.javaexamples.MixedThreadWorkload
java -cp target/classes net.optionfactory.javaexamples.MixedThreadWorkload > /dev/null
jps
top -H -p PID
 * get tid
bc
 * obase=16
 * uppercase
jstack PID
 * main thread waiting
 * Thread-0 Sleeping
 * Thread-1
   * initially in stdout
   * then in isprime



java -XX:+UseConcMarkSweepGC -Xms1g -Xmx1g -cp target/classes/ net.optionfactory.javaexamples.MemoryLeakInStatic
jps
jstat -gcutil PID 1000 10
jconsole
visualvm
 * allocazione temporanea
 * allocazione reclamabile
 * leak
 * workings of garbage collector



Ring Benchmark in Akka (Scala)
================================

This is another implementation in Akka of [Ring Benchmark](http://github.com/everpeace/ring-benchmark) which is originally written in Erlang.

The Problem is:
> Write a ring benchmark. Create N processes in a ring. Send a message round the ring M times so that a total of N * M messages get sent.
> Time how long this takes for different values of N and M.


How to run
------------
An example below is N=4, M=3

    $ sbt
    > run 4 3
    [info] Compiling 2 Scala sources to /Users/everpeace/Documents/github/ring-benchmark-in-akka/target/scala-2.9.2/classes...
    [info] Running org.everpeace.ringbench.RingBenchmarkInAkka 4 3
    [Actor[akka://RingBenchmarkInAkka/user/$a/0]] starting...
    [Actor[akka://RingBenchmarkInAkka/user/$a/1]] starting...
    [Actor[akka://RingBenchmarkInAkka/user/$a/2]] starting...
    [Actor[akka://RingBenchmarkInAkka/user/$a/3]] starting...
    [Actor[akka://RingBenchmarkInAkka/user/$a/0]] token "'token" is injected.
    [Actor[akka://RingBenchmarkInAkka/user/$a/0]] token "(0, 'token)" is forwarded to Actor[akka://RingBenchmarkInAkka/user/$a/1]
    [Actor[akka://RingBenchmarkInAkka/user/$a/1]] token "(0, 'token)" received,  forward to Actor[akka://RingBenchmarkInAkka/user/$a/2]
    [Actor[akka://RingBenchmarkInAkka/user/$a/2]] token "(0, 'token)" received,  forward to Actor[akka://RingBenchmarkInAkka/user/$a/3]
    [Actor[akka://RingBenchmarkInAkka/user/$a/3]] token "(0, 'token)" received,  forward to Actor[akka://RingBenchmarkInAkka/user/$a/0]
    [Actor[akka://RingBenchmarkInAkka/user/$a/0]] token "(0, 'token)" received,  the token reaches root 1 time
    [Actor[akka://RingBenchmarkInAkka/user/$a/0]] token "(1, 'token)" is forwarded to Actor[akka://RingBenchmarkInAkka/user/$a/1]
    [Actor[akka://RingBenchmarkInAkka/user/$a/1]] token "(1, 'token)" received,  forward to Actor[akka://RingBenchmarkInAkka/user/$a/2]
    [Actor[akka://RingBenchmarkInAkka/user/$a/2]] token "(1, 'token)" received,  forward to Actor[akka://RingBenchmarkInAkka/user/$a/3]
    [Actor[akka://RingBenchmarkInAkka/user/$a/3]] token "(1, 'token)" received,  forward to Actor[akka://RingBenchmarkInAkka/user/$a/0]
    [Actor[akka://RingBenchmarkInAkka/user/$a/0]] token "(1, 'token)" received,  the token reaches root 2 time
    [Actor[akka://RingBenchmarkInAkka/user/$a/0]] token "(2, 'token)" is forwarded to Actor[akka://RingBenchmarkInAkka/user/$a/1]
    [Actor[akka://RingBenchmarkInAkka/user/$a/1]] token "(2, 'token)" received,  forward to Actor[akka://RingBenchmarkInAkka/user/$a/2]
    [Actor[akka://RingBenchmarkInAkka/user/$a/2]] token "(2, 'token)" received,  forward to Actor[akka://RingBenchmarkInAkka/user/$a/3]
    [Actor[akka://RingBenchmarkInAkka/user/$a/3]] token "(2, 'token)" received,  forward to Actor[akka://RingBenchmarkInAkka/user/$a/0]
    [Actor[akka://RingBenchmarkInAkka/user/$a/0]] token "(2, 'token)" received,  the token reaches root 3 time
    [Actor[akka://RingBenchmarkInAkka/user/$a/0]] report the end of benchmark to main
    [main] received the end of benchmark. killing the ring...
    [Actor[akka://RingBenchmarkInAkka/user/$a/0]] exitting...
    [Actor[akka://RingBenchmarkInAkka/user/$a/1]] exitting...
    [Actor[akka://RingBenchmarkInAkka/user/$a/2]] exitting...
    [Actor[akka://RingBenchmarkInAkka/user/$a/3]] exitting...
    [main] ring benchmark for 4 processes and 3 rounds = 46 milliseconds
    [success] Total time: 0 s,  completed 2012/05/19 12:23:56

Licence
------------
under MIT License.  See also LICENSE.txt

Copyright
------------
copyright 2012- [everpeace](http://twitter.com/everpeace).


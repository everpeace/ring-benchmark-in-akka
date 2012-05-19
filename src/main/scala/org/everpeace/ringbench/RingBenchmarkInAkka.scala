package org.everpeace.ringbench

import akka.actor._
import akka.pattern.{ask, gracefulStop}
import akka.util.duration._
import akka.util.Timeout
import org.everpeace.ring.Ring
import akka.dispatch.{Await, Future}

object RingBenchmarkInAkka {
  def main(args: Array[String]) {
    val n = args(0).toInt
    val m = args(1).toInt

    val system = ActorSystem("RingBenchmarkInAkka")

    val start = System.currentTimeMillis()

    // generate ring.
    val ring = system.actorOf(Props(new Ring(n, m)))

    // set timeout to be 1000 seconds.
    implicit val timeout = Timeout(1000 seconds)

    // send message to ring and wait to finish.
    (ring ? 'start) onSuccess {
      case 'ended =>
        println("[main] received the end of benchmark. killing the ring...")

        // graceful stop:
        // send poison pill to ring and wait for termination of it
        // all process in the ring are also killed.
        val stopped: Future[Boolean] = gracefulStop(ring, timeout.duration)(system)
        Await.result(stopped, timeout.duration)

        // now system can be stopped safely.
        system.shutdown()
        val end = System.currentTimeMillis()
        println("[main] ring benchmark for %d processes and %d rounds = %d milliseconds" format(n, m, end - start))
    }
  }
}

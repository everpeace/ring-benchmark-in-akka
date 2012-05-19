package org.everpeace.ring

import akka.actor._

abstract class ProcessInRing extends Actor {

  import context._

  def receive = wait_for_link

  def wait_for_link: Receive = {
    case ('link, next: ActorRef) => become(listen_tokens(next))
  }

  def listen_tokens(next: ActorRef): Receive

  override def preStart() = println("[%s] starting..." format self)

  override def postStop() = println("[%s] exitting..." format self)
}


class Ordinal extends ProcessInRing {
  def listen_tokens(next: ActorRef): Receive = {
    case (i: Int, token) =>
      println("[%s] token \"%s\" received, forward to %s" format(self, (i, token), next))
      next forward(i, token)
  }
}

class Root(val M: Int) extends ProcessInRing {
  def listen_tokens(next: ActorRef): Receive = {
    case ('start, token) =>
      println("[%s] token \"%s\" is injected." format(self, token))
      println("[%s] token \"%s\" is forwarded to %s" format(self, (0, token),next))
      next forward(0, token)
    case (i: Int, token) if i == (M - 1) =>
      println("[%s] token \"%s\" received, the token reaches root %d time" format(self, (i, token), i + 1))
      println("[%s] report the end of benchmark to main" format (self))
      sender ! 'ended
    case (i: Int, token) =>
      println("[%s] token \"%s\" received, the token reaches root %d time" format(self, (i, token), i + 1))
      println("[%s] token \"%s\" is forwarded to %s" format(self, (i+1, token),next))
      next forward(i + 1, token)
  }
}

// Main --> Root --> Ordinal --> Ordinal --> ... --> Ordinal
//           +<----------------------------------------+
class Ring(val N: Int, val M: Int) extends Actor {
  require(N > 0 && M > 0)
  val root = init

  def receive = {
    case 'start => root forward ('start,'token)
  }

  private[this] def init: ActorRef = {
    val root = context.actorOf(Props(new Root(M)), name = "0")
    val first = create_ordinals(1, N - 1, root)
    root ! ('link, first)
    root
  }

  private[this] def create_ordinals(i: Int, num: Int, root: ActorRef): ActorRef = i match {
    case x if x == num =>
      val ordinal = context.actorOf(Props[Ordinal], name = i.toString)
      ordinal !('link, root)
      ordinal
    case _ =>
      val ordinal = context.actorOf(Props[Ordinal], name = i.toString)
      val next = create_ordinals(i + 1, num, root)
      ordinal !('link, next)
      ordinal
  }
}
package schedule

import akka.actor.ActorSystem

/**
  * Created by quybeans on 2/12/17.
  */
object Scheduler {
  val actorSystem =   ActorSystem()
  val scheduler = actorSystem.scheduler
  val task = new Runnable {
    override def run() = ???
  }

'
  scheduler.schedule(100,100,print(""))
}

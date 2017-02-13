package schedule

import akka.actor.ActorSystem
import scala.concurrent.duration._

/**
  * Created by quybeans on 2/12/17.
  */
object Scheduler {
  val actorSystem =   ActorSystem()
  val scheduler = actorSystem.scheduler

//  def doSchedule(doSomeThing: () => Unit) = {
//    scheduler.schedule(0 seconds, 5 minutes)(doSomeThing)
//  }
}

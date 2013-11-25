package net.zxwv.turtlespace.test.graphics

import scala.collection.mutable.ListBuffer

import net.zxwv.turtlespace.test.BaseSpec
import net.zxwv.turtlespace.world._
import net.zxwv.turtlespace.graphics._

class WorldRendererSpec extends BaseSpec {
  
  val renderedTurtles: ListBuffer[Turtle] = ListBuffer()
  
  class FakeRenderer(val world: World) extends WorldRenderer {
    
    override def renderTurtle(turtle: Turtle): Unit = { renderedTurtles += turtle }
    
  }
  
  val world = new World(10.0, 10.0)
  val turtles = for (i <- 1 to 100) yield new Turtle(world)
  val fakeRenderer = new FakeRenderer(world)
  
  behavior of "WorldRenderer"
  
  before {
    renderedTurtles.clear
    fakeRenderer.renderWorld
  }
  
  "A fake WorldRenderer" should "render all turtles, in ascending id order" in {
    renderedTurtles.size should equal (100)
    var lastSeenId = -1
    for (t <- renderedTurtles) {
      t.id should be > lastSeenId
      lastSeenId = t.id
    }
  }
  
}
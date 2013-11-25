package net.zxwv.turtlespace.graphics

import net.zxwv.turtlespace.world._

trait WorldRenderer {
  
  val world: World
  
  def renderStart: Unit = { }
  
  def renderTurtle(turtle: Turtle): Unit = { }
  
  def renderFinish: Unit = { }
  
  def renderWorld: Unit = {
    renderStart
    for (t <- world.entitiesOfType[Turtle].toList.sortBy(t => t.id)) { renderTurtle(t) }
    renderFinish
  }
  
}
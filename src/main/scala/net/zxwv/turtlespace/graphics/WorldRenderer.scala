package net.zxwv.turtlespace.graphics

import net.zxwv.turtlespace.world._

trait WorldRenderer {
  
  def renderStart: Unit = { }
  
  def renderTurtle(turtle: Turtle): Unit = { }
  
  def renderFinish: Unit = { }
  
  def renderWorld(world: World): Unit = {
    renderStart
    for (t <- world.entitiesOfType[Turtle].toList.sortBy(t => t.id)) { renderTurtle(t) }
    renderFinish
  }
  
}
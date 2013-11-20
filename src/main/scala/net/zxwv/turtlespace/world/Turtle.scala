package net.zxwv.turtlespace.world

class Turtle(world: World) extends Entity(world) {
  
  var x: Double = 0.0
  var y: Double = 0.0
  var angle: Double = 0.0
  
  def wrap: Unit = {
    while (x < 0.0) { x += world.width }
    while (x >= world.width) { x -= world.width }
    while (y < 0.0) { y += world.height }
    while (y >= world.height) { y -= world.height }
    while (angle < 0.0) { angle += 2 * Math.PI }
    while (angle > 2 * Math.PI) { angle -= 2 * Math.PI }
  }
  
  def forward(dist: Double): Unit = {
    x += dist * Math.cos(angle)
    y += dist * Math.sin(angle)
    wrap
  }
  
  def back(dist: Double): Unit = forward(-dist)
  
  def left(rads: Double): Unit = {
    angle += rads
    wrap
  }
  
  def right(rads: Double): Unit = left(-rads)
  
}
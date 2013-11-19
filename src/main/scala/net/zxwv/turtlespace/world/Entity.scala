package net.zxwv.turtlespace.world

class Entity(val world: World) {
  
  val id = Entity.generateId
  
  world.add(this)
  
}

object Entity {
  
  private var nextId: Long = -1L
  
  def generateId: Long = {
    nextId += 1
    nextId
  }
  
}
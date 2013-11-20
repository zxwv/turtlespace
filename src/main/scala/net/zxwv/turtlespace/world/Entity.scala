package net.zxwv.turtlespace.world

class Entity(val world: World) {
  
  val id: Int = world.nextEntityId
  
  world.add(this)
  
}
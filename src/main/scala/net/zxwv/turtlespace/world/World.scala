package net.zxwv.turtlespace.world

import scala.collection.mutable.Map

class World(val width: Double, val height: Double) {
  
  val entities: Map[Long, Entity] = Map()
  
  def add(ent: Entity): Unit = {
    entities.put(ent.id, ent)
  }
  
  def remove(entId: Long): Unit = {
    entities.remove(entId)
  }
  
  def remove(ent: Entity): Unit = {
    remove(ent.id)
  }
  
}
package net.zxwv.turtlespace.world

import scala.collection.mutable.Map
import scala.reflect.ClassTag

class World(val width: Double, val height: Double) {
  
  val entities: Map[Int, Entity] = Map()
  
  private[this] var entityIdCounter: Int = 0
  
  def nextEntityId: Int = {
    entityIdCounter += 1
    entityIdCounter
  }
  
  def add(ent: Entity): Unit = {
    entities.put(ent.id, ent)
  }
  
  def remove(entId: Int): Unit = {
    entities.remove(entId)
  }
  
  def remove(ent: Entity): Unit = {
    remove(ent.id)
  }
  
  def entitiesOfType[T <: Entity : ClassTag]: Traversable[T] = {
    /*
     * This uses an implicit type tag supplied by the compiler.
     * Adapted from this StackOverflow answer:
     * http://stackoverflow.com/a/16903706/3008170
     */
    val tag = implicitly[ClassTag[T]]
    entities.values.collect { case tag(t) => t }
  }
  
}
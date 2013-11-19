package net.zxwv.turtlespace.test.world

import net.zxwv.turtlespace.test.BaseSpec

import net.zxwv.turtlespace.world._

class WorldSpec extends BaseSpec {
  
  var world: World = null
  
  behavior of "The basic world modeling classes"
  
  before {
    world = new World(10.0, 10.0)
  }
  
  "A World" should "initially be empty" in {
    world.entities should have size 0
  }
  
  it should "add to itself Entities created in it, indexed by id" in {
    val entitiesCreated = for (i <- 1 to 10) yield { new Entity(world) }
    entitiesCreated should have size 10
    world.entities should have size 10
    
    for (ent <- entitiesCreated) {
      world.entities.get(ent.id).get should be theSameInstanceAs ent
    }
    
    for (ent <- world.entities.values) {
      entitiesCreated should contain (ent)
    }
  }
  
  "An Entity" should "reference the World it was created in" in {
    val entity = new Entity(world)
    entity.world should be theSameInstanceAs world
    world.entities.get(entity.id).get should be theSameInstanceAs entity
  }
  
}
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
      world.entity(ent.id).get should be theSameInstanceAs ent
    }
    
    for (ent <- world.entities) {
      entitiesCreated should contain (ent)
    }
  }
  
  it should "produce unique entity ids" in {
    val entitiesCreated = for (i <- 1 to 10) yield { new Entity(world) }
    val entityIds = for (e <- entitiesCreated) yield e.id
    entityIds.toSet should have size (10)
  }
  
  "An Entity" should "reference the World it was created in" in {
    val entity = new Entity(world)
    entity.world should be theSameInstanceAs world
    world.entity(entity.id).get should be theSameInstanceAs entity
  }
  
}
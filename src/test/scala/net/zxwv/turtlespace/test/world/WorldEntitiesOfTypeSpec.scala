package net.zxwv.turtlespace.test.world

import net.zxwv.turtlespace.test.BaseSpec
import net.zxwv.turtlespace.world._

class WorldEntitiesOfTypeSpec extends BaseSpec {
  
  class E1(world: World) extends Entity(world)
  class E2(world: World) extends Entity(world)
  
  behavior of "World#entitiesOfType"
  
  "entitiesOfType" should "correctly filter Entities by their type" in {
    val world = new World(10.0, 10.0)
    val entities = for (i <- 1 to 100) yield i match {
      case i if (i % 2 == 0) => new E1(world)
      case i if (i % 2 == 1) => new E2(world)
    }
    val entities1 = world.entitiesOfType[E1]
    val entities2 = world.entitiesOfType[E2]
    entities1.size should be (50)
    entities2.size should be (50)
    for (e <- entities1) {
      e.isInstanceOf[E1] should be (true)
      e.getClass should be (classOf[E1])
    }
    for (e <- entities2) {
      e.isInstanceOf[E2] should be (true)
      e.getClass should be (classOf[E2])
    }
  }
  
}
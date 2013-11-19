package net.zxwv.turtlespace.test.world

import scala.util.Random

import net.zxwv.turtlespace.test.BaseSpec
import net.zxwv.turtlespace.world._

class TurtleSpec extends BaseSpec {
  
  var world: World = null
  var turtle: Turtle = null
  
  behavior of "Turtles"
  
  before {
    world = new World(10.0, 10.0)
    turtle = new Turtle(world)
  }
  
  "A Turtle" should "correctly wrap distance and angular coordinates" in {
    turtle = new Turtle(world)
    
    turtle.x = -11.0
    turtle.y = -2.5
    turtle.wrap
    turtle.x should be (9.0)
    turtle.y should be (7.5)
    
    turtle.x = 11.0
    turtle.y = 125.0
    turtle.wrap
    turtle.x should be (1.0)
    turtle.y should be (5.0)
    
    turtle.angle = -1
    turtle.wrap
    turtle.angle should be (2.0 * Math.PI - 1.0)
    
    turtle.angle = 10
    turtle.wrap
    turtle.angle should be (10 - 2.0 * Math.PI)
  }
  
  it should "correctly move horizontally and vertically" in {
    val turtle1 = new Turtle(world)
    turtle1.angle = 0
    turtle1.forward(1.0)
    turtle1.x should be (1.0 +- 1.0e-5)
    turtle1.y should be (0.0 +- 1.0e-5)
    
    val turtle2 = new Turtle(world)
    turtle2.angle = Math.PI / 2.0
    turtle2.forward(1.0)
    turtle2.x should be (0.0 +- 1.0e-5)
    turtle2.y should be (1.0 +- 1.0e-5)
  }
  
  it should "correctly move diagonally" in {
    turtle.left(Math.PI / 4.0)
    turtle.forward(1.0)
    turtle.x should be (Math.cos(Math.PI / 4.0))
    turtle.y should be (Math.sin(Math.PI / 4.0))
  }
  
  it should "correctly turn" in {
    turtle.right(Math.PI / 2.0)
    turtle.angle should be (2.0 * Math.PI - Math.PI / 2.0)
  }
  
  it should "remain within world boundaries when moving" in {
    turtle = new Turtle(world)
    val rand = new Random
    for (i <- 1 to 100) {
      rand.nextInt(2) match {
        case 0 => {
          turtle.x = rand.nextDouble * 10.0
          turtle.y = rand.nextDouble * 10.0
          turtle.angle = rand.nextDouble * 2 * Math.PI
        }
        case 1 => {
          turtle.forward(rand.nextDouble * 100)
        }
        case 2 => {
          turtle.back(rand.nextDouble * 100)
        }
        case 3 => {
          turtle.left(rand.nextDouble * 25)
        }
        case 4 => {
          turtle.right(rand.nextDouble * 25)
        }
      }
      turtle.x should be >= 0.0
      turtle.x should be <= 10.0
      turtle.y should be >= 0.0
      turtle.y should be <= 10.0
      turtle.angle should be >= 0.0
      turtle.angle should be <= 2 * Math.PI
    }
  }
  
}